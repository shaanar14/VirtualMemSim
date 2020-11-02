/*
    Operating Systems Assignment 3
    Author: Shaan Arora C3236359
    Process.java
    Subclass of Process for the Clock page replacement policy
 */

public class ClockProcess extends Process
{
    //some sort of clock data structure
    public ClockProcess()
    {
        super();
    }

    //Parameter Constructor for assigning an ID, allocating frames & assigning a time slice
    public ClockProcess(int i, String n, int f, int ts)
    {
        super(i, n, f, ts);
    }
}
