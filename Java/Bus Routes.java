H
1534208810
tags: BFS

```
/*
We have a list of bus routes. Each routes[i] is a bus route that the i-th bus repeats forever. 
For example if routes[0] = [1, 5, 7], this means that the first bus (0-th indexed) 
travels in the sequence 1->5->7->1->5->7->1->... forever.

We start at bus stop S (initially not on a bus), and we want to go to bus stop T. 
Travelling by buses only, what is the least number of buses we must take to reach our destination? 
Return -1 if it is not possible.

Example:
Input: 
routes = [[1, 2, 7], [3, 6, 7]]
S = 1
T = 6
Output: 2
Explanation: 
The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
Note:

1 <= routes.length <= 500.
1 <= routes[i].length <= 500.
0 <= routes[i][j] < 10 ^ 6.
*/

/*
1. Map stop -> bus list
2. Add stop to queue
3. process queue, if stop match return; otherwise, try the linked bus (track visited bus)
*/
class Solution {
    public int numBusesToDestination(int[][] routes, int S, int T) {
        Set<Integer> visited = new HashSet<>();
        Map<Integer, Set<Integer>> stopMap = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        // init
        for (int i = 0; i < routes.length; i++) {
            for (int stop : routes[i]) {
                stopMap.putIfAbsent(stop, new HashSet<>());
                stopMap.get(stop).add(i); // add bus route to stop
            }
        }
        queue.offer(S);
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) { 
                int stop = queue.poll(); // 1, 2, 7
                if (stop == T) return count;
                for (int bus : stopMap.get(stop)) {
                    if (!visited.contains(bus)) {
                        visited.add(bus);
                        for (int nextStop : routes[bus]) {
                            queue.offer(nextStop); // 3, 6, 7
                        }
                    }
                }
            }
            count++;
        }
        
        return -1;
    }
}
```