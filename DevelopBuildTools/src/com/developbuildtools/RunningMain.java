package com.developbuildtools;

import java.io.IOException;

import jxl.read.biff.BiffException;

import com.developbuildtools.files.FilesBooleanCheckTools;
import com.developbuildtools.files.json2model.JsonToFileTools;

public class RunningMain {
	public static void main(String[] args) throws BiffException, IOException {
		// ʵ���ĵ�У�飬�жϹ��̵��ļ����Ƿ���ڣ�
		// FilesBooleanCheckTools filesBooleanCheckTools = new
		// FilesBooleanCheckTools();
		// filesBooleanCheckTools.doingFunction();

		// ����jsonֱ������ model�ļ�+����ת����UI���ļ�
		JsonToFileTools jsonToFileTools = new JsonToFileTools();
		jsonToFileTools.doingFunction();
		
	}
}
