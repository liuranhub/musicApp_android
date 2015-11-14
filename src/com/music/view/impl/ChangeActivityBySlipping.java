package com.music.view.impl;

import java.util.Date;

import com.music.view.inter.ChangeActivityBySlippingInter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.view.MotionEvent;

/**
 * ���� ChangeActivityBySlippingInter�ӿڵ�һ��ʵ�� ��ʹ����Ҫ�������¼������裺<br>
 * 1������һ��ChangeActivityBySlipping ���ڹ����ͬʱ����һ����ǰ��Context���󣬸����๤��<br>
 * 2�����������л���activity������ֵ������л�<br>
 * 3��ѭ������isChangeActivityByTouch(MotionEvent event)������Ƿ������л�����<br>
 * 4������ getIntent()��ȡ���ɵ�intent<br>
 * 5���ڶ��λص�����ʱ����reset()������˵������׼���ڶ����л�<br>
 * @author LR
 * @version 1.0 ���û������ƽ�����л�
 * */
public class ChangeActivityBySlipping implements ChangeActivityBySlippingInter {

	/**
	 * @param context
	 *            Context�������Ź����µ�activity
	 * */
	public ChangeActivityBySlipping(Context context) {
		this.context = context;
	}

	/**
	 * @param context
	 *            Context�������Ź����µ�activity
	 * @param rightActivity
	 *            Class���������л���activity class����
	 * @param liftActivity
	 *            Class���������л���activity class����
	 * */
	public ChangeActivityBySlipping(Context context, Class<?> rightActivity,
			Class<?> liftActivity) {
		this.context = context;
		this.rightActivity = rightActivity;
		this.liftActivity = liftActivity;
	}

	/**
	 * fristPoint ��һ�̴���������� <br>
	 * secondPoint ��ǰʱ���������������
	 * */
	private Point firstPoint = new Point();
	private Point secondPoint = new Point();

	/**
	 * firstTime ��һ�̵�ʱ�� <br>
	 * secondTime ��һ����ʱ�� <br>
	 * �������������ж��Ƿ��������Ļ����������л�
	 * */
	private long firstTime = 0;
	private long secondTime = 0;

	/**
	 * �ⲿ��ȡ��intent��ʹ�������Ķ��󣬿���ͨ����ȡ����������activity�ĸı�
	 * */
	private Intent intent = null;

	/**
	 * ��ֹ���������������˶����ͬ���߲�ͬ�Ľ���<br>
	 * 1�����ҽ���changeableֵΪtrue������intent�ǿ��Ը���<br>
	 * 2��changeableֵΪfalse������intent�ǲ������ٸ��ģ����ҽ���changeableΪfalse�ſ����ٴθ���
	 * */
	private boolean changeable = true;

	/**
	 * intent
	 * */
	private Context context = null;

	/**
	 * ����activity��class����
	 * */
	private Class<?> rightActivity = null;
	private Class<?> liftActivity = null;

	/**
	 * �ж��Ƿ��Ѿ�ΪActivity��ֵ����֤intent���������Ĵ���<br>
	 * 
	 * @return true ��ʾ���Դ����÷����intent
	 * @return false ��ʾ�����Դ����÷����intent
	 * */
	private boolean isRirht() {
		if (rightActivity != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * �ж��Ƿ��Ѿ�ΪActivity��ֵ����֤intent���������Ĵ���<br>
	 * 
	 * @return true ��ʾ���Դ����÷����intent
	 * @return false ��ʾ�����Դ����÷����intent
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
	 * ��ȡ�����¼�����֮���ʱ���<br>
	 * @return ����ʱ���
	 * */
	private int getTimeSlot() {
		return (int) (secondTime - firstTime);
	}

	/**
	 * ��ȡ��������֮��ľ���
	 **/
	private float getDistance_x_right() {
		return firstPoint.x - secondPoint.x;
	}

	/**
	 * ��ȡ��������֮��ľ���
	 **/
	private float getDistance_x_lift() {
		return secondPoint.x - firstPoint.x;
	}

	/**
	 * ����û��д��
	 * */
	@SuppressWarnings("unused")
	private float getDistance_y_lift() {
		return firstPoint.y - secondPoint.y;
	}

	/**
	 * ����û��д��
	 * */
	@SuppressWarnings("unused")
	private float getDistance_y_right() {
		return secondPoint.y - firstPoint.y;
	}

	/**
	 * �ж��Ƿ���Ըı�Intent<br>
	 * 
	 * @return true ���Ը���intent
	 * @return false �����Ը���intent
	 * */
	private boolean getChangeable() {
		return changeable;
	}

	/**
	 * �����Ƿ�ɸı�. <br>
	 * ���ҽ���changeableΪtrue�ǲſ�������Ϊfalse����������Ϊfalse��ֻ���ٵ���reset��ʱ��ſ����������е�����
	 * */
	private void setChangeable(boolean changeable) {
		if (this.changeable) {
			this.changeable = changeable;
		}
	}

	/**
	 * �ı�intent��ֵ
	 * 
	 * @return �����Ƿ���ٴθ���intent
	 * @return false ��ʾ�Ѿ�������intent��������ͨ�������¼�����intent
	 * @return true ��ʾҪ����ͨ��ѭ������������intent������
	 * */
	private boolean changeIntent() {

		/**
		 * ���ҽ�����������������������ʱ�Ž���intent������<br>
		 * 1������֮��ľ���ﵽָ��ֵ<br>
		 * 2��intent��ֵ��Ϊ��<br>
		 * 3��lift�������right���治Ϊ��ʱ<br>
		 * intent == null ��֤ intent = new Intent(context, XXXActivity)
		 * ��һ�������Ĵ���������ִֻ��һ��
		 **/
		if (getDistance_x_lift() > 50 && intent == null && isLift()) {
			intent = new Intent(context, liftActivity);
		}

		if (getDistance_x_right() > 50 && intent == null && isRirht()) {
			intent = new Intent(context, rightActivity);
		}

		/**
		 * ���ҽ���Intent��ֵ�ǲŸ���changeable��ֵ intent != null ��֤
		 * ֻ��������intent��ֵ��������intent�Ŀθı���
		 * */
		if (intent != null) {
			setChangeable(false);
			return changeable;
		}
		return changeable;
	}

	/**
	 * ���ô��ұ߿�ʼ����ʱ���ֵĽ���
	 * */
	public void setRightActivity(Class<?> rightActivity) {
		this.rightActivity = rightActivity;
	}

	/**
	 * ���ô���߿�ʼ����ʽ���ֵĽ���
	 * */
	public void setLiftActivity(Class<?> liftActivity) {
		this.liftActivity = liftActivity;
	}

	/**
	 * �ⲿ�������ݣ��������ݽ�������
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
	 * ��ȡintent
	 * 
	 * @return ���ظı���intent
	 * @exception NullPointerException
	 * */
	public Intent getIntent() {
		if (intent == null) {
			throw (new NullPointerException());
		}
		return intent;
	}

	/**
	 * �ж��Ƿ���Ըı�activity
	 * 
	 * @param event
	 *            �ⲿtouch�����󴫵ݵ�MotionEvent
	 * @return true �����л�activity������
	 * @return false �������л�activity������
	 * @exception NullPointerException
	 * */
	public boolean isChangeActivityByTouch(MotionEvent event) {

		if (event == null) {
			throw (new NullPointerException());
		}

		setFirstTime(getSecondTime());

		setSecondTime(new Date().getTime());

		/**
		 * �����߼��� <br>
		 * 1�����ȸ��Ĵ����������������ֵ; <br>
		 * 2���ж��Ƿ��ǵ�һ�δ��������ǵ�һ����ִ�к�������; <br>
		 * 3���ж��Ƿ���Ըı䣬������ܸı䣬�����intent�Ѿ���ֵ�ˣ���ֵ��ֻ��ͨ��reset��������Ϊ��ֹ�����ظ����治��ִ�к�������; <br>
		 * 4��������Լ���ִ���򣬵��øı��changeIntent��changeIntent����һ��booleanֵ�������ж��Ƿ���ĳɹ�; <br>
		 * 5������getChangeable����Ϊfalse��ʾ��ִ����������Ĳ������ⷵ�ص��ͷ���һ��true; <br>
		 * �ؼ���getChangeable �����ڲ�������if�ж��гɻ�������
		 * */
		setFirstPoint_x(getSecondPoint().x);

		setSecondPoint_x((int) (event.getX()));

		if (getFirstPoint().x != 0 && firstTime > 1) {
			//��ֹ��ϵ����ε���ɹ������¼�
			if (getTimeSlot() > 200) {
				reset();
				return false;
			}

			/**
			 * ��changeable==true����ʾ���Ը���intent���ŵ��� changeIntent()<br>
			 * ��changeable==false����ʾintent�Ѿ����ģ�����ֱ�ӷ��ص����õ�activity��ʹ��
			 * */
			//��changeable==true����ʾ���Ը���intent���ŵ��� changeIntent()
			if (getChangeable()) {
				changeIntent();
				//��changeable==false����ʾintent�Ѿ����ģ�����ֱ�ӷ��ص����õ�activity��ʹ��
				if (!getChangeable()) {
					return true;
				}
			}
		}
		return false;
	}
}
