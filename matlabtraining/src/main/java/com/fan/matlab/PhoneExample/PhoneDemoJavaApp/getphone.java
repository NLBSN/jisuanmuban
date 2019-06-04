package com.fan.matlab.PhoneExample.PhoneDemoJavaApp;/* getphone.java
 %   This file is used as an example for MATLAB
 %   Builder for Java.
 *
 * Copyright 2001-2007 The MathWorks, Inc.
 */

/* Necessary package imports */
import com.mathworks.toolbox.javabuilder.*;

import phonebookdemo.*;

/*
 * getphone class demonstrates the use of the MWStructArray class
 */
class getphone
{
    public static void main(String[] args)
    {
        phonebook thePhonebook = null;     /* Stores magic class instance */
        MWStructArray friends = null; /* Sample input data */
        Object[] result = null;    /* Stores the result */
        MWStructArray book = null; /* Output data extracted from result */

        try
        {
            /* Create new magic object */
            thePhonebook = new phonebook();

            /* Create an MWStructArray with two fields */
            String[] myFieldNames = {"name", "phone"};
            friends = new MWStructArray(2,2,myFieldNames);

            /* Populate struct with some sample data --- friends and phone numbers */
            friends.set("name",1,new MWCharArray("Jordan Robert"));
            friends.set("phone",1,3386);
            friends.set("name",2,new MWCharArray("Mary Smith"));
            friends.set("phone",2,3912);
            friends.set("name",3,new MWCharArray("Stacy Flora"));
            friends.set("phone",3,3238);
            friends.set("name",4,new MWCharArray("Harry Alpert"));
            friends.set("phone",4,3077);

            /* Show some of the sample data */
            System.out.println("Friends: ");
            System.out.println(friends.toString());

            /* Pass it to an M-function that determines external phone number */
            result = thePhonebook.makephone(1, friends);
            book = (MWStructArray)result[0];
            System.out.println("Result: ");
            System.out.println(book.toString());

            /* Extract some data from the returned structure */
            System.out.println("Result record 2:");
            System.out.println(book.getField("name",2));
            System.out.println(book.getField("phone",2));
            System.out.println(book.getField("external",2));

            /* Print the entire result structure using the helper function below */
            System.out.println("");
            System.out.println("Entire structure:");
            dispStruct(book);
        }
        catch (Exception e)
        {
            System.out.println("Exception: " + e.toString());
        }

        finally
        {
            /* Free native resources */
            MWArray.disposeArray(result);
            MWArray.disposeArray(friends);
            MWArray.disposeArray(book);
            if (thePhonebook != null)
                thePhonebook.dispose();
        }
    }

    public static void dispStruct(MWStructArray arr) {
    	System.out.println("Number of Elements: " + arr.numberOfElements());
    	//int numDims = arr.numberOfDimensions();
    	int[] dims = arr.getDimensions();
    	System.out.print("Dimensions: " + dims[0]);
    	for (int i = 1; i < dims.length; i++)
    	{
    		System.out.print("-by-" + dims[i]);
    	}
    	System.out.println("");
    	System.out.println("Number of Fields: " + arr.numberOfFields());
    	System.out.println("Standard MATLAB view:");
    	System.out.println(arr.toString());
    	System.out.println("Walking structure:");
        String[] fieldNames = arr.fieldNames();
        for (int element = 1; element <= arr.numberOfElements(); element++) {
            System.out.println("Element " + element);
            for (int field = 0; field < arr.numberOfFields(); field++) {
            	MWArray fieldVal = arr.getField(fieldNames[field], element);
            	/* Recursively print substructures, give string display of other classes */
            	if (fieldVal instanceof MWStructArray)
            	{
                	System.out.println("   " + fieldNames[field] + ": nested structure:");
                	System.out.println("+++ Begin of \"" + fieldNames[field] + "\" nested structure");
            		dispStruct((MWStructArray)fieldVal);
                	System.out.println("+++ End of \"" + fieldNames[field] + "\" nested structure");
            	} else {
                	System.out.print("   " + fieldNames[field] + ": ");
                    System.out.println(fieldVal.toString());
            	}
            }
        }
    }
}