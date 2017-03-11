package com.developbuildtools;

import java.io.IOException;

import jxl.read.biff.BiffException;

import com.developbuildtools.files.FilesBooleanCheckTools;
import com.developbuildtools.files.json2model.JsonToFileTools;

public class RunningMain {
	public static void main(String[] args) throws BiffException, IOException {
		// 实现文档校验，判断工程的文件（是否存在）
		// FilesBooleanCheckTools filesBooleanCheckTools = new
		// FilesBooleanCheckTools();
		// filesBooleanCheckTools.doingFunction();

		// 根据json直接生成 model文件+数据转换到UI的文件
		JsonToFileTools jsonToFileTools = new JsonToFileTools();
		jsonToFileTools.doingFunction();
		
	}
}
