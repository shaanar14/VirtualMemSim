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


    //Default Constructor
    public LRUSim()
    {
        this.waiting = new ArrayDeque<>();
        this.complete = new ArrayList<>();
        this.io = new ArrayList<>();
        this.current = new LRUProcess();
        this.globalTime = 0;
    }

    //Parameter Constructor
    public LRUSim(ArrayList<LRUProcess> list)
    {
        this.waiting = new ArrayDeque<>(list);
        this.complete = new ArrayList<>();
        this.io = new ArrayList<>();
        this.current = new LRUProcess();
        this.globalTime = 0;
    }

    //Operational Functions

    public String runSim()
    {
        int nextIndex = -1;
        //Run simulator for a specific length of time units
        while(this.globalTime < 40)
        {
            //if we dont have a process running
            if(current == null)
            {
                //if we have processes waiting then make current the first process in the queue
                if(this.waiting.size() > 0) this.current = this.waiting.poll();
                //if all processes are blocked then update the global time without executing an instruction
                else this.globalTime++;
                //TODO need to handle I/O bound processes
            }
            //capture the next instruction index, which is where the currently executing process instruction is in memory
            nextIndex = this.current.nextInstruction();
            //if the nextIndex is not -1 which is the default value we used for frames in Process
            if(nextIndex >= 0)
            {
                for(int i = 0; i < this.current.getTimeSlice(); i++)
                {
                    this.current.executePage();
                }

            }

        }

        return "";
    }

    //Output Function
    public void displayResults()
    {
        //must have at least one completed process
        assert this.complete.size() >= 1;
        System.out.println("LRU - Fixed:");
        System.out.println("PID  Process Name      Turnaround Time  # Faults  Fault Times  \n");
        for(LRUProcess l : this.complete)
        {
            System.out.print(l);
        }
    }

    //public

    //Setters

    public void setGlobalTime(int gt) {this.globalTime = gt;}

    public void setCurrent(LRUProcess c) {this.current = c;}

    //Getters

    public int getGlobalTime() {return this.globalTime;}

    public LRUProcess getCurrent() {return this.current;}
}
