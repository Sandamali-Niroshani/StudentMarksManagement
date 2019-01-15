package com.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
	
	public static Connection creatConnection(){
		
		Connection con=null;
		String url="jdbc:mysql://localhost:3306/school";
		String username1="root";
		String password1="1234";
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url, username1,password1);
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		 catch(Exception e){
			 e.printStackTrace();
		 }
		
			
		return con;
			
		}

}
