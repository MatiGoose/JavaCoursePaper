package Converter;

import Entities.CheckRepair;
import Entities.Repair;

import java.util.ArrayList;

public class Converter
{
    public static ArrayList<CheckRepair> convertRepairToCheck(ArrayList<Repair> repairs){
        ArrayList<CheckRepair> checkRepairs=new ArrayList<>();
        for (Repair repair : repairs) {
            checkRepairs.add(new CheckRepair(repair.getRepairID(),repair.getEmployeeID(),repair.getClientID(),
                    repair.getBrand(),repair.getModel(),repair.getRepairType(),
                    repair.getStatus(),repair.getValidateByManager()));
        }
        return  checkRepairs;

    }
}
