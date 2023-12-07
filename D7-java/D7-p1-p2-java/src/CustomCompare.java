import java.util.Comparator;
import java.util.HashMap;

class CustomCompare implements Comparator<Hand> {


    @Override
    public int compare(Hand o1, Hand o2) {
        if (o1.priority != o2.priority)
       return o1.priority > o2.priority ? 1 : -1;
        else
            return compareString(o1.cardStr,o2.cardStr);
    }

    private int compareString(String one, String two) {
        int i=0;
        while(i<one.length()){
            if(one.charAt(i)!=two.charAt(i)){
                return Main.MAP.get(one.charAt(i)) - Main.MAP.get(two.charAt(i));
            }
            i++;
        }
        return -1;
    }
}
