package archive.wafa.demo.convertor;


import archive.wafa.demo.model.User;
import archive.wafa.demo.security.UserDetailsImpl;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class UserToUserDetails implements Converter<User, UserDetails> {
    @Override
    public UserDetails convert(User user) {
        UserDetailsImpl userDetails = new UserDetailsImpl();

        if (user != null) {
            userDetails.setUsername(user.getUsername());
            userDetails.setPassword(user.getUserAuth().getencreptedPassword());
            userDetails.setEnabled(true);
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.getUserRoles().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getAccessDesc()));
            });
            userDetails.setAuthorities(authorities);
        }

        return userDetails;
    }
}
