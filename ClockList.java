/*
    Operating Systems Assignment 3
    Author: Shaan Arora C3236359
    ClockList.java
    A circular list of ClockNodes to store the second chance / used bits of a page for the clock page replacement policy
 */
//TODO document setters and getters
public class ClockList
{
    //Private Member Variables

    //Current ClockNode in the list
    private ClockNode current;

    //Size of the ClockList
    private int size;

    //Number of pages currently allocated
    private int allocated;

    //Default Constructor
    public ClockList()
    {
        this.current = null;
        this.size = 0;
        this.allocated = 0;
    }

    //Parameter Constructor
    public ClockList(int s)
    {
        //make the size of the list value of s
        this.size = s;
        //instantiate current
        this.current = new ClockNode(0, null);
        //temporary object so we can connect the list after creating it
        ClockNode temp = this.current;
        //create the list and link it as we go
        //start at 1 because current was created with an index of 0 and we do not want to duplicate that index
        for(int i = 1; i < s; i++)
        {
            //create a new node for currents next with an index of the value of i and null as its next
            this.current.setNext(new ClockNode(i, null));
            //update current to be the new node we created
            this.current = this.current.getNext();
        }
        //now we can link up and complete the list
        this.current.setNext(temp);
        //default value for allocated
        this.allocated = 0;
    }

    //Operational Methods
    public int getIndex()
    {
        //this allows use to make sure we are not overriding memory
        if(this.allocated < this.size)
        {
            return this.allocated++;
        }
        //iterate through the list until we find a ClockNode that does not have a second chance
        while(this.current.hasSecondChance())
        {
            //make the second chance of current to false
            this.current.assignFalse();
            //make current the next node in the list so we can loop through
            this.current = this.current.getNext();
        }
        //give a second chance to the ClockNode that does not have one
        this.current.assignTrue();
        //return the index of the ClockNode we just gave a second chance to
        return this.current.getIndex();
    }

    public void grantSecondChance(int idx)
    {
        //capture the current node
        ClockNode temp = this.current;
        //loop through the ClockList until we find a ClockNode that has an index that matches the value of idx
        while(this.current.getIndex() != idx)
        {
            this.current = this.current.getNext();
        }
        //give the current ClockNode a second chance
        this.current.assignTrue();
        //restore current to be the node we captured at the start of the function
        this.current = temp;
    }

    //Setters

    public void setCurrent(ClockNode c) {this.current = c;}

    public void setSize(int s) {this.size = s;}

    public void setAllocated(int a) {this.allocated = a;}

    //Getters

    public ClockNode getCurrent() {return this.current;}

    public int getSize() {return this.size;}

    public int getAllocated() {return this.allocated;}
}
