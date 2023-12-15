import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {


    private static int hash(String seq){
        int curVal = 0;

        for(int i=0;i<seq.length();i++){
            char ch= seq.charAt(i);
            // add ascii to curval
            curVal+= (int) ch;
            // *17
            curVal=curVal*17;
            // %256
            curVal=curVal%256;
        }
        return curVal;
    }


    private static boolean isSame(String s , String t){
        if(s.length()!=t.length()) return false;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)!=t.charAt(i)) return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        String path = "input.txt";

        String[] seqs = new String(Files.readAllBytes(Path.of(path))).split(",");

        HashMap<Integer, ArrayList<String>>hashmap = new HashMap<Integer,ArrayList<String>>();

        HashMap<String,Integer> map = new HashMap<>();

        ArrayList<ArrayList<String>> grid = new ArrayList<>();



        for(String seq : seqs) {

            if (seq.contains("=")) {
                String[] parts = seq.split("=");
                String label = parts[0];
                String focLen = parts[1];
                int boxnum = hash(label);

                ArrayList<String> str = hashmap.get(boxnum);
                boolean found = false;
                if (str != null) {
                    ArrayList<String> curStr = new ArrayList<String>();
                    for (String s : str) {
                        if (isSame(label, s.split("\\s")[0])){
                            String n = label + " " + focLen;
                            curStr.add(n);
                            found = true;
                        } else {
                            curStr.add(s);
                        }
                    }


                    if (!found) curStr.add(label + " " + focLen);

                    hashmap.put(boxnum, curStr);

                } else {
                    ArrayList<String> s = new ArrayList<>();
                    String n = label + " " + focLen;
                    s.add(n);
                    hashmap.put(boxnum, s);
                }


            } else {
                String label = seq.split("-")[0];
                int boxnum = hash(label);

                ArrayList<String> str = hashmap.get(boxnum);
                ArrayList<String> curStr = new ArrayList<>();
                if(str!=null){
                    for(String s : str){
                        if(!isSame(label,s.split("\\s")[0])){
                            curStr.add(s);
                        }
                    }
                    hashmap.put(boxnum,curStr);
                }

            }

        }
            int res=0;

            for(Map.Entry<Integer,ArrayList<String>> entry : hashmap.entrySet()){
                ArrayList<String>str = entry.getValue();
                int boxNum = entry.getKey()+1;
                int slot=1;
                for(String s : str){
                    int focLen = Integer.parseInt(String.valueOf(s.charAt(s.length()-1))) ;
                    int curVal = (boxNum*slot*focLen);
                    System.out.println(boxNum);
                    System.out.println(slot);
                    System.out.println(focLen);
                    System.out.println(curVal);
                    res+=curVal;
                    slot++;
                }
                System.out.println("****");
            }


            System.out.println("anser");
            System.out.println(res);


    }
}