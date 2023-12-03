import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class Main {
			// for part-2
	
	static int cnt=0;
	
	public static void main(String[] args) throws FileNotFoundException {

		Scanner sc = new Scanner(System.in);
		String path = sc.nextLine();
		sc.close();
		
		// issue -->
		// ...*..   taking this as 2 adjacent nums   ---     ...*..
		//	.23...											..234. -- 3times?

		
		File file = new File(path);
		
		Scanner scanner = new Scanner(file);
		
		int result=0;
		
		String prevLine = scanner.nextLine();
		String curLine = scanner.nextLine();
		int n = curLine.length();
		
		
		
		// extra iteration for prevLine
		for(int i=0;i<n;i++) {
			char ch = prevLine.charAt(i);
			if(ch=='*') {
				
				int temp = 1;
				
				// check cur
				temp*= checkPresentLine(i,prevLine);
				// check next line
				temp*= checkPrevOrNextLine(i,curLine);
								
					
					if(cnt==2) {
					result+=temp;
				}
			
				cnt=0;  // clearing static value
			}
			
		}
		
		// main part
		
		while(scanner.hasNextLine()) {

			
			String nextLine = scanner.nextLine();		
			
				// check for current line
			for(int i=0;i<n;i++) {
				char ch = curLine.charAt(i);
				if(ch=='*') {
					
					int temp =1;
					// prev
					temp*= checkPrevOrNextLine(i,prevLine);					
					// cur
					temp*= checkPresentLine(i,curLine);
					// next
					temp*= checkPrevOrNextLine(i,nextLine);
					
					if(cnt==2) result+=temp;
				
					cnt=0;  // clearing static value
				}
			}
			 // moving forward
			prevLine = curLine;
			curLine = nextLine;
		}
		
		
		// extra iteration for curLine ( last line ) 
			for(int i=0;i<n;i++) {
				char ch = curLine.charAt(i);
				if(ch=='*') {
					int temp =1;
					
					temp *= checkPrevOrNextLine(i,prevLine);

					temp*= checkPresentLine(i,curLine);
										
					if(cnt==2) result+=temp;
				
					cnt=0; // clearing static value
				}
			}
			
			System.out.println("************");
			System.out.println(result);
	
		}
	
	private static int checkPrevOrNextLine(int ind, String line) {
		
		// storing the index of values taken.. so we wont take twice
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		
		int res=1;
		
		// just below or above
		char eqlIndChar = line.charAt(ind);
		if(eqlIndChar>='0' && eqlIndChar<='9') {
			cnt++;
			res *= numFromEnd(ind,line,indexes);		
		}
		
		
		// below or above -1
		if(ind>0) {
			char minusOneIndChar = line.charAt(ind-1);
			if(minusOneIndChar>='0' && minusOneIndChar<='9' && indexes.contains(ind-1)==false) {
				res *= numFromEnd(ind-1,line,indexes);				
				cnt++;
			}
		}
		
		
		// below or above +1
		if(ind<line.length()-1) {
			char plusOneIndChar = line.charAt(ind+1);
			if(plusOneIndChar>='0' && plusOneIndChar<='9' && indexes.contains(ind+1)==false) {
				res *= numFromEnd(ind+1,line,indexes);				
				cnt++;
			}
			
		}		
		
		return res;
	}
	

	private static int checkPresentLine(int ind, String line) {
		int res=1;
		
		ArrayList<Integer> indexes = new ArrayList<Integer>(); // not needed but required for numFromEnd
		
		char prevChar = line.charAt(ind-1);
		if(prevChar>='0' && prevChar<='9') {
			res *= numFromEnd(ind-1,line,indexes);
			cnt++;
		}

		char nextChar = line.charAt(ind+1);
		if(nextChar>='0' && nextChar<='9') {
			res *= numFromStart(ind+1,line,indexes);		
			cnt++;
		}
		
		return res;
	}
	
	private static int numFromEnd(int ind,String line, ArrayList<Integer>indexes) {
		
		while(ind>=0 && line.charAt(ind)>='0' && line.charAt(ind)<='9') {
			ind--;
		}
		return numFromStart(ind+1,line,indexes);
	}
	
	private static int numFromStart(int ind, String line, ArrayList<Integer>indexes) {
		String str="";
		
		while(ind<line.length() && line.charAt(ind)>='0' && line.charAt(ind)<='9') {
			indexes.add(ind);
			str+=line.charAt(ind);
//			line.replace(ind, ind+1, "."); // cant replace in case the number is adjacent for 2 *
			ind++;
		}
		
		return Integer.parseInt(str);
	}

}
