package com.music.view.inter;

import java.util.LinkedList;

import com.music.view.impl.OrderPopupWindow;

public interface MusicLetterRelative extends MusicRowRelative {
	
	public void setOrderPopupWindow(OrderPopupWindow orderPopupWindow);

	public Integer getLetterHeight();
	
	public LinkedList<MusicRowRelative> getRowList() ;
	
}
