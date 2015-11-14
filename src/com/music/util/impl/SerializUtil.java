package com.music.util.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

public class SerializUtil {

	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;

	public SerializUtil() {

	}

	public void writeMusicBean(Object bean , String strFile) {
		File file = new File(strFile);
		if (!file.isFile()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				oos = new ObjectOutputStream(new FileOutputStream(file));
				oos.writeObject(bean);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (oos != null) {
					try {
						oos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

	}

	public Object readMusicBean(String strFile) {
		File file = new File(strFile) ;
		Object bean = null;
		if (!file.isFile()) {
			try {
				file.createNewFile();
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				ois = new ObjectInputStream(new FileInputStream(file));
				bean = (Object) ois.readObject();
			} catch (StreamCorruptedException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				return null;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				if (ois != null) {
					try {
						ois.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return bean;
	}

}
