/*
    Operating Systems Assignment 3
    Author: Shaan Arora C3236359
    Process.java
    Subclass of Process for the Clock page replacement policy
 */

import java.util.ArrayList;
import java.util.Map;

//TODO finish implementation
public class ClockProcess extends Process
{

    //HashMap to represent how the frames & pages are represented for the clock policy
    //  Integer for the page being allocated and Byte to represent the use bit
    private ArrayList<Map.Entry<Integer, Byte>> clock;

    //some sort of clock data structure
    public ClockProcess()
    {
        super();
        this.clock = new ArrayList<>();
    }

    //Parameter Constructor for assigning an ID, allocating frames & assigning a time slice
    public ClockProcess(int i, String n, int f, int ts)
    {
        super(i, n, f, ts);
        //Inital Capacity of the HashMap will be the number of frames allocated to a process
        this.clock = new ArrayList<>(f);
        this.initClock();

    }

    //Private helper function for the constructor
    private void initClock()
    {
        for(int i : this.frames)
        {
            this.clock.add(Map.entry(i, (byte) 1));
        }
    }

    //Operational Methods

    //Setters

    public void setClock(ArrayList<Map.Entry<Integer, Byte>> c) {this.clock = c;}

    //Getters

    public ArrayList<Map.Entry<Integer, Byte>> getClock() {return this.clock;}

    @Override
    public String toString()
    {
        return this.clock.toString();
    }
}
