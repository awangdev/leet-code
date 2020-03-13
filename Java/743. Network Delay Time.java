M
tags: Heap, DFS, BFS, Graph, PQ
time: O(nlogn)
space: O(n)

quesiton: sorting by travel delay/time will find better answer earlier?

#### Method1: BFS with PQ on graph
- `Dijkstras algorithm` is based on repeatedly making the candidate move that has the least distance travelled.
- PQ: pick close node to vist, and add siblings back to PQ
- avoid visited
- time: O(nLogn), visit n nodes, each time insert to heap takes O(logn) time
- space: O(n)

#### Method2: DFS with Sort
- 1) build graph map, 2) traverse map, 3) prioritize short delay nodes first
- use a map`<node, timeElapsed>` globally track dealy to nodes; compare all at the end

```
/*
There are N network nodes, labelled 1 to N.

Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node, and w is the time it takes for a signal to travel from source to target.

Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.

 

Example 1:



Input: times = [[2,1,1],[2,3,1],[3,4,1]], N = 4, K = 2
Output: 2
 

Note:

N will be in the range [1, 100].
K will be in the range [1, N].
The length of times will be in the range [1, 6000].
All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 0 <= w <= 100.
*/

/*
#### Method1: BFS with PQ on graph
- pick close node to vist, and add siblings back to PQ
- avoid visited
*/
class Solution {
    
    public int networkDelayTime(int[][] times, int N, int K) {
        
        Map<Integer, List<int[]>> graph = buildGraph(times);
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing(record -> record[1])); // sort by travel time, 
        Set<Integer> visited = new HashSet<>();
        pq.offer(new int[]{K, 0});
        int travelTime = 0;
        
        while (!pq.isEmpty()) {
            int size = pq.size();
            while (size-- > 0) {
                int[] record = pq.poll();
                int node = record[0];
                if (visited.contains(node)) continue;
                visited.add(node);
                travelTime = record[1]; // travel time to curr node
                if (!graph.containsKey(node)) continue;
                for (int[] next : graph.get(node)) {
                    pq.offer(new int[]{next[0], next[1] + travelTime});
                }
            }
        }
        
        return visited.size() != N ? -1 : travelTime;
    }
    
    private Map<Integer, List<int[]>> buildGraph(int[][] times) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] record : times) {
            graph.putIfAbsent(record[0], new ArrayList<>());
            graph.get(record[0]).add(new int[]{record[1], record[2]}); // map value: int[]{nextNode, travelTime to nextNode}
        }
        return graph;
    }
}

// #### Method2: DFS with Sort
class Solution {
    Map<Integer, Integer> timeMap = new HashMap<>(); // arrival time map
    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> graph = buildGraph(times);
        for (int i = 1; i <= N; i++) timeMap.put(i, Integer.MAX_VALUE);
        dfs(graph, K, 0);
        int time = 0;
        for (int timeElapsed : timeMap.values()) {
            if (timeElapsed == Integer.MAX_VALUE) return -1; // not visited
            time = Math.max(time, timeElapsed);
        }
        return time;
    }
    
    private void dfs(Map<Integer, List<int[]>> graph, int node, int timeElapsed) {
        if (timeElapsed >= timeMap.get(node)) return;
        timeMap.put(node, timeElapsed);
        if (!graph.containsKey(node)) return;
        List<int[]> targets = graph.get(node);
        Collections.sort(targets, Comparator.comparing(record -> record[2])); // sort by travel time
        for (int[] record : graph.get(node)) {
            int target = record[1], travelTime = record[2];
            dfs(graph, target, timeElapsed + travelTime);
        }
    }
    
    private Map<Integer, List<int[]>> buildGraph(int[][] times) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] record : times) {
            graph.putIfAbsent(record[0], new ArrayList<>());
            graph.get(record[0]).add(record);
        }
        return graph;
    }
}
```