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
		
		
		// need some work in seed to soil conversion
		ArrayList<ArrayList<Double>> seednRange = new ArrayList<ArrayList<Double>>();
		for(int i=0;i<storePrevs.length;i+=2) {
			ArrayList<Double>temp = new ArrayList();
			temp.add(Double.parseDouble(storePrevs[i]));
			temp.add(Double.parseDouble(storePrevs[i+1]));
			seednRange.add(temp);
			
		}
		
		System.out.println("Seed & range");
		for(int i =0;i< seednRange.size();i++) {
			for(int j=0;j<seednRange.get(i).size();j++) {
				System.out.println(seednRange.get(i).get(j));
			}
			System.out.println("****");
		}
		
		
//		ArrayList<Double> storePrev = new ArrayList<Double>();
		
//		for(String s : storePrevs) {
//			storePrev.add(Double.parseDouble(s));
//		}
		
		
		
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
		
		
		
		ArrayList<ArrayList<Double>> soilnRange = new ArrayList<ArrayList<Double>>(); // should change for each
		
		for(int index=0;index < seednRange.size();index++) {
			
			Double prev = seednRange.get(index).get(0);  // a
			Double prevRange = seednRange.get(index).get(1);  // b
			
			
			for(int ind=0; ind < prev2cur.size();ind++) {
				Double curStart = prev2cur.get(ind).get(0); // y
				Double mapPrev = prev2cur.get(ind).get(1);  // x
				Double range = prev2cur.get(ind).get(2); // r
				
				if(prev>=mapPrev && prev < mapPrev+range) {

					
					if(prevRange <= range) {
						
						ArrayList<Double> temp = new ArrayList();
//						temp.add(prev);
						temp.add(curStart+prev-mapPrev);
						temp.add(prevRange);
						soilnRange.add(temp);  // should change for each			
						
					} else {
						
						ArrayList<Double> temp = new ArrayList();
//						temp.add(prev);
						temp.add(curStart+prev-mapPrev);
						temp.add(mapPrev+range-prev);
						soilnRange.add(temp);  // should change for each
						
						ArrayList<Double> temp2 = new ArrayList<Double>();
						temp2.add(curStart+range);
						temp2.add(prev+prevRange-mapPrev-range);
						soilnRange.add(temp2);
					}
					
				}
				else if(prev<mapPrev && prevRange > mapPrev-prev) {
					
					
					if(range <= prev+prevRange-mapPrev ) {
						ArrayList<Double> temp = new ArrayList<Double>();
						temp.add(prev);
						temp.add(mapPrev-prev);
						soilnRange.add(temp);
						
						ArrayList<Double> temp2 = new ArrayList<Double>();
						temp2.add(mapPrev+range);
						temp2.add(prev+prevRange-mapPrev-range);
						soilnRange.add(temp2);
						
						ArrayList<Double> temp3 = new ArrayList<Double>();
						temp3.add(curStart);
						temp3.add(range);
						soilnRange.add(temp3);
						
						
					} else {
						ArrayList<Double> temp = new ArrayList<Double>();
						temp.add(prev);
						temp.add(mapPrev-prev);
						soilnRange.add(temp);
						
						ArrayList<Double> temp2 = new ArrayList<Double>();
						temp2.add(mapPrev);
						temp2.add(prev+prevRange-mapPrev);
						soilnRange.add(temp2);		
						
					}
								
				} else { // a starts ends before x or a starts after x+r  --- add the same into new
					ArrayList<Double> temp = new ArrayList<Double>();
					temp.add(prev);
					temp.add(prevRange);
					soilnRange.add(temp);
				}
			}
		}
			
		prev2cur.clear();
			
			
		System.out.println("Soil & range");
		for(int i =0;i< soilnRange.size();i++) {
		for(int j=0;j<soilnRange.get(i).size();j++) {
			System.out.println(soilnRange.get(i).get(j));
		}
		System.out.println("****");
	}	
		
		
	
		
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
		
		
		
		
		
		
		ArrayList<ArrayList<Double>> fertnRange = new ArrayList<ArrayList<Double>>(); // should change for each
		
		for(int index=0;index < soilnRange.size();index++) {   // --- change
			
			Double prev = soilnRange.get(index).get(0);  // a   --- change
			Double prevRange = soilnRange.get(index).get(1);  // b     --- change
			
			
			for(int ind=0; ind < prev2cur.size();ind++) {
				Double curStart = prev2cur.get(ind).get(0); // y
				Double mapPrev = prev2cur.get(ind).get(1);  // x
				Double range = prev2cur.get(ind).get(2); // r
				
				
if(prev>=mapPrev && prev < mapPrev+range) {

					
					if(prevRange <= range) {
						
						ArrayList<Double> temp = new ArrayList();
//						temp.add(prev);
						temp.add(curStart+prev-mapPrev);
						temp.add(prevRange);
						fertnRange.add(temp);  // should change for each			
						
					} else {
						
						ArrayList<Double> temp = new ArrayList();
//						temp.add(prev);
						temp.add(curStart+prev-mapPrev);
						temp.add(mapPrev+range-prev);
						fertnRange.add(temp);  // should change for each
						
						ArrayList<Double> temp2 = new ArrayList<Double>();
						temp2.add(curStart+range);
						temp2.add(prev+prevRange-mapPrev-range);
						fertnRange.add(temp2);
					}
					
				}
				else if(prev<mapPrev && prevRange > mapPrev-prev) {
					
					
					if(range <= prev+prevRange-mapPrev ) {
						ArrayList<Double> temp = new ArrayList<Double>();
						temp.add(prev);
						temp.add(mapPrev-prev);
						fertnRange.add(temp);
						
						ArrayList<Double> temp2 = new ArrayList<Double>();
						temp2.add(mapPrev+range);
						temp2.add(prev+prevRange-mapPrev-range);
						fertnRange.add(temp2);
						
						ArrayList<Double> temp3 = new ArrayList<Double>();
						temp3.add(curStart);
						temp3.add(range);
						fertnRange.add(temp3);
						
						
					} else {
						ArrayList<Double> temp = new ArrayList<Double>();
						temp.add(prev);
						temp.add(mapPrev-prev);
						fertnRange.add(temp);
						
						ArrayList<Double> temp2 = new ArrayList<Double>();
						temp2.add(mapPrev);
						temp2.add(prev+prevRange-mapPrev);
						fertnRange.add(temp2);		
						
					}
								
				} else { // a starts ends before x or a starts after x+r  --- add the same into new
					ArrayList<Double> temp = new ArrayList<Double>();
					temp.add(prev);
					temp.add(prevRange);
					fertnRange.add(temp);
				}
				
//				if(prev>=mapPrev && prev < mapPrev+range) {
//					ArrayList<Double> temp = new ArrayList();
////					temp.add(prev);
//					temp.add(curStart+prev-mapPrev);
//					temp.add(Math.min(prevRange, range));
//					fertnRange.add(temp);  // should change for each
//				}
//				else if(prev<mapPrev && prevRange > mapPrev-prev) {
//					ArrayList<Double> temp = new ArrayList();
////					temp.add(mapPrev);
//					temp.add(curStart);
//					temp.add(Math.min(range, prev+prevRange-mapPrev));
//					fertnRange.add(temp);  // should change for each	
//				}
			}
		}
		prev2cur.clear();
		
		
		
		System.out.println("Ferti & range");
		for(int i =0;i< fertnRange.size();i++) {
		for(int j=0;j<fertnRange.get(i).size();j++) {
			System.out.print(fertnRange.get(i).get(j));
			System.out.print("-");
		}
		System.out.println("****");
	}
		
		
//		for(int index=0;index < storePrev.size();index++) {
//			
//			Double prev = storePrev.get(index);
//			
//			for(int ind=0;ind<prev2cur.size();ind++) {
//						Double mapPrev = prev2cur.get(ind).get(1);
//						Double range = prev2cur.get(ind).get(2);
//						
//				if(prev >= mapPrev && prev < mapPrev+range  ) {
//					Double cur = prev2cur.get(ind).get(0) + (prev-mapPrev);
//					storePrev.set(index, cur);
//				}
//			}
//		}
		
		
		
		
		
		
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
		

		
		
		ArrayList<ArrayList<Double>> waternRange = new ArrayList<ArrayList<Double>>(); // should change for each
		
		for(int index=0;index < fertnRange.size();index++) {   // ---- change
			
			Double prev = fertnRange.get(index).get(0);  // a      --- change
			Double prevRange = fertnRange.get(index).get(1);  // b    --- change
			
			
			for(int ind=0; ind < prev2cur.size();ind++) {
				Double curStart = prev2cur.get(ind).get(0); // y
				Double mapPrev = prev2cur.get(ind).get(1);  // x
				Double range = prev2cur.get(ind).get(2); // r
				
				
				
if(prev>=mapPrev && prev < mapPrev+range) {

					
					if(prevRange <= range) {
						
						ArrayList<Double> temp = new ArrayList();
//						temp.add(prev);
						temp.add(curStart+prev-mapPrev);
						temp.add(prevRange);
						waternRange.add(temp);  // should change for each			
						
					} else {
						
						ArrayList<Double> temp = new ArrayList();
//						temp.add(prev);
						temp.add(curStart+prev-mapPrev);
						temp.add(mapPrev+range-prev);
						waternRange.add(temp);  // should change for each
						
						ArrayList<Double> temp2 = new ArrayList<Double>();
						temp2.add(curStart+range);
						temp2.add(prev+prevRange-mapPrev-range);
						waternRange.add(temp2);
					}
					
				}
				else if(prev<mapPrev && prevRange > mapPrev-prev) {
					
					
					if(range <= prev+prevRange-mapPrev ) {
						ArrayList<Double> temp = new ArrayList<Double>();
						temp.add(prev);
						temp.add(mapPrev-prev);
						waternRange.add(temp);
						
						ArrayList<Double> temp2 = new ArrayList<Double>();
						temp2.add(mapPrev+range);
						temp2.add(prev+prevRange-mapPrev-range);
						waternRange.add(temp2);
						
						ArrayList<Double> temp3 = new ArrayList<Double>();
						temp3.add(curStart);
						temp3.add(range);
						waternRange.add(temp3);
						
						
					} else {
						ArrayList<Double> temp = new ArrayList<Double>();
						temp.add(prev);
						temp.add(mapPrev-prev);
						waternRange.add(temp);
						
						ArrayList<Double> temp2 = new ArrayList<Double>();
						temp2.add(mapPrev);
						temp2.add(prev+prevRange-mapPrev);
						waternRange.add(temp2);		
						
					}
								
				} else { // a starts ends before x or a starts after x+r  --- add the same into new
					ArrayList<Double> temp = new ArrayList<Double>();
					temp.add(prev);
					temp.add(prevRange);
					waternRange.add(temp);
				}
				
			
			}
		}
		
		prev2cur.clear();
		
		System.out.println("water & range");
		for(int i =0;i< waternRange.size();i++) {
		for(int j=0;j<waternRange.get(i).size();j++) {
			System.out.print(waternRange.get(i).get(j));
			System.out.print("-");
		}
		System.out.println("****");
	}
		
		
		
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
		
		
		
ArrayList<ArrayList<Double>> lightnRange = new ArrayList<ArrayList<Double>>(); // should change for each
		
		for(int index=0;index < waternRange.size();index++) {   // ---- change
			
			Double prev = waternRange.get(index).get(0);  // a      --- change
			Double prevRange = waternRange.get(index).get(1);  // b    --- change
			
			
			for(int ind=0; ind < prev2cur.size();ind++) {
				Double curStart = prev2cur.get(ind).get(0); // y
				Double mapPrev = prev2cur.get(ind).get(1);  // x
				Double range = prev2cur.get(ind).get(2); // r
				
				
				
				
if(prev>=mapPrev && prev < mapPrev+range) {

					
					if(prevRange <= range) {
						
						ArrayList<Double> temp = new ArrayList();
//						temp.add(prev);
						temp.add(curStart+prev-mapPrev);
						temp.add(prevRange);
						lightnRange.add(temp);  // should change for each			
						
					} else {
						
						ArrayList<Double> temp = new ArrayList();
//						temp.add(prev);
						temp.add(curStart+prev-mapPrev);
						temp.add(mapPrev+range-prev);
						lightnRange.add(temp);  // should change for each
						
						ArrayList<Double> temp2 = new ArrayList<Double>();
						temp2.add(curStart+range);
						temp2.add(prev+prevRange-mapPrev-range);
						lightnRange.add(temp2);
					}
					
				}
				else if(prev<mapPrev && prevRange > mapPrev-prev) {
					
					
					if(range <= prev+prevRange-mapPrev ) {
						ArrayList<Double> temp = new ArrayList<Double>();
						temp.add(prev);
						temp.add(mapPrev-prev);
						lightnRange.add(temp);
						
						ArrayList<Double> temp2 = new ArrayList<Double>();
						temp2.add(mapPrev+range);
						temp2.add(prev+prevRange-mapPrev-range);
						lightnRange.add(temp2);
						
						ArrayList<Double> temp3 = new ArrayList<Double>();
						temp3.add(curStart);
						temp3.add(range);
						lightnRange.add(temp3);
						
						
					} else {
						ArrayList<Double> temp = new ArrayList<Double>();
						temp.add(prev);
						temp.add(mapPrev-prev);
						lightnRange.add(temp);
						
						ArrayList<Double> temp2 = new ArrayList<Double>();
						temp2.add(mapPrev);
						temp2.add(prev+prevRange-mapPrev);
						lightnRange.add(temp2);		
						
					}
								
				} else { // a starts ends before x or a starts after x+r  --- add the same into new
					ArrayList<Double> temp = new ArrayList<Double>();
					temp.add(prev);
					temp.add(prevRange);
					lightnRange.add(temp);
				}
				
				
//				if(prev>=mapPrev && prev < mapPrev+range) {
//					ArrayList<Double> temp = new ArrayList();
////					temp.add(prev);
//					temp.add(curStart+prev-mapPrev);
//					temp.add(Math.min(prevRange, range));
//					lightnRange.add(temp);  // should change for each
//				}
//				else if(prev<mapPrev && prevRange > mapPrev-prev) {
//					ArrayList<Double> temp = new ArrayList();
////					temp.add(mapPrev);
//					temp.add(curStart);
//					temp.add(Math.min(range, prev+prevRange-mapPrev));
//					lightnRange.add(temp);  // should change for each	
//				}
			}
		}
		prev2cur.clear();
		
		
		System.out.println("light & range");
		for(int i =0;i< lightnRange.size();i++) {
		for(int j=0;j<lightnRange.get(i).size();j++) {
			System.out.print(lightnRange.get(i).get(j));
			System.out.print("-");
		}
		System.out.println("****");
	}
		
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
		
		
		
ArrayList<ArrayList<Double>> tempnRange = new ArrayList<ArrayList<Double>>(); // should change for each
		
		for(int index=0;index < lightnRange.size();index++) {   // ---- change
			
			Double prev = lightnRange.get(index).get(0);  // a      --- change
			Double prevRange = lightnRange.get(index).get(1);  // b    --- change
			
			
			for(int ind=0; ind < prev2cur.size();ind++) {
				Double curStart = prev2cur.get(ind).get(0); // y
				Double mapPrev = prev2cur.get(ind).get(1);  // x
				Double range = prev2cur.get(ind).get(2); // r
				
				
				
				
				
if(prev>=mapPrev && prev < mapPrev+range) {

					
					if(prevRange <= range) {
						
						ArrayList<Double> temp = new ArrayList();
//						temp.add(prev);
						temp.add(curStart+prev-mapPrev);
						temp.add(prevRange);
						tempnRange.add(temp);  // should change for each			
						
					} else {
						
						ArrayList<Double> temp = new ArrayList();
//						temp.add(prev);
						temp.add(curStart+prev-mapPrev);
						temp.add(mapPrev+range-prev);
						tempnRange.add(temp);  // should change for each
						
						ArrayList<Double> temp2 = new ArrayList<Double>();
						temp2.add(curStart+range);
						temp2.add(prev+prevRange-mapPrev-range);
						tempnRange.add(temp2);
					}
					
				}
				else if(prev<mapPrev && prevRange > mapPrev-prev) {
					
					
					if(range <= prev+prevRange-mapPrev ) {
						ArrayList<Double> temp = new ArrayList<Double>();
						temp.add(prev);
						temp.add(mapPrev-prev);
						tempnRange.add(temp);
						
						ArrayList<Double> temp2 = new ArrayList<Double>();
						temp2.add(mapPrev+range);
						temp2.add(prev+prevRange-mapPrev-range);
						tempnRange.add(temp2);
						
						ArrayList<Double> temp3 = new ArrayList<Double>();
						temp3.add(curStart);
						temp3.add(range);
						tempnRange.add(temp3);
						
						
					} else {
						ArrayList<Double> temp = new ArrayList<Double>();
						temp.add(prev);
						temp.add(mapPrev-prev);
						tempnRange.add(temp);
						
						ArrayList<Double> temp2 = new ArrayList<Double>();
						temp2.add(mapPrev);
						temp2.add(prev+prevRange-mapPrev);
						tempnRange.add(temp2);		
						
					}
								
				} else { // a starts ends before x or a starts after x+r  --- add the same into new
					ArrayList<Double> temp = new ArrayList<Double>();
					temp.add(prev);
					temp.add(prevRange);
					tempnRange.add(temp);
				}
				
				
				
//				if(prev>=mapPrev && prev < mapPrev+range) {
//					ArrayList<Double> temp = new ArrayList();
////					temp.add(prev);
//					temp.add(curStart+prev-mapPrev);
//					temp.add(Math.min(prevRange, range));
//					tempnRange.add(temp);  // should change for each
//				}
//				else if(prev<mapPrev && prevRange > mapPrev-prev) {
//					ArrayList<Double> temp = new ArrayList();
////					temp.add(mapPrev);
//					temp.add(curStart);
//					temp.add(Math.min(range, prev+prevRange-mapPrev));
//					tempnRange.add(temp);  // should change for each	
//				}
			}
		}
		prev2cur.clear();
		
		
		System.out.println("temp & range");
		for(int i =0;i< tempnRange.size();i++) {
		for(int j=0;j<tempnRange.get(i).size();j++) {
			System.out.print(tempnRange.get(i).get(j));
			System.out.print("-");
		}
		System.out.println("****");
	}
		
		
//		for(int index=0;index < storePrev.size();index++) {
//			
//			Double prev = storePrev.get(index);
//			
//			for(int ind=0;ind<prev2cur.size();ind++) {
//						Double mapPrev = prev2cur.get(ind).get(1);
//						Double range = prev2cur.get(ind).get(2);
//						
//				if(prev >= mapPrev && prev < mapPrev+range  ) {
//					Double cur = prev2cur.get(ind).get(0) + (prev-mapPrev);
//					storePrev.set(index, cur);
//				}
//			}
//		}
		
		
		
		
		
		
		
		
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
		
		
		
		
ArrayList<ArrayList<Double>> humidnRange = new ArrayList<ArrayList<Double>>(); // should change for each
		
		for(int index=0;index < tempnRange.size();index++) {   // ---- change
			
			Double prev = tempnRange.get(index).get(0);  // a      --- change
			Double prevRange = tempnRange.get(index).get(1);  // b    --- change
			
			
			for(int ind=0; ind < prev2cur.size();ind++) {
				Double curStart = prev2cur.get(ind).get(0); // y
				Double mapPrev = prev2cur.get(ind).get(1);  // x
				Double range = prev2cur.get(ind).get(2); // r
				
				
				
				
if(prev>=mapPrev && prev < mapPrev+range) {

					
					if(prevRange <= range) {
						
						ArrayList<Double> temp = new ArrayList();
//						temp.add(prev);
						temp.add(curStart+prev-mapPrev);
						temp.add(prevRange);
						humidnRange.add(temp);  // should change for each			
						
					} else {
						
						ArrayList<Double> temp = new ArrayList();
//						temp.add(prev);
						temp.add(curStart+prev-mapPrev);
						temp.add(mapPrev+range-prev);
						humidnRange.add(temp);  // should change for each
						
						ArrayList<Double> temp2 = new ArrayList<Double>();
						temp2.add(curStart+range);
						temp2.add(prev+prevRange-mapPrev-range);
						humidnRange.add(temp2);
					}
					
				}
				else if(prev<mapPrev && prevRange > mapPrev-prev) {
					
					
					if(range <= prev+prevRange-mapPrev ) {
						ArrayList<Double> temp = new ArrayList<Double>();
						temp.add(prev);
						temp.add(mapPrev-prev);
						humidnRange.add(temp);
						
						ArrayList<Double> temp2 = new ArrayList<Double>();
						temp2.add(mapPrev+range);
						temp2.add(prev+prevRange-mapPrev-range);
						humidnRange.add(temp2);
						
						ArrayList<Double> temp3 = new ArrayList<Double>();
						temp3.add(curStart);
						temp3.add(range);
						humidnRange.add(temp3);
						
						
					} else {
						ArrayList<Double> temp = new ArrayList<Double>();
						temp.add(prev);
						temp.add(mapPrev-prev);
						humidnRange.add(temp);
						
						ArrayList<Double> temp2 = new ArrayList<Double>();
						temp2.add(mapPrev);
						temp2.add(prev+prevRange-mapPrev);
						humidnRange.add(temp2);		
						
					}
								
				} else { // a starts ends before x or a starts after x+r  --- add the same into new
					ArrayList<Double> temp = new ArrayList<Double>();
					temp.add(prev);
					temp.add(prevRange);
					humidnRange.add(temp);
				}
				
				
				
//				if(prev>=mapPrev && prev < mapPrev+range) {
//					ArrayList<Double> temp = new ArrayList();
////					temp.add(prev);
//					temp.add(curStart+prev-mapPrev);
//					temp.add(Math.min(prevRange, range));
//					humidnRange.add(temp);  // should change for each
//				}
//				else if(prev<mapPrev && prevRange > mapPrev-prev) {
//					ArrayList<Double> temp = new ArrayList();
////					temp.add(mapPrev);
//					temp.add(curStart);
//					temp.add(Math.min(range, prev+prevRange-mapPrev));
//					humidnRange.add(temp);  // should change for each	
//				}
			}
		}
		prev2cur.clear();
		
		System.out.println("humid & range");
		for(int i =0;i< humidnRange.size();i++) {
		for(int j=0;j<humidnRange.get(i).size();j++) {
			System.out.print(humidnRange.get(i).get(j));
			System.out.print("-");
		}
		System.out.println("****");
	}
		
		
//		for(int index=0;index < storePrev.size();index++) {
//			
//			Double prev = storePrev.get(index);
//			
//			for(int ind=0;ind<prev2cur.size();ind++) {
//						Double mapPrev = prev2cur.get(ind).get(1);
//						Double range = prev2cur.get(ind).get(2);
//						
//				if(prev >= mapPrev && prev < mapPrev+range  ) {
//					Double cur = prev2cur.get(ind).get(0) + (prev-mapPrev);
//					storePrev.set(index, cur);
//				}
//			}
//		}
		
		
		
		
		
		
		
		
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
		ArrayList<Double> temp1 = new ArrayList(3);
		for(String s : map) temp1.add(Double.parseDouble(s));
		prev2cur.add(temp1);
		
		
		
		
ArrayList<ArrayList<Double>> locnRange = new ArrayList<ArrayList<Double>>(); // should change for each
		
		for(int index=0;index < humidnRange.size();index++) {   // ---- change
			
			Double prev = humidnRange.get(index).get(0);  // a      --- change
			Double prevRange = humidnRange.get(index).get(1);  // b    --- change
			
			
			for(int ind=0; ind < prev2cur.size();ind++) {
				Double curStart = prev2cur.get(ind).get(0); // y
				Double mapPrev = prev2cur.get(ind).get(1);  // x
				Double range = prev2cur.get(ind).get(2); // r
				
				
				
				
if(prev>=mapPrev && prev < mapPrev+range) {

					
					if(prevRange <= range) {
						
						ArrayList<Double> temp = new ArrayList();
//						temp.add(prev);
						temp.add(curStart+prev-mapPrev);
						temp.add(prevRange);
						locnRange.add(temp);  // should change for each			
						
					} else {
						
						ArrayList<Double> temp = new ArrayList();
//						temp.add(prev);
						temp.add(curStart+prev-mapPrev);
						temp.add(mapPrev+range-prev);
						locnRange.add(temp);  // should change for each
						
						ArrayList<Double> temp2 = new ArrayList<Double>();
						temp2.add(curStart+range);
						temp2.add(prev+prevRange-mapPrev-range);
						locnRange.add(temp2);
					}
					
				}
				else if(prev<mapPrev && prevRange > mapPrev-prev) {
					
					
					if(range <= prev+prevRange-mapPrev ) {
						ArrayList<Double> temp = new ArrayList<Double>();
						temp.add(prev);
						temp.add(mapPrev-prev);
						locnRange.add(temp);
						
						ArrayList<Double> temp2 = new ArrayList<Double>();
						temp2.add(mapPrev+range);
						temp2.add(prev+prevRange-mapPrev-range);
						locnRange.add(temp2);
						
						ArrayList<Double> temp3 = new ArrayList<Double>();
						temp3.add(curStart);
						temp3.add(range);
						locnRange.add(temp3);
						
						
					} else {
						ArrayList<Double> temp = new ArrayList<Double>();
						temp.add(prev);
						temp.add(mapPrev-prev);
						locnRange.add(temp);
						
						ArrayList<Double> temp2 = new ArrayList<Double>();
						temp2.add(mapPrev);
						temp2.add(prev+prevRange-mapPrev);
						locnRange.add(temp2);		
						
					}
								
				} else { // a starts ends before x or a starts after x+r  --- add the same into new
					ArrayList<Double> temp = new ArrayList<Double>();
					temp.add(prev);
					temp.add(prevRange);
					locnRange.add(temp);
				}
				
				
//				if(prev>=mapPrev && prev < mapPrev+range) {
//					ArrayList<Double> temp = new ArrayList();
////					temp.add(prev);
//					temp.add(curStart+prev-mapPrev);
//					temp.add(Math.min(prevRange, range));
//					locnRange.add(temp);  // should change for each
//				}
//				else if(prev<mapPrev && prevRange > mapPrev-prev) {
//					ArrayList<Double> temp = new ArrayList();
////					temp.add(mapPrev);
//					temp.add(curStart);
//					temp.add(Math.min(range, prev+prevRange-mapPrev));
//					locnRange.add(temp);  // should change for each	
//				}
			}
		}
	
		
		System.out.println("location & range");
		for(int i =0;i< locnRange.size();i++) {
		for(int j=0;j<locnRange.get(i).size();j++) {
			System.out.print(locnRange.get(i).get(j));
			System.out.print("-");
		}
		System.out.println("****");
	}

		
		
		
			// some problem here
//		Double res = Double.MAX_VALUE;
		Double res = locnRange.get(0).get(0);
		
		for(int ind =1;ind<locnRange.size();ind++) {
			Double cur = locnRange.get(ind).get(0);
			if(cur < res) res=cur;
		}
//		
		
		System.out.println(res);
		
		
	}

}
