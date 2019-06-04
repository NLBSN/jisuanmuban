package com.fan.matlab.MatrixMathExample.MatrixMathDemoJavaApp;/* getfactor.java
 * This file is used as an example for the MATLAB
 * Java Package product.
 *
 * Copyright 2001-2007 The MathWorks, Inc.
 */

/* Necessary package imports */
import com.mathworks.toolbox.javabuilder.*;
import factormatrix.*;

/*
 * getfactor class computes cholesky, LU, and QR 
 * factorizations of a finite difference matrix
 * of order N. The value of N is passed on the
 * command line. If a second command line arg
 * is passed with the value of "sparse", then
 * a sparse matrix is used.
 */
class getfactor
{
   public static void main(String[] args)
   {
      MWNumericArray a = null;   /* Stores matrix to factor */
      Object[] result = null;    /* Stores the result */
      factor theFactor = null;   /* Stores factor class instance */

      try
      {
         /* If no input, exit */
         if (args.length == 0)
         {
            System.out.println("Error: must input a positive integer");
            return;
         }

         /* Convert input value */
         int n = Integer.valueOf(args[0]).intValue();

         if (n <= 0)
         {
            System.out.println("Error: must input a positive integer");
            return;
         }

         /*
          * Allocate matrix. If second input is "sparse"
          * allocate a sparse array 
          */
         int[] dims = {n, n};

         if (args.length > 1 && args[1].equals("sparse"))
            a = MWNumericArray.newSparse(dims[0], dims[1],n+2*(n-1), MWClassID.DOUBLE, MWComplexity.REAL);
         else
            a = MWNumericArray.newInstance(dims,MWClassID.DOUBLE, MWComplexity.REAL);

         /* Set matrix values */
         int[] index = {1, 1};

         for (index[0] = 1; index[0] <= dims[0]; index[0]++)
         {
            for (index[1] = 1; index[1] <= dims[1]; index[1]++)
            {
               if (index[1] == index[0])
                  a.set(index, 2.0);
               else if (index[1] == index[0]+1 || index[1] == index[0]-1)
                  a.set(index, -1.0);
            }
         }

         /* Create new factor object */
         theFactor = new factor();

         /* Print original matrix */
         System.out.println("Original matrix:");
         System.out.println(a);

         /* Compute cholesky factorization and print results. */
         result = theFactor.cholesky(1, a);
         System.out.println("Cholesky factorization:");
         System.out.println(result[0]);
         MWArray.disposeArray(result);

         /* Compute LU factorization and print results. */
         result = theFactor.ludecomp(2, a);
         System.out.println("LU factorization:");
         System.out.println("L matrix:");
         System.out.println(result[0]);
         System.out.println("U matrix:");
         System.out.println(result[1]);
         MWArray.disposeArray(result);

         /* Compute QR factorization and print results. */
         result = theFactor.qrdecomp(2, a);
         System.out.println("QR factorization:");
         System.out.println("Q matrix:");
         System.out.println(result[0]);
         System.out.println("R matrix:");
         System.out.println(result[1]);
      }

      catch (Exception e)
      {
         System.out.println("Exception: " + e.toString());
      }

      finally
      {
         /* Free native resources */
         MWArray.disposeArray(a);
         MWArray.disposeArray(result);
         if (theFactor != null)
            theFactor.dispose();
      }
   }
}
