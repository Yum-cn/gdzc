package org.qihuasoft.core.util.reader;

import java.io.*;
import java.util.List;

import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;

public class WordReader {

	/**
	 * @param filePath
	 *            文件路径
	 * @return 读出的Word的内容
	 */
	public String getTextFromWord(String filePath) {
		String result = null;
		File file = new File(filePath);
		try {
			FileInputStream fis = new FileInputStream(file);
			WordExtractor wordExtractor = new WordExtractor(fis);
			result = wordExtractor.getText();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		;
		return result;
	}

	public String getTextFromWord2007(String filePath) {
		File file = new File(filePath);
		try {
			FileInputStream fis = new FileInputStream(file);
			XWPFDocument document = new XWPFDocument(fis);
			XWPFWordExtractor xwpfWordExtractor = new XWPFWordExtractor(
					document);
			String text = xwpfWordExtractor.getText();
			fis.close();
			return text;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
}
