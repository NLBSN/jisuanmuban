package com.fan.matlab.RMIExamples.MagicSquare.MagicSquareDemoJavaApp;

import com.mathworks.toolbox.javabuilder.*;
import com.mathworks.toolbox.javabuilder.remoting.*;
import magiccomp.*;
import java.rmi.*;
import java.rmi.registry.*;

public class magicserver
{       
    public static void main(String[] args)
    {
        System.out.println("\nPlease wait for the server registration notification.");
        Registry reg = null;

        try
        {
            magic cls = new magic();
            magicRemote clsRem =
                 (magicRemote)RemoteProxy.newProxyFor(cls,/*object that handles remote method invocations*/
                                                      magicRemote.class,/*remote interface for the proxy object*/
                                                      false/*flag to decide whether or not MWArray-derived method outputs
                                                             should be converted to their corresponding Java types. Setting
                                                             it to false will retun values as MWArray derived class*/);
            reg = LocateRegistry.createRegistry(1099);
            reg.rebind("MyMagicInstance", clsRem);
            System.out.println("Server registered and running successfully!!\n");
        }
        catch(RemoteException remote_ex)
        {
            System.out.println("RemoteException being thrown...\n");
            remote_ex.printStackTrace();
        }
        catch(MWException mw_ex)
        {
            System.out.println("MWException being thrown...\n");
            mw_ex.printStackTrace();
        }
    }
}