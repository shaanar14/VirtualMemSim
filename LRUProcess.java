/*
    Operating Systems Assignment 3
    Author: Shaan Arora C3236359
    LRUProcess.java
    Subclass of Process for the Least Recently Used (LRU) page replacement policy
 */

import java.util.ArrayList;

public class LRUProcess extends Process
{
    //TODO add some sort of data structure to keep track of least recently used page

    //Queue to track the least recently used pages, the front of the queue will be the LRU and possibly the oldest page
    private ArrayList<Integer> tracker;

    //Default Constructor
    public LRUProcess()
    {
        super();
        this.tracker = new ArrayList<>();
    }

    //Parameter constructor for assigning an ID, allocating frames & assigning a time slice
    public LRUProcess(int i, String n, int f, int ts)
    {
        super(i, n,  f, ts);
        this.tracker = new ArrayList<>();
    }

    //Copy Constructor
    public LRUProcess(Process copy)
    {
        super(copy);
        this.tracker = new ArrayList<>();
    }

    //Operational Method

    //TODO possibly redo
    public void executePage()
    {
        //where in the memory block the instruction is
        int instructionIndex = 0;
        //Loop through until we find the frame with the instruction in it
        //TODO test
        for(int i = 0; i < frames.length; i++)
        {
            if(frames[i] == pages.get(currentPage))
            {
                instructionIndex = i;
            }
        }
        //index of the instruction in the tracker
        int trackerIndex = 0;
        //Loop through the tracker
        for(int i = 0; i < tracker.size(); i++)
        {
            if(tracker.get(i) == instructionIndex)
            {
                trackerIndex = i;
                break;
            }
        }
        //make that instruction the most recently used in the tracker
        int recentlyUsed = tracker.remove(trackerIndex);
        tracker.add(recentlyUsed);
        currentPage++;
    }

    //Setters

    public void setTracker(ArrayList<Integer> newTracker) {this.tracker = newTracker;}

    //Getters

    public ArrayList<Integer> getTracker() {return this.tracker;}


}
