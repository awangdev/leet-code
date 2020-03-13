H
1531238039
tags: Hash Table, Heap, Greedy

给一个string, 全是lowercase letter, 要求重新排列: 然后每个unique的character要有k distance apart.

跟Task Scheduler有点像, 只不过Task那道题里面还可以用其他方法求count, 这道题要求出排列结果

#### PriorityQueue + HashTable
- PriorityQueue排序+分布排列的一个经典用法.
- Count frequency and store in pq.
- Consume element of pq for k rounds, each time pick one element from queue.
- Exception: if k still has content but queue is consumed: cannot complete valid string, return "";
- space, O(n) extra space in sb, O(26) constant space with pq.
- time: O(n) to add all items

```
/*
Given a non-empty string s and an integer k, 
rearrange the string such that the same characters are at least distance k from each other.

All input strings are given in lowercase letters. 
If it is not possible to rearrange the string, return an empty string "".

Example 1:
s = "aabbcc", k = 3

Result: "abcabc"

The same letters are at least distance 3 from each other.
Example 2:
s = "aaabc", k = 3 

Answer: ""

It is not possible to rearrange the string.
Example 3:
s = "aaadbbcc", k = 2

Answer: "abacabcd"

Another possible answer is: "abcabcda"

The same letters are at least distance 2 from each other.
*/

/*
Count frequency and store in pq.
Consume element of pq for k rounds, each time pick one element from queue.
Exception: if k still has content but queue is consumed: can't complete valid string, return "";
*/
class Solution {
    class Node {
        char c;
        int count;
        public Node(char c, int count) {
            this.c = c;
            this.count = count;
        }
    }
    public String rearrangeString(String s, int k) {
        if (s == null || s.length() == 0 || k <= 0) return s;

        PriorityQueue<Node> pq = buildQueue(s);
        // Process        
        StringBuffer sb = new StringBuffer();
        while (!pq.isEmpty()) {
            Set<Node> tempSet = new HashSet<>();
            for (int i = k - 1; i >= 0 && !pq.isEmpty(); i--) { // process k items/round
                Node node = pq.poll();
                sb.append(node.c);
                
                if (node.count > 1) {
                    node.count--;
                    tempSet.add(node);
                }
                // exception case: k not consumed, but still has remains in tempSet
                if (pq.isEmpty() && i > 0 && !tempSet.isEmpty()) return ""; 
            }
            pq.addAll(tempSet);
        }
        
        return sb.toString();
    }
    
    private PriorityQueue<Node> buildQueue(String s) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) count[c - 'a']++;
        
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.count != b.count ? b.count - a.count : a.c - b.c);
        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) pq.offer(new Node((char)(i + 'a'), count[i]));
        }

        return pq;
    }
}
```