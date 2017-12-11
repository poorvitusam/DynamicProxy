Assuming you are in the directory containing this README:

## To clean:
ant -buildfile genericCheckpointing/src/build.xml clean

-----------------------------------------------------------------------
## To compile: 
ant -buildfile genericCheckpointing/src/build.xml all

-----------------------------------------------------------------------
## To run by specifying arguments from command line 
## We will use this to run your code
ant -buildfile genericCheckpointing/src/build.xml run -Darg0=firstarg -Darg1=SECOND -Darg2=THIRD

-----------------------------------------------------------------------

## To create tarball for submission
ant -buildfile src/build.xml tarzip

-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating I will have to sign an
official form that I have cheated and that this form will be stored in
my official university record. I also understand that I will receive a
grade of 0 for the involved assignment for my first offense and that I
will receive a grade of F for the course for any additional
offense.

[Date: ] -- 12/11/2017

-----------------------------------------------------------------------

Provide justification for Data Structures used in this assignment in
term of Big O complexity (time and/or space)
To store records: arraylist- O(n)

-----------------------------------------------------------------------
Provide list of citations (urls, etc.) from where you have taken code
(if any).
