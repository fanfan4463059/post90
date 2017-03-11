package com.developbuildtools.utils;

/***
 * �� StringBuffer ����������й���
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
	 * ��ӻ���
	 * 
	 * @param string
	 */
	public void append_feed(String string) {
		strBuffer.append(string);
		strBuffer.append(System.getProperty("line.separator"));
	}

	/**
	 * ����д��
	 * 
	 * @param string
	 */
	public void append(String string) {
		strBuffer.append(string);
	}

}
