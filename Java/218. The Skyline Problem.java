H
tags: Divide and Conquer, Heap, PriorityQueue, BIT, Segment Tree, Sweep Line, HashHeap
time: O(n^2logn)
space: O(n)

#### Sweep Line, Time O(nLogN), Space O(n)
- Analysis (inspired by, but not same solution: https://leetcode.com/problems/the-skyline-problem/solution/)
    - If there are just 2 overlapping building (totally 4 points on x-axis), here is the outline process:
    - Process x coordinate from left->right, one at a time.
        - 1) compare all `on-going heights` and find max, add as new outline point
        - 2) Handling building end: if the position ends a building, need to remove this height from the list of `on-going heights`
    - Requires 2 heap:
        1) sort by x coordinates
        2) `on-going heights`: maintain a pq of ongoing heights
- Steps: 
    - original reference http://codechen.blogspot.com/2015/06/leetcode-skyline-problem.html?_sm_au_=isVmHvFmFs40TWRt
    - 画图分析: 需要找到 non-overlaping height point at current index; also height needs to be different than prev height peek to be visible.
    - `on-going heights`: 用max-heap (reversed priorityqueue)，再iterate heightPoints 来存最大的height
    - NOTE: heightQueue里面加一个0, 用来在结尾的时候做closure
- time: initial sort O(nlogn) + calculate n * O(nlogn) [maxQueue sort]
- space: O(n)

#### Segment Tree
- 看了一些做法, segment tree写法很复杂, 估计在面试中难以用segment tree来写: https://www.cnblogs.com/tiezhibieek/p/5021202.html

#### HashHeap
- HashHeap template 可以考虑: https://www.jiuzhang.com/solution/building-outline/#tag-highlight-lang-java

Binary Indexed Tree?



```
/*
LeetCode:
A city's skyline is the outer contour of the silhouette formed by all the buildings in that city
when viewed from a distance. Now suppose you are given the locations and height of all the buildings
as shown on a cityscape photo (Figure A), write a program to output the skyline formed by these buildings
collectively (Figure B).

The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi],
where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively,
and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0.
You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

For instance, the dimensions of all buildings in Figure A are recorded as:
[ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .

The output is a list of "key points" (red dots in Figure B) in the format of
[ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline.
A key point is the left endpoint of a horizontal line segment. Note that the last key point,
where the rightmost building ends, is merely used to mark the termination of the skyline,
and always has zero height. Also, the ground in between any two adjacent buildings
should be considered part of the skyline contour.

For instance, the skyline in Figure B should be represented as:
[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].

Notes:

The number of buildings in any input list is guaranteed to be in the range [0, 10000].
The input list is already sorted in ascending order by the left x position Li.
The output list must be sorted by the x position.
There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]

*/

/*
Thoughts:
Sweep Line. 
1. Store the building start/end points into a Point object.
2. PriorityQueue on the Point: height low -> high && position lowX -> highX
3. Loop over PointQueue, and store the heights in a separate maxQueue
4. Based on the currPeak and prevPeak to detect diff: when there is a diff, there is a recording point.
   Keeping add start height into the maxHeap, and remove end points whenever facing one.

*/

// Sweep line, option1: sort by pos O(nlogn); process 1 pos at a time O(mlogm) 
class Solution {
    class Point {
        int pos, height;
        public Point(int pos, int height) {
            this.pos = pos;
            this.height = height;
        }
    }
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> rst = new ArrayList<>();
        if (isInvalid(buildings)) return rst;
        
        // init: sort points by pos
        PriorityQueue<Point> queue = buildQueue(buildings);

        // Mark height and calcualte the outline point.
        PriorityQueue<Integer> maxHeightQueue = new PriorityQueue<>(Collections.reverseOrder());
        maxHeightQueue.offer(0);
        int prevPeak = maxHeightQueue.peek();

        // process
        while (!queue.isEmpty()) {
            Point point = queue.peek();
            
            // 1) Add and trim all points on one X position
            while (!queue.isEmpty() && queue.peek().pos == point.pos) {
                point = queue.poll();
                if (point.height < 0) maxHeightQueue.offer(-point.height);
                else maxHeightQueue.remove(point.height); // remove 1 instance of this height, which marks end point of a building            
            }
            // 2) Add peak
            int currPeak = maxHeightQueue.peek();
            if (currPeak != prevPeak) {
                List list = new ArrayList<>();
                list.add(point.pos);
                list.add(currPeak);
                rst.add(list);
                prevPeak = currPeak;
            }
        }
        return rst;
    }
    
    private PriorityQueue<Point> buildQueue(int[][] buildings) {
        PriorityQueue<Point> queue = new PriorityQueue<>((a, b) -> a.pos - b.pos);
        for (int i = 0; i < buildings.length; i++) {
            queue.offer(new Point(buildings[i][0], -buildings[i][2])); // mark starting height negative
            queue.offer(new Point(buildings[i][1], buildings[i][2]));
        }
        return queue;
    }
    private boolean isInvalid(int[][] buildings) {
        return buildings == null || buildings.length == 0 || buildings[0] == null || buildings[0].length == 0;
    }
}

// SweepLine option2: sort by pos, and then by height; process 1 node at a time.
class Solution {
    class Point {
        int pos, height;
        public Point(int pos, int height) {
            this.pos = pos;
            this.height = height;
        }
    }
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> rst = new ArrayList<>();
        if (isInvalid(buildings)) return rst;
        
        int m = buildings.length;
        // init: sort points(xPos, height, isStart:boolean) by height && xPos with queue
        PriorityQueue<Point> queue = new PriorityQueue<>((a, b) -> a.pos == b.pos ? a.height - b.height : a.pos - b.pos);
        for (int i = 0; i < m; i++) {
            queue.offer(new Point(buildings[i][0], -buildings[i][2]));
            queue.offer(new Point(buildings[i][1], buildings[i][2]));
        }
        
        // Mark height and calcualte the outline point.
        PriorityQueue<Integer> maxHeightQueue = new PriorityQueue<>(Collections.reverseOrder());
        maxHeightQueue.offer(0);
        int prevPeak = maxHeightQueue.peek();

        // process
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            if (point.height < 0) maxHeightQueue.offer(-point.height);
            else maxHeightQueue.remove(point.height); // is end point of a building
            
            int currPeak = maxHeightQueue.peek();
            if (currPeak != prevPeak) {
                List list = new ArrayList<>();
                list.add(point.pos);
                list.add(currPeak);
                rst.add(list);
                prevPeak = currPeak;
            }
        }
        return rst;
    }
    
    private boolean isInvalid(int[][] buildings) {
        return buildings == null || buildings.length == 0 || buildings[0] == null || buildings[0].length == 0;
    }
}





/*
#### HashHeap
Well, based on JiuZhang, http://www.jiuzhang.com/solutions/building-outline/, implement a HashHeap. 
**HashHeap. Super long implementation: http://www.jiuzhang.com/solutions/hash-heap/
This is not applicable during an interview
What is HashHeap Exactly? Document below:

*/
class HashHeap {
    //Heap is a arraylist, which stores the actaul Integer values. It stores the real data
    ArrayList<Integer> heap;
    //HashMap stores the actual value, and the corresponding node.
    HashMap<Integer, Node> hash;
    String mode;
    int size_t;
   
    /*
      Used in HashMap,
      id: id in the Heap tree
      num: The frequency of the appearance of this id.
    */
    class Node {
        public Integer id;  
        public Integer num;

        Node(Node now) {
            id = now.id;
            num = now.num;
        }

        Node(Integer first, Integer second) {

            this.id = first;
            this.num = second;
        }
    }

    public HashHeap(String mod) { // 传入min 表示最小堆，max 表示最大堆
        // TODO Auto-generated constructor stub
        heap = new ArrayList<Integer>();
        mode = mod;
        hash = new HashMap<Integer, Node>();
        size_t = 0;
    }
    /*Regular peak, size, empty functions*/
    int peak() {
        return heap.get(0);
    }

    int size() {
        return size_t;
    }

    Boolean empty() {
        return (heap.size() == 0);
    }
    // Basis of heap
    int parent(int id) {
        if (id == 0) {
            return -1;
        }
        return (id - 1) / 2;
    }
    // Basis of heap. Our heap is base indxe = 0
    int lson(int id) {
        return id * 2 + 1;
    }
    // Basis of heap. Our heap is base indxe = 0
    int rson(int id) {
        return id * 2 + 2;
    }
    // Basis of heap. 
    //If min heap, parent < left/right child
    //If max heap, parent > left/right child
    boolean comparesmall(int a, int b) {
        if (a <= b) {
            if (mode == "min")
                return true;
            else
                return false;
        } else {
            if (mode == "min")
                return false;
            else
                return true;
        }

    }
    //swap value in heap based the 2 ids
    //based on value, create new node in hashmap.
    void swap(int idA, int idB) {
        int valA = heap.get(idA);
        int valB = heap.get(idB);

        int numA = hash.get(valA).num;
        int numB = hash.get(valB).num;
        hash.put(valB, new Node(idA, numB));
        hash.put(valA, new Node(idB, numA));
        heap.set(idA, valB);
        heap.set(idB, valA);
    }

    //Similar to delete, but only delete element at index==0, and return the value
    Integer poll() {
        size_t--;
        Integer now = heap.get(0);
        Node hashnow = hash.get(now);
        if (hashnow.num == 1) {
            swap(0, heap.size() - 1);
            hash.remove(now);
            heap.remove(heap.size() - 1);
            if (heap.size() > 0) {
                siftdown(0);
            }
        } else {
            hash.put(now, new Node(0, hashnow.num - 1));
        }
        return now;
    }
    //Add
    //If exist, count++ in hashmap
    //If not exited, add to tail, then sfitup
    void add(int now) {
        size_t++;
        if (hash.containsKey(now)) {
            Node hashnow = hash.get(now);
            hash.put(now, new Node(hashnow.id, hashnow.num + 1));

        } else {
            heap.add(now);
            hash.put(now, new Node(heap.size() - 1, 1));
        }

        siftup(heap.size() - 1);
    }
    //Remove node
    //If not last one, count-- from the hashMap
    //If last one, move it to tail of the structure, and delete it.
    //When the id is not tail (note, the id is already attached with new values after swap), then siftup and siftdown to sort all ids
    void delete(int now) {
        size_t--;
        ;
        Node hashnow = hash.get(now);
        int id = hashnow.id;
        int num = hashnow.num;
        if (hashnow.num == 1) {

            swap(id, heap.size() - 1);
            hash.remove(now);
            heap.remove(heap.size() - 1);
            if (heap.size() > id) {
                siftup(id);
                siftdown(id);
            }
        } else {
            hash.put(now, new Node(id, num - 1));
        }
    }
    //If the target id and its parent do not comply the heap structure, siftup.
    void siftup(int id) {
        while (parent(id) > -1) {
            int parentId = parent(id);
            if (comparesmall(heap.get(parentId), heap.get(id)) == true) {
                break;
            } else {
                swap(id, parentId);
            }
            id = parentId;
        }
    }
    //If the target id and its children do not comply with the heap structure, siftdown
    void siftdown(int id) {
        while (lson(id) < heap.size()) {
            int leftId = lson(id);
            int rightId = rson(id);
            int son;
            if (rightId >= heap.size() || (comparesmall(heap.get(leftId), heap.get(rightId)) == true)) {
                son = leftId;
            } else {
                son = rightId;
            }
            if (comparesmall(heap.get(id), heap.get(son)) == true) {
                break;
            } else {
                swap(id, son);
            }
            id = son;
        }
    }
}

```