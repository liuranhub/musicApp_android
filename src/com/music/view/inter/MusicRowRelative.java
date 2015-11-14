package com.music.view.inter;

import android.view.View;
import android.widget.RelativeLayout.LayoutParams;

import com.music.view.impl.OrderPopupWindow;

public interface MusicRowRelative {
	
	public void setOrderPopupWindow(OrderPopupWindow orderPopupWindow) ;

	public View getMusicView() ;

	public LayoutParams getMusicLayout() ;
	
	public void setId(Integer id) ;
	
}
