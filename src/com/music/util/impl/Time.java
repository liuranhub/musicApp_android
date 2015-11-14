package com.music.util.impl;

public class Time {
	public static String timeToString(int time){
		StringBuffer timeStr = new StringBuffer(); 
		int second = time/1000 ;
		int mintue = second / 60 ;
		second = second % 60 ;
		if(time<1000){
			return "00:00" ;
		}
		if(mintue<10){
			timeStr.append('0').append(mintue) ;
		}else{
			timeStr.append(mintue) ;
		}
		timeStr.append(':');
		if(second<10){
			timeStr.append('0').append(second);
		}else{
			timeStr.append(second);
		}
		return timeStr.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(Time.timeToString(4561989));
	}
}
