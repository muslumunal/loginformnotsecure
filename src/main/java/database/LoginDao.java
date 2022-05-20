package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.LoginBean;
import model.User;

public class LoginDao {
	public static User checkLogin(LoginBean loginbean) throws ClassNotFoundException {
			
			User user = null;
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			try {
				
				String username = loginbean.getUsername();
				String password = loginbean.getPassword();
				
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_example","root","");
				String query = "select * from users2 where username = '" + username +"' and password = '" + password+"'";
				System.out.println(query);
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(query);
				if(rs.next()) {
					user = new User();
					user.setId(Integer.parseInt(rs.getString("id")));
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
				}
				
			} catch (SQLException e) {
				e.getMessage();
			}
			return user;
			
		}
}
