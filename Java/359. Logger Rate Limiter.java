E
tags: Hash Table, Design
time: O(1)
space: O(n)

#### HashMap<Message, Timestamp>
- map: avoid duplicate message, records timestamp for validation
- time: O(1)
- space: O(n)

#### Queue + Set
- 1) keep a trimmed queue and set (all tasks to be within 10 secs); 
- 2) use set to O(1) check if incoming message exists.
- time: O(x), trimQueue()
- space: O(n)

```

/*
Design a logger system that receive stream of messages along with its timestamps, each message should be printed if and only if it is not printed in the last 10 seconds.

Given a message and a timestamp (in seconds granularity), return true if the message should be printed in the given timestamp, otherwise returns false.

It is possible that several messages arrive roughly at the same time.

Example:

Logger logger = new Logger();

// logging string "foo" at timestamp 1
logger.shouldPrintMessage(1, "foo"); returns true; 

// logging string "bar" at timestamp 2
logger.shouldPrintMessage(2,"bar"); returns true;

// logging string "foo" at timestamp 3
logger.shouldPrintMessage(3,"foo"); returns false;

// logging string "bar" at timestamp 8
logger.shouldPrintMessage(8,"bar"); returns false;

// logging string "foo" at timestamp 10
logger.shouldPrintMessage(10,"foo"); returns false;

// logging string "foo" at timestamp 11
logger.shouldPrintMessage(11,"foo"); returns true;
*/

class Logger {

    Map<String, Integer> map;
    /** Initialize your data structure here. */
    public Logger() {
        map = new HashMap();
    }
   
    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        if (!map.containsKey(message)) {
            map.put(message, timestamp);
            return true;
        } else if (timestamp - map.get(message) >= 10) {
            map.put(message, timestamp);
            return true;
        }
        return false;
    }
}


#### Queue + Set
class Logger {

    Queue<Pair<String, Integer>> queue;
    Set<String> set;
    /** Initialize your data structure here. */
    public Logger() {
        set = new HashSet();
        queue = new LinkedList();
    }
   
    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        trimQueue(timestamp);
        if (!set.contains(message)) {
            set.add(message);
            queue.offer(new Pair<>(message, timestamp));
            return true;
        }
        return false;
    }
    
    private void trimQueue(int timestamp) {
        while (!queue.isEmpty()) {
            Pair<String, Integer> task = queue.peek();
            if (timestamp - task.getValue() < 10) break;
            queue.poll();
            set.remove(task.getKey());
        }
    }
}

/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */
 
```