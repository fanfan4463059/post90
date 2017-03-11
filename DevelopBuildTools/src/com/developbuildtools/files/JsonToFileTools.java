package com.developbuildtools.files;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

/**
 * ����jsonֱ������ model�ļ�+����ת����UI���ļ�
 * 
 * @author Carry
 * 
 */
public class JsonToFileTools {

	public static String textJson = "{\"start\":{\"name\":\"վ������\",\"url\":\"http://tool.chinaz.com\",\"address\":{\"city\":\"����\",\"country\":\"�й�\",\"app\":{\"city\":1,\"country\":\"�й�\",\"name\":\"Baidu\",\"url\":\"http://www.baidu.com\"}},\"arrayBrowser\":[{\"name\":\"Google\",\"url\":\"http://www.google.com\"},{\"name\":\"Baidu\",\"url\":\"http://www.baidu.com\"},{\"name\":\"SoSo\",\"url\":\"http://www.SoSo.com\"}]}}";

	public JsonToFileTools() {

	}

	public void doingFunction() {
		JsonParser parse = new JsonParser(); // ����json������
		try {
			JsonParser parser = new JsonParser();
			JsonObject json = parser.parse(textJson).getAsJsonObject();// ����jsonObject����
			// ͨ��JsonParser������԰�json��ʽ���ַ���������һ��JsonElement����
			JsonElement el = parser.parse(textJson);

			// ��JsonElement����ת����JsonObject
			JsonObject jsonObj = null;
			if (el.isJsonObject()) {
				jsonObj = el.getAsJsonObject();
			}

			// ��JsonElement����ת����JsonArray
			JsonArray jsonArray = null;
			if (el.isJsonArray()) {
				jsonArray = el.getAsJsonArray();
			}

		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		}

	}

}
