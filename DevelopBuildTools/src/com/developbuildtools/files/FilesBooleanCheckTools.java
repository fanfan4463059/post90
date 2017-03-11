package com.developbuildtools.files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import jxl.Sheet;
import jxl.read.biff.BiffException;

import com.developbuildtools.utils.ExcelUtils;
import com.developbuildtools.utils.FilesUtils;

/**
 * 实现文档校验，判断工程的文件（是否存在）
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
	 * 执行方法
	 * 
	 * @throws BiffException
	 * @throws IOException
	 */
	public void doingFunction() throws BiffException, IOException {
		// 获取src下 java文件名称的list
		String[] replaceJava = { ".java" };
		javaFileNameList = FilesUtils.getFileNameList(new File(
				"H://git_src//save_scr//wacth//src_space//TwsApiDemos//src"),
				new ArrayList<String>(), ".java", replaceJava);

		// 获取资源文件下 文件名称的list
		String[] replaceRes = { ".xml", ".png" };
		resFileNameList = FilesUtils.getFileNameList(new File(
				"H://git_src//save_scr//wacth//src_space//TwsApiDemos//res"),
				new ArrayList<String>(), "", replaceRes);

		// 获取excl表格中java文件+资源文件的名称
		Sheet[] sheets = ExcelUtils
				.getExcelSheets("C://Users//Carry//Desktop//test.xls");
		sheetExclNameList = new ArrayList<ArrayList<String>>();
		for (Sheet sheet : sheets) {
			exclNameList = ExcelUtils.getRowsInfo(sheet, 0);
			sheetExclNameList.add(exclNameList);
		}

		// 进行对比，将状态写入指定的那一列中
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
