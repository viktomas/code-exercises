Rostering system for small businesses
=====================================

* Write a rostering system for a small business.
* Each day of the week has 2 shifts (early and late), and each shift requires 2 people to work it.
* Employees submit their preferences for which shifts they want to work in advance.
* The system must ensure that an employee only works 1 shift a day, and a maximum of 4 shifts a week.
* The system must ensure that each employee is allocated to at least one shift.
* The system should pair an experienced employee with a new employee where possible.
* Please submit all source code.  We want be able to execute your program with the sample
  data and easily examine its output.  You can provide the sample data to the program in any
  way you choose, and output in any way you choose (csv input and output is fine!)

Sample data
-----------
Sample data in CSV form:

    Employee Name,Experienced?,Mon,Tue,Wed ,Thur,Fri,Sat,Sun
    Phil,Y,Either,Either,Either,Either,Either,Either,Either
    Dave,N,None,None,Early,Early,Early,Early,Early
    Sandra,N,Either,Either,Either,Either,Either,None,None
    Belinda,Y,Late,Late,Late,Late,None,Either,Either
    Roger,Y,Either,Either,None,Early,Late,None,None
    Stef,N,None,None,Early,Early,Either,Either,Either
    Cassie,Y,None,None,None,Early,Early,None,None
    Bill,N,Late,None,Late,None,None,Either,Early
    
Criteria for evaluation
-----------------------
We're looking for clear, concise, logical code that is easy to read and understand.  We don't want to see
an over-engineered solution.  We don't care too much about the the code that reads the input and writes the
output (so don't fret too much about input validation and error handling) - we're much more interested in
how your code solves the problem of allocating shifts.