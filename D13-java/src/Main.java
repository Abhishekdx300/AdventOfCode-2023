import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    private static boolean check(String s, String t, ArrayList<Integer>smudge){

        int l = s.length();
        int notmatch=0;
        for(int i=0;i<l;i++){
            if(s.charAt(i)!=t.charAt(i)) notmatch++;
        }

        if(notmatch==0) return true;
        else if(notmatch==1){
            if(smudge.get(0)==1){
                smudge.set(0,0);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

    private static Integer cnt(ArrayList<String>lines){

        int maxCnt=-1;

        for(int ind=0;ind<lines.size()-1;ind++){

            int left = ind;
            int right = ind+1;
            
            ArrayList<Integer> smudge = new ArrayList<>();
            smudge.add(1); // every possible partition has exactly 1 smudge, it must be used.

            int tempCnt=0;

            while(left>=0 && right< lines.size()){
                // if(!lines.get(left).equals(lines.get(right))){  part-1
                if(!check(lines.get(left),lines.get(right),smudge)) { // part-2
                    tempCnt= -1;
                    break;
                }
                else{
                    left--;
                    right++;
                }
            }
            if(tempCnt!=-1 && smudge.get(0)==0) maxCnt=ind+1;

        }
        return maxCnt;
    }

    public static void main(String[] args) throws IOException {
        String path = "input.txt";
        String[] patterns = new String(Files.readAllBytes(Path.of(path))).split("\n\n");

        long result = 0;

        for(String pattern : patterns){

            String[] rowsArr = pattern.split("\n");
            ArrayList<String> rows = new ArrayList<>(Arrays.asList(rowsArr));


            int sz = rows.get(0).length();



            ArrayList<String> cols = new ArrayList<>();

            for(int i=0;i<sz;i++){
                StringBuilder sb = new StringBuilder();
                for(String s : rows){
                    sb.append(s.charAt(i));
                }
                cols.add(sb.toString());
            }

            int rowCnts = cnt(rows);
            int colCnts = 0;
            if(rowCnts==-1){
                colCnts=cnt(cols);
                result +=colCnts;
            } else {
                result+=rowCnts* 100L;
            }

        }

        System.out.println("Answer");
        System.out.println(result);

    }
}