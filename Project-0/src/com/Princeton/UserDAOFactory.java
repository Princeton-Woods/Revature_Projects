package com.Princeton;

import java.sql.*;
import java.util.ArrayList;

public class UserDAOFactory {
	private static UserDAO dao;
	//public static ArrayList<User> users = new ArrayList<User>();
    private UserDAOFactory() throws SQLException{
    	Connection conn = ConnectionFactory.getConnection();
    	String sql = "select * from users";
		PreparedStatement preparedStatement = conn.prepareStatement(sql);		
		ResultSet resset = preparedStatement.executeQuery(sql);
		while(resset.next()) { //convert to string for user
														//ID					Email				
			System.out.println("User ID: " + resset.getInt(1) +" User Email: " + resset.getString(2)+ " User Password: " 
													//Password							Balance							Name
											+ resset.getString(3) + " User Balance: " + resset.getDouble(4)+ " User Name: " + resset.getString(5));
			User tempUser = new User(resset.getInt(1),resset.getString(2), resset.getString(3),resset.getString(5), resset.getDouble(4));
			Main.users.add(resset.getInt(1),tempUser);
		}

    }

    public static UserDAO getUserDao(){
        if(dao==null)
            dao = new UserImpl();
        return dao;
    }

}
