package com.music.util.inter;

import android.widget.SeekBar.OnSeekBarChangeListener;

public interface MusicSeekBar {
	
	/**
	 * ����seekBar����������<br>
	 * 1��seekBar��ǰλ�õ�����<br>
	 * 2��seekBar�����ֵ<br>
	 * */
	public void resetSeekBar(int musicTime);

	/**
	 * ��ȡseekBar�ĵ�ǰλ�ã���ǰλ�ú����ֵĲ��Ž��ȱ���һ��<br>
	 * */
	public int getProgress();

	/**
	 * ����seekBar������λ��<br>
	 * seekBar����λ�õĸ�������ɺ����ֵĽ���Ҳ��Ӧ�Ľ����޸�<br>
	 * ���ڽ�������ȫȷ�����ٲ��޸�music�Ĳ��Ž���
	 * */
	public void setProgress(int currentPosition);

	public void changeListener(OnSeekBarChangeListener listener) ;
	
}
