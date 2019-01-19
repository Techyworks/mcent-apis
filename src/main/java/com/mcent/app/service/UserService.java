package com.mcent.app.service;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.mcent.app.Connection.ConnectionUtil;
import com.mcent.app.model.Response;
import com.mcent.app.model.UserModel.User;

public class UserService {

   public Connection recoverConnection(){
	   Connection conn = null;
	   ConnectionUtil con = null;
	   
	   if(conn == null){
		   try{
		   con = new ConnectionUtil();
		   conn = con.getConnection();
		   }catch(Exception e){
			   
		   }
	   }

	   return conn;
   }
     
   public List<User> getAllUsers(){
	   
	   Connection con = null;
	   Statement stmt = null;
	   List<User> usersList = null; 
	   User user = null;
	   ArrayList aList = null;
	   List<String> referredFriends = new ArrayList<String>();
	   List<String> onlinePurchases = new ArrayList<String>();
	   if(con == null){
		   con = recoverConnection();
	   }
	   try{
		   stmt = con.createStatement();
		   usersList = new ArrayList<User>();

		      String sql = "SELECT Name, LastName, FirstName, Email,"
		      		+ "Mobile, Password, ReferredFriends, OnlinePurchase, Points FROM mcent.User";
		      ResultSet rs = stmt.executeQuery(sql);
		      while(rs.next()){
		    	 String value = null;
		    	 user = new User();
		         user.setName(rs.getString("Name"));
		         user.setLastName(rs.getString("LastName"));
		         user.setFirstName(rs.getString("FirstName"));
		         user.setEmail(rs.getString("Email"));
		         user.setMobile(rs.getString("Mobile"));
		         user.setPoints(rs.getDouble("Points"));
		         user.setPassword(rs.getString("Password"));
		         if(referredFriends.isEmpty()){		
		        	 if(rs.getString("ReferredFriends").contains("[")){
		        		 value = rs.getString("ReferredFriends").substring(1, rs.getString("ReferredFriends").length()-1);	
			        	 aList= new ArrayList(Arrays.asList(value.split(",")));
			        	 for(int i=0;i<aList.size();i++)
			        	 {
			        		 referredFriends.add(aList.get(i).toString().trim());
			        	 }
		        	 }else{
		        		 value = rs.getString("ReferredFriends");
		        	 aList= new ArrayList(Arrays.asList(value.split(",")));
		        	 for(int i=0;i<aList.size();i++)
		        	 {
		        		 referredFriends.add(aList.get(i).toString().trim());
		        	 }
		        	 }
		         }else{
		        	 
		        	 referredFriends = new ArrayList<String>();
		        	 if(rs.getString("ReferredFriends").contains("[")){
		        		 value = rs.getString("ReferredFriends").substring(1, rs.getString("ReferredFriends").length()-1);
		        		 aList= new ArrayList(Arrays.asList(value.split(",")));
			        	 for(int i=0;i<aList.size();i++)
			        	 {
			        		 referredFriends.add(aList.get(i).toString().trim());
			        	 }
			         }else{
			        	 value = rs.getString("ReferredFriends");
		        	 aList= new ArrayList(Arrays.asList(value.split(",")));
		        	 for(int i=0;i<aList.size();i++)
		        	 {
		        		 referredFriends.add(aList.get(i).toString().trim());
		        	 }
			         }
		         }
		         user.setReferredFriends(referredFriends);
		         if(onlinePurchases.isEmpty()){	
			        	 if(rs.getString("OnlinePurchase").contains("[")){
			        		 value = rs.getString("OnlinePurchase").substring(1, rs.getString("OnlinePurchase").length()-1);	
			        		 aList= new ArrayList(Arrays.asList(value.split(",")));
		        	          for(int i=0;i<aList.size();i++){
		        		       onlinePurchases.add(aList.get(i).toString().trim());
		        	          }
			        	 }else{
			        		 value = rs.getString("OnlinePurchase");
		                 aList= new ArrayList(Arrays.asList(value.split(",")));
		        	          for(int i=0;i<aList.size();i++){
		        		       onlinePurchases.add(aList.get(i).toString().trim());
		        	          }
			        	 }
		         }else{
		        	 onlinePurchases = new ArrayList<String>();
		        	 if(rs.getString("OnlinePurchase").contains("[")){
		        		 value = rs.getString("OnlinePurchase").substring(1, rs.getString("OnlinePurchase").length()-1);	
		        		 aList= new ArrayList(Arrays.asList(value.split(",")));
	        	          for(int i=0;i<aList.size();i++){
	        		       onlinePurchases.add(aList.get(i).toString().trim());
	        	          }
		        	 }else{
		        		 value = rs.getString("OnlinePurchase");
	                 aList= new ArrayList(Arrays.asList(value.split(",")));
	        	          for(int i=0;i<aList.size();i++){
	        		       onlinePurchases.add(aList.get(i).toString().trim());
	        	          }
		        	 }
		         }
		         user.setOnlinePurchase(onlinePurchases);
		         usersList.add(user);
		      }
		     
		      rs.close();
		   }catch(SQLException se){
		      se.printStackTrace();
		   }catch(Exception e){
		      e.printStackTrace();
		   }finally{
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se){
		      }
		      try{
		         if(con!=null)
		            con.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }

	   }
	   
	   return usersList;
   }

public Response addUser(User user) {
	Response response = null;
	String name = user.getName();
	String firstName = user.getFirstName();
	String lastName = user.getLastName();
	String email = user.getEmail();
	String mobile = user.getMobile();
	Double points = user.getPoints();
	String passwd = user.getPassword();
	List<String> onlinePurchase = new ArrayList<String>();
	Iterator<String> itO = user.getOnlinePurchase().iterator();
	while(itO.hasNext()){
		onlinePurchase.add(itO.next());
	}
	
	List<String> referredFriends = new ArrayList<String>();
	Iterator<String> itR = user.getReferredFriends().iterator();
	while(itR.hasNext()){
		referredFriends.add(itR.next());
	}
	Connection conn = null;
	PreparedStatement stmt = null;
	
	if(conn == null){
		conn = recoverConnection();
	}
	 try{
		 response = new Response();
		  

		      String sql = "Insert into mcent.User(Name, LastName, FirstName, Email, Mobile, Password, ReferredFriends, OnlinePurchase, Points) "
		      		+ "values(?,?,?,?,?,?,?,?,?)";
		      stmt = conn.prepareStatement(sql);
		      stmt.setString(1, name);
		      stmt.setString (2, lastName);
		      stmt.setString(3, firstName);
		      stmt.setString(4, email);
		      stmt.setString(5, mobile);
		      stmt.setString(6, passwd);
		      stmt.setString(7, referredFriends.toString());
		      stmt.setString(8, onlinePurchase.toString());
		      stmt.setDouble(9, points);

		      stmt.execute();
		      response.setStatus("Success");
			  response.setMessage("Successfully Registered User");
		      
		   }catch(SQLException se){
			  response.setStatus("Failed");
			  response.setMessage(se.getMessage());
		   }catch(Exception e){
		      e.printStackTrace();
		   }finally{
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se){
		      }
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }

	   }
	return response;
}
}
