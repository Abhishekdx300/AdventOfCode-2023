import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws IOException {
//        String path = "test3.txt";
        String path="input.txt";
        String content = new String(Files.readAllBytes(Path.of(path)));

        String[] parts  = content.split("\n\n");

        String command = parts[0];

        String[] inputLines = parts[1].split("\n");




        ArrayList<String> starts = new ArrayList<>();

        HashMap<String,String[]> map = new HashMap<>();

        for(String line : inputLines){

            String cur = line.substring(0,3);
            String curLeft = line.substring(7,10);
            String curRight = line.substring(12,15);

            String[] targets = {curLeft,curRight};

            map.put(cur,targets);

            if(cur.charAt(2)=='A') starts.add(cur);



        }

//        int res = part1(map,command);  // for part-1
        long res = part2again(map,command,starts);

        System.out.println("****");
        System.out.println(res);

    }


    private static long part2again(HashMap<String, String[]>map,String command, ArrayList<String>positions){
        long steps=0;
        int len = command.length();

        ArrayList<Integer> allStepCounts = new ArrayList<>();

        for(String current : positions){


            int stepCount=0;

            while(current.charAt(2)!='Z'){

                if(command.charAt(stepCount%len)=='L') current = map.get(current)[0];
                else current = map.get(current)[1];
                stepCount++;
            }

            allStepCounts.add(stepCount);
        }

        steps = findLCM(allStepCounts);

        return steps;
    }

    static long findLCM(ArrayList<Integer> mem){
        long maxelem =0;
        for(int m : mem) if(maxelem<m) maxelem = m;

        int i=1;
        long cur = maxelem;
        while(true){
             cur = maxelem*i;
            boolean b = true;
            for(long m : mem)
                if (cur % m != 0) {
                    b = false;
                    break;
                }
            if(b) break;
            i++;
        }
        return cur;
    }

    // didn't run
    private static int part2(HashMap<String, String[]> map, String command, ArrayList<String>positions) {

        int steps=0;
        long len = command.length();

        // cycles
        ArrayList<ArrayList<Long>> cycles = new ArrayList<>();

        for(String current : positions){
            ArrayList<Long> cycle= new ArrayList<>();

            long stepcount=0;
            String firstZ = null;

            while(true){
                while(stepcount==0 || !current.endsWith("Z")){
                    stepcount++;
                    if(command.charAt(Integer.parseInt(String.valueOf(stepcount%len)))=='L') current = map.get(current)[0];
                    else current = map.get(current)[1];
                }
                cycle.add(stepcount);

                if(firstZ==null){
                    firstZ=current;
                    stepcount=0;
                } else if (current.equals(firstZ)) {
                    break;
                }
            }
            cycles.add(cycle);
        }

        return steps;
    }

    private static int part1(HashMap<String,String[]> map,String command){

        String cur = "AAA";
        int steps=0;
        int len = command.length();

        while(!Objects.equals(cur, "ZZZ")){
        char curChar = command.charAt(steps%len);

        if(curChar=='L'){
            cur = map.get(cur)[0];
        } else{
            cur=map.get(cur)[1];
        }
        steps++;
        }

        return steps;
    }


}