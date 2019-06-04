package com.fan.matlab.PlotDemoJavaApp;
/* Createplot.java
 * This file is used as an example for the MATLAB
 * Java Package product.
 *
 * Copyright 2001-2007 The MathWorks, Inc.
 */

/* Necessary package imports */

import com.mathworks.toolbox.javabuilder.*;
import drawplot.Plotter;

/*
 * createplot class demonstrates plotting x-y data into
 * a MATLAB figure window by graphing a simple parabola.
 */
class Createplot {
    public static void main(String[] args) {
        MWNumericArray x = null;   /* Array of x values */
        MWNumericArray y = null;   /* Array of y values */
        Plotter thePlot = null;    /* Plotter class instance */
        int n = 20;                /* Number of points to plot */

        try {
            /* Allocate arrays for x and y values */
            int[] dims = {1, n};
            x = MWNumericArray.newInstance(dims, MWClassID.DOUBLE, MWComplexity.REAL);
            y = MWNumericArray.newInstance(dims, MWClassID.DOUBLE, MWComplexity.REAL);

            /* Set values so that y = x^2 */
            for (int i = 1; i <= n; i++) {
                x.set(i, i);
                y.set(i, i * i);
            }

            /* Create new plotter object */
            thePlot = new Plotter();

            /* Plot data */
            thePlot.drawplot(x, y);
            thePlot.waitForFigures();
        } catch (Exception e) {
            System.out.println("Exception: " + e.toString());
        } finally {
            /* Free native resources */
            MWArray.disposeArray(x);
            MWArray.disposeArray(y);
            if (thePlot != null)
                thePlot.dispose();
        }
    }
}
