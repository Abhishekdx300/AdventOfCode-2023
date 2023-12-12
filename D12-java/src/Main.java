import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Main {

    private static long count(int ind, int arrInd,String input,ArrayList<Integer>arr,ArrayList<ArrayList<Long>>dp){

        if(ind<0){
            if(arrInd<0) return 1;
            else return  0;
        }
        if(arrInd<0){
            if(input.substring(0,ind+1).contains("#")) return 0;
            else return 1;
        }

        if(dp.get(ind).get(arrInd)!=-1) return dp.get(ind).get(arrInd);

    // ???.### 1,1,3


        long res=0L;
        // nottake
        if(".?".contains(Character.toString(input.charAt(ind))))
            res+= count(ind-1,arrInd,input,arr,dp);
        // take
        if("?#".contains(Character.toString(input.charAt(ind)))) {
            if (arr.get(arrInd) <= input.substring(0, ind + 1).length() // remaining str must be atleast the size = number at arr
                    && !input.substring(ind - arr.get(arrInd) + 1, ind + 1).contains(".") // shouldn't contain any "." till the cur no. is finished
                    && (arr.get(arrInd) == input.substring(0, ind + 1).length() || input.charAt(ind - arr.get(arrInd)) != '#')
                    // either the size of str is same as num or right after the str index eql. to num, there should be a separator ("." or "#")
            ) {
                res += count(ind - arr.get(arrInd)-1, arrInd - 1, input, arr,dp);
            }
        }

        dp.get(ind).set(arrInd,res);
        return res;
    }


    public static void main(String[] args) throws IOException {
        String path = "input.txt";

        String[] lines = new String(Files.readAllBytes(Path.of(path))).split("\n");


        long ans =0;

        for(String line : lines){

            String[] part = line.split("\\s");

            StringBuilder lineSB = new StringBuilder();

            lineSB.append(part[0]);

            // for part-2
            for(int i=1;i<=4;i++){
                lineSB.append("?");
                lineSB.append(part[0]);
            }

            String inputLine = lineSB.toString();


            String[] numsStr = part[1].split(",");
            ArrayList<Integer>arr = new ArrayList<>();
            for(String n : numsStr) arr.add(Integer.parseInt(n));

            // for part-2
            int iniSize=arr.size();
            for(int i=1;i<=4;i++){
                for(int j=0;j<iniSize;j++) arr.add(arr.get(j));
            }

            ArrayList<ArrayList<Long>> dp = new ArrayList<>();
            for(int i=0;i<=inputLine.length();i++){
                ArrayList<Long> row = new ArrayList<>();
                for(int j=0;j<=arr.size();j++){
                    row.add(-1L);
                }

                dp.add(row);

            }
            long cnt = count(inputLine.length()-1,arr.size()-1,inputLine,arr,dp);


           ans+=cnt;

        }

        System.out.println(ans);

    }
}