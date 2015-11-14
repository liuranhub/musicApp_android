package com.music.json;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.music.bean.MusicClassJson;
import com.music.bean.MusicOrderJson;
import com.music.bean.MusicRowJson;
import com.music.bean.MusicTypeJson;

public class MusicJsonUtil {
	public static List<MusicRowJson> createMusicList(String json) {
		JSONObject jsonObj = null;
		LinkedList<MusicRowJson> list = new LinkedList<MusicRowJson>();
		if (json == null) {
			return list;
		}
		try {
			jsonObj = new JSONObject(json);
			JSONArray jsonArray = jsonObj.getJSONArray("musicList");
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject obj = jsonArray.getJSONObject(i);
				MusicRowJson row = new MusicRowJson();
				row.setMusicName(obj.getString("musicName"));
				row.setId(obj.getInt("id"));
				list.add(row);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return list;
	}

	public static List<MusicOrderJson> createOrderList(String json) {
		LinkedList<MusicOrderJson> list = new LinkedList<MusicOrderJson>();
		if (json == null) {
			return list;
		}
		JSONObject jsonObj = null;
		try {
			jsonObj = new JSONObject(json);
			JSONArray jsonArray = jsonObj.getJSONArray("jsonOrders");

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject obj = jsonArray.getJSONObject(i);

				MusicOrderJson order = new MusicOrderJson();
				order.setId(obj.getInt("id"));
				order.setOrderName(obj.getString("orderName"));
				order.setOrderSize(obj.getInt("orderSize"));
				list.add(order);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		for (MusicOrderJson o : list) {
			System.out.println(o.getOrderName());
		}
		return list;
	}

	static public MusicClassJson createTypeClass(String json) {

		MusicClassJson typeClass = new MusicClassJson();
		if (json == null) {
			return typeClass;
		}
		JSONObject jsonObj = null;
		try {
			jsonObj = new JSONObject(json);
			JSONArray jsonArray = jsonObj.getJSONArray("typeClassList");

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObj2 = jsonArray.getJSONObject(i);

				String name = jsonObj2.getString("className");
				JSONArray jsonArray2 = jsonObj2.getJSONArray("types");

				List<MusicTypeJson> types = new LinkedList<MusicTypeJson>();
				for (int j = 0; j < jsonArray2.length(); j++) {
					JSONObject jsonObj3 = jsonArray2.getJSONObject(j);
					MusicTypeJson type = new MusicTypeJson();
					type.setId(jsonObj3.getInt("id"));
					type.setTypeName(jsonObj3.getString("typeName"));
					types.add(type);

					System.out.println(jsonObj3.getString("typeName"));
				}

				typeClass.put(name, types);

			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return typeClass;
	}

}
