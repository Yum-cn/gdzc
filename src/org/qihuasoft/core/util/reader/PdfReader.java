package org.qihuasoft.core.util.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;


public class PdfReader {
	public PdfReader() {
	}

	/**
	 * @param filePath
	 *            文件路径
	 * @return 读出的pdf的内容
	 */
	public String getTextFromPdf(String filePath) {
		String result = null;
		File is = null;
		PDDocument document = null;
		try {
			is = new File(filePath);
//			PDDocument document=PDDocument.load(pdfFile)
//			PDFParser parser = new PDFParser((RandomAccessRead) is, "");
//			parser.parse();
//			document = parser.getPDDocument();
			document=PDDocument.load(is);
			PDFTextStripper stripper = new PDFTextStripper();
			result = stripper.getText(document);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
//			if (is != null) {
//				try {
//					is.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
			if (document != null) {
				try {
					document.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

}