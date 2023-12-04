import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	// part-2
	public static void main(String[] args) throws FileNotFoundException {
		

		Scanner sc = new Scanner(System.in);
		String path = sc.nextLine();
		sc.close();
		
		File file = new File(path);
		Scanner scanner = new Scanner(file);
		
		int totalCount=0;
		
		// will store the extra counts created for future nos cards due to current card.
		ArrayList<Integer> extras = new ArrayList<Integer>();
		
		while(scanner.hasNextLine()) {
			
			int winCount=0;
			
			String curLine = scanner.nextLine();
			int i = curLine.indexOf(':');
			String headlessCurLine = curLine.substring(i+1);
			
			
			String[] parts = headlessCurLine.split("\\|",2);

			// extra "" is generated as a string in both arrays below
			String[] winningNums = parts[0].split("\\s+");
			
			String myNums = parts[1].replaceAll("\\s+", "%") + "%";
			
			
			for(String str : winningNums) {
				if(str!="" && myNums.contains("%"+str+"%")) { 
					winCount++;
				}
			}
			
			int count = 1;
			
			for(int ind =0; ind<extras.size();ind++) {
				Integer val = extras.get(ind);
				if(val!=0) {
					count++;
					extras.set(ind, val-1);
				}
			}
			
			//	if i have total 4 2nd card (1 original, 3 copies -- count=4) -- each has winning count -- 2 ( winCount)
			//	then 3rd & 4th card (2nos) will have extra 4 copies due to no.2
			//	so by adding winCount(2)  count (4) times into the array, i will have increment count(3rd) for each copy of 2nd.
			for(int cnt = 0 ; cnt < count; cnt++) extras.add(winCount);
			
			
			
		totalCount+=count;			
			
		}
		System.out.println("*************");
		System.out.println(totalCount);
	}

}
