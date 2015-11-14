package com.music.bean;

public class User {
	public User() {

	}

	private Integer id = null;
	
	private String username = null;
	
	private static boolean loginState = false;
	
	private String password = null;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLogin() {
		return loginState;
	}

	public void setLoginState(boolean loginState) {
		User.loginState = loginState;
	}

}
