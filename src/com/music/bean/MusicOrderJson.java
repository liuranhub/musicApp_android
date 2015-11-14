package com.music.bean;

import java.io.Serializable;

public class MusicOrderJson implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String orderName;
	private int orderSize;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public int getOrderSize() {
		return orderSize;
	}

	public void setOrderSize(int orderSize) {
		this.orderSize = orderSize;
	}

}
