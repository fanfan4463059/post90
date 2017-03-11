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
 * ����excl���ͨ�÷���
 * 
 * @author Carry
 * 
 */
public class ExcelUtils {

	/**
	 * ��ȡָ��excel�����б��
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
		// ��ȡ��һ��Sheet��
		Sheet[] sheets = workbook.getSheets();
		return sheets;
	}

	/**
	 * ��ȡָ������������
	 * 
	 * @param sheet
	 * @param x
	 * @return
	 */
	@SuppressWarnings("null")
	public static ArrayList<String> getRowsInfo(Sheet sheet, int x) {
		ArrayList<String> list = new ArrayList<>();
		int[] size = getSheetSize(sheet);
		// ��ȡ�����
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
	 * ��ȡ�����ָ��x y��λ��ֵ
	 * 
	 * @return
	 */
	public static String getSheetItemInfo(Sheet sheet, int x, int y) {
		// ��ȡitem��������Ϣ
		Cell cell = sheet.getCell(x, y);
		String info = cell.getContents();
		return info;
	}

	/**
	 * ��ȡ���max x��y
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
