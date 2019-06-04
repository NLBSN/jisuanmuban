package com.fan.matlab.RMIExamples.MagicSquare.MagicSquareDemoJavaApp;

import com.mathworks.toolbox.javabuilder.*;
import magiccomp.*;
import java.rmi.*;
import java.rmi.registry.*;

public class magicclient
{
    public static void main(String[] args)
    {
        if (args.length == 0)
        {
            System.out.println("Error: must input a positive integer\n");
            return;
        }

        System.out.println("Running the client application!!\n");
        Registry reg = null;
        Object[] obj = null;
        magicRemote magicInstance = null;

        try
        {
            reg = LocateRegistry.getRegistry(1099);
            magicInstance = (magicRemote)reg.lookup("MyMagicInstance");
            MWNumericArray n = new MWNumericArray(Double.valueOf(args[0]),MWClassID.DOUBLE);
            System.out.println("Magic square of order : " + n.toString());
            
            obj = magicInstance.myMagic(1,n);
            MWNumericArray num = (MWNumericArray)obj[0];
            System.out.println("Receiving magic array from MATLAB : \n" + num.toString());
        }
        catch(RemoteException remote_ex)
        {
            System.out.println("RemoteException being thrown...\n");
            remote_ex.printStackTrace();
        } 
        catch(NotBoundException nb_ex)
        {
            System.out.println("NotBoundException being thrown...\n");
            nb_ex.printStackTrace();
        }
        finally
        {
            MWArray.disposeArray(obj);

            try
            {
                if(magicInstance!=null)
                    magicInstance.dispose();
            }
            catch(RemoteException ex)
            {
                System.out.println("RemoteException being thrown while disposing off the remote stub instance...\n");
                ex.printStackTrace();
            }
        }
    }
}