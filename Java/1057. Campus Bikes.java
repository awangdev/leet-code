M
tags: Greedy, Sort, PriorityQueue, Bucket Sort
time: O(mn)
space: O(mn)

#### Method1: PriorityQueue
- PQ can be used to sort on multiple attributes
- follow the specified rules, and build all possible pairs of visits vs. bike. Pair {int dist, workerIndex, bikeIndex}
- PQ to sort them
    - first by dist
    - if same dist, sort by workerIndex
    - if same workderIndex, sort by bikeIndex
- process all candidates, and skip the ones (workers/bikes) visited 

#### Method2: Bucket Sort
- Similar to using PQ: the goal is to find: 1) min dist; 2) closer worker index, 3) closer bike index
- can use bucket sort to hold all possible distances [0 ~ 2000]: bucket[List of pairs]
    - do a hard iteration (ordered access from min dist). 
- time: O(mn), no need to sort
- space: O(mn)

```
/*

On a campus represented as a 2D grid, there are N workers and M bikes, with N <= M. Each worker and bike is a 2D coordinate on this grid.

Our goal is to assign a bike to each worker. Among the available bikes and workers, we choose the (worker, bike) pair with the shortest Manhattan distance between each other, and assign the bike to that worker. (If there are multiple (worker, bike) pairs with the same shortest Manhattan distance, we choose the pair with the smallest worker index; if there are multiple ways to do that, we choose the pair with the smallest bike index). We repeat this process until there are no available workers.

The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.

Return a vector ans of length N, where ans[i] is the index (0-indexed) of the bike that the i-th worker is assigned to.

 

Example 1:



Input: workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
Output: [1,0]
Explanation: 
Worker 1 grabs Bike 0 as they are closest (without ties), and Worker 0 is assigned Bike 1. So the output is [1, 0].
Example 2:



Input: workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
Output: [0,2,1]
Explanation: 
Worker 0 grabs Bike 0 at first. Worker 1 and Worker 2 share the same distance to Bike 2, thus Worker 1 is assigned to Bike 2, and Worker 2 will take Bike 1. So the output is [0,2,1].
 

Note:

0 <= workers[i][j], bikes[i][j] < 1000
All worker and bike locations are distinct.
1 <= workers.length <= bikes.length <= 1000
*/
/*
- follow the specified rules, and build all possible pairs of visits vs. bike. Pair {int dist, workerIndex, bikeIndex}
- PQ to sort them
    - first by dist
    - if same dist, sort by workerIndex
    - if same workderIndex, sort by bikeIndex
- process all candidates, and skip the ones (workers/bikes) visited 
*/
class Solution {
    class Pair {
        int dist, i, j; // i = workder index, j = bikeIndex
        public Pair(int dist, int i, int j) {
            this.i = i;
            this.j = j;
            this.dist = dist;
        }
    }
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        
        PriorityQueue<Pair> queue = buildQueue();
        
        int n = workers.length, m = bikes.length;
        for (int i = 0; i < n; i++) {
            int[] worker = workers[i];
            for (int j = 0; j < m; j++) {
                int[] bike = bikes[j];
                int dist = Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
                queue.offer(new Pair(dist, i, j));
            }
        }
        
        Set<Integer> visitedBike = new HashSet<>();
        int[] rst = new int[n];
        Arrays.fill(rst, -1);
        while (visitedBike.size() < n) {
            Pair p = queue.poll();
            if (rst[p.i] == -1 && !visitedBike.contains(p.j)) {
                rst[p.i] = p.j;
                visitedBike.add(p.j);
            }
        }
            
        return rst;
    }
    
    private PriorityQueue<Pair> buildQueue() {
        return new PriorityQueue<>(new Comparator<Pair>() {
            public int compare(Pair p1, Pair p2) {
                int comp = Integer.compare(p1.dist, p2.dist);
                if (comp == 0) {
                    comp = Integer.compare(p1.i, p2.i); // compare worker index
                    if (comp == 0) return Integer.compare(p1.j, p2.j); // compare bike index
                    return comp;
                }
                return comp;
            }
        });
    }
}


/*
Method2: Bucket Sort
- Similar to using PQ: the goal is to find: 1) min dist; 2) closer worker index, 3) closer bike index
- can use bucket sort to hold all possible distances [0 ~ 2000]: bucket[List of pairs]
    - do a hard iteration (ordered access from min dist). 
- follow the specified rules, and build all possible pairs of visits vs. bike. Pair {int dist, workerIndex, bikeIndex}
- PQ to sort them
    - first by dist
    - if same dist, sort by workerIndex
    - if same workderIndex, sort by bikeIndex
- process all candidates, and skip the ones (workers/bikes) visited 
*/
class Solution {
    class Pair {
        int dist, i, j; // i = workder index, j = bikeIndex
        public Pair(int dist, int i, int j) {
            this.i = i;
            this.j = j;
            this.dist = dist;
        }
    }
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        List[] bucket = buildBucket(workers, bikes);
        Set<Integer> visitedBike = new HashSet<>();
        int[] rst = new int[workers.length];
        Arrays.fill(rst, -1);

        for (int dist = 0; dist < bucket.length; dist++) {
            List<Pair> list = bucket[dist];
            if (list == null) continue;
            for (Pair p : list) {
                if (rst[p.i] == -1 && !visitedBike.contains(p.j)) {
                    rst[p.i] = p.j;
                    visitedBike.add(p.j);
                }
            }
        }  
        return rst;
    }
    
    private List[] buildBucket(int[][] workers, int[][] bikes) {
        List[] bucket = new List[2001];
        int n = workers.length, m = bikes.length;
        for (int i = 0; i < n; i++) {
            int[] worker = workers[i];
            for (int j = 0; j < m; j++) {
                int[] bike = bikes[j];
                int dist = Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
                if (bucket[dist] == null) bucket[dist] = new ArrayList<>();
                bucket[dist].add(new Pair(dist, i, j));
            }
        }
        
        return bucket;
    }
    
}
```