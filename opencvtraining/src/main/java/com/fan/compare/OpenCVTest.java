package com.fan.compare;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class OpenCVTest {

    public static void main(String[] args) {
        // 载入dll（必须先加载）
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat source, template;
        //将文件读入为OpenCV的Mat格式
        source = Imgcodecs.imread("/data/bidui-01.png");
        template = Imgcodecs.imread("/data/bidui-02.png");
        // 创建于原图相同的大小，储存匹配度
        Mat result = new Mat();
        //调用模板匹配方法
        Imgproc.matchTemplate(source, template, result, Imgproc.TM_SQDIFF);
        //规格化
        Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1);
        //获得最可能点，MinMaxLocResult是其数据格式，包括了最大、最小点的位置x、y
        Core.MinMaxLocResult mlr = Core.minMaxLoc(result);
        Point matchLoc = mlr.minLoc;
        //mlr.minVal越小标示匹配度越高比如0.0标示匹配到
        System.out.println(mlr.minVal);
        //在原图上的对应模板可能位置画一个绿色矩形
        Imgproc.rectangle(source, matchLoc, new Point(matchLoc.x + template.width(), matchLoc.y + template.height()), new Scalar(0, 255, 0));
        // 将结果输出到对应位置
        Imgcodecs.imwrite("/data/bidui-03.png", source);

    }
}