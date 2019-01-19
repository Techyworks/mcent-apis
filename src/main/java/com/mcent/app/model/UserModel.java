package com.mcent.app.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

public class UserModel {

	public List<User> user;
	
	public static class User{
		
	private String Name;
	private String FirstName;
	private String LastName;
	private String Password;
	private String Email;
	private String Mobile;
	private double Points;
	private List<String> ReferredFriends;
	private List<String> OnlinePurchase;
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getMobile() {
		return Mobile;
	}
	public void setMobile(String mobile) {
		Mobile = mobile;
	}
	public double getPoints() {
		return Points;
	}
	public void setPoints(double points) {
		Points = points;
	}
	public List<String> getReferredFriends() {
		return ReferredFriends;
	}
	public void setReferredFriends(List<String> referredFriends) {
		ReferredFriends = referredFriends;
	}
	public List<String> getOnlinePurchase() {
		return OnlinePurchase;
	}
	public void setOnlinePurchase(List<String> onlinePurchase) {
		OnlinePurchase = onlinePurchase;
	}
	
}
}
