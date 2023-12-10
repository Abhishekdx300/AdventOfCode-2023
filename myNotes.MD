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

## #Day-4 :
    --- [1st] : Each card (line) has winning numbers & your numbers separated by '|', count the number of wins per card (line) and return the sum.

    solution --->
         -- First, i removed the "CARD 1 :" part from each line, the splitted the remaining string into 2 parts at '|' by using regex in .split().
         -- Then, splitted the 1st part (winning numbers part) into indivisual number strings, and replaced all spaces (" ") with '%' in the 2nd string( my numbers).
         -- Then for each winning num string, checked if it is present or not in my string and counted.


    --- [2nd] : for each wins in card, the same no. of next cards will have an extra copy. E.g. -- if card2 has 2 wins, then 3rd and 4th card will have an extra copy. For the extra copy also the same will be true.    

    solution --->
         -- Declared an ArrayList at the top, this will store the number of copies which will be added in the future due to current and previous cards. (trickiest part)
         -- for each line looped through the array and increased the count for every non-zero element present in array and also decremented them (means used once, so will reduce.). 
         --  Then added the no. of wins per current card n times. (n == no. of current card). Means if 2nd card has 2 wins,and no. of 2nd card is 4, then the no. of 3rd and 4th card will increase by 4 individually (Implemented by ArrayList). (Trickiest Part)

    review : 1st part was easy with regex. Learned some regex there. In the 2nd part, had to think of a way to store
             future copies, which was a bit difficult. But did them both in less time than yesterday.     


## #Day-5 : 
    --- [part-1] : Didn't clear up the mess, could've done better, but didn't take much time to code compared to the '2nd part'.
    --- Took me the whole day and "1000+ lines of code"(literal waste) to finally code it.
    Had to restart from scratch 3 times (1000+,800+ and ~100 lines ). Uploading the abomination of a code I've wrote for my future self to look back. 

## #Day-6 :
    --- both problems easiest till now. Just simple binary search for finding the min and max of the range.     

## #Day-7 :
    --- Took 2hr to solve both, had to use hashmap, priority queue, custom comparator class, class for each hand, function for calculating priority(most time consuming) etc.Overall had good time coding it up and learned a lot.      

## #Day-8 :
    --- [1st] mapping every position to its left and right using hashmap, then start at "AAA", iterate according to L or R till current is "ZZZ".    
    --- [2nd] the distance from start("**A") to first dest ("**Z") is same as the dist from first dest("**Z") to its own loop for every starting pos.
    ---  So, answer will be the LCM of all the dists of first dest for all starting pos.
    --- So, calc the distance for each, and ans is LCM of them all.

## #Day-9 :
    -- [1st] -- it was very straight-forward, just did what was explained in the problem statement.
    -- [2nd] -- needed to formulate the way the calculate  the result easily, only took 5 min.
    Overall it was easy and solved very quickly under 40 min.

## #Day-10 : 
    -- 2nd most time consuming problem this year.
    -- code has all the explanation. Not going to think about the problems again.