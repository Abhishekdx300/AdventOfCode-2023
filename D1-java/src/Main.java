
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) throws FileNotFoundException {

		
        File file = new File("C:\\Users\\Abhishek\\OneDrive\\Desktop\\my\\AdventOfCode\\D1-java\\input.txt");
//        File file = new File("C:\\Users\\Abhishek\\OneDrive\\Desktop\\my\\AdventOfCode\\D1-java\\test.txt");
        Scanner scanner = new Scanner(file);
		
        String curLine=" ";
        int result = 0;
        
        String[] numbers = { "one", "two", "three", "four", "five", "six", "seven","eight", "nine" };
        
        while (scanner.hasNextLine()) {
        	
        	curLine  = scanner.nextLine();
           
            // to-do: check for the strings and store their starting character index
            
            int firstStrIndex = curLine.length();
            int lastStrIndex = -1;
            
            int firstStringNum = -1;
            int lastStringNum = -1;
            
            
            
            // got the first index of first string number
            
            for( int ind=0;ind<numbers.length;ind++) {
            	
            	String s= numbers[ind];
            	
            	if(curLine.contains(s)) {
            		if( returnFirstPos(curLine,s) != -1  && returnFirstPos(curLine,s)<firstStrIndex) {
            			
            			firstStrIndex= returnFirstPos(curLine,s);
            			firstStringNum=ind+1;
            		}
            		// need to find the last
            		if( returnLastPos(curLine,s) != -1 && returnLastPos(curLine,s)>lastStrIndex) {
            			lastStrIndex = returnLastPos(curLine,s);
            			lastStringNum=ind+1;
            		}
            	}
            	
            }
//            
//            System.out.println("string nums :");
//            System.out.println(firstStrIndex+"-"+firstStringNum);
//            System.out.println(lastStrIndex+"-"+lastStringNum);
            
            

            
            // to-do:  compare numeric and string take the least for one and highest for another
            // also check the compare functions returning the index
            
            // numeric values
            
            int firstIntPos=curLine.length();
            int lastIntPos = -1;
            
            int flag=0;
            
            for(int i=0;i<curLine.length();i++) {
            	
            	char curChar = curLine.charAt(i);
            	
            	if(isInt(curChar)) {
            		
            		if(flag==0) {
            			firstIntPos= i;
            			flag=1;
            		}
            		
            		lastIntPos=i;
            	}
            }
            
            
//            
//            System.out.println("value nums :");
//            if(firstIntPos == curLine.length())     System.out.println(curLine.length()+" not possible");
//            else System.out.println(firstIntPos+"-"+returnInt(curLine.charAt(firstIntPos)));
//            if(lastIntPos == -1)  System.out.println(lastIntPos+" not possible");
//            else System.out.println(lastIntPos+"-"+returnInt(curLine.charAt(lastIntPos)));
//            
            
            
            int temp=0;
            
            if(firstIntPos < firstStrIndex) {
            	temp = returnInt(curLine.charAt(firstIntPos))*10;
//            	result += returnInt(curLine.charAt(firstIntPos))*10;
            	
            } else {
            	temp= firstStringNum*10;
            	//result += firstStringNum*10;
            }
            
            if(lastIntPos > lastStrIndex ) {
            	temp += returnInt(curLine.charAt(lastIntPos));
//            	result += returnInt(curLine.charAt(lastIntPos));
            } else {
            	temp += lastStringNum;
//            	result += lastStringNum;
            }
            
            result+=temp;
//            System.out.println(temp);
//            System.out.println("********************");
           
        }
           
        System.out.println(result);
	}
	
	private static boolean isInt(char ch) {
			String str = "0123456789";
			for(int i=0;i<str.length();i++) {
				if(ch==str.charAt(i)) return true;
			}
		return false;
	}
	
	private static int returnInt(char ch) {
		String str = "0123456789";
		for(int i=0;i<str.length();i++) {
			if(ch==str.charAt(i)) return i;
		}
	return -1;
}

	private static int returnFirstPos(String curLine, String num) {
		
		return curLine.indexOf(num);
	}
	
	
private static int returnLastPos(String curLine, String num) {
		
	  StringBuilder revCurLine = new StringBuilder();
	  StringBuilder revnumStr = new StringBuilder();
	  
	  
	  revCurLine.append(curLine);
	  revnumStr.append(num);

	  revCurLine.reverse();
	  revnumStr.reverse();
	  
	  
	  int ind = revCurLine.indexOf(revnumStr.toString());
	  
	  return curLine.length() - ind - num.length();
 	}
	
}
