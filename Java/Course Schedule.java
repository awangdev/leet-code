M

有点绕，但是做过一次就明白一点。    
是topological sort的题目。一般都是给有dependency的东西排序。    

最终都会到一个sink node， 再不会有向后的dependency, 在那个点截止。    
我就已这样子的点为map的key, 然后value是以这个node为prerequisite的 list of courses.    

画个图的话，prerequisite都是指向那个sink node， 然后我们在组成map的时候，都是从sink node 发散回来到dependent nodes.    

在DFS里面，我们是反向的， 然后，最先完全visited的那个node, 肯定是最左边的node了，它被mark的seq也是最高的。    

而我们的sink node，当它所有的支线都visit完了，seq肯定都已经减到最小了，也就是0，它就是第一个被visit的。   


最终结果：
每个有pre-requisit的node都trace上去（自底向上），并且都没有发现cycle.也就说明schedule可以用了。

```
/*
There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, 
which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

For example:

2, [[1,0]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.

2, [[1,0],[0,1]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0, 
and to take course 0 you should also have finished course 1. 
So it is impossible.

Note:
The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.

click to show more hints.

Hints:
1. This problem is equivalent to finding if a cycle exists in a directed graph. 
	If a cycle exists, no topological ordering exists and therefore it will be impossible to take all courses.
2. Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic concepts of Topological Sort.
3. Topological sort could also be done via BFS.

Hide Company Tags Zenefits
Hide Tags Depth-first Search Breadth-first Search Graph Topological Sort
Hide Similar Problems (M) Course Schedule II (M) Graph Valid Tree (M) Minimum Height Trees

*/

/*
	Thoughts: Try some help. make shorter version. 
	This version does not use a class, but does the exact same algorithm: mark visited and check cycle with dfs.
	The idea is almost exaclty same (http://www.jyuan92.com/blog/leetcode-course-schedule/)
	Instead of using a seq,visited to check mark seq, and visted, we can do a 'mark' backtracking.
		This means: we can mark the visited = -1 before the dfs, and if anything in the dfs sees a -1,
			it returns false;
		After the dfs of children nodes in the map, wwe mark a node.visited = 1. That means the spot has been visited : )

	Note:
	SHould build he map: map<prerequisite, list of course based on this prerequisites>
*/

public class Solution {
	HashMap<Integer, List<Integer>> map;
	int[] visited;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0 || prerequisites == null || prerequisites.length == 0 ||
        	prerequisites[0] == null || prerequisites[0].length == 0) {
        	return true;
        }
        visited = new int[numCourses];
        map = new HashMap<Integer, List<Integer>>();
        //Put all start node into map.
        for (int i = 0; i < prerequisites.length; i++) {
        	if (!map.containsKey(prerequisites[i][1])) {
        		map.put(prerequisites[i][1], new ArrayList<Integer>());
        	}
        	map.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }
        //dfs on each start node in the pair
        for (int i = 0; i < prerequisites.length; i++) {
        	if (!dfs(prerequisites[i][0])) {
        		return false;
        	}
        }

        return true;
    }

    public boolean dfs (int node) {
    	if (visited[node] == 1) {//has been through this path, true.
    		return true;
    	}
    	if (visited[node] == -1) {//visiting a visited node from a deper level node, cycle
    		return false;
    	}
    	//mark it -1 then after dfs mark it 1. Marking and backtracking skills
    	visited[node] = -1;

    	//Visit each child and make sure there is no cycle.
    	if (map.containsKey(node)) {
    		for (int nextNode : map.get(node)) {
    			if (!dfs(nextNode)) {
    				return false;
    			}
    		}
    	}
    	
    	visited[node] = 1;
    	return true;
    }

}






/*
	Thoughts: TO LONG. Should try shorter version.
	ALSO, built the map based map<course, list of prerequisite>: this will cause trouble and hard to work if we want to return 
		the sequence of course.

	Though, no need to find the correct order of course, but we can do Topological Sort via DFS, 
	where in the process we check if there is cycle.
	0. Create node {val, visited, list of child node}. OR: just a map
	1. Put all prerequisites in map <node num, node>
	2. For loop on all nodes in the map.
		Each node, DFS on it, add all outgoing child on stack if that node is not visited
		Check on cycle: after DFS on all child, we need to mark the parent node itself to be visited. 
		At this moment, if this node has been visited once, that means a cycle has happended, then return false;

	Implementation:
	Use a node, track value, sequence number, and if visited, and its child nodes.
	In DFS: first mark curr node visited, then DFS on child, then mark curr node with a sequence number.
		Cycle: if a node has been visited but does not have a sequence number yet ,
		(that means some nodes at the earlier level, which has no been back-tracking to yet)
		then there must be a cycle backwards.
*/
public class Solution {
	public class Node {
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
	public int n;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0 || prerequisites == null || prerequisites.length == 0 ||
            prerequisites[0] == null || prerequisites[0].length == 0) {
        	return true;
        }
        HashMap<Integer, Node> map = new HashMap<Integer, Node>();
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
        n = prerequisites.length;
        for (int i = 0; i < prerequisites.length; i++) {
        	if (!DFS(prerequisites[i][0], map)) {
        		return false;
        	}
        }

        return true;
    }

    public boolean DFS(int val, HashMap<Integer, Node> map) {
    	Node node = map.get(val);
    	node.visited = true;
    	map.put(val, node);
    	
    	for (int child : node.children) {
    		if (map.get(child).visited && map.get(child).seq == -1) {//Check cycle
    			return false;
    		} else if (!map.get(child).visited) {
    			if(!DFS(child, map)){
					return false;
				}
    		}
    	}

    	node.seq = n--;
    	map.put(val, node);
    	return true;
    }
}

















```