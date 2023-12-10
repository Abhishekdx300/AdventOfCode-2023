import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.rmi.UnexpectedException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String path = "input.txt";
        String content = new String(Files.readAllBytes(Path.of(path)));

        String[] lines = content.split("\n");
        part1(lines);

    }

    private static void part1(String[] lines) throws UnexpectedException {

        int lineNo = 0;
        int index = 0;
        int lineInd = 0;

        ArrayList<ArrayList<String>> matrix = new ArrayList<>();

        for (String line : lines) {

            ArrayList<String> temp = new ArrayList<>(line.length());
            for (int i = 0; i < line.length(); i++) temp.add(Character.toString(line.charAt(i)));
            matrix.add(temp);

            if (line.indexOf('S') > -1) {
                index = line.indexOf('S');
                lineInd = lineNo;
                System.out.println(lineInd+" "+index);
            }
            lineNo++;
        }

        bfs2(matrix, lineInd, index);

    }

    private static boolean checkVisited(ArrayList<ArrayList<Integer>> visited, int row, int col){
        boolean isVisited = false;
        for(ArrayList<Integer> arr : visited){
            if(arr.get(0)==row && arr.get(1)==col) {
                isVisited=true;
                break;
            }
        }
        return isVisited;
    }
    private static boolean checkSet(HashSet<ArrayList<Integer>> visited, int row, int col){
        boolean isVisited = false;
        for(ArrayList<Integer> arr : visited){
            if(arr.get(0)==row && arr.get(1)==col) {
                isVisited=true;
                break;
            }
        }
        return isVisited;
    }
    private static void bfs2(ArrayList<ArrayList<String>> matrix, int startRow, int startCol) throws UnexpectedException {

        Queue<ArrayList<Integer>> queue = new LinkedList<>();
        ArrayList<ArrayList<Integer>> visited = new ArrayList<>();

        ArrayList<Integer> temp = new ArrayList<>(2);
        temp.add(startRow);
        temp.add(startCol);
        visited.add(temp);

        String dot =".";

        String[] poss = {"|","-","L","J","7","F"};
        ArrayList<String> possibleS = new ArrayList<>(Arrays.asList(poss));

        queue.add(temp);

        while (!queue.isEmpty()){
            ArrayList<Integer> cur = queue.remove();
            int row = cur.get(0);
            int col = cur.get(1);
            String ch = (matrix.get(row).get(col));


                 /*
          |    is a vertical pipe connecting north and south.
          -    is a horizontal pipe connecting east and west.
          L    is a 90-degree bend connecting north and east.
          J    is a 90-degree bend connecting north and west.
          7    is a 90-degree bend connecting south and west.
          F    is a 90-degree bend connecting south and east.
          .    is ground; there is no pipe in this tile.
         */

            //left
            if(col>0 && "S-7J".contains(ch) && "FL-".contains(matrix.get(row).get(col-1)) && !checkVisited(visited,row,col-1)){
                ArrayList<Integer> temp2 = new ArrayList<>(2);
                temp2.add(row); temp2.add(col-1);
                visited.add(temp2);
                queue.add(temp2);
                if(ch.equals("S")){
                    int i=0;
                    while(i<possibleS.size()){
                        if(!"-7J".contains(possibleS.get(i))){
                            possibleS.remove(i);
                        } else {
                            i++;
                        }
                    }
                }
            }
            // right
            if(col<matrix.get(0).size() && "SFL-".contains(ch) && "J-7".contains(matrix.get(row).get(col+1)) && !checkVisited(visited,row,col+1)){
                ArrayList<Integer> temp2 = new ArrayList<>(2);
                temp2.add(row); temp2.add(col+1);
                visited.add(temp2);
                queue.add(temp2);
                if(ch.equals("S")){
                    int i=0;
                    while(i<possibleS.size()){
                        if(!"FL-".contains(possibleS.get(i))){
                            possibleS.remove(i);
                        } else {
                            i++;
                        }
                    }
                }
            }
            //up
            if(row>0 && "SL|J".contains(ch) && "|7F".contains(matrix.get(row-1).get(col)) && !checkVisited(visited,row-1,col)){
                    ArrayList<Integer> temp2 = new ArrayList<>(2);
                    temp2.add(row-1); temp2.add(col);
                    visited.add(temp2);
                    queue.add(temp2);
                    if(ch.equals("S")){
                        int i=0;
                        while(i<possibleS.size()){
                            if(!"L|J".contains(possibleS.get(i))){
                                possibleS.remove(i);
                            } else {
                                i++;
                            }
                        }
                    }
                }
            // down
            if(row<matrix.size()-1 && "S|7F".contains(ch) && "|LJ".contains(matrix.get(row+1).get(col)) && !checkVisited(visited,row+1,col)){
                ArrayList<Integer> temp2 = new ArrayList<>(2);
                temp2.add(row+1); temp2.add(col);
                visited.add(temp2);
                queue.add(temp2);
                if(ch.equals("S")){
                    int i=0;
                    while(i<possibleS.size()){
                        if(!"|7F".contains(possibleS.get(i))){
                            possibleS.remove(i);
                        } else {
                            i++;
                        }
                    }
                }
            }

        }

        System.out.println("****");
        System.out.println(visited.size()/2); // part-1 answer --> halfway through the full loop is answer

        // set it into grid
        matrix.get(startRow).set(startCol,possibleS.get(0)); // set

        for(int row=0;row<matrix.size();row++){
            for(int col=0;col<matrix.get(0).size();col++){
                if(!checkVisited(visited,row,col)) matrix.get(row).set(col,dot);
            }
        }


        HashSet<ArrayList<Integer>> set = new HashSet<>();

        for(int r=0;r< matrix.size();r++){

            boolean within =false;
            boolean up = false;
            // -- on row if even no. if line crossed its outside, else inside
            for(int c=0;c<matrix.get(0).size();c++){
                String ch = matrix.get(r).get(c);
                if(Objects.equals(ch,"|")){
                        within=!within;  // --> line crossed
                } else if (Objects.equals(ch,"-")) {
                    // do nothing --> along the line
                } else if ("LF".contains(ch)) {
                        up = ch.equals("L"); // if L --> need 7 later for it to consider cross -- same for (f,J)
                } else if ("7J".contains(ch)) { // this checks for (L,7) or (F,J) pair
                    if(up){
                        if( !ch.equals("J")) within=!within;
                    }
                    else {
                        if(!ch.equals("7")) within=!within;
                        }
                    up=false;
                } else if (Objects.equals(ch,".")) {
                    // do nothing
                } else {
                 throw new UnexpectedException("Unexpected character--here"+ch); // not possible
                }

                if(!within){
                    ArrayList<Integer> tempo = new ArrayList<>();
                    tempo.add(r);
                    tempo.add(c);
                    set.add(tempo);
                }
            }

        }

        // that means  total - (outside+visited (they might overlap))---> answer

        int outside =0;
        int visitedOnly =0;
        for(int i=0;i<matrix.size();i++){
            for (int j=0;j<matrix.get(0).size();j++){

                boolean isVisited = checkVisited(visited,i,j);
                boolean isOutside = checkSet(set,i,j);
            if(isVisited && !isOutside) visitedOnly++;
            else if(isOutside) outside++;  // if both are true...will be added in this
            }
        }

        System.out.println("Answer");
        int answer = matrix.size()*matrix.get(0).size()-outside-visitedOnly;
        System.out.println(answer); // part-2 answer

    }

}