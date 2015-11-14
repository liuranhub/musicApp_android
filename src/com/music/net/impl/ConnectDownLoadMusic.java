package com.music.net.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

import android.os.AsyncTask;
import android.os.Environment;

public class ConnectDownLoadMusic extends ConnectServerAbstract {

	private String musicName = "unknow";

	public String getMusicName() {
		return musicName;
	}

	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}

	@Override
	public String getResult(String urlStr) {
		try {
			new ConnectServerAsyncTask().execute(urlStr).get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 检查是否有重名的音乐
	 * */
	private boolean checkName() {
		return true;
	}

	private class ConnectServerAsyncTask extends
			AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			doing(params[0]);
			return null;
		}

		private String doing(String urlStr) {

			InputStream in = getInputStream(urlStr);

			File dir = Environment.getExternalStorageDirectory();
			checkName();
			File downloaded = new File(dir.toString() + "/file/music/download/"
					+ getMusicName() + ".mp3");
			FileOutputStream out = null;
			byte[] b = new byte[1024];

			try {
				downloaded.createNewFile();
				out = new FileOutputStream(downloaded);
				int len = -1;
				while ((len = in.read(b)) > 0) {
					/**
					 * 文件的写入方式会导致输出损失
					 * */
					out.write(b, 0, len);
					out.flush();
				}

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					out.close();
					System.out.println("closed");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return null;
		}
	}
}
