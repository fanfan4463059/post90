package com.developbuildtools.utils;

/***
 * 对 StringBuffer 共性问题进行管理
 * 
 * @author Carry
 * 
 */
public class StrBufferUtils {
	private StringBuffer strBuffer;

	public StrBufferUtils() {
		strBuffer = new StringBuffer();
	}

	public StringBuffer getStrBuffer() {
		return strBuffer;
	}

	/**
	 * 添加换行
	 * 
	 * @param string
	 */
	public void append_feed(String string) {
		strBuffer.append(string);
		strBuffer.append(System.getProperty("line.separator"));
	}

	/**
	 * 正常写入
	 * 
	 * @param string
	 */
	public void append(String string) {
		strBuffer.append(string);
	}

}
