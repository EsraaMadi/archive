package archive.wafa.demo.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name="XX_DMS_ACCESS_TYPE_T")
public class UserRoles {





	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "role_generator")
	@SequenceGenerator(name="role_generator", sequenceName = "XX_DMS_ACCESS_TYPE_SEQ", allocationSize=1)
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long roleId;
	
	@Column (name="ACCESS_DESC")
	private String accessDesc;
	

	 @ManyToMany(mappedBy = "userRoles" ,fetch = FetchType.EAGER)
	private Set<User> users = new HashSet<>();


	 public UserRoles() {
	 }
	 
	public UserRoles(String accessDesc) {
		super();
		this.accessDesc = accessDesc;

	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getAccessDesc() {
		return accessDesc;
	}

	public void setAccessDesc(String accessDesc) {
		this.accessDesc = accessDesc;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	 public void addUser(User user){
	        if(!this.users.contains(user)){
	            this.users.add(user);
	        }
	 
	        if(!user.getUserRoles().contains(this)){
	            user.getUserRoles().add(this);
	        }
	    }
	 
	    public void removeUser(User user){
	        this.users.remove(user);
	        user.getUserRoles().remove(this);
	    }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UserRoles userRoles = (UserRoles) o;

		return roleId != null ? roleId.equals(userRoles.roleId) : userRoles.roleId == null;
	}

	@Override
	public int hashCode() {
		return roleId != null ? roleId.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "UserRoles{" +
				"roleId=" + roleId +
				", accessDesc='" + accessDesc + '\'' +
				//", users=" + users +
				'}';
	}
}
