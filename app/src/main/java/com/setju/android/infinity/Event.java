package com.setju.android.infinity;

/**
 * Created by Gagan Lokesh on 23-03-2018.
 */

public class Event {



    public String name;
    public String type;
    public String about;
    public String fee;
    public String venue;
    public String dt;
    public String rules;
    public String prize;
    public String c1;
    public String p1;


    public Event(){

    }


   public Event(String Name, String Type, String About, String Fee, String Dt, String Venue, String Rules, String Prize, String C1, String P1){
        name = Name;
        type = Type;
        about = About;
        fee = Fee;
        venue = Venue;
        dt = Dt;
        rules = Rules;
        prize = Prize;
        c1 = C1;
        p1 = P1;
    }


    public String getName(){
       return name;
    }

    public String getType(){
        return type;
    }

    public String getAbout(){
        return about;
    }

    public String getFee(){
        return fee;
    }

    public String getVenue(){
        return venue;
    }

    public String getDt(){
        return dt;
    }

    public String getRules(){
        return rules;
    }

    public String getPrize(){
        return prize;
    }

    public String getC1(){
        return c1;
    }

    public String getP1(){
        return p1;
    }
}
