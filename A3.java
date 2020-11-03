/*
    Operating Systems Assignment 3
    Author: Shaan Arora C3236359
    A3.java
    Driver class for virtual memory simulator
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class A3
{

    public static void main(String[] args)
    {
        assert args.length > 3 : "Not enough command line arguments";
        //Capture the total number of frames for the
        final int totalFrames = Integer.parseInt(args[0]);
        //Capture the time quantum for the round robin scheduling
        final int timeSlice = Integer.parseInt(args[1]);
        //Capture the file names passed in through the command line arguments
        String[] files = new String[args.length-2];
        System.arraycopy(args, 2, files, 0, args.length - 2);
        //Simulator object for LRU
        LRUSim lruSim = new LRUSim();
        //Divide the frames up equally among all the processes
        int allocateFrame = totalFrames / files.length;
        //counter for the ID of a process
        int i = 1;
        for(String s : files)
        {
            File f = new File(s);
            try
            {
                Scanner scan = new Scanner(f);
                //Create a new object with an ID of the value of i, allocate frames and assign a time slice
                LRUProcess test = new LRUProcess(i, s, allocateFrame, timeSlice);
                while(scan.hasNext())
                {
                    String next = scan.next();
                    //for every number in the file add it as a page in the process
                    if (!next.equalsIgnoreCase("begin") && !next.equalsIgnoreCase("end"))
                    {
                        test.addPage(Integer.parseInt(next));
                        //TODO add ClockProcess object
                    }
                }
                //Add a new LRU process to the waiting list of lSim by using the copy constructor
                lruSim.addProcess(test);
                i++;
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
        }
        lruSim.runSim();
        lruSim.displayResults();
        System.out.println("------------------------------------------------------------");
        //ClockSim.run()
        //LRUSim.displayResults()
        //ClockSim.displyResults()
    }
}
