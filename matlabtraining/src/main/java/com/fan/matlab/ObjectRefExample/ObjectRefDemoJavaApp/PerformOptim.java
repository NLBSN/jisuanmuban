package com.fan.matlab.ObjectRefExample.ObjectRefDemoJavaApp;/* PerformOptim.java
 * This file is used as an example for the MATLAB
 * Java Package product.
 *
 * Copyright 2001-2007 The MathWorks, Inc.
 */

/* Necessary package imports */
import com.mathworks.toolbox.javabuilder.*;
import OptimDemo.*;

/*
 * Demonstrates the use of the MWJavaObjectRef class
 * Takes initial point for optimization as two arguments:
 *    PerformOptim -1.2 1.0
 */
class PerformOptim
{
	public static void main(String[] args)
	{
		Optimizer theOptimizer = null;		/* Stores component instance */
		MWJavaObjectRef origRef = null;		/* Java object reference to be passed to component */
		MWJavaObjectRef outputRef = null;	/* Output data extracted from result */
		MWNumericArray x0 = null;	/* Initial point for optimization */
		MWNumericArray x = null;	/* Location of minimal value */
		MWNumericArray fval = null;	/* Minimal function value */
		Object[] result = null;	/* Stores the result */


		try
		{
			/* If no input, exit */
			if (args.length < 2)
			{
				System.out.println("Error: must input initial x0_1 and x0_2 position");
				return;
			}

			/* Instantiate a new Builder component object */
			/* This should only be done once per application instance */
			theOptimizer = new Optimizer();

			try {
				/* Initial point --- parse data from text fields */
				double[] x0Data = new double[2];
				x0Data[0] = Double.valueOf(args[0]).doubleValue();
				x0Data[1] = Double.valueOf(args[1]).doubleValue();
				x0 = new MWNumericArray(x0Data, MWClassID.DOUBLE);
				System.out.println("Using x0 =");
				System.out.println(x0);

				/* Create object reference to objective function object */
				BananaFunction objectiveFunction = new BananaFunction();
				origRef = new MWJavaObjectRef(objectiveFunction);

				/* Pass Java object to an M-function that lists its methods, etc */            
				System.out.println("*****************************************************");
				System.out.println("** Properties of Java object                       **");
				System.out.println("*****************************************************");
				result = theOptimizer.displayObj(1, origRef);     
				MWArray.disposeArray(result);
				System.out.println("** Finished DISPLAYOBJ ******************************");

				/* Call the Java component to optimize the function */
				/* using the MATLAB function FMINSEARCH */
				System.out.println("*****************************************************");
				System.out.println("** Performing unconstrained nonlinear optimization **");
				System.out.println("*****************************************************");
				result = theOptimizer.doOptim(2, origRef, x0);
				try {
					System.out.println("** Finished DOOPTIM *********************************");
					x = (MWNumericArray)result[0];
					fval = (MWNumericArray)result[1];

					/* Display the results of the optimization */
					System.out.println("Location of minimum: ");
					System.out.println(x);
					System.out.println("Function value at minimum: ");
					System.out.println(fval.toString());
				}
				finally
				{
					MWArray.disposeArray(result);
				}
			}
			finally
			{
				/* Free native resources */
				MWArray.disposeArray(origRef);
				MWArray.disposeArray(outputRef);
				MWArray.disposeArray(x0);
			}
		}
		catch (Exception e)
		{
			System.out.println("Exception: " + e.toString());
		}

		finally
		{
			/* Free native resources */
			if (theOptimizer != null)
				theOptimizer.dispose();
		}
	}
}
