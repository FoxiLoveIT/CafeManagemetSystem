/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import javax.swing.JOptionPane;
import model.User;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class accessDao {
    
    public static void save(User user){
        String query = "insert into access(name,email,mobileNumber,password,status) values('" + user.getName()+"','"+ user.getEmail()+"','"+ user.getMobileNumber()+"','"+ user.getPassword()+"','false');";
        DbOperations.setDataOrDelete(query, "Registered Successfully! Wait for Admin Approval!");
    }
    public static User login(String email, String password){
        User user = null;
        try{
        ResultSet rs = DbOperations.getData("Select * from ACCESS  WHERE email ='"+email+"' and password ='"+password+"'");
        while(rs.next()){
        user = new User();
        user.setStatus(rs.getString("status"));
        }
        }
        catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
       
    }
        return user;
}
    
    public static void update(String email,String newPassword){
        String query = "update ACCESS set password = '" + newPassword + "' where email '" + email + "'";
        DbOperations.setDataOrDelete(query, "Password Changed Successfully");
    }
    
    public static ArrayList<User> getAllRecords(String email){
        ArrayList<User> arrayList = new ArrayList<>();
        try{
         ResultSet rs = DbOperations.getData("Select * from ACCESS  WHERE email like '%"+email+"%'");
         while(rs.next()){
             User user = new User();
             user.setId(rs.getInt("id"));
             user.setName(rs.getString("name"));
             user.setEmail(rs.getString("email"));
             user.setMobileNumber(rs.getString("mobileNumber"));
             user.setStatus(rs.getString("status"));
             arrayList.add(user);
             
         }
        }
        catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
        }return arrayList;
    }
    
    public static void changeStatus(String email, String status){
       String query = "update ACCESS set status = '"+status+"' where email = '"+email+"'";
        DbOperations.setDataOrDelete(query, "Status Changed Successfully");
    }
    
 }
