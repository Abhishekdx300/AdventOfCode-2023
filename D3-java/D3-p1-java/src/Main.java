import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		// take input lines as prev, cur and next , for cur check if other than [0-9] & . is present, if present,
		// check 8 direction from it, add the number (need some logic) and replace them with . for no further future use.
		
		// how to change the numeric characters to .  ??   --> StringBuilder
		
		
		Scanner sc = new Scanner(System.in);
		String path = sc.nextLine();
		sc.close();
		
		File file = new File(path);
		
		Scanner scanner = new Scanner(file);
		
		int result=0;
		
		String prevLine = scanner.nextLine();
		String curLine = scanner.nextLine();
		int n = curLine.length();
		
		
		StringBuilder prevLineSB = new StringBuilder();
		prevLineSB.append(prevLine);
		StringBuilder curLineSB = new StringBuilder();
		curLineSB.append(curLine);
		
		
		// we are starting the curLine from line no. 2 ,
		// so we have separately check for the 1st line
		
		// extra iteration for prevLine
		for(int i=0;i<n;i++) {
			char ch = prevLineSB.charAt(i);
			if(isSymbol(ch)) {
				
				result += checkPresentLine(i,prevLineSB);
				// check next line
				result += checkPrevOrNextLine(i,curLineSB);
			}
			
		}
		
		// main part
		
		while(scanner.hasNextLine()) {
			
			String nextLine = scanner.nextLine();
			StringBuilder nextLineSB = new StringBuilder();
			nextLineSB.append(nextLine);
			
				// check for current line
			for(int i=0;i<n;i++) {
				char ch = curLineSB.charAt(i);
				if(isSymbol(ch)) {
					// check previous line
					result += checkPrevOrNextLine(i,prevLineSB);					
					// check current line
					result += checkPresentLine(i,curLineSB);
					// check next line
					result += checkPrevOrNextLine(i,nextLineSB);
				}
				
				
			}
	
			 // moving forward
			prevLineSB = curLineSB;
			curLineSB = nextLineSB;
		}
		
		// after the last iteration there's no nextLine,
		//but we have a curLine, so we have to check for it separately.
		
		// extra iteration for curLine ( last line ) 
			for(int i=0;i<n;i++) {
				char ch = curLineSB.charAt(i);
				if(isSymbol(ch)) {
				
					// check previous line
					result += checkPrevOrNextLine(i,prevLineSB);
					// check current line
					result += checkPresentLine(i,curLineSB);
				}
				
			}
			
			System.out.println("************");
			System.out.println(result);
	
		}
	
	private static int checkPrevOrNextLine(int ind, StringBuilder line) {
		// 3 cases
		int res=0;
		
		// just below or above
		char eqlIndChar = line.charAt(ind);
		if(eqlIndChar>='0' && eqlIndChar<='9') res += numFromEnd(ind,line);		
		// below or above -1
		if(ind>0) {
			char minusOneIndChar = line.charAt(ind-1);
			if(minusOneIndChar>='0' && minusOneIndChar<='9') res += numFromEnd(ind-1,line);				
		}
		
		// below or above +1
		if(ind<line.length()-1) {
			char plusOneIndChar = line.charAt(ind+1);
			if(plusOneIndChar>='0' && plusOneIndChar<='9') res += numFromEnd(ind+1,line);				
		}		
		
		return res;
	}
	

	private static int checkPresentLine(int ind, StringBuilder line) {
		int res=0;
		// check prev pos
		char prevChar = line.charAt(ind-1);
		if(prevChar>='0' && prevChar<='9') res += numFromEnd(ind-1,line);
		
		// check next pos
		char nextChar = line.charAt(ind+1);
		if(nextChar>='0' && nextChar<='9') res += numFromStart(ind+1,line);		
		
		return res;
	}
	
	private static int numFromEnd(int ind,StringBuilder line) {
		
		while(ind>=0 && line.charAt(ind)>='0' && line.charAt(ind)<='9') {
			ind--;
		}
		return numFromStart(ind+1,line);
	}
	
	private static int numFromStart(int ind, StringBuilder line) {
		String str="";
		
		while(ind<line.length() && line.charAt(ind)>='0' && line.charAt(ind)<='9') {
			str+=line.charAt(ind);
			line.replace(ind, ind+1, ".");
			ind++;
		}
		
		return Integer.parseInt(str);
	}
	
		
	private static boolean isSymbol(Character ch) {
		String str = "0123456789.";
		for(int i=0;i<str.length();i++) {
			if(ch==str.charAt(i)) return false;
		}
		return true;
	}
	
}
