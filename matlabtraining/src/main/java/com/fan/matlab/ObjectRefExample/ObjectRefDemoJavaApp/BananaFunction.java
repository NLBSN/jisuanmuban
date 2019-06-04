package com.fan.matlab.ObjectRefExample.ObjectRefDemoJavaApp;/* BananaFunction.java
 * This file is used as an example for the MATLAB
 * Java Package product.
 *
 * Copyright 2001-2007 The MathWorks, Inc.
 */

public class BananaFunction {
	public BananaFunction() {}
	public double evaluateFunction(double[] x)
	{
		/* Implements the Rosenbrock banana function described in 
		 * the FMINSEARCH documentation
		 */
		double term1 = 100* Math.pow((x[1]-Math.pow(x[0],2.0)),2.0);
		double term2 =  Math.pow((1-x[0]),2.0);
		return term1 + term2;
	}

}
