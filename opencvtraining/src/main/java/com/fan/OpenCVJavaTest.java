package com.fan;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

/**
 * @dsecription 创建一个矩阵
 * -Djava.library.path=C:\bao\opencv\opencv\build\java\x64
 */
public class OpenCVJavaTest {

    static {
        // 两种添加变量的方式，第一种是执行时加参数，第二种是，直接将dll文件放到jdk下面
        // -Djava.library.path=$PROJECT_DIR$\opencv\x64
        // -Djava.library.path=C:\bao\opencv\opencv\build\java\x64
        // 在安装好的目录下找到\build\java\x64（64位系统）的dll文件并放在jdk配置目录bin文件夹下，同时在项目中引入\build\java\下的jar
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) {
        System.out.println("Welcome to OpenCV " + Core.VERSION);
        Mat m = new Mat(5, 10, CvType.CV_8UC1, new Scalar(0));
        System.out.println("OpenCV Mat: " + m);
        Mat mr1 = m.row(1);
        mr1.setTo(new Scalar(1));
        Mat mc5 = m.col(5);
        mc5.setTo(new Scalar(5));
        System.out.println("OpenCV Mat data:\n" + m.dump());
    }

}