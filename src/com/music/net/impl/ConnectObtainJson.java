package com.music.net.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import android.os.AsyncTask;

public class ConnectObtainJson extends ConnectServerAbstract {

	public String getResult(String urlStr) {

		String json = null;

		try {
			json = new ConnectServerAsyncTask().execute(urlStr).get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		return json;

	}

	private class ConnectServerAsyncTask extends
			AsyncTask<String, Void, String> {

		protected String doInBackground(String... params) {

			 return doing(params[0]);
		}

		private String doing(String urlStr) {

			BufferedReader reader = getBufferedReader(urlStr);

			if (reader == null) {
				return null;
			}

			String str = null;
			StringBuffer data = new StringBuffer();

			try {
				while ((str = reader.readLine()) != null) {
					data.append(str);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			return data.toString();

		}

		/**
		 * ֱ����UI�߳���ִ�еķ���
		 * */
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
		}

	}

}
