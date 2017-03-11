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
					// System.out.println("这是文件:" + f.getName());
					String fileName = f.getName();
					// 更具string[] 删除掉
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
	 * 功能：Java读取txt文件的内容 步骤：1：先获得文件句柄 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
	 * 3：读取到输入流后，需要读取生成字节流 4：一行一行的输出。readline()。 备注：需要考虑的是异常情况
	 * 
	 * @param filePath
	 */
	public static String readTxtFile(String filePath) {
		StringBuffer jsonStrBuffer = new StringBuffer();
		try {
			String encoding = "UTF8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					jsonStrBuffer.append(lineTxt);
				}
				read.close();
				return jsonStrBuffer.toString();
			} else {
				System.out.println("找不到指定的文件");
				return "";
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
			return "";
		}

	}

	/**
	 * 生成文件
	 * 
	 * @param content
	 * @param fileName
	 */
	@SuppressWarnings("resource")
	public static void makeStr(String content, String fileName, String path) {
		File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
		String desktopPath = desktopDir.getAbsolutePath();

		try {
			// 没有文件夹的场合创建
			File filepath = new File(desktopPath + "\\" + path);
			filepath.mkdirs();
			// 创建文件场合
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
	 * 获取桌面地址
	 * 
	 * @return
	 */
	public static String getHomePath() {
		File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
		return desktopDir.getAbsolutePath();
	}
}
