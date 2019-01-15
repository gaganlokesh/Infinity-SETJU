package com.setju.android.infinity;

/**
 * Created by Gagan Lokesh on 31-03-2018.
 */

public class Contact {

    public String contactName;
    public String contactDepartment;
    public String contactPhone;

    public Contact(){

    }

    public Contact(String name, String department, String Phone){

        contactName = name;
        contactDepartment = department;
        contactPhone = Phone;

    }

    public String getContactName(){
        return contactName;
    }

    public String getContactDepartment(){
        return contactDepartment;
    }

    public String getContactPhone(){
        return contactPhone;
    }
}
