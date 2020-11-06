/*
    Operating Systems Assignment 3
    Author: Shaan Arora C3236359
    Process.java
    Subclass of Process for the Clock page replacement policy
 */

public class ClockProcess extends Process
{
    //Private Member Variables

    //Array of boolean values to store if a page being added has a second chance or not
    private boolean[] secondChance;

    public ClockProcess()
    {
        super();
        //Arbitrary default length
        //initial value of every index is false
        this.secondChance = new boolean[20];
    }

    //Parameter Constructor for assigning an ID, allocating frames & assigning a time slice
    public ClockProcess(int i, String n, int f, int ts)
    {
        super(i, n, f, ts);
        //the length of secondChange has to be the same length as the number of frames
        //initial value of every index is false
        this.secondChance = new boolean[f];
    }

    //Operational Methods

    //Preconditions:  ClockProcess has been declared & intialized
    //Postconditions: Executes the next instruction/page in the list
    public void executePage()
    {
        //the index of the page to give a second chance
        int indexSecondChance = 0;
        //TODO test this if statement
        //if the index of the current page has become larger than the number of frames, then reset it
        if(this.currentPage >= this.frames.length) this.currentPage = 0;
        for(int i = 0; i < this.frames.length; i++)
        {
            if(this.frames[i] == this.pages.get(this.currentPage))
            {
                indexSecondChance = i;
                break;
            }
        }
        this.giveSecondChance(indexSecondChance);
        this.currentPage++;
    }

    //Preconditions:  ClockProcess has been declared & intialized
    //Postconditions: Returns the index of the memory slot to be placed into the memory allocated to the process
    public int getIndex()
    {
        //avoid over writing memory
        if(this.getAllocated() < this.getFrames().length)
        {
            return this.allocated++;
        }
        for(int i = 0; i < this.getSecondChance().length; i++)
        {
            if(this.secondChance[i])
            {
                this.secondChance[i] = false;
            }
        }
        //set the second chance of the current page to true
        this.secondChance[this.currentPage] = true;
        return this.currentPage;
    }

    //Preconditions:  ClockProcess has been declared & intialized
    //Postconditions: Iterates through secondChance until it hits the index value passed in, assigning true to that index
    public void giveSecondChance(int idx)
    {
        for(int i = 0; i < this.secondChance.length; i++)
        {
            if(i == idx)
            {
                this.secondChance[i] = true;
            }
        }
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
            //get index will return the index of the least recently used in the tracker
            this.frames[this.getIndex()] = this.pages.get(this.currentPage);
            return true;
        }
        return false;
    }

    //Setters

    //Preconditions:  ClockProcess has been declared & intialized
    //Postconditions: Assigns the array sc to secondChance
    public void setSecondChance(boolean[] sc) {this.secondChance = sc;}

    //Getters

    //Preconditions:  ClockProcess has been declared & intialized
    //Postconditions: Returns the array secondChance
    public boolean[] getSecondChance() {return this.secondChance;}

}
