package com.Princeton;
import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    void addUser(User user) throws SQLException;
    void updateUser(User user) throws SQLException;
    void deleteUser(int id) throws SQLException;
    List<User> getUsers()  throws SQLException;
    User userById(int id)  throws SQLException;
    public User LogIn(String username, String password) throws SQLException;
    public User ModifyBalance(User u, int modtype, double amount) throws SQLException;
    public User MakeTransfer(User u, String targetName, String targetEmail, double amount) throws SQLException;
}
