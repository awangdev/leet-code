M
1520320428
tags: Union Find

没有跑过这个程序, 是一个UnionFind的简单实现.
Document了每个环节的计算原理/思想.

```
/*
LintCode
Given n nodes in a graph labeled from 1 to n. There is no edges in the graph at beginning.

You need to support the following method:
1. connect(a, b), add an edge to connect node a and node b.
2. query(a, b), check if two nodes are connected


Example
5 // n = 5
query(1, 2) return false
connect(1, 2)
query(1, 3) return false
connect(2, 4)
query(1, 4) return true

*/

/*
Thoughts:
Not tested on lintcode.
Implementation of Union Find
*/

public class ConnectingGraph {
    // Placeholder for all the UninFind relationships
    private int[] father = null;

    /**
        Initialize one element to each individual union.
     */
    public ConnectingGraph(int n) {
        father = new int[n + 1];
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
            parent[a] = rootB; // doesn't mater which assigns to which one.
        }
    }

    /** Check if the two integer are in the same union */
    public boolean query(int a, int b) {
        return find(a) == find(b);
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