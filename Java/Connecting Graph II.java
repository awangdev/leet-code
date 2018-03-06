M
1520320968
tags: Union Find

Lint还不能跑, 全部按照题意和答案document的.

```
/*
LintCode:
Given n nodes in a graph labeled from 1 to n. There is no edges in the graph at beginning.

You need to support the following method:
1. connect(a, b), add an edge to connect node a and node b.
2. query(a), Returns the number of connected component nodes which include node a.


Example
5 // n = 5
query(1) return 1
connect(1, 2)
query(1) return 2
connect(2, 4)
query(1) return 3
connect(1, 4)
query(1) return 3

*/

/**
Thoughts:
No chance to run on LintCode.


 */
public class ConnectingGraph {
    // Placeholder for all the UninFind relationships
    private int[] father = null;

    // Stores the number of union elements connected from downstream to each individual node
    private int[] size = null;

    /**
        Initialize one element to each individual union; size will be 1 for all unions as well.
     */
    public ConnectingGraph(int n) {
        father = new int[n + 1];
        size = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            father[i] = i;
            size[i] = 1;
        }
    }

    /**
        Union function.
        Find the root father, if not the same, union them together.
     */
    public void connect(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) {
            parent[a] = rootB; // doesn't mater which assigns to which one.
            // Merge union of A into the union of B, so unionB should grow.
            size[rootB] += size[rootA]
        }
    }

    /** 
        Returns the number of connected component nodes which include node a.
    */
    public boolean query(int x) {
        int rootX = find(x);
        return size[rootX];
    }

    /*
        Find function: find the root parent as the head for entire union.
        If found parent as itself, return it.
        Otherwise, recursively look for father and assign the result eventually.
    */
    private int find(int x) {
        if (father[x] == x) {
            return x; // x is the root parent, return itself.
        }
        return father[x] = find(father[x]);
    }
}
```