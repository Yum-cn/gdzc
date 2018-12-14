package org.qihuasoft.core.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class DimensionCode {
	public static void encoderCode(String contents, HttpServletResponse response,File file) {

		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET, "GBK");
		hints.put(EncodeHintType.MARGIN,0);
		BitMatrix matrix = null;
		try {
			matrix = new MultiFormatWriter().encode(contents,
				BarcodeFormat.QR_CODE, 126, 126, hints);
		} catch (WriterException e) {
			e.printStackTrace();
		}
		//File file = new File("D://qrcodeImage.png");
		try {
			MatrixToImageWriter.writeToFile(matrix, "png", file);
//			MatrixToImageWriter.writeToStream(matrix, "png", response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**
     * @为图片添加文字
     * @param pressText 文字
     * @param newImg    带文字的图片
     * @param targetImg 需要添加文字的图片
     * @param fontStyle 
     * @param color
     * @param fontSize
     * @param width
     * @param heigh
     */
    public static void pressText(String pressText, String newImg, String targetImg, int fontStyle, Color color, int fontSize, int width, int height) {
        
        //计算文字开始的位置
        //x开始的位置：（图片宽度-字体大小*字的个数）/2
        int startX = (width-(fontSize*pressText.length()))/2;
        //y开始的位置：图片高度-（图片高度-图片宽度）/2
        int startY = height-(height-width)/2;        
        
        try {
            File file = new File(targetImg);
            Image src = ImageIO.read(file);
            int imageW = src.getWidth(null);
            int imageH = src.getHeight(null);
            BufferedImage image = new BufferedImage(imageW, imageH, BufferedImage.TYPE_INT_RGB);
            Graphics g = image.createGraphics();
            g.drawImage(src, 0, 0, imageW, imageH, null);
            g.setColor(color);
            g.setFont(new Font("宋体", Font.PLAIN  , fontSize));
            g.drawString(pressText, startX-40, startY);
            g.dispose();

            FileOutputStream out = new FileOutputStream(newImg);
            ImageIO.write(image, "JPEG", out);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(image);
            out.close();
            System.out.println("image press success");
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
