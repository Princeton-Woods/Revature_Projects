package com.Princeton;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserImpl implements UserDAO{
	private static Statement statement = null;
    Connection connection = null;
	public static List<String> log = new ArrayList<String>();
    
    public UserImpl()  {
        try {
            this.connection = ConnectionFactory.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    //User params are ( email password balance, name) make sure to put a limit for balance and ensure the email contains an @ and a .com
    
	@Override
    public void addUser(User user) throws SQLException {
        String sql = "insert into users (userEmail, userPassword, userBalance, user_name) values (?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getEmail());
        preparedStatement.setString(2, user.getPword());
        preparedStatement.setDouble(3, user.getBalance());
        preparedStatement.setString(4, user.getName());
        int count = preparedStatement.executeUpdate();
        if(count > 0) {
            System.out.println("User saved");
            Main.users.add(user.getId(), user);
            log.add( "\u001B[32m" + "User added with parameters: " + user.getName() + " " +user.getEmail() + " " + user.getPword()+ " " + user.getBalance()+ " " +"\u001B[0m");
        }
        else {
            System.out.println("Error");
            log.add("\u001B[31m" +"Unsuccessful User Creation"+"\u001B[0m");
        }
    }

	@Override
    public void updateUser(User user) throws SQLException {
        String sql = "update users set user_name = ?, userEmail = ?, userPassword = ? , userBalance = ? where id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getEmail());
        preparedStatement.setString(3, user.getPword());
        preparedStatement.setDouble(4, user.getBalance());
        preparedStatement.setInt(5, user.getId());
        int count = preparedStatement.executeUpdate();
        if(count > 0) {
            System.out.println("User updated");
            log.add( "\u001B[32m" + "User updated with parameters: Users name- " + user.getName() + " Users email- " +user.getEmail() + " Users password- " + user.getPword()+ " User balance- " + user.getBalance()+ " User ID- " + user.getId()+"\u001B[0m");
        }
        else {
            System.out.println("Error ");
            log.add("\u001B[31m" + "Unsuccessful User Update");
        }
        
    }

	@Override
    public void deleteUser(int id) throws SQLException {
        String sql = "delete from users where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        int count = preparedStatement.executeUpdate();
        if(count > 0) {
            System.out.println("User deleted");
        	Main.users.remove(id);
        	log.add( "\u001B[32m" + "User with ID: " + id + " deleted" +"\u001B[0m");
		}
        else {
            System.out.println("Invalid id zero rows affected.");
            log.add("\u001B[31m" + "Unsuccessful User Delete");
        }
    }
	public List<User> getUsers()  throws SQLException{
		String sql = "select * from users";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);		

		ResultSet resset = preparedStatement.executeQuery();
		List<User> usersR = new ArrayList<User>();
		while(resset.next()) { //convert to string for user			
			usersR.add(new User(resset.getInt(1),resset.getString(2), resset.getString(3),resset.getString(4), resset.getDouble(5)));
		}		return usersR;
	}

	@Override
	public User userById(int id)  throws SQLException { //Convert to string for user
		// TODO Auto-generated method stub
		String sql = "select * from users where id = ? ";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);	
		preparedStatement.setInt(1, id);
		ResultSet resset = preparedStatement.executeQuery();
		User temp = null;
		while(resset.next()) {
			
			temp = new User(resset.getInt(1),resset.getString(2), resset.getString(3),resset.getString(4), resset.getDouble(5));
			break;
		}
		return temp;
	}
	
	public User LogIn(String username, String password) throws SQLException {
		String sql = "Select * from users where userPassword = ? and userEmail= ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);	
		preparedStatement.setString(1, password);
		preparedStatement.setString(2, username);
		
		ResultSet resset = preparedStatement.executeQuery();
		
		User temp = null;
		while(resset.next()) {
			temp = new User(resset.getInt(1),resset.getString(2), resset.getString(3),resset.getString(4), resset.getDouble(5));
			break;
		}
		return temp;
	}
	public User ModifyBalance(User u,int modtype, double amount) throws SQLException {
		if(modtype == 1) { //W
			if((u.getBalance() - amount) < 0) {
				log.add("\u001B[31m" + "Transaction Failed- Improper or insufficient balance. " + "\u001B[0m");
				return null;
			}
			String sql = "update users set userBalance = ? where user_name = ? and userEmail = ?";
			u.setBalance(u.getBalance() - amount);
			PreparedStatement preparedStatement = connection.prepareStatement(sql);	
			preparedStatement.setDouble(1, u.getBalance());
			preparedStatement.setString(2, u.getName());
			preparedStatement.setString(3, u.getEmail());
	        preparedStatement.executeUpdate();
	        log.add("\u001B[32m" + "Transaction Successful- Widthdrawl of: " + amount + " from the User " + u.getName() + " at " + u.getEmail() + "\u001B[0m");
	        return u;
		}
		if(modtype == 2) { //D
			
			String sql = "update users set userBalance = ? where user_name = ? and userEmail = ?";
			u.setBalance(u.getBalance() + amount);
			PreparedStatement preparedStatement = connection.prepareStatement(sql);	
			preparedStatement.setDouble(1, u.getBalance());
			preparedStatement.setString(2, u.getName());
			preparedStatement.setString(3, u.getEmail());
			preparedStatement.executeUpdate();
	        log.add("\u001B[32m" + "Transaction Successful- Deposit of: " + amount + " from the User " + u.getName() + " at " + u.getEmail() + "\u001B[0m");	        
	        return u;
		} //T
		return null;

	}
	public User MakeTransfer(User u, String targetName, String targetEmail, double amount) throws SQLException {
		if((u.getBalance() - amount) < 0 || targetName.isEmpty() || targetEmail.isEmpty() || targetName.isBlank() || targetEmail.isBlank()) {
			log.add("\u001B[31m" + "Transfer Failed- Transfer of: " + amount + " Sent to: " + targetName + " at " + targetEmail + " Failed " + "\u001B[0m");
			return null;
			
		}
		String sql = "update users set userBalance = ? where user_name = ? and userEmail = ?";
		u.setBalance(u.getBalance() - amount);
		PreparedStatement preparedStatement = connection.prepareStatement(sql);	
		preparedStatement.setDouble(1, u.getBalance());
		preparedStatement.setString(2, u.getName());
		preparedStatement.setString(3, u.getEmail());
        preparedStatement.executeUpdate();
        
        String sql2 = "UPDATE users SET userbalance = userbalance + ? WHERE user_name = ? and userEmail = ?";
		preparedStatement = connection.prepareStatement(sql2);	
		preparedStatement.setDouble(1,amount);
		preparedStatement.setString(2, targetName);
		preparedStatement.setString(3, targetEmail);
        int count = preparedStatement.executeUpdate();
        if(count > 0) {
            System.out.println("User balance updated");
            log.add("\u001B[32m" + "Transfer Successful- Transfer of: " + amount + " Sent to: " + targetName + " at " + targetEmail + "\u001B[0m");
        }
        else {
            System.out.println("Error ");
		}

		return u;
		
	}
	public void GetHistory() {
		if(log.isEmpty()) {
			System.out.println("No Transaction History for this session");
		} else	{
			for(String S: log) {
				System.out.println(S);
			}
		}
	}

}
