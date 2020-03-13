M
tags: Union Find, Lint
time: O(n), with Path Compression O(mN), with Union by Rank O(logN)
space: O(n)

#### Method1: Union Find with Array
- union(), find()
- Path Compresion: store skip father after found, which makes find O(1)

#### Method2: Union Find with HashMap



```
class Solution {
    // Method1: Union Find with Array
    class UnionFind {
        int father[] = null;
        int count;

        public UnionFind(int n) {
            father = new int[n];
            for (int i = 0; i < n; i++) {
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

        // Alternative: union with rank[]
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                if (rank[rootX] > rank[rootY]) {
                    father[rootY] = rootX;    
                } else if (rank[rootX] < rank[rootY]) {
                    father[rootX] = rootY;    
                } else { // rank[rootX] == rank[rootY]
                    father[rootY] = rootX;    
                    rank[rootX]++;
                }
                count--;
            }
        }
        
        
        public int query() {
            return count;
        }
        
        public void setCount(int value) {
            count = value;
        }

        private int find(int x) {
            if (father[x] == x) return x; // found root father
            return father[x] = find(father[x]);
        }
    }

    // Method1: Union Find with HashMap
    class UnionFind {
        private HashMap<Integer, Integer> map = new HashMap<>();
        
        /*
        Model the disjoint set with 1D array
        During initialization, assume each spot has itself as the parent
        */
        public UnionFind(int size) {
            for (int i = 0; i < size; i++) {
                map.put(i, i);
            }
        }
        
        /*
        Use one key and find out the root parent of this set where they key belongs to.
        */
        public int findRootParent(int item) {
            int parent = map.get(item);
            while (parent != map.get(parent)) {
                parent = map.get(parent);
            }
            return parent;
        }

        /*
        Find the root parent of each item. If the root parent is different,
        join them together by adding them into the map as <key, value> pair.
        */
        public void union(int itemX, int itemY) {
            int parentX = findRootParent(itemX);
            int parentY = findRootParent(itemY);
            if (parentX != parentY) {
                map.put(parentX, parentY);
            }
        }
    }

}


```