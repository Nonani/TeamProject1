package teamassignment;

public class User {
	private String name;
	private String id;
	private String phone;
	private String password;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User(String id, String password, String name, String phone) {
		super();
		this.name = name;
		this.id = id;
		this.phone = phone;
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", id=" + id + ", phone=" + phone + ", password=" + password + "]";
	}
	
	
}
