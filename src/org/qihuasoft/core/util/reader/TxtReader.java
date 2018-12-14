package org.qihuasoft.core.util.reader;

import java.io.*;

public class TxtReader {
	public TxtReader() {
	}

	/**
	 * @param filePath
	 *            文件路径
	 * @return 读出的txt的内容
	 */
	public String getTextFromTxt(String filePath) throws Exception {

		FileReader fr = new FileReader(filePath);
		BufferedReader br = new BufferedReader(fr);
		StringBuffer buff = new StringBuffer();
		String temp = null;
		while ((temp = br.readLine()) != null) {
			buff.append(temp + "\r\n");
		}
		br.close();
		return buff.toString();
	}
}