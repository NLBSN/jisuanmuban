package com.fan.matlab.RMIExamples.DataTypes.DataTypesDemoJavaApp;

import com.mathworks.toolbox.javabuilder.*;
import dataTypesComp.*;
import java.rmi.*;
import java.rmi.registry.*;

public class DataTypesClient
{
    public static void main(String[] args)    
    {
        System.out.println("\nRunning the client application!!"); 
        Registry reg = null;
        dataTypesClassRemote dTypes = null;
        Object[] o = null;
        
        try
        {
            reg = LocateRegistry.getRegistry(1099);
            dTypes = (dataTypesClassRemote)reg.lookup("DataTypesInstance");

            MWCellArray field_names = new MWCellArray(1,2);
            field_names.set(new int[]{1,1}, "Name");
            field_names.set(new int[]{1,2}, "Address");

            o = dTypes.createEmptyStruct(1,field_names);
            MWStructArray struct = (MWStructArray)o[0];
            System.out.println("\nEVENT 2: Initialized structure as received in client applications:\n\n" + struct.toString());
            
            MWStructArray address = new MWStructArray(new int[]{1,1},new String[]{"Street","City","State","Zip"});
            address.set("Street",1,"3, Apple Hill Drive");
            address.set("City",1,"Natick");            
            address.set("State",1,"MA");
            address.set("Zip",1,"01760"); 
            
            System.out.println("\nUpdating the 'Address' field to :\n\n" + address.toString());
            System.out.println("\n#################################\n");
            struct.set("Address",1,address);
            
            o = dTypes.updateField(1,struct,"Name");
            struct = (MWStructArray)o[0];
            
            System.out.println("\nEVENT 5: Final structure as received by client:\n\n" + struct.toString()); 
            System.out.println("\nAddress field: \n\n" + struct.getField("Address",1).toString());
            System.out.println("\n#################################\n");
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
            MWArray.disposeArray(o);

            try
            {
                if(dTypes!=null)
                    dTypes.dispose();
            }
            catch(RemoteException ex)
            {
                System.out.println("RemoteException being thrown while disposing off the remote stub instance...\n");
                ex.printStackTrace();
            }
        }
    }    
}