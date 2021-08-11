package com.Princeton;

public class User {
	private int id; //
    private String email; //
    private String pword; //
    private double balance; //
    private String name;//
    //( email password balance, name)
    
    public User(){

    }
    
    public User(String name, String email,String pword,double balance) {
        //this.id = id;
        this.name = name;
        this.email = email;
        this.pword = pword;
        this.balance = balance;
    }
    public User(int id, String name, String email,String pword,double balance) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.pword = pword;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPword(String pword) {
    	this.pword = pword;
    }
    public void setBalance(double balance) {
    	this.balance = balance;
    }
    public String getPword() {
    	return pword;
    }
    public double getBalance() {
    	return balance;
    }
}
