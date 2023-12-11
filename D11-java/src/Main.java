import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        String path = "input.txt";
        String[] lines = new String(Files.readAllBytes(Path.of(path))).split("\n");
        ArrayList<Integer> freeRows = new ArrayList<>(lines.length);
        ArrayList<Integer> freeCols = new ArrayList<>(lines[0].length());

        for(int i=0;i<lines.length;i++) freeRows.add(i);
        for(int i=0;i<lines[0].length();i++) freeCols.add(i);

        ArrayList<ArrayList<Integer>> points = new ArrayList<>();
        for(int i=0;i< lines.length;i++){
            ArrayList<String> line = new ArrayList<>();
            for(int j=0;j< lines[0].length();j++){
            if(lines[i].charAt(j)!='.') {

                if(freeRows.contains(i)) freeRows.remove((Integer) i);
                if(freeCols.contains(j)) freeCols.remove((Integer) j);
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(i);
                temp.add(j);
                points.add(temp);
            }

            }
        }

        part(points,freeRows,freeCols);
    }

    private static void part(ArrayList<ArrayList<Integer>>points, ArrayList<Integer>freeRows, ArrayList<Integer>freeCols){
        Long result= 0L;

        for(int ind=0;ind<points.size();ind++){
            Integer strRow = points.get(ind).get(0);
            Integer strCol = points.get(ind).get(1);
            for(int nxt = ind+1;nxt< points.size();nxt++){
                Integer destRow = points.get(nxt).get(0);
                Integer destCol = points.get(nxt).get(1);

                long rowDist = (long) Math.abs(strRow-destRow);
                long colDist = (long) Math.abs(strCol-destCol);

                    // 1000000 -- part-2
                for(int i=Math.min(strRow,destRow)+1;i<Math.max(strRow,destRow);i++){
                    if(freeRows.contains((Integer) i)) rowDist+=999999;
                }

                for(int i=Math.min(strCol,destCol)+1;i<Math.max(strCol,destCol);i++){
                    if(freeCols.contains((Integer) i)) colDist+=999999;
                }

                result+=rowDist+colDist;

            }
        }

        System.out.println("Answer");
        System.out.println(result);
    }
}


