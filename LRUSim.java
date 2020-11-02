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

    //TODO implement
    public String runSim()
    {
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

    //TODO write pre and post conditions
    //Setters

    public void setWaiting(Queue<LRUProcess> list) {this.waiting = list;}

    //Specific setter to add to a LRUProcess object into the waiting list
    public void addProcess(LRUProcess p) {this.waiting.add(p);}

    public void setComplete(ArrayList<LRUProcess> c) {this.complete = c;}

    public void setIO(ArrayList<LRUProcess> i) {this.io = i;}

    public void setCurrent(LRUProcess c) {this.current = c;}

    public void setGlobalTime(int gt) {this.globalTime = gt;}

    //Getters

    public Queue<LRUProcess> getWaiting() {return this.waiting;}

    public ArrayList<LRUProcess> getComplete() {return this.complete;}

    public ArrayList<LRUProcess> getIO() {return this.io;}

    public LRUProcess getCurrent() {return this.current;}

    public int getGlobalTime() {return this.globalTime;}
}
