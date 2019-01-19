package com.mcent.app.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

	public Connection getConnection() throws SQLException{
		String url="jdbc:mysql://localhost:3306/mcent";
		String username="root";
		String password="root";
		Connection con = null;
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			if(con == null){
			   con= DriverManager.getConnection(url,username,password); 
			   if(con !=null){
				  System.out.println("Connected to DB"); 
			   }
			 }
			  
			}catch(Exception e){
				System.out.println(e);
			}
		return con;
		
	}
}
