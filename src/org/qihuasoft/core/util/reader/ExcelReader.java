package org.qihuasoft.core.util.reader;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ExcelReader {

	@SuppressWarnings("deprecation")
	/**  
	 * @param filePath 文件路径  
	 * @return 读出的Excel的内容  
	 */
	public String getTextFromExcel(String filePath) {
		StringBuffer buff = new StringBuffer();
		try {
			// 创建对Excel工作簿文件的引用
			HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(filePath));
			// 创建对工作表的引用。
			for (int numSheets = 0; numSheets < wb.getNumberOfSheets(); numSheets++) {
				if (null != wb.getSheetAt(numSheets)) {
					HSSFSheet aSheet = wb.getSheetAt(numSheets);// 获得一个sheet
					for (int rowNumOfSheet = 0; rowNumOfSheet <= aSheet
							.getLastRowNum(); rowNumOfSheet++) {
						if (null != aSheet.getRow(rowNumOfSheet)) {
							HSSFRow aRow = aSheet.getRow(rowNumOfSheet); // 获得一个行
							for (int cellNumOfRow = 0; cellNumOfRow <= aRow
									.getLastCellNum(); cellNumOfRow++) {
								if (null != aRow.getCell(cellNumOfRow)) {
									HSSFCell aCell = aRow.getCell(cellNumOfRow);// 获得列值
									switch (aCell.getCellType()) {
									case HSSFCell.CELL_TYPE_FORMULA:
										break;
									case HSSFCell.CELL_TYPE_NUMERIC:
										buff.append(aCell.getNumericCellValue())
												.append('\t');
										break;
									case HSSFCell.CELL_TYPE_STRING:
										buff.append(aCell.getStringCellValue())
												.append('\t');
										break;
									}
								}
							}
							buff.append('\n');
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buff.toString();
	}

	public String getTextFromExcel2007(String filePath) {
		try {
			StringBuffer buff = new StringBuffer();
			FileInputStream input = new FileInputStream(new File(filePath)); // 读取的文件路径
			XSSFWorkbook wb = new XSSFWorkbook(new BufferedInputStream(input));

			int sheet_numbers = wb.getNumberOfSheets();// 获取表的总数
			String[] sheetnames = new String[sheet_numbers];

			for (int i = 0; i < sheet_numbers; i++) {// 遍历所有表
				XSSFSheet sheet = wb.getSheetAt(i); // 获取 某个表
				sheetnames[i] = sheet.getSheetName();// 获取表名，存入数组
				System.out.println("------>>>---正在读取Excel表数据，当前表："
						+ sheetnames[i]);
				
				for (int rows = 0; rows < sheet.getLastRowNum(); rows++) {// 有多少行
					XSSFRow row = sheet.getRow(rows);// 取得某一行 对象
					String[] s = new String[5];// 初始化数组长度
					if(row!=null){
					for (int columns = 0; columns < row.getLastCellNum(); columns++) {// 读取所有列
						// s[columns] =
						// row.getCell(columns).getStringCellValue();
						// //取得某单元格内容，字符串类型
						XSSFCell cell = row.getCell(columns);
						if (cell != null) {
							switch (cell.getCellType()) {
							case XSSFCell.CELL_TYPE_STRING: // 字符串
								buff.append(cell.getStringCellValue());
								break;
							case XSSFCell.CELL_TYPE_NUMERIC: // 数字
								buff.append(cell.getNumericCellValue());
								break;
							case XSSFCell.CELL_TYPE_BLANK: // 空值
								
								break;
							default:
								System.out.print("\n---单元格格式不支持---");
								break;
							}
						}
					}
					}
				}
			}
			
			return buff.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "";
	}

}