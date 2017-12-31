package archive.wafa.demo.model;






import org.hibernate.validator.constraints.*;

import javax.persistence.*;


@Entity
@Table(name = "XX_DMS_ACCESS_T")
public class UserAuth  {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "access_generator")
	@SequenceGenerator(name="access_generator", sequenceName = "XX_DMS_ACCESS_SEQ", allocationSize=1)
    @Column(name="ACCESS_ID")
    private Long accessId;

	@Column(name = "ESKA_USER_NAME")
    private String eskaUserName;  
	
	
	@OneToOne(fetch = FetchType.EAGER ,cascade = {CascadeType.PERSIST })
    @JoinColumn(name = "USER_ID")
	private User user ;
 

	@Column(name = "ACCESS_PASSWORD")
    private String encreptedPassword;
	
	@Transient
	//@Length(min = 5, message = "*Your password must have at least 5 characters")
	//@NotEmpty(message = "*Please provide your password")
	private String password;

	
	
	public UserAuth() {

	}
	
	

	public UserAuth(  User user,  String password) {
		super();
		this.eskaUserName = user.getUsername();
		this.user = user;
		this.password = password;
		//user.setUserAuth( this);
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public Long getAccessId() {
		return accessId;
	}

	public void setAccessId(Long accessId) {
		this.accessId = accessId;
	}

	public String getEskaUserName() {
		return eskaUserName;
	}

	public void setEskaUserName(String eskaUserName) {
		this.eskaUserName = eskaUserName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {

		this.user = user;
		//user.setUserAuth(this);
	}

	public String getencreptedPassword() {
		return encreptedPassword;
	}

	public void setencreptedPassword(String encreptedPassword) {
		this.encreptedPassword = encreptedPassword;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UserAuth userAuth = (UserAuth) o;

		return accessId != null ? accessId.equals(userAuth.accessId) : userAuth.accessId == null;
	}

	@Override
	public int hashCode() {
		return accessId != null ? accessId.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "UserAuth{" +
				"accessId=" + accessId +
				", eskaUserName='" + eskaUserName + '\'' +
			//	", user=" + user +
				", encreptedPassword='" + encreptedPassword + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}