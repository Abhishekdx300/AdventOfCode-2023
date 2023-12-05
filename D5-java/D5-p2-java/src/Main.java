import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		String path = sc.nextLine();
		sc.close();


        String content = new String(Files.readAllBytes(Paths.get(path)));
        String[] blocks = content.split("\n\n"); // each block is separated by two line breaks...


        String seedstr = blocks[0].substring(7);  // extracting the seeds
        String[] seedsdiv = seedstr.split("\\s");   // splitting seeds

        ArrayList<ArrayList<Long>> seedRange = new ArrayList<>();  // will store all ranges...currently seed Ranges

        for(int i=0;i<seedsdiv.length;i+=2){
            Long s = Long.parseLong(seedsdiv[i]);
            Long r = Long.parseLong(seedsdiv[i+1]);

            ArrayList<Long> t = new ArrayList<>() {{
                add(s);
                add(s + r);
            }};

            seedRange.add(t);
        }


        for(int ind=1;ind<blocks.length;ind++){ //--ignoring 1st block which is for seeds

            String[] splittedLines = blocks[ind].split("\n");  // splitting the blocks into lines


            ArrayList<ArrayList<Long>> ranges = new ArrayList<>();  // will store the current block ranges

            for(int i=1;i<splittedLines.length;i++){
              // text line ignored
                String[] numline = splittedLines[i].split("\\s"); // single line -- splitted into nums strs


                ArrayList<Long> temp = new ArrayList<>(){
                    {
                        add(Long.parseLong(numline[0]));
                        add(Long.parseLong(numline[1]));
                        add(Long.parseLong(numline[2]));
                    }};

                ranges.add(temp);

            } //** all ranges done for --> each block


        ArrayList<ArrayList<Long>> mew = new ArrayList<>(); // new ranges which replace the current seedRange

            while (!seedRange.isEmpty()){

                ArrayList<Long> rem = seedRange.get(0);
                seedRange.remove(0);  // removing one range --> later if needed will add it again. ( sometimes more than one is added) --> multiple parts

                Long s = rem.get(0);  // start
                Long e = rem.get(1);  // end  -- non-inclusive

                int matched = 0; // check if at least one matched or not --> if not add the same again

                for (ArrayList<Long> range : ranges) {
                    Long a = range.get(0);  // des
                    Long b = range.get(1);  // src
                    Long c = range.get(2);  // range

                    long overlapStart = Math.max(s, b);
                    long overlapEnd = Math.min(e, b + c);

                    if (overlapStart < overlapEnd) {   //** non-empty
                        matched = 1;
                        ArrayList<Long> temp = new ArrayList<>() {{
                                add(overlapStart - b + a);
                                add(overlapEnd - b + a);
                            }};
                        mew.add(temp);  // added the overlapped part in new Range

                        if (overlapStart > s) {  // has some remaining before the overlapped part --> add the not found part again in the seedRange
                            seedRange.add(new ArrayList<>() {{
                                add(s);
                                add(overlapStart);
                            }});
                        }
                        if (e > overlapEnd) {  // has some remaining range before the overlapped part  --> add the not found part again in the seedRange
                            seedRange.add(new ArrayList<>() {{
                                add(overlapEnd);
                                add(e);
                            }});
                        }
                        break;
                    }

                }
                if(matched==0){ // no-one matched so, it will remain same  -- as per statement , so added the range directly into new Range
                    mew.add(new ArrayList<>(){{
                        add(s);
                        add(e);
                    }});
                }

            }

            seedRange = mew;  // for the next iteration of block

        }

        Long res = Long.MAX_VALUE;
        for (ArrayList<Long> longs : seedRange) {
            if (res > longs.get(0)) res = longs.get(0);
        }

        System.out.println("******");
        System.out.println(res);

    }
}