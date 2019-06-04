package com.fan.matlab.RMIExamples.FactoryAndProxyAPI.FactoryAndProxyAPIDemoJavaApp;

import com.mathworks.toolbox.javabuilder.*;
import com.mathworks.toolbox.javabuilder.remoting.*;
import FactoryProxyComp.*;
import java.rmi.*;
import java.rmi.registry.*;


public class ServerUsingProxyAPI
{
    public static void main(String[] args)    
    {
        System.out.println("\nPlease wait for the server registration notification.");
        Registry reg = null;
        
        try
        {
            FactoryProxyClass cls = new FactoryProxyClass();
            FactoryProxyClassRemote clsRem =
            (FactoryProxyClassRemote)RemoteProxy.newProxyFor(cls, /*object that handles remote method invocations*/
                                                             FactoryProxyClassRemote.class, /*remote interface for the proxy object*/
                                                             false /* flag to decide whether or not MWArray-derived method outputs
                                                                      should be converted to their corresponding Java types. Setting
                                                                      it to false will retun values as MWArray derived class */);
                        
            reg = LocateRegistry.createRegistry(1099);
            reg.rebind("ProxyInstance", clsRem);
            System.out.println("Server registered and running successfully!!\n");
        }
        catch(RemoteException remote_ex)
        {
            System.out.println("RemoteException being thrown...");
            remote_ex.printStackTrace();            
        }        
        catch(MWException mw_ex)
        {
            System.out.println("MWException being thrown...");
            mw_ex.printStackTrace();
        }
    }    
}