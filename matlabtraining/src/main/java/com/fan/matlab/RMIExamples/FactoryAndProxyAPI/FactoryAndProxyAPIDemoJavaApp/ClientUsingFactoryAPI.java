package com.fan.matlab.RMIExamples.FactoryAndProxyAPI.FactoryAndProxyAPIDemoJavaApp;

import com.mathworks.toolbox.javabuilder.*;
import com.mathworks.toolbox.javabuilder.remoting.*;
import java.rmi.*;
import java.rmi.registry.*;

public class ClientUsingFactoryAPI
{
    public static void main(String[] args)    
    {
        System.out.println("\nRunning the client application!!"); 
        Registry reg = null;
        FactoryProxyClassRemote r = null;
        FactoryProxyClassRemote r1 = null;

        try
        {            
            reg = LocateRegistry.getRegistry(1099);
            RemoteFactory f = (RemoteFactory)reg.lookup("FactoryInstance");
            r  = (FactoryProxyClassRemote)f.newInstance();
            r1 = (FactoryProxyClassRemote)f.newInstance();
            Object[] o = r.getGlobalVar(1);
            System.out.println("Getting the value of global variable before initializing through first stub  = "
                                + ((MWArray)o[0]).toString());
            o = r1.getGlobalVar(1);
            System.out.println("Getting the value of global variable before initializing through second stub = "
                                + ((MWArray)o[0]).toString());

            double val = 10;
            System.out.println("\n*** Initializing global variable through first stub to value of " + val + " ***\n");
            r.initGlobalVar(val);
            
            o = r.getGlobalVar(1);
            System.out.println("Getting the value of global variable through first stub  = "
                                + ((MWArray)o[0]).toString());
            o = r1.getGlobalVar(1);
            System.out.println("Getting the value of global variable through second stub = "
                                + ((MWArray)o[0]).toString());
            System.out.println("\n*** In case of Factory implementation, stubs do not share workspace. \n" +
                               "As a result, change made to the global variable through first stub cannot be\n" +
                               "seen when the same global variable is accessed through second stub. The \n" +
                               "value of the global variable will not be retained even if the client \n" +
                               "application is run again. ***\n");
        }
        catch(RemoteException remote_ex)
        {
            System.out.println("RemoteException being thrown...");
            remote_ex.printStackTrace();
        } 
        catch(NotBoundException nb_ex)
        {
            System.out.println("NotBoundException being thrown...");
            nb_ex.printStackTrace();            
        }
        finally
        {
            try
            {
                if(r != null)
                    r.dispose();

                if(r1 != null)
                    r1.dispose();
            }
            catch(RemoteException ex)
            {
                System.out.println("RemoteException being thrown while disposing off the factory instances...");
                ex.printStackTrace();
            }
        }
    }    
}