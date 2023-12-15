import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        String path = "test.txt";
        String[] rowss = new String(Files.readAllBytes(Path.of(path))).split("\n");
        ArrayList<String> grid = new ArrayList<>(Arrays.asList(rowss));


        ArrayList<ArrayList<String>> seen = new ArrayList<>();
        seen.add(grid);

        grid=zip(grid);
        int ans = modify(grid);
//
//        System.out.println(ans);

        for(String s : grid) System.out.println(s);



    }

    private static void cycle(ArrayList<String>grid){

        // row <-> col
        grid=zip(grid);
        // modify the grid
        modify(grid);
        // reverse the position of columns



    }

    private static ArrayList<String> zip(ArrayList<String> cur){
        int sz = cur.get(0).length();

        ArrayList<String> cols = new ArrayList<>();

        for(int i=0;i<sz;i++){
            StringBuilder sb = new StringBuilder();
            for(String s : cur){
                sb.append(s.charAt(i));
            }
            cols.add(sb.toString());
        }

        return cols;
    }

    private static int modify(ArrayList<String>grid){

        for(int i=0;i<grid.size();i++){
            String str = grid.get(i);
            int ind=0;
            StringBuilder main = new StringBuilder();
            StringBuilder sb = new StringBuilder();
            while (ind<str.length()){
                char ch = str.charAt(ind);
                if(ch=='#'){
                    String ns = customSort(sb.toString());
                    sb.setLength(0);
                    main.append(ns);
                    main.append('#');
                } else{
                    sb.append(ch);
                }
                ind++;
            }
            main.append(sb.toString());
            grid.set(i,main.toString());
        }



        // count here
        int res=0;
//        for(String s : grid ){
//            int i=0;
//            int n = s.length();
//            while(i<s.length() && s.charAt(i)=='O') {
//                res+=n-i;
//                i++;
//            }
//        }
    return res;
    }

    private static String customSort(String s) {
        StringBuilder sb = new StringBuilder();
        int o = 0;
        int dot = 0;
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if(ch=='O') o++;
            else dot++;
        }
        sb.append("O".repeat(Math.max(0, o)));
        sb.append(".".repeat(Math.max(0, dot)));

        return sb.toString();
    }

    private static ArrayList<String> reverseList(ArrayList<String>cols){
        cols.replaceAll(Main::reverseStr);

        return cols;
    }

    private static String reverseStr(String s) {
        StringBuilder sb = new StringBuilder(s);
        sb.reverse();

        return sb.toString();
    }

    private static int calc(String str) {
        Queue<Integer> queue = new LinkedList<>();

        int res = 0;
        int n= str.length();

        for(int i = 0;i<n;i++){
            char ch = str.charAt(i);

            if(ch=='.') queue.add(i);
            else if(ch=='#') queue.clear();
            else {
                if(queue.isEmpty()){
                    res+=n-i;
                } else {
                    int ind = queue.poll();
                    res+=n-ind;
                    queue.add(i);
                }


            }


        }

        return res;

    }
}