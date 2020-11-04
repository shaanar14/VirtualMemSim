/*
    Operating Systems Assignment 3
    Author: Shaan Arora C3236359
    Process.java
    Parent/Superclass for the entire simulation, mostly reused from assignment 1
 */

//TODO add logic for pages and frames

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Process
{
    //Private Member Variables

    //The ID of the process
    protected int ID;

    //Name of the process which will be the file name
    protected String name;

    //The turnaround time of the process calculated by the time it left the system - arrival time
    protected int tat;

    //Time slice of the priority if its required for an algorithm
    protected int timeSlice;

    //Counter of how much time of the time slice has occured
    protected int currentSlice;

    //Current time spent being blocked inside the IO queue
    protected int ioTime;

    //Index of the current page
    protected int currentPage;

    //Counter for how many pages have been allocated so far
    protected int allocated;

    //List of page which contains a single instruction
    protected ArrayList<Integer> pages;

    //List of all the timestamps where we had page faults
    protected ArrayList<Integer> faults;

    //Allocated number of frames
    protected int[] frames;

    //Default Constructor
    public Process()
    {
        this.ID = 0;
        this.name = "";
        this.tat = 0;
        this.timeSlice = 0;
        this.currentSlice = 0;
        this.ioTime = 0;
        this.currentPage = 0;
        this.allocated = 0;
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
        this.tat = 0;
        this.timeSlice = ts;
        this.currentSlice = 0;
        this.ioTime = 0;
        //indexing of lists start at 0 so default value has to be 0
        this.currentPage = 0;
        this.allocated = 0;
        this.faults = new ArrayList<>();
        //initial capacity of 50 because that is the maximum number of pages a process can store
        this.pages = new ArrayList<>(50);
        //Assign the number of frames passed in
        this.frames = new int[f];
        //Mark every index in the frames array with -1 as a default value since it hasn't been loaded with pages
        Arrays.fill(this.frames, -1);
    }

    //Setters

    //Preconditions: None
    //Postconditions: Assigns the value of id to the Process object's ID private member variable
    public void setID(int id) {this.ID = id;}

    public void setName(String n) {this.name = n;}

    //Preconditions: None
    //Postconditions: The turnaround time for the Process object is calculated and updated
    public void setTat(int timeCompleted) {this.tat = timeCompleted;}

    //Preconditions: none
    //Postconditions: Assigns the value of ts to the private member variable timeSlice
    public void setTimeSlice(int ts) {this.timeSlice = ts;}

    public void setCurrentSlice(int cs) {this.currentSlice = cs;}

    //Specific mutator for currentSlice
    public void updateCurrentSlice()
    {
        this.currentSlice++;
    }

    public void setIOTime(int i) {this.ioTime = i;}

    //Updates the amount of time spent being blocked inside the io queue
    //Preconditions:  None
    //Postconditions: Increments ioTime for the current Process object
    public void updateIOTime() {this.ioTime++;}

    public void setCurrentPage(int cp) {this.currentPage = cp;}

    public void setAllocated(int a) {this.allocated = a;}

    //Specific type of setter for allocated so we can update its value by incrementing

    public void incAllocated() {this.allocated++;}

    public void setPages(ArrayList<Integer> newPages) {this.pages = newPages;}

    //Adds a specific page to the process
    public void addPage(int p) {this.pages.add(p);}

    public void setFaults(ArrayList<Integer> newFaults) {this.faults = newFaults;}

    //Adds a timestamp when a fault occurs
    public void pageFault(int f) {this.faults.add(f);}

    public void setFrames(int[] newFrames) {this.frames = newFrames;}

    //Getters

    //Preconditions: None
    //Postconditions: Returns the ID of the Process object
    public int getID() {return this.ID;}

    public String getName() {return this.name;}

    //Preconditions: None
    //Postconditions: Returns the turnaround time of the Process object
    public int getTat() {return tat;}

    //Preconditions: None
    //Postconditions: Returns the time slice/quantum of the process
    public int getTimeSlice(){return timeSlice;}

    public int getCurrentSlice()
    {
        return this.currentSlice;
    }

    public int getIOTime() {return this.ioTime;}

    //Specific getter to return a specific page from the process using the currentPage member variable as the index
    //Could add a getter and pass an int and use that as an index
    public int getPage() {return this.pages.get(this.currentPage);}

    //Postconditions:  Returns the index of the current page in memory
    public int getCurrentPage() {return this.currentPage;}

    public int getAllocated() {return this.allocated;}

    public ArrayList<Integer> getPages() {return this.pages;}

    //Returns the index of the next instruction in memory
    public int nextInstruction()
    {
        for(int i = 0; i < this.frames.length; i++)
        {
            if(this.pages.get(this.currentPage) == this.frames[i])
            {
                return i;
            }
        }
        //if its not in memory then we just return the value we used as a default value in frames
        return -1;
    }

    public ArrayList<Integer> getFaults() {return this.faults;}

    public int[] getFrames() {return this.frames;}

    public int totalFaults() {return this.faults.size();}

    //Helper function for toString to build and format display the timestamps stored in faults
    //Preconditions:  None
    //Postconditions: Returns a String containing all the time stamps of when a page fault occured, formatted nicely for output
    private String formatFaults()
    {
        //Start the output string with a {
        StringBuilder output = new StringBuilder("{");
        for(int i : this.faults)
        {
            if(i != 0)
            {
                output.append(", ");
            }
            output.append(i);
        }
        //End the output string with a }
        output.append("}");
        return output.toString();
    }

    //Preconditions:  None
    //Postconditions: Returns a string with all the relevant info regarding the current process formatted nicely for output
    @Override
    public String toString()
    {
        return String.format("%-5d%-18s%-17d%-10d%s\n", this.getID(), this.getName(), this.getTat(), this.totalFaults(), this.formatFaults());
    }

    //Comparator so we can sort by ID
    public static class sortByID implements Comparator<Process>
    {
        @Override
        public int compare(Process p1, Process p2)
        {
            return Integer.compare(p1.getID(), p2.getID());
        }
    }
}