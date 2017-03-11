package com.developbuildtools.utils;

import java.io.File;
import java.util.ArrayList;

public class FilesUtils {
	/**
	 * 获取指定路劲下所有文件的路径
	 * 
	 * @param url
	 */
	public static void getFilePathList(File url) {
		String files[] = url.list();
		for (String file : files) {
			File f = new File(url, file);
			// 判断得到的文件对象是否是文件路径
			if (f.isDirectory()) {
				System.out.println("这是文件路径:" + f);
				getFilePathList(f);
			}
		}
	}

	/**
	 * 获取指定路劲下所有文件的名字
	 * 
	 * @param url
	 */
	public static ArrayList<String> getFileNameList(File url,
			ArrayList<String> list, String key, String[] rep) {
		String files[] = url.list();
		if (files == null || files.length == 0) {
			return list;
		}
		for (String file : files) {
			File f = new File(url, file);

			if (f.isDirectory()) {
				getFileNameList(f, list, key, rep);
			}
			// 判断得到的是否是文件
			if (f.isFile()) {
				if (key.equals("") || f.getName().contains(key)) {
//					System.out.println("这是文件:" + f.getName());
					String fileName = f.getName();
					// 更具string[] 删除掉
					if (rep != null) {
						for (int i = 0; i < rep.length; i++) {
							fileName = fileName.replace(rep[i], "");
						}
//						System.out.println(fileName);
					}
					list.add(fileName);
				}
			}
		}
		return list;
	}
}
