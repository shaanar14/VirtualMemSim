/*
    Operating Systems Assignment 3
    Author: Shaan Arora C3236359
    Process.java
    Subclass of Process for the Clock page replacement policy
 */

public class ClockProcess extends Process
{
    //Private Member Variables

    //A ClockList to store all the second chances for page replacement
    private ClockList secondChance;

    public ClockProcess()
    {
        super();
        //arbitrary default size
        this.secondChance = new ClockList(20);
    }

    //Parameter Constructor for assigning an ID, allocating frames & assigning a time slice
    public ClockProcess(int i, String n, int f, int ts)
    {
        super(i, n, f, ts);
        //set the size of the ClockList storing the second chances the same number of frames allocated to this ClockProcess object
        this.secondChance = new ClockList(f);
    }

    //Operational Methods

    //Preconditions:  ClockProcess has been declared & intialized
    //Postconditions: Executes the next instruction/page in the list
    public void executePage()
    {
        //the index of the page to give a second chance
        int indexSecondChance = 0;
        for(int i = 0; i < this.frames.length; i++)
        {
            if(this.frames[i] == this.pages.get(this.currentPage))
            {
                indexSecondChance = i;
                break;
            }
        }
        this.secondChance.grantSecondChance(indexSecondChance);
        this.currentPage++;
    }

    //Preconditions:  ClockProcess has been declared & intialized
    //Postconditions: Returns the index of the memory slot to be placed into the memory allocated to the process
    public int getIndex()
    {
        return this.secondChance.getIndex();
    }

    //Preconditions:  ClockProcess has been declared & intialized
    //Postconditions: Checks if the current ClockProcess object has completed its time in the IO queue
    public boolean isIOFinished(int maxIOTime)
    {
        if(this.ioTime >= maxIOTime)
        {
            //reset the time spent in io since a process can go be added to the io list multiple times
            this.ioTime = 0;
            //add back into memory
            //page replacement happens here
            this.frames[this.getIndex()] = this.pages.get(this.currentPage);
            return true;
        }
        return false;
    }

    //Setters

    //Preconditions:  ClockProcess has been declared & intialized
    //Postconditions: Assigns the array sc to secondChance
    public void setSecondChance(ClockList sc) {this.secondChance = sc;}

    //Getters

    //Preconditions:  ClockProcess has been declared & intialized
    //Postconditions: Returns the array secondChance
    public ClockList getSecondChance() {return this.secondChance;}

}
