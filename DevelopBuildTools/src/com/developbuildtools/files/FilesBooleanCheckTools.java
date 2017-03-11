package com.developbuildtools.files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import jxl.Sheet;
import jxl.read.biff.BiffException;

import com.developbuildtools.utils.ExcelUtils;
import com.developbuildtools.utils.FilesUtils;

/**
 * ʵ���ĵ�У�飬�жϹ��̵��ļ����Ƿ���ڣ�
 * 
 * @author Carry
 * 
 */
public class FilesBooleanCheckTools {
	ArrayList<String> javaFileNameList, resFileNameList, exclNameList;
	ArrayList<ArrayList<String>> sheetExclNameList;

	public FilesBooleanCheckTools() {

	}

	/**
	 * ִ�з���
	 * 
	 * @throws BiffException
	 * @throws IOException
	 */
	public void doingFunction() throws BiffException, IOException {
		// ��ȡsrc�� java�ļ����Ƶ�list
		String[] replaceJava = { ".java" };
		javaFileNameList = FilesUtils.getFileNameList(new File(
				"H://git_src//save_scr//wacth//src_space//TwsApiDemos//src"),
				new ArrayList<String>(), ".java", replaceJava);

		// ��ȡ��Դ�ļ��� �ļ����Ƶ�list
		String[] replaceRes = { ".xml", ".png" };
		resFileNameList = FilesUtils.getFileNameList(new File(
				"H://git_src//save_scr//wacth//src_space//TwsApiDemos//res"),
				new ArrayList<String>(), "", replaceRes);

		// ��ȡexcl�����java�ļ�+��Դ�ļ�������
		Sheet[] sheets = ExcelUtils
				.getExcelSheets("C://Users//Carry//Desktop//test.xls");
		sheetExclNameList = new ArrayList<ArrayList<String>>();
		for (Sheet sheet : sheets) {
			exclNameList = ExcelUtils.getRowsInfo(sheet, 0);
			sheetExclNameList.add(exclNameList);
		}

		// ���жԱȣ���״̬д��ָ������һ����
		ArrayList<String> s = new ArrayList<>();
		s.addAll(javaFileNameList);
		s.addAll(resFileNameList);
		for (int i = 0; i < s.size(); i++) {
			String javaFileName = s.get(i);
			if (!exclNameList.contains(javaFileName)) {
				System.out.println(javaFileName + "---" + i);
			}
		}
	}
}
