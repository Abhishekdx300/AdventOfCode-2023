import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        String path = sc.nextLine();
        sc.close();

        File file = new File(path);
        Scanner scanner = new Scanner(file);

        String timeStr = scanner.nextLine();
        String distStr = scanner.nextLine();

        timeStr = timeStr.substring(timeStr.indexOf(":")+1);
        distStr = distStr.substring(distStr.indexOf(":")+1);

        System.out.println(timeStr);
        System.out.println(distStr);

        String[] times = timeStr.split("\\s+"); // one or more spaces
        String[] dists = distStr.split("\\s+");
            // 1st string is a space
        part1(times,dists);
//        part2(times,dists);

    }

    public static void part1(String[] times, String[] dists){

        int n = times.length;

        long res=0;

        for(int i =1 ;i<n;i++){

            long maxTime = Integer.parseInt(times[i]);
            long minDist = Integer.parseInt(dists[i]);

             long start = findMinRange(maxTime,minDist);
             long end = findMaxRange(maxTime,minDist);

             long cnt = end - start +1;

                if(res==0) res=cnt;
                else{
                    res*=cnt;
                }

        }

        System.out.println("********");
        System.out.println(res);
    }

    public static void part2(String[] times, String[] dists){

        StringBuilder t = new StringBuilder();
        StringBuilder d = new StringBuilder();
        for(String s : times){
            if(s != "") t.append(s);
        }
        for(String s : dists){
            if(s!="") d.append(s);
        }


        long time = Long.parseLong(t.toString());
        long dist = Long.parseLong(d.toString());

        System.out.println(time+" "+dist);

        // binary search
        long s = findMinRange(time,dist);
        long e =findMaxRange(time,dist);


        System.out.println("******");
        System.out.println("("+s+","+e+")");
        System.out.println(e-s+1);
    }

    static Long findMinRange(Long maxTime, Long minDist){
        // need to return min time which is used for charge, can win;

        long left = 1L;
        long right = maxTime-1;

        long res=maxTime-1;

        while(left<=right){
            long mid = (left+right)/2;
            // if possible store it go left;
            if(possible(mid,maxTime,minDist)){
                res=mid;
                right=mid-1;
                //  else go right
            } else {
                left=mid+1;
            }
        }
        return res;
    }

    static Long findMaxRange(Long maxTime, Long minDist){
        // need to return max time which is used for charge, can win;
        long left = 1L;
        long right = maxTime-1;

        long res=1L;

        while(left<=right){
            long mid = (left+right)/2;
            // if possible store it, go right
            if(possible(mid,maxTime,minDist)){
                res=mid;
                left=mid+1;
            } else { // go left
                right = mid-1;
            }
        }
        return res;
    }
    static boolean possible(Long time, Long maxTime, Long minDist){
        long remTime = maxTime-time;
        long coverDist = time *remTime;  // time given ==== speed
        return coverDist > minDist;
    }

}