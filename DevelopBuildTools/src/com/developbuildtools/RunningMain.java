package com.developbuildtools;

import java.io.IOException;

import jxl.read.biff.BiffException;

import com.developbuildtools.files.FilesBooleanCheckTools;
import com.developbuildtools.files.JsonToFileTools;

public class RunningMain {
	public static void main(String[] args) throws BiffException, IOException {
		// ʵ���ĵ�У�飬�жϹ��̵��ļ����Ƿ���ڣ�
		FilesBooleanCheckTools s = new FilesBooleanCheckTools();
		s.doingFunction();

		// ����jsonֱ������ model�ļ�+����ת����UI���ļ�
		// JsonToFileTools s=new JsonToFileTools();
		// s.doingFunction();
	}
}
