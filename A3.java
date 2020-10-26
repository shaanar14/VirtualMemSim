/*
    Operating Systems Assignment 3
    Author: Shaan Arora C3236359
    A3.java
    Driver class for virtual memory simulator
 */
public class A3
{

    public static void main(String[] args)
    {
        assert args.length > 3 : "Not enough command line arguments";
        //Capture the total number of frames for the
        final int totalFrames = Integer.parseInt(args[0]);
        //Capture the time quantum for the round robin scheduling
        final int timeSlice = Integer.parseInt(args[1]);
        //Test for capturing first file to read in
        String file = args[2];
        String file2 = args[3];
        String file3 = args[4];
        System.out.printf("Frames: %d\nTime Slice: %d\n", totalFrames, timeSlice);
        System.out.printf("Files given: %s , %s , %s", file, file2, file3);
    }
}
