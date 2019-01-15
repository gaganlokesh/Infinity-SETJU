package com.setju.android.infinity;

/**
 * Created by Gagan Lokesh on 18-03-2018.
 */

public class Feed {

    public String title;
    public String body;
    public String date;
    public String time;


    public Feed(){

    }

    public Feed(String Title, String Body, String Date, String Time){

        title = Title;
        body = Body;
        date = Date;
        time = Time;
    }


    public String getTitle(){
        return title;
    }

    public String getBody(){
        return body;
    }

    public String getDate(){
        return date;
    }

    public String getTime(){
        return time;
    }
}
