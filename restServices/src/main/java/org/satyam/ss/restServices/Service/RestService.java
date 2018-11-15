package org.satyam.ss.restServices.Service;

import java.sql.Connection;
import java.sql.DriverManager;

public class RestService {
	private Connection connection;
	private String username;
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public RestService(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/student?autoReconnect=true&useSSL=false",username,password);
		}catch(Exception e) {
			System.out.println("Error occurred");
			System.out.println(e);
		}
	}
}
