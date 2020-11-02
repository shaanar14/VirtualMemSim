/*
    Operating Systems Assignment 3
    Author: Shaan Arora C3236359
    Process.java
    Parent/Superclass for the entire simulation, mostly reused from assignment 1
 */

//TODO add logic for pages and frames

import java.util.ArrayList;
import java.util.Arrays;

public class Process
{
    //Private Member Variables

    //The ID of the process
    protected int ID;

    //Name of the process which will be the file name
    protected String name;

    //The total amount of execution/service time for the process
    protected int serviceTime;

    //How long a process has been executed for, mostly to assist with interrupts
    protected int serviceCount;

    //The turnaround time of the process calculated by the time it left the system - arrival time
    protected int tat;

    //Time slice of the priority if its required for an algorithm
    protected int timeSlice;

    //List of page which contains a single instruction
    protected ArrayList<Integer> pages;

    //Index of the current page
    protected int currentPage;

    //List of all the timestamps where we had page faults
    protected ArrayList<Integer> faults;

    //Allocated number of frames
    protected int[] frames;

    //Default Constructor
    public Process()
    {
        this.ID = 0;
        this.name = "";
        this.serviceTime = 0;
        this.serviceCount = 0;
        this.tat = 0;
        this.timeSlice = 0;
        this.faults = new ArrayList<>();
        //initial capacity of 50 because that is the maximum number of pages a process can store
        this.pages = new ArrayList<>(50);
        //arbitrary default size
        this.frames = new int[20];
    }

    //Parameter constructor for assigning an ID, a name, allocating frames & assigning a time slice
    public Process(int i, String n, int f, int ts)
    {
        this.ID = i;
        this.name = n;
        this.serviceTime = 0;
        this.serviceCount = 0;
        this.tat = 0;
        this.timeSlice = ts;
        this.faults = new ArrayList<>();
        //initial capacity of 50 because that is the maximum number of pages a process can store
        this.pages = new ArrayList<>(50);
        //indexing of lists start at 0 so default value has to be 0
        this.currentPage = 0;
        //Assign the number of frames passed in
        this.frames = new int[f];
        //Mark every index in the frames array with -1 as a default value since it hasn't been loaded with pages
        Arrays.fill(this.frames, -1);
    }

    //Copy Constructor
    public Process(Process copy)
    {

        this.ID = copy.getID();
        this.name = copy.getName();
        this.serviceTime = copy.getServiceTime();
        this.serviceCount = copy.getServiceCount();
        this.tat = copy.getTat();
        this.timeSlice = copy.getTimeSlice();
        this.currentPage = copy.getCurrentPage();
        this.faults = copy.getFaults();
        this.pages = copy.getPages();
        this.frames = copy.getFrames();
    }

    //Specific Setters

    //Adds a specific page to the process
    public void addPage(int p) {this.pages.add(p);}

    //Adds a timestamp when a fault occurs
    public void pageFault(int f) {this.faults.add(f);}

    //Returns the index of the next instruction in memory
    public int nextInstruction()
    {
        for(int i = 0; i < frames.length; i++)
        {
            if(pages.get(currentPage) == frames[i])
            {
                return i;
            }
        }
        //if its not in memory then we just return the value we used as a default value in frames
        return -1;
    }

    //Setters

    //Preconditions: None
    //Postconditions: Assigns the value of id to the Process object's ID private member variable
    public void setID(int id) {this.ID = id;}

    //Preconditions: None
    //Postconditions: Assigns the value of service to the Process object's serviceTime private member variable
    public void setServiceTime(int service) {this.serviceTime = service;}

    //Preconditions: None
    //Postconditions: Assigns the value of time to the private member variable serviceCount
    public void setServiceCount(int time) {this.serviceCount = time;}

    //Preconditions: None
    //Postconditions: Increments serviceCount
    public void incServiceCount() {this.serviceCount++;}

    //Preconditions: None
    //Postconditions: The turnaround time for the Process object is calculated and updated
    public void setTat(int timeCompleted) {this.tat = timeCompleted;}

    //Preconditions: none
    //Postconditions: Assigns the value of ts to the private member variable timeSlice
    public void setTimeSlice(int ts) {this.timeSlice = ts;}

    public void setPages(ArrayList<Integer> newPages) {this.pages = newPages;}

    public void setCurrentPage(int cp) {this.currentPage = cp;}

    public void setFaults(ArrayList<Integer> newFaults) {this.faults = newFaults;}

    public void setFrames(int[] newFrames) {this.frames = newFrames;}

    //Getters

    //Preconditions: None
    //Postconditions: Returns the ID of the Process object
    public int getID() {return this.ID;}

    public String getName() {return this.name;}

    //Preconditions: None
    //Postconditions: Returns the total time a Process objects takes to execute
    public int getServiceTime() {return serviceTime;}

    //Preconditions: None
    //Postconditions: Returns how long the process has been executed for
    public int getServiceCount() {return this.serviceCount;}

    //Preconditions: None
    //Postconditions: Returns the turnaround time of the Process object
    public int getTat() {return tat;}

    //Preconditions: None
    //Postconditions: Returns the time slice/quantum of the process
    public int getTimeSlice(){return timeSlice;}

    public ArrayList<Integer> getPages() {return this.pages;}

    //Specific getter to return a specific page from the process using the currentPage member variable as the index
    //Could add a getter and pass an int and use that as an index
    public int getPage() {return this.pages.get(this.currentPage);}

    public int getCurrentPage() {return this.currentPage;}

    public ArrayList<Integer> getFaults() {return this.faults;}

    public int totalFaults() {return this.faults.size();}

    public int[] getFrames() {return this.frames;}

    @Override
    public String toString()
    {
        return String.format("%-5d%-18s%-17d%-10d\n", this.getID(), this.getName(), this.getTat(), this.totalFaults());
    }
}