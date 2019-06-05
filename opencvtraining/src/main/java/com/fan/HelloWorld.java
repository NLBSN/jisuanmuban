package com.fan;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 * @description -Djava.library.path=C:\bao\opencv\opencv\build\java\x64
 */
public class HelloWorld {
    public static void main(String[] args) {
        // 很明显是一个读取硬盘中图片文件的工作，其中Mat类是OpenCV中的一个基础类，本身还可以与Java中常用的图片格式进行相互转换，其本质实际是一个矩阵（图片其实就是矩阵啦）；
        Mat img = Imgcodecs.imread("./data/lt01.jpg");
        // 是一个将原彩色图转为灰度图的工作，对于二值化来说是必要的；
        Imgproc.cvtColor(img, img, Imgproc.COLOR_RGB2GRAY);
        // 实际就是二值化了，调用的是Imgproc静态类的一个方法，与第三行一样，里面的各个参数将会在以后去了解；
        Imgproc.adaptiveThreshold(img, img, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY_INV, 25, 10);
        // 将二值化后的图片写回硬盘。如果不理解二值化是什么意思，看一下图片大概就明白啦
        Imgcodecs.imwrite("./data/lt01_01.jpg", img);
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
