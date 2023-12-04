import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner sc = new Scanner(System.in);
		String path = sc.nextLine();
		sc.close();
		
		File file = new File(path);
		Scanner scanner = new Scanner(file);
		
		int result=0;
		
		while(scanner.hasNextLine()) {
			
			int count=0;
			
			String curLine = scanner.nextLine();
			int i = curLine.indexOf(':');
			String headlessCurLine = curLine.substring(i+1);
//			System.out.println(headlessCurLine);
			
			String[] parts = headlessCurLine.split("\\|",2);

			// extra "" is generated as a string in both arrays below
			String[] winningNums = parts[0].split("\\s+");
//			String[] myNums = parts[1].split("\\s+");
			
			String myNums = parts[1].replaceAll("\\s+", "%") + "%"; // extra will be needed below
			
			
			
//			System.out.println(myNums);
			
			for(String str : winningNums) {
				if(str!="" && myNums.contains("%"+str+"%")) { // for the case -- 76 will be true for 765...which is wrong
					if(count==0) count++;
					else count *=2;
				}
			}
			
		result+=count;			
			
		}
		System.out.println("*************");
		System.out.println(result);
	}

}
