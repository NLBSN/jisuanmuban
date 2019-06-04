package com.fan.matlab.SpectraExample.SpectraDemoJavaApp;/* powerspect.java
 * This file is used as an example for the MATLAB
 * Java Package product.
 *
 * Copyright 2001-2007 The MathWorks, Inc.
 */

/* Necessary package imports */
import com.mathworks.toolbox.javabuilder.*;
import spectralanalysis.*;

/*
 * powerspect class computes and plots the power
 * spectral density of an input signal.
 */
class powerspect
{
   public static void main(String[] args)
   {
      double interval = 0.01;     /* Sampling interval */
      int nSamples = 1001;        /* Number of samples */
      MWNumericArray data = null; /* Stores input data */
      Object[] result = null;     /* Stores result */
      fourier theFourier = null;  /* Fourier class instance */

      try
      {
         /*
          * Construct input data as sin(2*PI*15*t) + 
          * sin(2*PI*40*t) plus a random signal.
          *    Duration = 10
          *    Sampling interval = 0.01
          */
         int[] dims = {1, nSamples};
         data = MWNumericArray.newInstance(dims, MWClassID.DOUBLE,
                                              MWComplexity.REAL);
         for (int i = 1; i <= nSamples; i++)
         {
            double t = (i-1)*interval;
            double x = Math.sin(2.0*Math.PI*15.0*t) +
               Math.sin(2.0*Math.PI*40.0*t) + 
               Math.random();
            data.set(i, x);
         }

         /* Create new fourier object */
         theFourier = new fourier();
         theFourier.waitForFigures();

         /* Compute power spectral density and plot result */
         result = theFourier.plotfft(3, data, 
            new Double(interval));
      }

      catch (Exception e)
      {
         System.out.println("Exception: " + e.toString());
      }

      finally
      {
         /* Free native resources */
         MWArray.disposeArray(data);
         MWArray.disposeArray(result);
         if (theFourier != null)
            theFourier.dispose();
      }
   }
}
