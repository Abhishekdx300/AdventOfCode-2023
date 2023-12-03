# Advent of Code 2023 
-- [Go to aoc🚀](https://adventofcode.com/2023)

## #Day-1 :
    -- [1st] get the first and last instance of digits, make a 2-digit number, return sum of numbers for every line.
    -- [2nd] numbers are also in string( eg. -- one, two..etc)
    
    -- run - give path as input in the terminal.

## #Day-2 :
    --- [1st] given gameNo. string with each cube(red,green,blue) and their occurence, check if this instance is possible or not (max value given for each cube).
    --- if possible return the sum of each game no.
    --- [2nd] power( of each game ) = (min. of red needed)*(min. of blue needed)*(min. of green needed)
    -- return sum of all power for all game.

    review : over-complicated the code. should've just checked for _r, _b, _g and stored the max. value for each game, and compared them to the given constrains. part-2 would've easier too. 

## #Day-3 :
    --- [1st] given strings with numbers, '.' and symbols, need to find the numbers 8 directionally adjacent to any symbols, add them and return sum. (edge-case: should take a number only once).
    solution --->  
        --- took 3 lines, prev, cur and next together and for every symbol in curLine, check prev,cur and next line.
        --- to not take a number more than once had to modify the nums to "..." .
        --- So, had to take StringBuilder as Strings are immutable in java.
        --- checked for the first line and last line separately.

    --- [2nd] : for every "*" symbol, take multiplication of adjacent numbers, only if "*" has exactly 2 adjacent nums. return of all such characters.

    solution --->
        --- Had to do many modification to the code of part-1, so copied the code to separate project and modified there.
        --- couldn't use StringBuilder to update the nums to "..", because same num can be adjacent to multiple "*".
        --- to count the no. of adjacent number, had to create a "global static counter" as we cant pass the int by reference in java.
        --- there was a special case like --
        
```java
    //  . . . * . . .
    //  . . 1 2 3 . .       
```  
        --- in this case, for * would've taken '123' 3 times (below,below-1 & below+1), because we couldn't update the String(1) . So, used an ArrayList (pass by ref.) to store the index of the already taken number.

    review : Took good amount (3hr+) of time to think,search and debug the code.
            learned a lot.  
