package com.fan;

import com.mathworks.toolbox.javabuilder.MWException;
import testTry.FirstTest;

public class Test {

    public static void main(String[] args) {
        try {

            FirstTest test = new FirstTest();
            Object[] result = test.testTry(1, 120);
            // System.out.println(result[0]);
            for (Object o : result) {
                System.out.println(o);
            }
            //释放与此对象关联的本机资源
            test.dispose();
        } catch (MWException e) {
            e.printStackTrace();
        }
    }

}