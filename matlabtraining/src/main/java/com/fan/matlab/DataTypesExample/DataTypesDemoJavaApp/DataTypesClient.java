package com.fan.matlab.DataTypesExample.DataTypesDemoJavaApp;

import com.mathworks.toolbox.javabuilder.*;
import createEmptyStruct.DataTypesClass;

public class DataTypesClient {
    public static void main(String[] args) {
        System.out.println("\nRunning the JAVA client application!!\n");

        DataTypesClass dTypes = null;
        Object[] o = null;

        try {
            dTypes = new DataTypesClass();

            MWCellArray field_names = new MWCellArray(1, 2);
            field_names.set(new int[]{1, 1}, "Name");
            field_names.set(new int[]{1, 2}, "Address");

            o = dTypes.createEmptyStruct(1, field_names);
            MWStructArray struct = (MWStructArray) o[0];
            System.out.println("\nEVENT 2: Initialized structure as received in client applications:\n\n" + struct.toString());

            MWStructArray address = new MWStructArray(new int[]{1, 1}, new String[]{"Street", "City", "State", "Zip"});
            address.set("Street", 1, "3, Apple Hill Drive");
            address.set("City", 1, "Natick");
            address.set("State", 1, "MA");
            address.set("Zip", 1, "01760");

            System.out.println("\nUpdating the 'Address' field to :\n\n" + address.toString());
            System.out.println("\n#################################\n");
            struct.set("Address", 1, address);

            o = dTypes.updateField(1, struct, "Name");
            struct = (MWStructArray) o[0];

            System.out.println("\nEVENT 5: Final structure as received by client:\n\n" + struct.toString());
            System.out.println("\nAddress field: \n\n" + struct.getField("Address", 1).toString());
            System.out.println("\n#################################\n");
        } catch (MWException mw_ex) {
            System.out.println("MWException being thrown...");
            mw_ex.printStackTrace();
        } finally {
            MWArray.disposeArray(o);

            if (dTypes != null)
                dTypes.dispose();
        }
    }
}