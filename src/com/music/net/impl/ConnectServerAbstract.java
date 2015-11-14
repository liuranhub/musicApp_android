package com.music.net.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.music.net.inter.ConnectServerIf;

public abstract class ConnectServerAbstract implements ConnectServerIf {

	/**
	 * 错误标志
	 * */
	public final static int ERROR = 0;
	/**
	 * 连接超时标志
	 * */
	public final static int CONN_TIMEOUT = 1;
	/**
	 * 连接正常标志
	 * */
	public final static int CONN_TRUE = 2;
	private int connState = 0;

	public int getConnState() {
		return connState;
	}

	private void setConnState(int connState) {
		this.connState = connState;
	}

	protected InputStream getInputStream(String urlStr) {
		InputStream in = null;
		int response = -1;
		URL url = null;
		try {
			url = new URL(urlStr);
			URLConnection conn = url.openConnection();
			HttpURLConnection httpConn = (HttpURLConnection) conn;
			if (!(conn instanceof HttpURLConnection)) {
				throw new Exception("not an http connection");
			}

			httpConn.setAllowUserInteraction(false);
			httpConn.setInstanceFollowRedirects(true);
			httpConn.setRequestMethod("GET");

			httpConn.setConnectTimeout(5000);

			try {
				httpConn.connect();
				setConnState(CONN_TIMEOUT);
			} catch (Exception e) {
				return null;
			}

			response = httpConn.getResponseCode();

			if (response == HttpURLConnection.HTTP_OK) {
				setConnState(CONN_TRUE);
				in = httpConn.getInputStream();
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return in;
	}

	protected BufferedReader getBufferedReader(String urlStr) {
		InputStream is = getInputStream(urlStr);
		BufferedReader reader = null;
		if (is != null) {
			reader = new BufferedReader(new InputStreamReader(is));
		}
		return reader;
	}

}
