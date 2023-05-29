/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Yahya
 */
public class account {

    private int id;
    private int USER_ID;
    private int ACCOUNT;
    private String username;
    private String currency;
    private double BALANCE;
    private String creation_DATE;

    public account(int id, String username, String currency, double BALANCE, String creation_DATE) {
        this.id = id;
        this.USER_ID = USER_ID;
        this.ACCOUNT = ACCOUNT;
        this.username = username;
        this.currency = currency;
        this.BALANCE = BALANCE;
        this.creation_DATE = creation_DATE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(int USER_ID) {
        this.USER_ID = USER_ID;
    }

    public int getACCOUNT() {
        return ACCOUNT;
    }

    public void setACCOUNT(int ACCOUNT) {
        this.ACCOUNT = ACCOUNT;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getBALANCE() {
        return BALANCE;
    }

    public void setBALANCE(double BALANCE) {
        this.BALANCE = BALANCE;
    }

    public String getCreation_DATE() {
        return creation_DATE;
    }

    public void setCreation_DATE(String creation_DATE) {
        this.creation_DATE = creation_DATE;
    }
    
    

 
    //create a new user in users table
    public int save() throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter = 0;
        String sql = "INSERT INTO accounts (id ,USER_ID,ACCOUNT ,username,currency,BALANCE,creation_DATE) VALUES (?, ?, ?, ?,?,?,?)";
        ps = c.prepareStatement(sql);
        ps.setInt(1, this.getId());
        ps.setInt(2, this.getUSER_ID());
        ps.setInt(3, this.getACCOUNT());
        ps.setString(4,this.getUsername() );
        ps.setString(5, this.getCurrency());
        ps.setDouble(6, this.getBALANCE());
         ps.setString(7, this.getCreation_DATE());
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println(this.getUsername()
                    + " User was added successfully!");
        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return recordCounter;
    }

    //get all users from users table
    public static ArrayList<account> getAllaccount() throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM accounts ";
        ps = c.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            
            account accounta = new account(rs.getInt(3), rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getString(7));
            accounta.setId(rs.getInt(1));
            accounts.add(accounta);
           

        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return accounts;
    }

    //update an existing user in users table 
    public int update() throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter = 0;
        String sql = "UPDATE accounts SET ACCOUNT=?, username=? , currency=?,BALANCE=? ,creation_DATE=? WHERE id=?";
        ps = c.prepareStatement(sql);
        ps.setInt(1, this.getACCOUNT());
        ps.setString(2, this.getUsername());
        ps.setString(3, this.getCurrency());
        ps.setDouble(4, this.getBALANCE());
        ps.setString(5, this.getCreation_DATE());
        ps.setInt(6, this.getId());
       
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println("account with id : " + this.getId() + " was updated successfully!");
        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return recordCounter;
    }

    //delete an user from users table 
    public int delete() throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter = 0;
        String sql = "DELETE FROM accounts WHERE ID=? ";
        ps = c.prepareStatement(sql);
        ps.setInt(1, this.getId());
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println("The account with id : " + this.getId() + " was deleted successfully!");
        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return recordCounter;
    }

}
