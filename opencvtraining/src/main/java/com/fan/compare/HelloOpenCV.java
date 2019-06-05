package com.fan.compare;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

/**
 * @description 找出两张图片的不同点
 * -Djava.library.path=C:\bao\opencv\opencv\build\java\x64
 */
public class HelloOpenCV {
    public static void main(String[] args) {
        System.out.println("Hello, OpenCV");

        // new DetectFaceDemo().run();

        // TODO Auto-generated method stub
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Mat img1 = Imgcodecs.imread("./data/bidui-01.png");
        Mat img2 = Imgcodecs.imread("./data/bidui-02.png");
         // 图像做差：Core.absdiff(Mat src1, Mat src2, Mat dst)
        Mat img = new Mat();
        //像素做差
        Core.absdiff(img1, img2, img);
        Imgcodecs.imwrite("./data/bidui-03.png", img);
    }

    static {
        // 与我们的程序逻辑无关，但为了运行必须加上，否则会报错java.lang.UnsatisfiedLinkError；
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        // 两种添加变量的方式，第一种是执行时加参数，第二种是，直接将dll文件放到jdk下面
        // -Djava.library.path=$PROJECT_DIR$\opencv\x64
        // -Djava.library.path=C:\bao\opencv\opencv\build\java\x64
        // 在安装好的目录下找到\build\java\x64（64位系统）的dll文件并放在jdk配置目录bin文件夹下，同时在项目中引入\build\java\下的jar
    }
}