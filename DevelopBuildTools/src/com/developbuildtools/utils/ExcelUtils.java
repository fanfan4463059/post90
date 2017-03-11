package com.developbuildtools.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * 关于excl相关通用方法
 * 
 * @author Carry
 * 
 */
public class ExcelUtils {

	/**
	 * 获取指定excel中所有表格
	 * 
	 * @return
	 * @throws IOException
	 * @throws BiffException
	 */
	public static Sheet[] getExcelSheets(String path) throws BiffException,
			IOException {
		File file = new File(path);
		InputStream in = new FileInputStream(file);
		Workbook workbook = Workbook.getWorkbook(in);
		// 获取第一张Sheet表
		Sheet[] sheets = workbook.getSheets();
		return sheets;
	}

	/**
	 * 获取指定列所有数据
	 * 
	 * @param sheet
	 * @param x
	 * @return
	 */
	@SuppressWarnings("null")
	public static ArrayList<String> getRowsInfo(Sheet sheet, int x) {
		ArrayList<String> list = new ArrayList<>();
		int[] size = getSheetSize(sheet);
		// 获取长或宽
		int rows = size[1];
		for (int j = 0; j < rows; j++) {
			String info = getSheetItemInfo(sheet, x, j);
			if (info != null || !info.equals("")) {
				list.add(info);
			}
		}
		return list;
	}

	/**
	 * 获取表格中指定x y定位的值
	 * 
	 * @return
	 */
	public static String getSheetItemInfo(Sheet sheet, int x, int y) {
		// 获取item的所有信息
		Cell cell = sheet.getCell(x, y);
		String info = cell.getContents();
		return info;
	}

	/**
	 * 获取表格max x，y
	 * 
	 * @param sheet
	 * @return
	 */
	public static int[] getSheetSize(Sheet sheet) {
		int x_size, y_size;
		x_size = sheet.getColumns();
		y_size = sheet.getRows();
		int[] size = { x_size, y_size };
		return size;
	}
}
