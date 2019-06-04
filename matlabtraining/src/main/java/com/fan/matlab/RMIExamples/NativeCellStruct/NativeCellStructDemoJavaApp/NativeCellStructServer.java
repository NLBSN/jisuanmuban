package com.fan.matlab.RMIExamples.NativeCellStruct.NativeCellStructDemoJavaApp;

import com.mathworks.toolbox.javabuilder.*;
import com.mathworks.toolbox.javabuilder.remoting.*;
import dataTypesComp.*;
import java.rmi.*;
import java.rmi.registry.*;

public class NativeCellStructServer
{
    public static void main(String[] args)    
    {
        System.out.println("\nPlease wait for the server registration notification.");
        Registry reg = null;
        
        try
        {
            dataTypesClass cls = new dataTypesClass();
            dataTypesClassRemote clsRem =
                    (dataTypesClassRemote)RemoteProxy.newProxyFor(cls,/*object that handles remote method invocations*/
                                                                  dataTypesClassRemote.class,/*remote interface for the proxy object*/
                                                                  true/*flag to decide whether or not MWArray-derived method outputs
                                                                         should be converted to their corresponding Java types. Setting
                                                                         it to false will retun values as MWArray derived class*/);
            reg = LocateRegistry.createRegistry(1099);
            reg.rebind("DataTypesInstance", clsRem);
            System.out.println("Server registered and running successfully!!\n");
        }
        catch(RemoteException remote_ex)
        {
            System.out.println("\nRemoteException being thrown...\n");
            remote_ex.printStackTrace();            
        }        
        catch(MWException mw_ex)
        {
            System.out.println("\nMWException being thrown...\n");
            mw_ex.printStackTrace();
        }
    }    
}