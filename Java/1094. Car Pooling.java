M
tags: PriorityQueue, Heap, Sort, Greedy
time: O(n)
space: O(1) only use bucket size 1000

#### Method1: bucket sort
- define the bucket by index: the total distance is fixed [0, 1000]
- +/- capacities for each pos and save into the bucket
- go over the bucket and see if the total cap goes over input capacity
- O(n), trips size
- space: O(1), bucket size 1000 is constant
- `IMPORTANT`: before using PQ to sort, consider bucket sort:
    - if the boundary set and seems resonable? i.e., max size = `1000`
    - is the sorted items index based?

#### Method2: Priority Queue, sort distnace
- Like meeting room, merge interval
- process items on same index

```
/*
You are driving a vehicle that has capacity empty seats initially available for passengers.  The vehicle only drives east (ie. it cannot turn around and drive west.)

Given a list of trips, trip[i] = [num_passengers, start_location, end_location] contains information about the i-th trip: the number of passengers that must be picked up, and the locations to pick them up and drop them off.  The locations are given as the number of kilometers due east from your vehicle's initial location.

Return true if and only if it is possible to pick up and drop off all passengers for all the given trips. 

 

Example 1:

Input: trips = [[2,1,5],[3,3,7]], capacity = 4
Output: false
Example 2:

Input: trips = [[2,1,5],[3,3,7]], capacity = 5
Output: true
Example 3:

Input: trips = [[2,1,5],[3,5,7]], capacity = 3
Output: true
Example 4:

Input: trips = [[3,2,7],[3,7,9],[8,3,9]], capacity = 11
Output: true
 
 

Constraints:

trips.length <= 1000
trips[i].length == 3
1 <= trips[i][0] <= 100
0 <= trips[i][1] < trips[i][2] <= 1000
1 <= capacity <= 100000
*/


/*
Method1: bucket sort
- define the bucket by index: the total distance is fixed [0, 1000]
- +/- capacities for each pos and save into the bucket
- go over the bucket and see if the total cap goes over input capacity
- O(n), n = trips size
*/
class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        if (trips == null || trips.length == 0) return true;
        
        int[] bucket = new int[1000];
        for (int[] trip : trips) {
            bucket[trip[1]] += trip[0];
            bucket[trip[2]] += -trip[0];
        }
        int cap = 0;
        for (int num : bucket) {
            cap += num;
            if (cap > capacity) return false;
        }
        return true;
    }
    
}

// Method2: Priority Queue, sort distnace
class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        if (trips == null || trips.length == 0) return true;
        
        PriorityQueue<int[]> queue = buildQueue(trips);
        int cap = 0;
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            cap += point[1];
            while (!queue.isEmpty() && queue.peek()[0] == point[0]) {
                cap += queue.poll()[1];
            }
            if (cap > capacity) return false;
        }
        return true;
    }
    
    private PriorityQueue<int[]> buildQueue(int[][] trips) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparing(p -> p[0]));
        for (int[] trip : trips) {
            queue.offer(new int[]{trip[1], trip[0]});
            queue.offer(new int[]{trip[2], -trip[0]});
        }
        return queue;
    }
}
```