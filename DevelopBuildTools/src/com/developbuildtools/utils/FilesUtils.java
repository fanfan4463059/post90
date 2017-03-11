package com.developbuildtools.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import javax.swing.filechooser.FileSystemView;

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
					// System.out.println("�����ļ�:" + f.getName());
					String fileName = f.getName();
					// ����string[] ɾ����
					if (rep != null) {
						for (int i = 0; i < rep.length; i++) {
							fileName = fileName.replace(rep[i], "");
						}
						// System.out.println(fileName);
					}
					list.add(fileName);
				}
			}
		}
		return list;
	}

	/**
	 * ���ܣ�Java��ȡtxt�ļ������� ���裺1���Ȼ���ļ���� 2������ļ��������������һ���ֽ���������Ҫ��������������ж�ȡ
	 * 3����ȡ������������Ҫ��ȡ�����ֽ��� 4��һ��һ�е������readline()�� ��ע����Ҫ���ǵ����쳣���
	 * 
	 * @param filePath
	 */
	public static String readTxtFile(String filePath) {
		StringBuffer jsonStrBuffer = new StringBuffer();
		try {
			String encoding = "UTF8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // �ж��ļ��Ƿ����
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);// ���ǵ������ʽ
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					jsonStrBuffer.append(lineTxt);
				}
				read.close();
				return jsonStrBuffer.toString();
			} else {
				System.out.println("�Ҳ���ָ�����ļ�");
				return "";
			}
		} catch (Exception e) {
			System.out.println("��ȡ�ļ����ݳ���");
			e.printStackTrace();
			return "";
		}

	}

	/**
	 * �����ļ�
	 * 
	 * @param content
	 * @param fileName
	 */
	@SuppressWarnings("resource")
	public static void makeStr(String content, String fileName, String path) {
		File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
		String desktopPath = desktopDir.getAbsolutePath();

		try {
			// û���ļ��еĳ��ϴ���
			File filepath = new File(desktopPath + "\\" + path);
			filepath.mkdirs();
			// �����ļ�����
			File file = new File(desktopPath + "\\" + path + "\\" + fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(desktopPath + "\\"
					+ path + "\\" + fileName);
			OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
			osw.write(content);
			osw.flush();

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��ȡ�����ַ
	 * 
	 * @return
	 */
	public static String getHomePath() {
		File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
		return desktopDir.getAbsolutePath();
	}
}
