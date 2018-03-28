package archive.wafa.demo.configuration;

import org.jasypt.springsecurity3.authentication.encoding.PasswordEncoder;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


//Spring Security with the DAO authentication provider to secure our Spring Boot Web application
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationProvider authenticationProvider;


    
    @Autowired
    @Qualifier("daoAuthenticationProvider")
    public void setAuthenticationProvider(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(StrongPasswordEncryptor passwordEncryptor){
        PasswordEncoder passwordEncoder = new PasswordEncoder();
        passwordEncoder.setPasswordEncryptor(passwordEncryptor);
        return passwordEncoder;
    }

    @Bean // set up the DAO authentication provider,
    //passwordEncoder : use the Jasypt library for encoding the password and verifying that the passwords match
    //userDetailsService : will fetch the User object from the database and hand over to Spring Security as a UserDetails object
    public DaoAuthenticationProvider daoAuthenticationProvider(PasswordEncoder passwordEncoder,
                                                               UserDetailsService userDetailsService){

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }
// Authentication : step 1 (in memory -configureGlobal()) 
    //The name of the configureGlobal method is not important. However, it is important to only configure AuthenticationManagerBuilder in a class
    @Autowired
    public void configureAuthManager(AuthenticationManagerBuilder authenticationManagerBuilder){
        authenticationManagerBuilder.authenticationProvider(authenticationProvider);
        //in memory user : auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN")
        //jdbc :
        /*
		auth.
		jdbcAuthentication()
			.usersByUsernameQuery(usersQuery)
			.authoritiesByUsernameQuery(rolesQuery)
			.dataSource(dataSource)
			.passwordEncoder(bCryptPasswordEncoder);*/
    }
    
 //Authorization, 
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
    	 httpSecurity.
 		authorizeRequests()
 			.antMatchers("/").permitAll()
                 .antMatchers("/NewUser").permitAll()
                 .antMatchers("/login_page").permitAll()

                 .antMatchers("/index").hasAuthority("Archive_User")
                 .antMatchers("/archive").hasAuthority("Archive_User")
            .antMatchers("/scan/doc").permitAll()
 			.antMatchers("/registration").permitAll()
 			.antMatchers("/home").hasAuthority("Archive_User")
            .antMatchers("/NewUserW").permitAll()
            .antMatchers("/NewUser/Search/**").permitAll()
 			.antMatchers("/admin/**").hasAuthority("ADMIN")
 			.anyRequest().authenticated() // any request comming to app pages must be autorized
            .and().csrf().disable()
 			.formLogin() // customize form login page
 			.loginPage("/login_page") // url for login page // need contoller to display form
 			.failureUrl("/login_page?error=true")
 			.defaultSuccessUrl("/index") // or use .loginProcessingUrl() to check username and password
 			.usernameParameter("username") // spring security filter will read this field from login form directly
 			.passwordParameter("password")
 			.and().logout()
 			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
 			.logoutSuccessUrl("/login_page?logout") // it is optional to use it because spring security by defult send ?logout paramter
 			.and().exceptionHandling().accessDeniedPage("/access-denied");
    	 
    	/* httpSecurity
         .authorizeRequests().antMatchers("/").permitAll()
         .anyRequest().authenticated()
         .and()
         .formLogin().loginPage("/login_page").permitAll()
         .and()
         .logout().permitAll();

 httpSecurity.csrf().disable();
 httpSecurity.headers().frameOptions().disable();*/
    }



}