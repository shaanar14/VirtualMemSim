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
        System.out.printf("Frames: %d Time Slice: %d\n", totalFrames, timeSlice);
        //Capture the file names passed in through the command line arguments
        String[] files = new String[args.length-2];
        System.arraycopy(args, 2, files, 0, args.length - 2);
        //Master list of process objects
        ArrayList<Process> processes = new ArrayList<>();
        //Divide the frames up equally among all the processes
        int allocateFrame = totalFrames / files.length;
        System.out.println("Allocated frames: " + allocateFrame);
        //counter for the ID of a process
        int i = 1;
        for(String s : files)
        {
            File f = new File(s);
            try
            {
                Scanner scan = new Scanner(f);
                //Create a new object with an ID of the value of i, allocate frames and assign a time slice
                //TODO either send the parent object to a LRU/Clock child object or create both of them at the same time
                Process p = new Process(i, s,  allocateFrame, timeSlice);
                while(scan.hasNext())
                {
                    String next = scan.next();
                    //for every number in the file add it as a page in the process
                    if (!next.equalsIgnoreCase("begin") && !next.equalsIgnoreCase("end")) p.addPage(Integer.parseInt(next));
                }
                //add the process to the master list of process objects
                processes.add(p);
                i++;
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
        }
        for(Process p : processes)
        {
            System.out.println(p);
        }
        System.out.println("------------------------------------------------------------");
        //LRUSim.run()
        //ClockSim.run()
        //LRUSim.displayResults()
        //ClockSim.displyResults()
    }
}
