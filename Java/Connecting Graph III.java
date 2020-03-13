M
1520323016
tags: Union Find

还是UnionFind的变形, 这次是算有剩下多少个union. 其实非常简单, 维持一个全局变量count:
一开始count=n, 因为全是散装elements;  每次union, count--.

```
/*
Given n nodes in a graph labeled from 1 to n. There is no edges in the graph at beginning.

You need to support the following method:
1. connect(a, b), add an edge to connect node a and node b.
2. query(), Returns the number of connected component in the graph


Example
5 // n = 5
query() return 5
connect(1, 2)
query() return 4
connect(2, 4)
query() return 3
connect(1, 4)
query() return 3
*/

/**
Thoughts:
Not able to run on LintCode.

Count the number of unions left
*/

public class ConnectingGraph {
    // Placeholder for all the UninFind relationships
    private int[] father = null;
    private int count;

    /**
        Initialize one element to each individual union.
     */
    public ConnectingGraph(int n) {
        father = new int[n + 1];
        count = n;
        for (int i = 1; i <= n; i++) {
            father[i] = i;
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
            count--;
            parent[a] = rootB; // doesn't mater which assigns to which one.
        }
    }

    /** Return count of union left */
    public boolean query() {
        return count;
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