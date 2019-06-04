package com.fan.matlab.RMIExamples.FactoryAndProxyAPI.FactoryAndProxyAPIDemoJavaApp;

import com.mathworks.toolbox.javabuilder.*;
import FactoryProxyComp.*;
import java.rmi.*;
import java.rmi.registry.*;

public class ClientUsingProxyAPI
{
    public static void main(String[] args)    
    {
        System.out.println("\nRunning the client application!!"); 
        Registry reg = null;
        
        try
        {
            reg = LocateRegistry.getRegistry(1099);
            FactoryProxyClassRemote r  = (FactoryProxyClassRemote)reg.lookup("ProxyInstance");
            FactoryProxyClassRemote r1 = (FactoryProxyClassRemote)reg.lookup("ProxyInstance");

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
            System.out.println("\n*** In case of Proxy implementation, both the stubs share the same workspace. \n" +
                               "As a result, change made to the global variable through first stub can be\n" +
                               "seen when the same global variable is accessed through second stub. This value \n" +
                               "will be retained by server and if the client application is run again, the server  \n" +
                               "will notify presence of value and the retained value will be returned to the client" +
                               " ***");
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
    }    
}