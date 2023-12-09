import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        String path = "input.txt";
        String content = new String(Files.readAllBytes(Path.of(path)));

        String[] lines = content.split("\n");

        part1(lines);

    }

    private static void part1(String[] lines){

        int result=0;
        int result2=0; // for part-2

        for(String line : lines){
           String[] values = line.split("\\s");

            ArrayList<Integer> arr = new ArrayList<>(values.length);

            for(String val : values){
                arr.add(Integer.parseInt(val));
            }


            /*
            * for part-1 :
            *      Addition of last value of each step is the answer.
            * */


            /*                   1st       2nd  3rd  4     5  ...
            *  for part-2 :  firstValue - (ND + OD + ND + OD .... till allZero)
            *  ND -- Normal Difference --> arr[1] - arr[0]
            *  OD -- Opposite Difference --> arr[0] - arr[1]
            * */



            int pos=arr.size()-1;
            int tempRes = 0;


            int tempRes2= arr.get(0);  // 1st value taken
            tempRes2 -= arr.get(1)- arr.get(0); // 1st time done
            boolean next= false;  // if true --> ind(1) - ind(0) else, opposite

            while(!allZero(arr,pos)){
                tempRes+=arr.get(pos);
                pos--;

                // for p-2
                if(next){
                    tempRes2 -=(arr.get(1)- arr.get(0));
                    next=false;
                } else {
                    tempRes2 -=(arr.get(0)- arr.get(1));
                    next=true;
                }
            }

            result+=tempRes;

            result2+=tempRes2;

        }

        System.out.println("**********");
        System.out.println(result);
        System.out.println(result2);
    }

    private static boolean allZero(ArrayList<Integer>arr,Integer pos){
        boolean allzero = true;
        for(int i =0;i<=pos;i++){
            if(arr.get(i)!=0){
                allzero=false;
                break;
            }
        }

        for(int i=0;i<pos;i++){
            arr.set(i,arr.get(i+1)-arr.get(i));
        }

        return allzero;
    }

}