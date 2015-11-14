package com.music.view.impl;

import java.util.Date;

import com.music.view.inter.ChangeActivityBySlippingInter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.view.MotionEvent;

/**
 * 类是 ChangeActivityBySlippingInter接口的一个实现 ，使用需要经过以下几个步骤：<br>
 * 1、构造一个ChangeActivityBySlipping ，在构造的同时传递一个当前的Context对象，辅助类工作<br>
 * 2、设置左右切换的activity，不赋值则代表不切换<br>
 * 3、循环调用isChangeActivityByTouch(MotionEvent event)，检测是否满足切换条件<br>
 * 4、调用 getIntent()获取生成的intent<br>
 * 5、第二次回到界面时调用reset()，重置说有数据准备第二次切换<br>
 * @author LR
 * @version 1.0 利用滑动控制界面的切换
 * */
public class ChangeActivityBySlipping implements ChangeActivityBySlippingInter {

	/**
	 * @param context
	 *            Context对象，用着构造新的activity
	 * */
	public ChangeActivityBySlipping(Context context) {
		this.context = context;
	}

	/**
	 * @param context
	 *            Context对象，用着构造新的activity
	 * @param rightActivity
	 *            Class对象，向右切换的activity class对象
	 * @param liftActivity
	 *            Class对象，向左切换的activity class对象
	 * */
	public ChangeActivityBySlipping(Context context, Class<?> rightActivity,
			Class<?> liftActivity) {
		this.context = context;
		this.rightActivity = rightActivity;
		this.liftActivity = liftActivity;
	}

	/**
	 * fristPoint 上一刻触摸的坐标点 <br>
	 * secondPoint 当前时间所触摸的坐标点
	 * */
	private Point firstPoint = new Point();
	private Point secondPoint = new Point();

	/**
	 * firstTime 上一刻的时间 <br>
	 * secondTime 下一个的时间 <br>
	 * 两个变量用着判断是否是连续的滑动触发的切换
	 * */
	private long firstTime = 0;
	private long secondTime = 0;

	/**
	 * 外部获取的intent，使用这个类的对象，可以通过获取这个对象进行activity的改变
	 * */
	private Intent intent = null;

	/**
	 * 防止连续触发而启动了多个相同或者不同的界面<br>
	 * 1、当且仅当changeable值为true，代表intent是可以更改<br>
	 * 2、changeable值为false，代表intent是不可以再更改，当且仅当changeable为false才可以再次更改
	 * */
	private boolean changeable = true;

	/**
	 * intent
	 * */
	private Context context = null;

	/**
	 * 左右activity的class对象
	 * */
	private Class<?> rightActivity = null;
	private Class<?> liftActivity = null;

	/**
	 * 判断是否已经为Activity赋值，保证intent可以正常的创建<br>
	 * 
	 * @return true 表示可以创建该方向的intent
	 * @return false 表示不可以创建该方向的intent
	 * */
	private boolean isRirht() {
		if (rightActivity != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断是否已经为Activity赋值，保证intent可以正常的创建<br>
	 * 
	 * @return true 表示可以创建该方向的intent
	 * @return false 表示不可以创建该方向的intent
	 * */
	private boolean isLift() {
		if (liftActivity != null) {
			return true;
		} else {
			return false;
		}
	}

	private void setFirstPoint_x(int x) {
		firstPoint.x = x;
	}

	private void setFirstPoint_y(int y) {
		firstPoint.y = y;
	}

	@SuppressWarnings("unused")
	private void setSecondPoint(int x, int y) {
		setFirstPoint_x(x);
		setFirstPoint_y(y);
	}

	private Point getSecondPoint() {
		return secondPoint;
	}

	private Point getFirstPoint() {
		return firstPoint;
	}

	private void setSecondPoint_x(int x) {
		secondPoint.x = x;
	}

	@SuppressWarnings("unused")
	private void setSecondPoint_y(int y) {
		firstPoint.y = y;
	}

	private void setFirstTime(long firstTime) {
		this.firstTime = firstTime;
	}

	private void setSecondTime(long secondTime) {
		this.secondTime = secondTime;
	}

	private long getSecondTime() {
		return secondTime;
	}

	@SuppressWarnings("unused")
	private void setFirstPoint(int x, int y) {
		setFirstPoint_x(x);
		setFirstPoint_y(y);
	}

	/**
	 * 获取两次事件触发之间的时间差<br>
	 * @return 返回时间差
	 * */
	private int getTimeSlot() {
		return (int) (secondTime - firstTime);
	}

	/**
	 * 获取连续两点之间的距离
	 **/
	private float getDistance_x_right() {
		return firstPoint.x - secondPoint.x;
	}

	/**
	 * 获取连续两点之间的距离
	 **/
	private float getDistance_x_lift() {
		return secondPoint.x - firstPoint.x;
	}

	/**
	 * 方法没有写完
	 * */
	@SuppressWarnings("unused")
	private float getDistance_y_lift() {
		return firstPoint.y - secondPoint.y;
	}

	/**
	 * 方法没有写完
	 * */
	@SuppressWarnings("unused")
	private float getDistance_y_right() {
		return secondPoint.y - firstPoint.y;
	}

	/**
	 * 判断是否可以改变Intent<br>
	 * 
	 * @return true 可以更改intent
	 * @return false 不可以更改intent
	 * */
	private boolean getChangeable() {
		return changeable;
	}

	/**
	 * 设置是否可改变. <br>
	 * 当且仅当changeable为true是才可以设置为false，并且设置为false后，只能再调用reset的时候才可以重置所有的数据
	 * */
	private void setChangeable(boolean changeable) {
		if (this.changeable) {
			this.changeable = changeable;
		}
	}

	/**
	 * 改变intent的值
	 * 
	 * @return 返回是否可再次更改intent
	 * @return false 表示已经更改了intent，不可再通过触发事件更改intent
	 * @return true 表示要继续通过循环检查满足更改intent的条件
	 * */
	private boolean changeIntent() {

		/**
		 * 当且仅当满足下面三个条件条件时才进行intent的设置<br>
		 * 1、两点之间的距离达到指定值<br>
		 * 2、intent的值不为空<br>
		 * 3、lift界面或者right界面不为空时<br>
		 * intent == null 保证 intent = new Intent(context, XXXActivity)
		 * 在一次完整的触发过程中只执行一次
		 **/
		if (getDistance_x_lift() > 50 && intent == null && isLift()) {
			intent = new Intent(context, liftActivity);
		}

		if (getDistance_x_right() > 50 && intent == null && isRirht()) {
			intent = new Intent(context, rightActivity);
		}

		/**
		 * 当且仅当Intent有值是才更改changeable的值 intent != null 保证
		 * 只有设置了intent的值才能设置intent的课改变性
		 * */
		if (intent != null) {
			setChangeable(false);
			return changeable;
		}
		return changeable;
	}

	/**
	 * 设置从右边开始滑动时出现的界面
	 * */
	public void setRightActivity(Class<?> rightActivity) {
		this.rightActivity = rightActivity;
	}

	/**
	 * 设置从左边开始滑动式出现的界面
	 * */
	public void setLiftActivity(Class<?> liftActivity) {
		this.liftActivity = liftActivity;
	}

	/**
	 * 外部重置数据，所有数据进行清零
	 * */
	public void reset() {
		intent = null;
		secondPoint = new Point(0, 0);
		firstPoint = new Point(0, 0);
		firstTime = 0;
		secondTime = 0;
		changeable = true;
	}

	/**
	 * 获取intent
	 * 
	 * @return 返回改变后的intent
	 * @exception NullPointerException
	 * */
	public Intent getIntent() {
		if (intent == null) {
			throw (new NullPointerException());
		}
		return intent;
	}

	/**
	 * 判断是否可以改变activity
	 * 
	 * @param event
	 *            外部touch触发后传递的MotionEvent
	 * @return true 满足切换activity的条件
	 * @return false 不满足切换activity的条件
	 * @exception NullPointerException
	 * */
	public boolean isChangeActivityByTouch(MotionEvent event) {

		if (event == null) {
			throw (new NullPointerException());
		}

		setFirstTime(getSecondTime());

		setSecondTime(new Date().getTime());

		/**
		 * 代码逻辑： <br>
		 * 1、首先更改触发函数的两个点的值; <br>
		 * 2、判断是否是第一次触发，不是第一次则执行后续操作; <br>
		 * 3、判断是否可以改变，如果不能改变，则代码intent已经有值了（有值后只能通过reset解锁），为防止启动重复界面不再执行后续操作; <br>
		 * 4、如果可以继续执行则，调用改变的changeIntent，changeIntent返回一个boolean值，用着判断是否更改成功; <br>
		 * 5、若果getChangeable返回为false表示可执行启动界面的操作，这返回到客服端一个true; <br>
		 * 关键：getChangeable ；最内部的两个if判断行成互斥条件
		 * */
		setFirstPoint_x(getSecondPoint().x);

		setSecondPoint_x((int) (event.getX()));

		if (getFirstPoint().x != 0 && firstTime > 1) {
			//防止间断的两次点击成功触发事件
			if (getTimeSlot() > 200) {
				reset();
				return false;
			}

			/**
			 * 当changeable==true，表示可以更改intent，才调用 changeIntent()<br>
			 * 当changeable==false，表示intent已经更改，可以直接返回到调用的activity中使用
			 * */
			//当changeable==true，表示可以更改intent，才调用 changeIntent()
			if (getChangeable()) {
				changeIntent();
				//当changeable==false，表示intent已经更改，可以直接返回到调用的activity中使用
				if (!getChangeable()) {
					return true;
				}
			}
		}
		return false;
	}
}
