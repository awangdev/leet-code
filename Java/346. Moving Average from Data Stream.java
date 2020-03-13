E
tags: Design, Queue, Sliding Window
time: O(1) for `next()`
space: O(size) for fixed storage

给一个interface, design一个structure, 能够计算moving window average.

#### Queue
- 读懂题目, 注意average 和 window 的处理.
- 简单的queue.size() comparison
- Note: if we it is calculate moving-window-product, better to use deque :)
- Sliding window?
    - It has the spirit of slinding window: 1) maintain a range; 2) check range size `if (queue.size() > size)`
    - Though, the solution must use a data structure to store data; it is not the traditional sliding window type of `left/right` pointer problem

```
/**
Given a stream of integers and a window size, 
calculate the moving average of all integers in the sliding window.

For example,
MovingAverage m = new MovingAverage(3);
m.next(1) = 1
m.next(10) = (1 + 10) / 2
m.next(3) = (1 + 10 + 3) / 3
m.next(5) = (10 + 3 + 5) / 3
 */

class MovingAverage {
    double sum;
    int size;
    Queue<Integer> queue;
    
    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        this.sum = 0.0;
        this.size = size;
        this.queue = new LinkedList<>();
    }
    
    public double next(int val) {
        sum += val;
        queue.offer(val);
        if (queue.size() > size) sum -= queue.poll();
        return sum / queue.size();
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */
```