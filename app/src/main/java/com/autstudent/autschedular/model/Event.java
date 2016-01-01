package com.autstudent.autschedular.model;

import com.parse.ParseObject;

/**
 * Created by MI on 1/01/16.
 */
public class Event extends ParseObject {

    final private String START = "start";
    final private String END = "end";
    final private String NOTE = "note";
    final private String ROOM = "room";


    public Event() { super(); }

    //Today View Methods
    public String getEventStartTime() {return getString(START);}
    public String getEventEndTime() {return getString(END);}
    public String getEventNote() {return getString(NOTE);}
    public String getEventRoom() {return getString(ROOM);}



}
