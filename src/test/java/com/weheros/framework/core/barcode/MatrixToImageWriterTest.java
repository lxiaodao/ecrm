/**
 * 
 */
package com.weheros.framework.core.barcode;

import java.io.File;
import java.util.Hashtable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

/**
 * @author liuy
 *
 */
public class MatrixToImageWriterTest {
	
	
	
	  /** 
     * @param args 
     * @throws Exception  
     */  
    public static void main(String[] args) throws Exception {  
        String text = "http://cn.bing.com/search?q=pom+zxing+maven&qs=n&form=QBRE&sp=-1&pq=pom+zxing+maven&sc=0-15&sk=&cvid=A99480FA727041CEA8EE0B4F34D2E691";  
        int width = 300;  
        int height = 300;  
        //二维码的图片格式  
        String format = "gif";  
        Hashtable hints = new Hashtable();  
        //内容所使用编码  
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");  
        BitMatrix bitMatrix = new MultiFormatWriter().encode(text,  
                BarcodeFormat.QR_CODE, width, height, hints);  
        //生成二维码  
        File outputFile = new File("d:"+File.separator+"newbarcode.gif");  
        MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);  
        System.out.println("---生成成功---");
  
    }  

}
