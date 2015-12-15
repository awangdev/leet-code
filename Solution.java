import java.util.*;
public class Solution {
    static int  index;
    static HashMap<Integer, List<Integer>> map;
    static Queue<Integer> queue;
    
    public static void ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        map = new HashMap<Integer, List<Integer>>();
        queue = new LinkedList<Integer>();
        index = 0;
        if (v1 != null) {
            map.put(0, v1);
        }    
        if (v2 != null) {
            map.put(1, v2);
        }
        
        //init with head item
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            queue.offer(entry.getValue().get(0));
            entry.getValue().remove(0);
            index++;
            if (index == map.size()) {
                index = 0;
            }
        }
    }

    public  static int next() {
        int next = queue.poll();
        while (true) {
            if (map.get(index).size() != 0) {
                queue.offer(map.get(index).get(0));
                map.get(index).remove(0);
                index++;
                if (index == map.size()) {
                    index = 0;
                }
                break;
            }
            index++;
            if (index == map.size()) {
                index = 0;
                break;
            }
        }
        return next;
    }

    public static boolean hasNext() {
        return !queue.isEmpty();
    }


    public static void main(String[] args){
    	System.out.println("START");
        List<Integer> v1 = new ArrayList<Integer>();
        List<Integer> v2 = new ArrayList<Integer>();
        v1.add(1);v1.add(3);
        v2.add(2); v2.add(4);
        ZigzagIterator(v1, v2);

        System.out.println(hasNext());

        System.out.println("END " );
    }
}










