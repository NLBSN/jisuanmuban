package com.fan.matlab.MagicSquareExample.MagicDemoJavaApp;/* getmagic.java
 * This file is used as an example for the MATLAB
 * Java Package product.
 *
 * Copyright 2001-2007 The MathWorks, Inc.
 */

/* Necessary package imports */
import com.mathworks.toolbox.javabuilder.*;
import magicsquare.*;

/*
 * getmagic class computes a magic square of order N. The
 * positive integer N is passed on the command line.
 */
class getmagic
{
   public static void main(String[] args)
   {
      MWNumericArray n = null;   /* Stores input value */
      Object[] result = null;    /* Stores the result */
      magic theMagic = null;     /* Stores magic class instance */

      try
      {
         /* If no input, exit */
         if (args.length == 0)
         {
            System.out.println("Error: must input a positive integer");
            return;
         }

         /* Convert and print input value*/
         n = new MWNumericArray(Double.valueOf(args[0]),MWClassID.DOUBLE);

         System.out.println("Magic square of order " + n.toString());

         /* Create new magic object */
         theMagic = new magic();

         /* Compute magic square and print result */
         result = theMagic.makesqr(1, n);
         System.out.println(result[0]);
      }
      catch (Exception e)
      {
         System.out.println("Exception: " + e.toString());
      }

      finally
      {
         /* Free native resources */
         MWArray.disposeArray(n);
         MWArray.disposeArray(result);
         if (theMagic != null)
            theMagic.dispose();
      }
   }
}
