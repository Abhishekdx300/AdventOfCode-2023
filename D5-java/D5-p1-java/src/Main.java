import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner sc = new Scanner(System.in);
		String path = sc.nextLine();
		sc.close();

		
		File file = new File(path);
		
		Scanner scanner = new Scanner(file);
		
		String storePrevLine = scanner.nextLine();
		String withoutHeaderstorePrevLine = storePrevLine.substring(7);
		String[] storePrevs = withoutHeaderstorePrevLine.split("\\s");
		
		ArrayList<Double> storePrev = new ArrayList<Double>(storePrevs.length);
		
		for(String s : storePrevs) {
			storePrev.add(Double.parseDouble(s));
		}
		
		 scanner.nextLine();
		 ArrayList<ArrayList<Double>> prev2cur = new ArrayList<ArrayList<Double>>();
		 

		 
		 scanner.nextLine();
		// seed-to-soil starts
		 String curLine = scanner.nextLine();	 
		 
		 
		while(curLine != "") {
			
			String[] map = curLine.split("\\s");
			ArrayList<Double> temp = new ArrayList(3);
			for(String s : map) temp.add(Double.parseDouble(s));
			prev2cur.add(temp);
			curLine = scanner.nextLine();
		}
		
		for(int index=0;index < storePrev.size();index++) {
			
			Double prev = storePrev.get(index);
			
			for(int ind=0;ind<prev2cur.size();ind++) {
						Double mapPrev = prev2cur.get(ind).get(1);
						Double range = prev2cur.get(ind).get(2);
						
				if(prev >= mapPrev && prev < mapPrev+range  ) {
					Double cur = prev2cur.get(ind).get(0) + (prev-mapPrev);
					storePrev.set(index, cur);
				}
			}
		}
		prev2cur.clear();
		
	
		
		scanner.nextLine();
		// soil-to-fertilizer starts
		curLine = scanner.nextLine();
		
		
		while(curLine != "") {
			
			String[] map = curLine.split("\\s");
			ArrayList<Double> temp = new ArrayList(3);
			for(String s : map) temp.add(Double.parseDouble(s));
			prev2cur.add(temp);
			curLine = scanner.nextLine();
		}
		
		for(int index=0;index < storePrev.size();index++) {
			
			Double prev = storePrev.get(index);
			
			for(int ind=0;ind<prev2cur.size();ind++) {
						Double mapPrev = prev2cur.get(ind).get(1);
						Double range = prev2cur.get(ind).get(2);
						
				if(prev >= mapPrev && prev < mapPrev+range  ) {
					Double cur = prev2cur.get(ind).get(0) + (prev-mapPrev);
					storePrev.set(index, cur);
				}
			}
		}
		prev2cur.clear();
		
		
		
		scanner.nextLine();
		// fertilizer-to-water starts
		curLine = scanner.nextLine();
		
		
		while(curLine != "") {
			
			String[] map = curLine.split("\\s");
			ArrayList<Double> temp = new ArrayList(3);
			for(String s : map) temp.add(Double.parseDouble(s));
			prev2cur.add(temp);
			curLine = scanner.nextLine();
		}
		
		for(int index=0;index < storePrev.size();index++) {
			
			Double prev = storePrev.get(index);
			
			for(int ind=0;ind<prev2cur.size();ind++) {
						Double mapPrev = prev2cur.get(ind).get(1);
						Double range = prev2cur.get(ind).get(2);
						
				if(prev >= mapPrev && prev < mapPrev+range  ) {
					Double cur = prev2cur.get(ind).get(0) + (prev-mapPrev);
					storePrev.set(index, cur);
				}
			}
		}
		prev2cur.clear();
		
		
		
		scanner.nextLine();
		// water-to-light starts
		curLine = scanner.nextLine();
		
		
		while(curLine != "") {
			
			String[] map = curLine.split("\\s");
			ArrayList<Double> temp = new ArrayList(3);
			for(String s : map) temp.add(Double.parseDouble(s));
			prev2cur.add(temp);
			curLine = scanner.nextLine();
		}
		
		for(int index=0;index < storePrev.size();index++) {
			
			Double prev = storePrev.get(index);
			
			for(int ind=0;ind<prev2cur.size();ind++) {
						Double mapPrev = prev2cur.get(ind).get(1);
						Double range = prev2cur.get(ind).get(2);
						
				if(prev >= mapPrev && prev < mapPrev+range  ) {
					Double cur = prev2cur.get(ind).get(0) + (prev-mapPrev);
					storePrev.set(index, cur);
				}
			}
		}
		prev2cur.clear();
		
		
		
		scanner.nextLine();
		// light-to-temperature starts
		curLine = scanner.nextLine();
		
		
		while(curLine != "") {
			
			String[] map = curLine.split("\\s");
			ArrayList<Double> temp = new ArrayList(3);
			for(String s : map) temp.add(Double.parseDouble(s));
			prev2cur.add(temp);
			curLine = scanner.nextLine();
		}
		
		for(int index=0;index < storePrev.size();index++) {
			
			Double prev = storePrev.get(index);
			
			for(int ind=0;ind<prev2cur.size();ind++) {
						Double mapPrev = prev2cur.get(ind).get(1);
						Double range = prev2cur.get(ind).get(2);
						
				if(prev >= mapPrev && prev < mapPrev+range  ) {
					Double cur = prev2cur.get(ind).get(0) + (prev-mapPrev);
					storePrev.set(index, cur);
				}
			}
		}
		prev2cur.clear();
		
		
		
		scanner.nextLine();
		// temperature-to-humidity starts
		curLine = scanner.nextLine();
		
		
		while(curLine != "") {
			
			String[] map = curLine.split("\\s");
			ArrayList<Double> temp = new ArrayList(3);
			for(String s : map) temp.add(Double.parseDouble(s));
			prev2cur.add(temp);
			curLine = scanner.nextLine();
		}
		
		for(int index=0;index < storePrev.size();index++) {
			
			Double prev = storePrev.get(index);
			
			for(int ind=0;ind<prev2cur.size();ind++) {
						Double mapPrev = prev2cur.get(ind).get(1);
						Double range = prev2cur.get(ind).get(2);
						
				if(prev >= mapPrev && prev < mapPrev+range  ) {
					Double cur = prev2cur.get(ind).get(0) + (prev-mapPrev);
					storePrev.set(index, cur);
				}
			}
		}
		prev2cur.clear();
		
		
		
		
		scanner.nextLine();
		// humidity-to-location starts
		curLine = scanner.nextLine();
		
		
		while(scanner.hasNextLine() && curLine != "") {
			
			String[] map = curLine.split("\\s");
			ArrayList<Double> temp = new ArrayList(3);
			for(String s : map) temp.add(Double.parseDouble(s));
			prev2cur.add(temp);
			curLine = scanner.nextLine();
		}
		// extra trying for last line
		
		String[] map = curLine.split("\\s");
		ArrayList<Double> temp = new ArrayList(3);
		for(String s : map) temp.add(Double.parseDouble(s));
		prev2cur.add(temp);
		
		
		
		for(int index=0;index < storePrev.size();index++) {
			
			Double prev = storePrev.get(index);
			
			for(int ind=0;ind<prev2cur.size();ind++) {
						Double mapPrev = prev2cur.get(ind).get(1);
						Double range = prev2cur.get(ind).get(2);
						
				if(prev >= mapPrev && prev < mapPrev+range  ) {
					Double cur = prev2cur.get(ind).get(0) + (prev-mapPrev);
					storePrev.set(index, cur);
				}
			}
		}
		
		Collections.sort(storePrev);
		
		System.out.println(storePrev.get(0));
		
		

		
	}

}
