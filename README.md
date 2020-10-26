In the Scheduler assignment it was assumed that the system had an infinite amount of memory. 

Now this assignment/project simulates a a system that uses paging with virtual memory.

Least Recently Used (LRU) and Clock policy are implemented with a Fixed Allocation with Local Replacement Scope strategy.

The strategy assumes that frames allocated to a process do not change over the simulation period and page replacements (if necessary) will occur within the allocated frames.

For scheduling of processes, the standard Round Robin algorithm is used with a time quantum acquired from a command line argument
