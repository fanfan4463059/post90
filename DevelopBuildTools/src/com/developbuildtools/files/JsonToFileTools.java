package com.developbuildtools.files;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

/**
 * 根据json直接生成 model文件+数据转换到UI的文件
 * 
 * @author Carry
 * 
 */
public class JsonToFileTools {

	public static String textJson = "{\"start\":{\"name\":\"站长工具\",\"url\":\"http://tool.chinaz.com\",\"address\":{\"city\":\"厦门\",\"country\":\"中国\",\"app\":{\"city\":1,\"country\":\"中国\",\"name\":\"Baidu\",\"url\":\"http://www.baidu.com\"}},\"arrayBrowser\":[{\"name\":\"Google\",\"url\":\"http://www.google.com\"},{\"name\":\"Baidu\",\"url\":\"http://www.baidu.com\"},{\"name\":\"SoSo\",\"url\":\"http://www.SoSo.com\"}]}}";

	public JsonToFileTools() {

	}

	public void doingFunction() {
		JsonParser parse = new JsonParser(); // 创建json解析器
		try {
			JsonParser parser = new JsonParser();
			JsonObject json = parser.parse(textJson).getAsJsonObject();// 创建jsonObject对象
			// 通过JsonParser对象可以把json格式的字符串解析成一个JsonElement对象
			JsonElement el = parser.parse(textJson);

			// 把JsonElement对象转换成JsonObject
			JsonObject jsonObj = null;
			if (el.isJsonObject()) {
				jsonObj = el.getAsJsonObject();
			}

			// 把JsonElement对象转换成JsonArray
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
