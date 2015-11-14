package com.music.bean;

public class TestBean {
	public TestBean(){
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "TestBean [id=" + id + ", version=" + version + ", name=" + name
				+ ", password=" + password + "]";
	}

	private Integer id ;
	private Integer version ;
	private String  name ;
	private String password ;
}
