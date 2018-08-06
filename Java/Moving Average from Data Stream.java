E
1533510925
tags:Design, Queue

给一个interface, design一个structure, 能够计算moving window average.

#### Queue
- 读懂题目, 注意average 和 window 的处理.
- 简单的queue.size() comparison

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
        if (queue.size() > size) {
            sum -= queue.poll();
        }
        return sum / queue.size();
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */
```