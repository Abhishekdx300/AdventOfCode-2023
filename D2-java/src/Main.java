import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		
		
		Scanner sc = new Scanner(System.in);
		
		// give the full path of the input file in terminal -- in Windows \ -- \\
		String path = sc.nextLine();
		sc.close();
		
		
		File file = new File(path);
		
		Scanner scanner = new Scanner(file);
   
		String curLine=" ";
		int result=0;
		int gameCnt=1;
		int result2=0;
		
		// creating hashmap for "red" ,"blue", "green" with their allowed no.
		
		HashMap<String,Integer> map=new HashMap<String,Integer>();//Creating HashMap    
		   map.put(" red",12);  //Put elements in Map  
		   map.put(" green",13);    
		   map.put(" blue",14);   
		
        
        while (scanner.hasNextLine()) {
        	
        	curLine  = scanner.nextLine();
        	
        	int sepIndex = curLine.indexOf(':');
        	
        	String removedHeaderLine = curLine.substring(sepIndex+1);
        	
        	String[] sets = removedHeaderLine.split(";");
       	
        	boolean possible = true;
        	
        	// for part-2
        	int redNeeded = 0;
        	int blueNeeded = 0;
        	int greenNeeded = 0;
        	
        	for(String set : sets) {
        		//  3 blue, 4 red
        		//	1 red, 2 green, 6 blue
        		
        		String[] elems = set.split(",");  // { "_3_blue"  "_4_red"}
        		
        		for(String elem : elems) {  // _3_blue 
        			
        			
        				// iterate over map to check for the instance
            		for(Map.Entry cube : map.entrySet()) {
            			
            			String color = (String) cube.getKey();
            			int allowedNo = (int) cube.getValue();
            			
            			if(elem.contains(color)) {
            				
            				int givenNoEndInd = elem.indexOf(color); // not doing -1,will be taken care substring() method
            				int givenNoBeginInd = 1;
            				String givenNo = elem.substring(givenNoBeginInd, givenNoEndInd);
            				int givenIntNo = Integer.parseInt(givenNo);
            				
            				if(givenIntNo > allowedNo) {
            					possible = false;
//            					continue;       // cant use for part-2          					
            				}	
            				
            				// for part-2
            				
            				if(color==" red" && redNeeded < givenIntNo) redNeeded=givenIntNo;
            				else if(color==" blue" && blueNeeded < givenIntNo) blueNeeded=givenIntNo;
            				else if(color==" green" && greenNeeded < givenIntNo) greenNeeded=givenIntNo;
            				           				
            			}	
            		}	
        		}        		
        	}
        	    
        if(possible==true) result+=gameCnt;	
        gameCnt++;
        result2 += (redNeeded*blueNeeded*greenNeeded);
        }
        
        System.out.println(result);
        System.out.println(result2);
		
	}

}
