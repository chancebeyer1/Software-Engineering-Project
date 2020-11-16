package Client;

public class User
{
	private String username;
	private String password;
	private String email;

	public User(String u, String p)
	{
		username = u;
		password = p;
	}
	
	public String getUsername()
	{
		return this.username;
	}
}
