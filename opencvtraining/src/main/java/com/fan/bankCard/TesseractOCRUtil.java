package com.fan.bankCard;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Created by zhangwenchao on 2017/9/28.
 * https://www.cnblogs.com/jianqingwang/p/6978724.html
 */
public class TesseractOCRUtil {

    private static final String LANG_OPTION = "-l";
    private static final String EOL = System.getProperty("line.separator");

    /**
     * @param imageFile 传入的图像文件
     * @return 识别后的字符串
     */
    public static String recognizeText(File imageFile) throws Exception {
        // 设置输出文件的保存的文件目录
        File outputFile = new File(imageFile.getParentFile(), "output");

        StringBuffer strB = new StringBuffer();
        // https://digi.bib.uni-mannheim.de/tesseract/
        Process pro = Runtime.getRuntime().exec(new String[]{"C:\\Program Files\\Tesseract-OCR\\tesseract.exe", imageFile.getPath(), outputFile.getPath()});
        int w = pro.waitFor();
        if (w == 0) // 0代表正常退出
        {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(outputFile.getAbsolutePath() + ".txt"), "UTF-8"));
            String str;

            while ((str = in.readLine()) != null) {
                strB.append(str).append(EOL);
            }
            in.close();
        } else {
            String msg;
            switch (w) {
                case 1:
                    msg = "Errors accessing files. There may be spaces in your image's filename.";
                    break;
                case 29:
                    msg = "Cannot recognize the image or its selected region.";
                    break;
                case 31:
                    msg = "Unsupported image format.";
                    break;
                default:
                    msg = "Errors occurred.";
            }
            throw new RuntimeException(msg);
        }
        new File(outputFile.getAbsolutePath() + ".txt").delete();
        return strB.toString().replaceAll("\\s*", "");


    }

    public static void main(String[] args) {
        try {
            String result = recognizeText(new File("E:/resImage.jpg"));
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
 