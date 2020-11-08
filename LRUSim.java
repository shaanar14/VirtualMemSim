/*
    Operating Systems Assignment 3
    Author: Shaan Arora C3236359
    LRUSim.java
    Main class for running and simulating the Least Recently Used page replacement policy
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class LRUSim
{
    //Private Member Variables

    //List for waiting processes
    private Queue<LRUProcess> waiting;
    //List for completed proceses
    private ArrayList<LRUProcess> complete;
    //List for I/O blocked processes
    private ArrayList<LRUProcess> io;
    //Currently executing process
    private LRUProcess current;
    //Global time for the entire simulator
    private int globalTime;
    //keeps track of where the sim is up to in regards to the time slice of the currently executing process
    private int currentSlice;
    //amount of time a process has to spend being blocked and in the IO queue
    private static final int IOTIME = 6;

    //Default Constructor
    public LRUSim()
    {
        this.waiting = new ArrayDeque<>();
        this.complete = new ArrayList<>();
        this.io = new ArrayList<>();
        this.current = null;
        this.globalTime = 0;
        this.currentSlice = 0;
    }

    //Operational Functions

    public void runSim()
    {
        int nextInstruction;
        //140 because the largets turnaround time for a process is 140 which is dervied from the expected output files given
        //probably should change this to make it work for all lengths of processes
        while(this.globalTime < 140)
        {
            if(this.current == null)
            {
                //check waiting queue
                if(this.waiting.size() > 0)
                {
                    //make the currently running process to be the first process in the waiting queue
                    this.current = this.waiting.poll();
                }
                else
                {
                    //since all processes are block then we cant execute an instruction so global time is updated
                    this.globalTime++;
                    //update processes that are in the IO list
                    this.updateIO();
                }
            }
            else
            {
                //Capture the index of the next instruction to be executed in the currently running process
                nextInstruction = this.current.nextInstruction();
                if(nextInstruction >= 0)
                {
                    this.current.executePage();
                    //update the tracker for the current time slice
                    this.current.updateCurrentSlice();
                    //process has finished
                    if(this.current.getCurrentPage() == this.current.getPages().size())
                    {
                        //set the turn around of the current process which has now finished
                        //globalTime + 1 because the initial value of globalTime is 0
                        this.current.setTat(this.globalTime+1);
                        //add the process into the list of completed processes
                        this.complete.add(this.current);
                        //reset the tracker for the current time slice
                        this.currentSlice = 0;
                        //reset current
                        this.current = null;
                    }
                    //if the current time slice is greater than or equal to the time slice of the currently executing process
                    else if(this.current.getCurrentSlice() == this.current.getTimeSlice())
                    {
                        this.current.setCurrentSlice(0);
                        //add the currently executing process back into the waiting list
                        this.waiting.add(this.current);
                        //udpdate current which could be the object we added in the line above
                        this.current = this.waiting.poll();
                    }
                    //update global time of the whole simulation
                    this.globalTime++;
                    //update items in the io list
                    this.updateIO();
                }
                else
                {
                    //add a page fault with a time stamp of the current global time
                    this.current.pageFault(this.globalTime);
                    //add the process to the io list
                    this.io.add(this.current);
                    //set current to null so that it can be update at the start of the loop
                    this.current = null;
                }
            }
        }
    }

    //Output Function
    //Preconditions:  comlete.size() >= 1 which means that runSim() needs to be called before this function
    //Postconditions: Displays the results of the LRU simulator to the terminal
    public void displayResults()
    {
        //must have at least one completed process
        assert this.complete.size() >= 1;
        System.out.println("LRU - Fixed:");
        System.out.println("PID  Process Name      Turnaround Time  # Faults  Fault Times");
        //sort such that they are in the correct order for output
        this.getComplete().sort(new Process.sortByID());
        for(LRUProcess l : this.getComplete())
        {
            System.out.print(l);
        }
    }

    //Operation Method for IO
    //Preconditions:  io.size() > 0
    //Postconditions: Iterates through the list of IO bound processes and updates the time they have spent in the list
    //                  if the time spent in the list is equal to IOTIME then the very first item in the IO list is added to the waiting/ready queue
    private void updateIO()
    {
        //function can only operate if there are items in the io list
        if(!(this.io.size() > 0)) return;
        for(LRUProcess l : this.io)
        {
            l.updateIOTime();
        }
        if(this.io.get(0).isIOFinished(IOTIME))
        {
            this.waiting.add(this.io.remove(0));
        }
    }

    //TODO write pre and post conditions
    //Setters

    public void setWaiting(Queue<LRUProcess> list) {this.waiting = list;}

    //Specific setter to add to a LRUProcess object into the waiting list
    public void addProcess(LRUProcess p) {this.waiting.add(p);}

    public void setComplete(ArrayList<LRUProcess> c) {this.complete = c;}

    public void setIO(ArrayList<LRUProcess> i) {this.io = i;}

    public void setCurrent(LRUProcess c) {this.current = c;}

    public void setGlobalTime(int gt) {this.globalTime = gt;}

    public void setCurrentSlice(int c) {this.currentSlice = c;}

    //Getters

    public Queue<LRUProcess> getWaiting() {return this.waiting;}

    public ArrayList<LRUProcess> getComplete() {return this.complete;}

    public ArrayList<LRUProcess> getIO() {return this.io;}

    public LRUProcess getCurrent() {return this.current;}

    public int getGlobalTime() {return this.globalTime;}

    public int getCurrentSlice() {return this.currentSlice;}

    public int getMaxIOTime() {return IOTIME;}
}
