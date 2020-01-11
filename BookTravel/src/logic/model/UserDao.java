package logic.model;

public class UserDao {
	
	public static User findUserMock(String username, String password) {
		
		if(username.equals("username") && password.equals("password"))
			return new User();
		
		else return null;
		
	}

}
