package com.developbuildtools.utils;

import java.io.File;
import java.util.ArrayList;

public class FilesUtils {
	/**
	 * ��ȡָ��·���������ļ���·��
	 * 
	 * @param url
	 */
	public static void getFilePathList(File url) {
		String files[] = url.list();
		for (String file : files) {
			File f = new File(url, file);
			// �жϵõ����ļ������Ƿ����ļ�·��
			if (f.isDirectory()) {
				System.out.println("�����ļ�·��:" + f);
				getFilePathList(f);
			}
		}
	}

	/**
	 * ��ȡָ��·���������ļ�������
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
			// �жϵõ����Ƿ����ļ�
			if (f.isFile()) {
				if (key.equals("") || f.getName().contains(key)) {
//					System.out.println("�����ļ�:" + f.getName());
					String fileName = f.getName();
					// ����string[] ɾ����
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
