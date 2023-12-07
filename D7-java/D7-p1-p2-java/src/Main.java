import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class Main {

    public static final HashMap<Character,Integer> MAP;
    static {
        // A, K, Q, J, T, 9, 8, 7, 6, 5, 4, 3, or 2
        MAP = new HashMap<>();
        MAP.put('A',14);
        MAP.put('K',13);
        MAP.put('Q',12);
//        MAP.put('J',11); for part-1
        MAP.put('T',10);
        MAP.put('9',9);
        MAP.put('8',8);
        MAP.put('7',7);
        MAP.put('6',6);
        MAP.put('5',5);
        MAP.put('4',4);
        MAP.put('3',3);
        MAP.put('2',2);
        MAP.put('J',1); // for part-2

    }


    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        String path = sc.nextLine();
        sc.close();


        String content = new String(Files.readAllBytes(Paths.get(path)));
        String[] lines = content.split("\n");


        PriorityQueue<Hand> pq = new PriorityQueue<>(1000,new CustomCompare());
        int games = lines.length;

        for(String line : lines ){

            String[] handNbet = line.split("\\s");

            Hand a = new Hand();
            a.cardStr = handNbet[0];
            a.bet = Long.parseLong(handNbet[1]);

//            a.priority = getPriority(a.cardStr); // for part-1
            a.priority = getPriority2(a.cardStr); // for part-2

            pq.add(a);
        }

        // now remove and calculate
        long result=0;

        int multiplier = 1;
        while(!pq.isEmpty()){
            Hand p = pq.poll();
            result+=multiplier*p.bet;
            multiplier++;
        }

        System.out.println("*****");
        System.out.println(result);
    }


    static int getPriority2(String hand){
        char[] temp = hand.toCharArray();
        Arrays.sort(temp);

        Map<Character,Integer>mp = new HashMap<>();

        for(char ch : temp){
            if(mp.containsKey(ch)){
                mp.put(ch, mp.get(ch) + 1);
            } else {
                mp.put(ch,1);
            }
        }

        if(mp.size()==1) return 7; // 1 char -- 5 times -- 7

        int Jcnt=0;
        if(mp.containsKey('J')) Jcnt=mp.get('J');

        if(Jcnt==0) return getPriority(hand); // will follow the normal rule

        if(Jcnt==4) return 7; // will copy the another one

        // 1,2,3 -- remaining j condition

        if(Jcnt==1){
            if(mp.size()==5) return 2; // J + another --- one pair(2)
           else if(mp.size()==4) return 4; // J + 2same= 3same + 2 diff
           else if(mp.size()==3) {
                // 3 same + J + 1 diff  or 2 same + j + 2 same
                for(Map.Entry<Character,Integer> entry : mp.entrySet()){
                    if(entry.getValue()==3) return 6; // four of a kind
                }
                // full house -- 3 same , 2 same
                return 5;
            }
          else if(mp.size()==2) return 7; // J+ 4 same
        }

        else if(Jcnt==2){
            if(mp.size()==4) return 4; // 2J + (1,1,1) -- three of a kind
           else if(mp.size()==3) return 6;// 2J + 2 same + 1 diff == four of a kind
           else if(mp.size()==2) return 7; // 2J + 3 same
        } else {
            // 3J
            if(mp.size()==3) return 6; // 3J + (1,1) -- four of a kind
           else if(mp.size()==2) return 7; // five of kind
        }

        return 0;

    }

    static int getPriority(String hand){

        char[] temp = hand.toCharArray();
        Arrays.sort(temp);

       Map<Character,Integer>mp = new HashMap<>();

       for(char ch : temp){
           if(mp.containsKey(ch)){
               mp.put(ch, mp.get(ch) + 1);
           } else {
               mp.put(ch,1);
           }
       }

       if(mp.size()==1) return 7; // 1 char -- 5 times -- 7
       else if (mp.size()==2){
           // four of kind or full house(3 cards same, 2 cards same)
           for(Map.Entry<Character,Integer> entry : mp.entrySet()){
               if(entry.getValue()==4) return 6; // four of a kind
           }
           // if it doesn't return above then its full house
           return 5;
       }
      else if(mp.size()==3){
          // three of a kind or two pair
           for(Map.Entry<Character,Integer> entry : mp.entrySet()){
               if(entry.getValue()==3) return 4; // three of a kind
           }
            // two pair
           return 3;
       }
        else if(mp.size()==4) return 2; // one pair -- 3 distinct
        else if(mp.size()==5) return 1;  // all distinct char --- 1


        return 0;
    }

}