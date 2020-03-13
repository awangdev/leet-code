M
tags: Design, Stack, Doubly Linked List, TreeMap
time: avg O(1), [O(logN) peekMax(), TreeMap]; [O(n) popMax(), TwoStack] 
space: O(n)

#### Two Stack
- one to keep regular elements
- one to repat the max at current stack level
- time: O(n) for popMax() and O(1) for the rest operations
- space: O(n)

#### TreeMap
- Reference: https://leetcode.com/problems/max-stack/solution/
- Use TreeMap to store <Int, List of Nodes>, which gives: **O(logN) insert, delete and find MAX**
- Key reason to use `DoubleLinkedList` is to perform O(1) removal for `popMax()`
- The problem becomes finding the target value & remove from DoubleLinkedList
- time: O(1) for popMax() and O(logN) for the rest
- space: O(n)

```
/*
Design a max stack that supports push, pop, top, peekMax and popMax.

push(x) -- Push element x onto stack.
pop() -- Remove the element on top of the stack and return it.
top() -- Get the element on the top.
peekMax() -- Retrieve the maximum element in the stack.
popMax() -- Retrieve the maximum element in the stack, and remove it. If you find more than one maximum elements, only remove the top-most one.
Example 1:
MaxStack stack = new MaxStack();
stack.push(5); 
stack.push(1);
stack.push(5);
stack.top(); -> 5
stack.popMax(); -> 5
stack.top(); -> 1
stack.peekMax(); -> 5
stack.pop(); -> 1
stack.top(); -> 5
Note:
-1e7 <= x <= 1e7
Number of operations won't exceed 10000.
The last four operations won't be called when stack is empty.
*/

/*
Use two stacks: 
    - one to keep regular elements
    - one to repat the max at current stack level
*/
class MaxStack {
    Stack<Integer> stack;
    Stack<Integer> max;

    /** initialize your data structure here. */
    public MaxStack() {
        stack = new Stack<>();
        max = new Stack<>();
    }
    
    public void push(int x) {
        stack.push(x);
        int maxVal = max.isEmpty() ? x : Math.max(max.peek(), x);
        max.push(maxVal);
    }
    
    public int pop() {
        max.pop();
        return stack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int peekMax() {
        return max.peek();
    }
    
    public int popMax() {
        int maxVal = peekMax();
        Stack<Integer> temp = new Stack<>();
        while(top() != maxVal) temp.push(pop());
        pop();
        
        while(!temp.isEmpty()) push(temp.pop());
        
        return maxVal;
    }
}


/*
Reference: https://leetcode.com/problems/max-stack/solution/
- Use TreeMap to store <Int, List of Nodes>, which gives O(logN) insert, delete and find MAX
- Build DoubleLinkedList class to perform O(1) removal
- The problem becomes finding the target value & remove from DoubleLinkedList
*/
class MaxStack {
    TreeMap<Integer, List<Node>> map;
    DoubleLinkedList dll;

    public MaxStack() {
        map = new TreeMap();
        dll = new DoubleLinkedList();
    }

    // O(1)
    public void push(int x) {
        Node node = dll.add(x);
        map.putIfAbsent(x, new ArrayList<Node>());
        map.get(x).add(node);
    }

    // O(1)
    public int pop() {
        int val = dll.pop();
        removeFromMap(val);
        return val;
    }

    // O(1)
    public int top() {
        return dll.peek();
    }

    // O(logN)
    public int peekMax() {
        return map.lastKey();
    }

    // O(1)
    public int popMax() {
        int max = peekMax();
        Node node = removeFromMap(max);
        dll.unlink(node);
        return max;
    }
    
    // Find val from map, remove it from list, & remove list if empty
    // O(1)
    private Node removeFromMap(int val) {
        List<Node> list = map.get(val);
        Node node = list.remove(list.size() - 1);
        if (list.isEmpty()) map.remove(val);
        return node;
    }
    
    // Define DoubleLinkedList class
    class DoubleLinkedList {
        Node head, tail;

        public DoubleLinkedList() {
            head = new Node(0);
            tail = new Node(0);
            head.next = tail;
            tail.prev = head;
        }

        public Node add(int val) {
            Node x = new Node(val);
            x.next = tail;
            x.prev = tail.prev;
            tail.prev = tail.prev.next = x; // append to tail
            return x;
        }

        public int pop() {
            return unlink(tail.prev).val;
        }

        public int peek() {
            return tail.prev.val;
        }

        public Node unlink(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            return node;
        }
    }

    class Node {
        int val;
        Node prev, next;
        public Node(int v) {val = v;}
    }
}

```