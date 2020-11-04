/*
    Operating Systems Assignment 3
    Author: Shaan Arora C3236359
    LRUProcess.java
    Subclass of Process for the Least Recently Used (LRU) page replacement policy
 */

import java.util.ArrayList;

public class LRUProcess extends Process
{
    //Private Member Variables

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

    //TODO write pre & post conditions
    //Operational Methods

    public void executePage()
    {
        //index for where the instruction/page is in the memory block
        //index for where in the tracker the memory slot containing the instruction/page
        int memoryIndex = 0, trackerIndex = 0;
        //iterate through memory
        for(int i = 0; i < this.frames.length; i++)
        {
            if(this.frames[i] == this.pages.get(this.currentPage)) memoryIndex = i;
        }
        //iterator through tracker
        for(int j = 0; j < this.tracker.size(); j++)
        {
            if(this.tracker.get(j) == memoryIndex)
            {
                trackerIndex = j;
                //if sucessful then leave the for loop
                break;
            }
        }
        //update such that the index of the memory is now the most recently used
        int lruBit = this.tracker.remove(trackerIndex);
        this.tracker.add(lruBit);
        //update the counter
        this.currentPage++;
    }

    //Returns the index of the memory slot to be placed into the memory allocated to the process
    public int getIndex()
    {
        //avoiding overwriting memory
        if(this.getAllocated() < this.getFrames().length)
        {
            //Store the current value of allocated
            int i = this.getAllocated();
            this.tracker.add(this.getAllocated());
            //update the counter for how many pages have been allocated so far
            this.incAllocated();
            return i;
        }
        //capture the index of the least recently used in the tracker
        int index = this.tracker.remove(0);
        //add it back into the tracker as the most recently used
        this.tracker.add(index);
        //return the index of the least recently used
        return index;
    }

    //checks to see if the process has finished its time being blocked in io
    //the number passed in is the total amount of time a process has to be blocked in the io queue
    public boolean isIOFinished(int maxIOTime)
    {
        if(this.ioTime >= maxIOTime)
        {
            //reset the time spent in io since a process can go be added to the io list multiple times
            this.ioTime = 0;
            //add back into memory
            //get index will return the index of the least recently used in the tracker
            this.frames[this.getIndex()] = this.pages.get(this.currentPage);
            return true;
        }
        return false;
    }

    //Setter

    public void setTracker(ArrayList<Integer> newTracker) {this.tracker = newTracker;}

    //Getter

    public ArrayList<Integer> getTracker() {return this.tracker;}
}
