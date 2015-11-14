package com.music.view.inter;

import android.content.Intent;
import android.view.MotionEvent;

public interface ChangeActivityBySlippingInter {
	/**
	 * 判断是否可以改变activity
	 * 
	 * @param event
	 *            外部touch触发后传递的MotionEvent
	 * @return true 满足切换activity的条件
     * @return false 不满足切换activity的条件
	 * */
	public boolean isChangeActivityByTouch(MotionEvent event) ;
	
	/**
	 * 获取intent
	 * 
	 * @return 返回改变后的intent
	 * @exception NullPointerException
	 * */
	public Intent getIntent() ;
	
	/**
	 * 外部重置数据
	 * */
	public void reset() ;
	
	/**
	 * 设置从右边开始滑动时出现的界面
	 * */
	public void setRightActivity(Class<?> rightActivity) ;
	
	/**
	 * 设置从左边开始滑动式出现的界面
	 * */
	public void setLiftActivity(Class<?> liftActivity) ;
}
