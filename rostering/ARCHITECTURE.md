Architecture of Rostering system
===================================
This document serves as a explanation of architectonic decisions made during the process of implementing this assignment.

Pools
-----
Before describing the algorithm itself, it's important to explain how it gets input. During parsing input CSV, all
employees' preferences are loaded into Pool. There is firstly `ShiftPool`, which contains all employees willing
to work that shift. Then `DayPoll` with two shift pool (early and late). And finally `WeekPool` containing 7 day pools.

Core - the algorithm and state space
------------------------------------
I've decided to use an altered version of Depth First Search algorithm with limited state space. There are two main steps
in finding the solution:

### Create state space
This is responsibility of `StateSpace` class. It gets a `WeekPool` (parsed data from csv input) and starts creating
all possible combinations of states in this order:

1. Combine employees from one `ShiftPool` to all possible shifts.
2. For each day, make all possible (employee is not in both shifts) combinations of its shifts (early+late)

Then it returns List (size 7) of Lists with all possible `DaySchedule`s

### Algorithm
The algorithm is stored in `SimpleAlgorithm` class. It is a recursive algorithm which builds week shift tips (possible
week schedules) and then tests this tip for validity. The only rule validated on week's level is that **every
employee works at least one and no more than 4 shifts**. The `#iterate()` method returns solution. If the returned solution is optimal (each shift has experienced and inexperienced employee), algorithm ends.

Algorithm might return 3 results:

* Optimal solution - algorithm ended as soon as it was found
* Non-optimal solution - algorithm went through whole state space and didn't find optimal solution
* null - solution doesn't exist

### Possible optimizations
I was intentionally avoiding any kind of optimization which would raise the complexity of the algorithm. But here is a few
ideas:

* All isOptimal methods would be computed just once during object's construction
* State space for algorithm would be sorted (optimal first). After depleting all optimal combinations, the algorithm would
  take first non-optimal.
 
But the first thing I would do would be running profiler. This implementation has obvious limits (rapid growth of the state space with
every new employee) so for bigger state spaces some different algorithm should be used because language and heuristic
optimization can only go so far.

Little things
-------------------
This chapter describes small architectonic decisions made during the process of implementation.

### Runtime exceptions
I personally hate checked exceptions on places, where the client doesn't have a chance to recover from them. That is the reason why Input and Output interfaces have method declarations without checked exceptions and their implementations has to convert them to runtime exceptions. It makes the code much cleaner.

### Immutable objects
It is good practice to make all possible objects immutable. I simulated it on Employee. All classes in `schedule` package
should be also immutable, but for lack of time they aren't.

### Optimal interface
`Optimal` interface in schedule package makes easy to mark all schedule classes as something, that could be optimal.
If I would have more time, I would go one step further, and created something like:
    
    public interface OptimalRule<T> {
        boolean isOptimal(T);
    }
    
For easier addition of rules for optimal solution.

Two things I'm not happy with
----------------------------
I'm not happy with my test coverage. I didn't run any static tool, but I would like to cover core algorithm with more
tests testing more edge cases.

Days in a week are determined by their index in List (in both `WeekPool` and `WeekSchedule`) which just doesn't feel
right. But again, for lack of time i didn't change it to anything nicer.