import java.util.*;
public class Solution {
	public static class Node {
		int val;
		int seq;
		boolean visited;
		ArrayList<Integer> children;
		public Node(int val){
			this.val = val;
			this.visited = false;
			this.children = new ArrayList<Integer>();
			this.seq = -1;
		}
	}
	public static int sequence;
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] rst = new int[numCourses];
        if (numCourses <= 0 ) {
        	return rst;
        } else if (prerequisites == null || prerequisites.length == 0 ||
            prerequisites[0] == null || prerequisites[0].length == 0) {
            //No schedule dependency, so basically any order
            for (int i = 0; i < numCourses; i++) {
                rst[i] = i;
            }  
            return rst;
        }
        HashMap<Integer, Node> map = new HashMap<Integer, Node>();
       

        //Build node tree
        for (int i = 0; i < prerequisites.length; i++) {
        	Node node;
        	//Add curr nodes
        	if (map.containsKey(prerequisites[i][0])) {
        		node = map.get(prerequisites[i][0]);
        	} else {
        		node = new Node(prerequisites[i][0]);	
        	}
        	node.children.add(prerequisites[i][1]);
        	map.put(node.val, node);
        	//Add Child nodes
        	if (!map.containsKey(prerequisites[i][1])) {
        	    map.put(prerequisites[i][1], new Node(prerequisites[i][1]));
        	}
        }
        /*
        if (numCourses > map.size()) {
            return rst;
        }*/
        System.out.println("Map size" + map.size());
        sequence = map.size() - 1;
        //DFS
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < prerequisites.length; i++) {
        	if (!DFS(prerequisites[i][0], map, queue)) {
        		return new int[]{};
        	}
        }
        rst = new int[map.size()];
        //Output sequence
        for (int i = 0; i < map.size(); i++) {
        	
        	rst[i] = queue.poll();
        	System.out.println(rst[i]);
        }
        return rst;
    }

    public static boolean DFS(int val, HashMap<Integer, Node> map, Queue<Integer> queue) {
    	Node node = map.get(val);
    	node.visited = true;
    	map.put(val, node);
    	
    	for (int child : node.children) {
    		if (map.get(child).visited && map.get(child).seq == -1) {//Check cycle
    			return false;
    		} else if (!map.get(child).visited) {
    			if(!DFS(child, map, queue)){
					return false;
				}
    		}
    	}
    	if (node.seq == -1) {
    		node.seq = sequence--;
	    	queue.offer(node.val);//Add that sequence to stack
	    	//System.out.println(node.val + " " + stack.peek() + " " + node.seq);
	    	map.put(val, node);
    	}
    	return true;
    }

    public static void main(String[] args){
    	System.out.println("START");
    	int[][] input = {{1,0}};
    	int[] rst = findOrder(3, input);
    	for (int i = 0; i < rst.length; i++) {
    		System.out.println(rst[i]);
    	}
    	System.out.println("END " + rst.length);
    }
}










