package com.fan.matlab.RMIExamples.FactoryAndProxyAPI.FactoryAndProxyAPIDemoJavaApp;

import com.mathworks.toolbox.javabuilder.remoting.*;
import FactoryProxyComp.*;
import java.rmi.*;
import java.rmi.registry.*;

public class ServerUsingFactoryAPI
{
    public static void main(String[] args)    
    {
        System.out.println("\nPlease wait for the server registration notification.");
        Registry reg = null;

        try
        {
            BasicRemoteFactory factory =
                    new BasicRemoteFactory(FactoryProxyClass.class, /* Class to use to implement the given remote interface. */
                                           FactoryProxyClassRemote.class, /* The factory produces objects implementing this interface */
                                           false /* flag to decide whether or not MWArray-derived method outputs
                                                  should be converted to their corresponding Java types. Setting
                                                  it to false will retun values as MWArray derived class */);
            Remote stub = factory.getStub();
            reg = LocateRegistry.createRegistry(1099);
            reg.rebind("FactoryInstance", factory);
            System.out.println("Server registered and running successfully!!\n");
        }
        catch(RemoteException remote_ex)
        {
            System.out.println("RemoteException being thrown...");
            remote_ex.printStackTrace();            
        }
    }    
}