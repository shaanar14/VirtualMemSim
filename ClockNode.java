/*
    Operating Systems Assignment 3
    Author: Shaan Arora C3236359
    ClockNode.java
    Core object/data structure for ClockList.java which stores its own index, a reference to the next ClockNode and whether or not it has a second chance
 */

 //TODO document setters and getters
public class ClockNode
{
    //Private Member Variables

    //Index of the ClockNode
    private int index;
    //Reference to the next node
    private ClockNode next;
    //boolean to act as the used bits or the second chance for a page
    private boolean secondChance;

    public ClockNode()
    {
        this.index = 0;
        this.next = null;
        this.secondChance = false;
    }

    public ClockNode(int idx, ClockNode n)
    {
        this.index = idx;
        this.next = n;
        this.secondChance = false;
    }

    //Setters

    public void setIndex(int i) {this.index = i;}

    public void setNext(ClockNode n) {this.next = n;}

    public void setSecondChance(boolean sc) {this.secondChance = sc;}

    //Specific Setters for secondChance
    public void assignTrue() {this.secondChance = true;}

    public void assignFalse() {this.secondChance = false;}

    //Getters

    public int getIndex() {return this.index;}

    public ClockNode getNext() {return this.next;}

    public boolean hasSecondChance() {return this.secondChance;}


}
