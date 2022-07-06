# Quantexa Exercise

To run the code with sbt installed please run "sbt", and once in the sbt terminal type in "run".
The run will generate 3 files that contain the solution to the exercises: 
- question1Solution
- question2Solution
- question3Solution

Please note that if an error happens reading an input file the programme will throw and exception and terminate. This was a design decision I made on the assumption that if the file was incorrect for some reason we wouldn't want to calculate the answers as the results wouldn't be as expected.
If there was a requirement to ignore incorrect lines this could also easily be achieved by simply ignoring the line that we failed to parse.

As it wasn't specified I rounded the output values to 2 decimals. Potentially this could be made configurable if it was required

