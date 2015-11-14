package com.music.view.inter;

import android.content.Intent;
import android.view.MotionEvent;

public interface ChangeActivityBySlippingInter {
	/**
	 * �ж��Ƿ���Ըı�activity
	 * 
	 * @param event
	 *            �ⲿtouch�����󴫵ݵ�MotionEvent
	 * @return true �����л�activity������
     * @return false �������л�activity������
	 * */
	public boolean isChangeActivityByTouch(MotionEvent event) ;
	
	/**
	 * ��ȡintent
	 * 
	 * @return ���ظı���intent
	 * @exception NullPointerException
	 * */
	public Intent getIntent() ;
	
	/**
	 * �ⲿ��������
	 * */
	public void reset() ;
	
	/**
	 * ���ô��ұ߿�ʼ����ʱ���ֵĽ���
	 * */
	public void setRightActivity(Class<?> rightActivity) ;
	
	/**
	 * ���ô���߿�ʼ����ʽ���ֵĽ���
	 * */
	public void setLiftActivity(Class<?> liftActivity) ;
}
