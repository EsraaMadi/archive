package archive.wafa.demo.model;

import org.hibernate.validator.constraints.*;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "XX_DMS_USER_T")
public class User  {


			

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "user_generator")
	@SequenceGenerator(name="user_generator", sequenceName = "XX_USER_SEQ", allocationSize=1)
	@Column(name="USER_ID")
    private Long userId;

	//@NotEmpty(message = "*Please provide your username")
	@Column(name = "USER_NAME")
    private String username;   


	@Column(name = "ACCESS_TYP")
    private int accessType;


	@Email(message = "*Please provide a valid Email")
	@NotEmpty(message = "*Please provide an email")
	@Column(name = "EMAIL")
    private String email;
	
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL,
			fetch = FetchType.EAGER, optional = false)
	private UserAuth userAuth;

	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "XX_DMS_USER_ACCESS",
        joinColumns = { @JoinColumn(name = "USER_ID") }, 
        inverseJoinColumns = { @JoinColumn(name = "ACCESS_GROUP_ID") }
    )
	private Set<UserRoles> userRoles = new HashSet<>();
	
	//private Boolean enabled = true;
	public User() {

	}
	public User( String userName, int accessType, String email) {
		super();
		this.username = userName;
		this.accessType = accessType;
		this.email = email;
	}

	public Set<UserRoles> getUserRoles() {
		return userRoles;
	}


	public void setUserRoles(Set<UserRoles> userRoles) {
		this.userRoles = userRoles;
	}




	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}




	public String getUsername() {
		return username;
	}




	public void setUsername(String username) {
		this.username = username;
	}




	public int getAccessType() {
		return accessType;
	}


	public void setAccessType(int accessType) {
		this.accessType = accessType;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public UserAuth getUserAuth() {
		return userAuth;
	}


	public void setUserAuth(UserAuth userAuth) {
		this.userAuth = userAuth;
		// userAuth.setUser(this);
	}

	public void addRole(UserRoles role){
        if(!this.userRoles.contains(role)){
            this.userRoles.add(role);
        }
 
        if(!role.getUsers().contains(this)){
            role.getUsers().add(this);

        }
    }
 
    public void removeRole(UserRoles role){
        this.userRoles.remove(role);
        role.getUsers().remove(this);
    }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;

		return userId != null ? userId.equals(user.userId) : user.userId == null;
	}

	@Override
	public int hashCode() {
		return userId != null ? userId.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "User{" +
				"userId=" + userId +
				", username='" + username + '\'' +
				", accessType=" + accessType +
				", email='" + email + '\'' +
				", userAuth=" + userAuth +
				", userRoles=" + userRoles +
				'}';
	}
}