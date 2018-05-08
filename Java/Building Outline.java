R
1521176156
tags: Divide and Conquer, Heap, Binary Indexed Tree, Segment Tree, Sweep Line

又叫做skyline. 用Sweep Line做的O(nLogN), 但是貌似还有很多做法: segement tree, hashheap, treeSet?

#### 方法1: Sweep Line, Time O(nLogN), Space O(n)
original reference http://codechen.blogspot.com/2015/06/leetcode-skyline-problem.html?_sm_au_=isVmHvFmFs40TWRt

sweep line:
- 把所有点分出来， 每个点有index x, 再加上一个height.         
- 在这个list上排序，根据index和height. 注意用负数标记building start point height, 这样保证start在end 之前
- 用负数的height标记start: 在priority queue里面同一个x-pos比较 startPoint.height, endPoint.height 的时候, 因为end height是整数, 所以compare时会自动把start point放在end point前面
- 当然了, 如果两个 start point比较, 第二个point的负数超大的话(也就是height很高), 就会顺理compare return正数, 成章形成倒位
- 在processs时候用max-heap (reversed priorityqueue)，再iterate heightPoints 来存最大的height . 遇到peek,就是一个合理的解    
- heightQueue里面加一个0, 用来在结尾的时候做closure

#### 方法2: Segment Tree
REVIEW

Binary Indexed Tree?

HashHeap?

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
class Solution {
    class Point {
        int pos;
        int height;
        public Point(int pos, int height) {
            this.pos = pos;
            this.height = height;
        }
    }
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> rst = new ArrayList<>();
        if (buildings == null || buildings.length == 0 || buildings[0] == null || buildings[0].length == 0) {
            return rst;
        }
        int m = buildings.length;
        // Sort points(xPos, height, isStart:boolean) by height && xPos with queue
        PriorityQueue<Point> queue = new PriorityQueue<Point>(new Comparator<Point>() {
           public int compare(Point a, Point b) {
               if (a.pos == b.pos) {
                   return a.height - b.height;
               } else {
                   return a.pos - b.pos;
               }
           }
        });
        for (int i = 0; i < m; i++) {
            queue.offer(new Point(buildings[i][0], -buildings[i][2]));
            queue.offer(new Point(buildings[i][1], buildings[i][2]));
        }
        
        // Mark height and calcualte the outline point.
        PriorityQueue<Integer> maxHeightQueue = new PriorityQueue<>(Collections.reverseOrder());
        maxHeightQueue.offer(0);
        int prevPeak = maxHeightQueue.peek();

        while (!queue.isEmpty()) {
            Point point = queue.poll();
            if (point.height < 0) {
                maxHeightQueue.offer(-point.height);
            } else { // is end point of a building
                maxHeightQueue.remove(point.height);
            }
            
            int currPeak = maxHeightQueue.peek();
            if (currPeak != prevPeak) {
                rst.add(new int[]{point.pos, currPeak});
                prevPeak = currPeak;
            }
        }
        return rst;
    }
}


/*
LintCode description:
Given N buildings in a x-axis，each building is a rectangle and can be represented by a triple (start, end, height)，
where start is the start position on x-axis, end is the end position on x-axis and height is the height of the building. 
Buildings may overlap if you see them from far away，find the outline of them。
An outline can be represented by a triple, (start, end, height), 
where start is the start position on x-axis of the outline, 
end is the end position on x-axis and height is the height of the outline.

Example
Given 3 buildings：
[
  [1, 3, 3],
  [2, 4, 4],
  [5, 6, 1]
]
The outlines are：
[
  [1, 2, 3],
  [2, 4, 4],
  [5, 6, 1]
]
Note
Please merge the adjacent outlines if they have the same height and make sure different outlines cant overlap on x-axis.
Tags Expand 
LintCode Copyright Heap
*/

/*LeetCode description
A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).

 Buildings  Skyline Contour
The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .

The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.

For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].

Notes:

The number of buildings in any input list is guaranteed to be in the range [0, 10000].
The input list is already sorted in ascending order by the left x position Li.
The output list must be sorted by the x position.
There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]

Hide Tags Divide and Conquer Binary Indexed Tree Heap Segment Tree

*/

/*
    Thoughts
    Based idea here:http://codechen.blogspot.com/2015/06/leetcode-skyline-problem.html?_sm_au_=isVmHvFmFs40TWRt
    Here is the thinking process, 3.15.2016

    The class HeightPoint{int x, int height} is very similar to Sweep Line Point{int x, int flag}. However the usage is a bit different.
        Sort all of the heightPoints by index && by height. 
        Use nagtive height for start point to make sure start appears before end point, tho they share same height
    We use an extra priorityQueue to store the height being processed (note, this queue, decending order, max in front)
        When having a negative height(start point), add into queue
        At each point, find heightest point (common sense!) and mark it on map: the overlapping point at this index will be skipped because the rest are not high enough.
        How to process the rest redundant point?
            Here introduce a 'prev' variable that stores last processed value. If same height appears right before curr, don't add to result; it's added during this continuous line.
        How to maintain the queue?
            Once a height > 0 appears, remove that height from queue. 
            OKay, let's break it down:
                because we sort HeightPoint object by index and height, start height will appear before end height of same building, for sure.
                So whenever positive height appears, the same bulding must have been marked, so can safely remove the height instance from queue.
                Well, in HeightPoint object, start height is negative for sorting purpose. When adding into queue, use it's absolute value : )    
*/
public class Solution {
    public class HeightPoint{
        int x, height;
        public HeightPoint(int x, int height) {
            this.x = x;
            this.height = height;
        }
    }
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> rst = new ArrayList<int[]>();
        if (buildings == null || buildings.length == 0 || buildings[0].length == 0) {
            return rst;
        }

        //Init the list sorted by index && height
        List<HeightPoint> heightPoints = new ArrayList<HeightPoint>();
        for (int i = 0; i < buildings.length; i++) {
            heightPoints.add(new HeightPoint(buildings[i][0], -buildings[i][2]));
            heightPoints.add(new HeightPoint(buildings[i][1], buildings[i][2]));
        }
        Collections.sort(heightPoints, new Comparator<HeightPoint>() {
            public int compare(HeightPoint p1, HeightPoint p2) {
                if (p1.x == p2.x) {
                    return p1.height - p2.height;
                } else {
                    return p1.x - p2.x;
                }
            }
        });

        //Process height points
        //reversed priorityqueue, becase for same pos x, we always want the highest point
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(1000, Collections.reverseOrder());
        queue.offer(0);
        int prev = queue.peek();  

        for (HeightPoint p : heightPoints) {
            if (p.height < 0) {
                queue.offer(-p.height);
            } else {
                queue.remove(p.height);
            }

            int currPeak = queue.peek();
            if (currPeak != prev) {
                rst.add(new int[] {p.x, currPeak});
                prev = currPeak;
            }
        }

        return rst;
    }
}



/*
Thoughts:
Well, based on JiuZhang, http://www.jiuzhang.com/solutions/building-outline/, implement a HashHeap. 
**HashHeap. Super long implementation: http://www.jiuzhang.com/solutions/hash-heap/

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