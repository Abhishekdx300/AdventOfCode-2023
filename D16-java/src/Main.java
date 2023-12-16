import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String path = "input.txt";

        String[] lines = new String(Files.readAllBytes(Path.of(path))).split("\n");
        ArrayList<ArrayList<Character>> grid = new ArrayList<>();
        for(String line : lines){

            ArrayList<Character> row = new ArrayList<>();

            for(int i=0;i< line.length();i++){
                char ch = line.charAt(i);
                row.add(ch);
            }
            grid.add(row);
        }

        int n = grid.size();
        int m = grid.get(0).size();

        int maxVal=0;

        for(int i=0;i<n;i++){
            maxVal=Math.max(maxVal,calc(i,-1,0,1,n,m,grid));
            maxVal=Math.max(maxVal,calc(i,m,0,-1,n,m,grid));
        }

        for(int j=0;j<m;j++){
            maxVal=Math.max(maxVal,calc(-1,j,1,0,n,m,grid));
            maxVal=Math.max(maxVal,calc(n,j,-1,0,n,m,grid));
        }

        System.out.println(maxVal);

    }

    private static int calc(int row, int col, int delrow, int delcol,int n,int m,ArrayList<ArrayList<Character>>grid){
        HashSet<int[]>set = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();

        // r, c, dr, dc
        int[] arr = {row,col,delrow,delcol};
        queue.add(arr);
        while(!queue.isEmpty()){
            int[] elem = queue.poll();

            int r = elem[0];
            int c = elem[1];
            int dr = elem[2];
            int dc = elem[3];



            r+=dr;
            c+=dc;

            if(r<0 || r>=n || c<0 || c>=m) continue;

            char cur = grid.get(r).get(c);


            if(cur=='.' || (cur=='-' && dc!=0) || (cur=='|'&& dr!=0)){
                int[] newone = {r,c,dr,dc};
                if(!visited(set,newone)){
                    set.add(newone);
                    queue.add(newone);
                }
            }
            else if(cur=='/'){
                // (dr, dc) --> (-dc,-dr)
                int temp = dr;
                dr = -dc;
                dc=-temp;
                int[] newone = {r,c,dr,dc};
                if(!visited(set,newone)){
                    set.add(newone);
                    queue.add(newone);
                }
            }
            else if(cur=='\\'){
                // (dr, dc) --> (dc,dr)
                int temp = dr;
                dr = dc;
                dc=temp;
                int[] newone = {r,c,dr,dc};
                if(!visited(set,newone)){
                    set.add(newone);
                    queue.add(newone);
                }
            }
            else {
                if(cur=='|'){
                    int[][] poss = {{1,0},{-1,0}};
                    for(int i=0;i<2;i++){
                        dr = poss[i][0];
                        dc = poss[i][1];
                        int[] newone = {r,c,dr,dc};
                        if(!visited(set,newone)){
                            set.add(newone);
                            queue.add(newone);
                        }
                    }
                }
                else{
                    int[][] poss = {{0,1},{0,-1}};
                    for(int i=0;i<2;i++){
                        dr = poss[i][0];
                        dc = poss[i][1];
                        int[] newone = {r,c,dr,dc};
                        if(!visited(set,newone)){
                            set.add(newone);
                            queue.add(newone);
                        }
                    }
                }
            }


        }

        HashSet<int[]> cord = new HashSet<>();

        for(int[] Arr : set){
            if(!has(cord,Arr))
                cord.add(new int[]{Arr[0],Arr[1]});
        }

        return cord.size();
    }
    private static boolean has(HashSet<int[]>set,int[]arr){
        for(int[] s : set){
            if(s[0]==arr[0] && s[1]==arr[1]) return true;
        }
        return false;
    }
    private static boolean visited(HashSet<int[]>set,int[]arr){
        for(int[] s : set){
            if(s[0]==arr[0] && s[1]==arr[1] && s[2]==arr[2] && s[3]==arr[3]) return true;
        }
    return false;
    }

}