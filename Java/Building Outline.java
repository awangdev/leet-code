/*
Given N buildings in a x-axis，each building is a rectangle and can be represented by a triple (start, end, height)，where start is the start position on x-axis, end is the end position on x-axis and height is the height of the building. Buildings may overlap if you see them from far away，find the outline of them。
An outline can be represented by a triple, (start, end, height), where start is the start position on x-axis of the outline, end is the end position on x-axis and height is the height of the outline.
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
/*
Thoughts:
Well, based on JiuZhang, http://www.jiuzhang.com/solutions/building-outline/, implement a HashHeap. 
**HashHeap. Super long implementation: http://www.jiuzhang.com/solutions/hash-heap/
*/





/****
  Attempt1, may not be correct.
  Thoughts: 
  PriorityQueue<Point>, sort by start.
  1. Keep track of max height. 
  2. Find max height.
  3. Poll() queue. Whenever there is a jump(up or down) at current node, close a interval.
  4. When closing interval, set prev = new node.h
****/


/**

What is HashHeap Exactly? Document below:

**/
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
