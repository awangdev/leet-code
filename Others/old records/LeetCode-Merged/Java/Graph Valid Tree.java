/*
Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), 
write a function to check whether these edges make up a valid tree.

For example:

Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.

Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.

Hint:

Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], what should your return? Is this case a valid tree?
According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path. 
In other words, any connected graph without simple cycles is a tree.”
Note: you can assume that no duplicate edges will appear in edges. 
Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

Hide Company Tags Google Facebook Zenefits
Hide Tags Depth-first Search Breadth-first Search Graph Union Find
Hide Similar Problems (M) Course Schedule

*/

/*
	Thoughts:
		The edge here has no directin.
		The goal is to check if the graphc is connected && if there is cycle.
	Do union-find.
	Check each pair: If they are in the same set: do they share same parent?
		If they do, that makes a triangle between this node elementsi the pair and their parent. That is a cycle. False.
		If no common parent, union them into 1 set.

	find(x): recursively set the current node equals to it's parent node's parent's parent's .... partent's node.
	Via just 1 call to x, it will update all parent nodes to equal to the root parent lol
*/


public class Solution {
    int[] parents;
    public boolean validTree(int n, int[][] edges) {
        //edges can be [], when n == 1, it'll be true.
    	if (edges.length != n - 1) {
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
    */
    public int find(int node) {
    	if (parents[node] == node) {
    		return node;
    	}
    	parents[node] = find(parents[node]);
    	return parents[node];
    }

    public void union(int x, int y) {
    	int findX = parents[x];
    	int findY = parents[y];
    	if (findX != findY) {
    		parents[findX] = findY;
    	}
    }
}







