M
1527744235
tags: DFS, BFS, Union Find, Graph 

给一个数字n代表n nodes, marked from 1 ~ n, 和一串undirected edge int[][]. 

检查这些edge是否能合成一个 valid tree

#### Union Find
- 复习Union-Find的另外一个种形式, track union size
- 题目类型：查找2个元素是不是在一个union里面。如果不在，false. 如果在，那就合并成一个set,共享parent.   
- 存储的关键都是：元素相对的index上存着他的root parent.    
- 注意: 结尾要检查, 是否只剩下1个union: Tree必须连接到所有给出的node.
- 另一个union-find, 用hashmap的:
- http://www.lintcode.com/en/problem/find-the-weak-connected-component-in-the-directed-graph/

#### DFS
- Create adjacent list graph: Map<Integer, List<Integer>>
- 检查: 
- 1. 是否有cycle using dfs, check boolean[] visited
- 2. 是否所有的node全部链接起来: check if any node not visited

#### BFS
- (还没做, 可以写一写)
- 也是检查: 1. 是否有cycle, 2. 是否所有的node全部链接起来

```
/*
Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes),
write a function to check whether these edges make up a valid tree.

For example:

Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.

Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.

Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected,
[0, 1] is the same as [1, 0] and thus will not appear together in edges.


 */
/*
Thoughts:
Tree should not have cycle, which means adding each edge should connect a new node and that node should not be in the tree yet.
If found cycle, return false.

Note: need to count # of unions after merging. Count should be 1 for a tree.
*/

class Solution {
    public boolean validTree(int n, int[][] edges) {
        // No node, false
        if (n == 0) {
            return false;
        }
        // 1 Node without edges, true
        if (n == 1 && (edges == null || edges.length == 0)) {
            return true;
        }
        // >= 2 nodes but no edges, false;
        if (edges == null || edges.length == 0 || edges[0] == null || edges[0].length == 0) {
            return false;
        }
        UnionFind unionFind = new UnionFind(n);
        for (int i = 0; i < edges.length; i++) {
            int x = edges[i][0];
            int y = edges[i][1];
            if (unionFind.find(x) == unionFind.find(y)) {
                return false;
            }
            unionFind.union(x, y);
        }
        return unionFind.query() == 1;
    }
}

class UnionFind {
    int[] father;
    int count;

    UnionFind(int x) {
        father = new int[x];
        count = x;
        for (int i = 0; i < x; i++) {
            father[i] = i;
        }
    }
    
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            father[rootX] = rootY;
            count--;
        }
    }

    public int find(int x) {
        if (father[x] == x) {
            return x;
        }
        return father[x] = find(father[x]);
    }
    
    public int query() {
        return count;
    }
}

/*
Thoughts:
Tree should not have cycle, which means adding each edge should connect a new node and that node should not be in the tree yet.
If found cycle, return false.

Note: need to count # of unions after merging. Count should be 1 for a tree.
*/

class Solution {
    public boolean validTree(int n, int[][] edges) {
        // No node, false
        if (n == 0) {
            return false;
        }
        // 1 Node without edges, true
        if (n == 1 && (edges == null || edges.length == 0)) {
            return true;
        }
        // >= 2 nodes but no edges, false;
        if (edges == null || edges.length == 0 || edges[0] == null || edges[0].length == 0) {
            return false;
        }
        
        Map<Integer, List<Integer>> graph = buildGraph(n, edges);
        boolean[] visited = new boolean[n];

        // dfs(graph, visited, i, -1) and validate cycle
        if (!dfs(graph, visited, 0, -1)) {
            return false;
        }
        
        // validate if all edge connected
        for (boolean status : visited) {
            if (!status) {
                return false;
            }
        }
        
        return true;
    }
    
    // build graph in form of adjacent list
    private Map<Integer, List<Integer>> buildGraph(int n, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (!graph.containsKey(i)) {
                graph.put(i, new ArrayList<>());
            }
        }
        for (int[] edge: edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        return graph;
    }

    // dfs: mark visited nodes, and keep dfs into children nodes
    private boolean dfs(Map<Integer, List<Integer>> graph, boolean[] visited, int i, int pre) {
        if (visited[i]) {
            return false;
        }
        visited[i] = true;
        for (int child : graph.get(i)) {
            if (child != pre && !dfs(graph, visited, child, i)) {
                return false;
            }
        }
        return true;
    }
}


/*
Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), 
write a function to check whether these edges make up a valid tree.

Example
Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.

Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.

Note
You can assume that no duplicate edges will appear in edges. 
Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

Tags Expand 
Depth First Search Breadth First Search Union Find Facebook Zenefits Google

*/


/*
	Thoughts: do union-find. //http://www.jiuzhang.com/solutions/graph-valid-tree/
	How to check if we have disconnected pair? Think about it:
		A valid tree has n-1 edges. If we have 1 disconnected pair, that means,
		we lost 1 edge, like n-2 edgets now. 
		OR, keep thinking: if we have a cycle, which is a extra edge, that becomes n edgets.
	Therefore, a first check (assume if with n-1 edgets is valid), just check if edges.lenght == n -1.

	Then, natually we can think of : what if missing a couple edges and have a couple cycles,
	which makes edges.length == n - 1?
	So if in this complex case, there must be >=1 cycles. Now just explicitly check for cycle. 
	How to use unin-find to check no cycle: 
***		if a pair of node has same parent. If they do, that makes an triangle. False.
	
	What does Union-Find do?
	Union-Find is a data structure (this problem implemented as a array), that union 2 sets and 
	checks if 2 elements are in the same set.
	In another problem, it can be implemented with HashMap as well.
	
*/

public class Solution {
    int[] parents;
    public boolean validTree(int n, int[][] edges) {
    	if (n - 1 != edges.length) {
    		return false;
    	}
    	parents = new int[n];
    	//Init
    	for (int i = 0; i < parents.length; i++) {
    		parents[i] = i;
    	}
    	//Use union-find to detect if pair has common parents, and merge then to 1 set
    	for (int i = 0; i < edges.length; i++){
    		if (find(edges[i][0]) == find(edges[i][1])) {
    			return false;
    		}
    		union(edges[i][0], edges[i][1]);
    	}

    	return true;
    }

    /*
		Not only find parent, also update the spot parents[node] with parent node, recursively.

        *** The fact is, at all levels, if any curr != its parent, it'll trigger the find() method,
            Then it makes sure parent node will be assigned to this curr node index.

        Goal: Mark curr node: who is your ancestor parent; and that indicates if other nodes are 
            in the same union as curr.
    */
    public int find(int node) {
    	if (parents[node] == node) {//If curr node == its parent,  return curr node.
    		return node;
    	}
        //If curr node != its parent, we will attempt to find its grandparents, and assign to curr node.
    	parents[node] = find(parents[node]);
    	return parents[node];
    }
    /*
        Either union x into y, or the other way
    */
    public void union(int x, int y) {
    	int findX = parents[x];
    	int findY = parents[y];
    	if (findX != findY) {
    		parents[findX] = findY;
    	}
    }
}











/*
	Attempt failed:
	check:No cycle.Connected.
	Sort the input based on edges[i][0]. 
	However, this is wrong; both 0 and 1 index of an edge can be used a root.
*/

//Just use priorityqueue
public class Solution {
    /**
     * @param n an integer
     * @param edges a list of undirected edges
     * @return true if it's a valid tree, or false
     */
    class Pair {
    	int from, to;
    	public Pair(int f, int t) {
    		this.from = f;
    		this.to = t;
    	}
    }
    public boolean validTree(int n, int[][] edges) {
        if (n == 1) {
            return true;
        }
    	if (edges == null || edges.length == 0 || edges[0].length == 0 || n <= 0) {
    		return false;
    	}
    	HashSet<Integer> set = new HashSet<Integer>();
    	PriorityQueue<Pair> queue = new PriorityQueue<Pair>(2, new Comparator<Pair>(){
    	    public int compare(Pair A, Pair B){
    	        return A.from - B.from;
    	    }  
    	});

    	//add into queue, format it like pair(small, large)
    	for (int i = 0; i < edges.length; i++) {
    		if (edges[i][0] < edges[i][1]) {
				queue.offer(new Pair(edges[i][0], edges[i][1]));
    		} else {
    			queue.offer(new Pair(edges[i][1], edges[i][0]));
    		}
    	}
    	//check
    	set.add(queue.peek().from);//0
    	while (!queue.isEmpty()) {
    		Pair p = queue.poll();
    		//check node existance && cycle
    		if (!set.contains(p.from) || set.contains(p.to)) {
    			return false;
    		}
    		set.add(p.to);
    	}
    	return true;
    }
}




















```