M
tags: String, Heap, Greedy, Sort, Hash Table
time: O(m), m = # of unique letters
space: O(nLogm), n = length


We want to exhaust largest population and merge like merging k list.
Problem: largest population may result in them being adjacent. How to resolve?

1) process and check at the end, or, 2) sanitize first and process assume correct input

#### Method1: K(k=2) seats apart problem (w/o sanitization)
- Aggregate map<char, count>, and sort the entry with priority queue.(Optionally, can use object `Letter {char c, int count}`)
- Naturally: we want to prioritize the largest population and exhaust it first, so we want to keep it in the a buffer queue
    - it is a queue, first in first out
    - monitor queue size k = 2, so that it holds off the just last-processed letter for 1 unit of time
    - the buffer then sends the last-process item to the main priority queue (pq will sort it again)
- Error handling: largest population may have extra letter
    - the main PQ has already exhausted
    - but the largest-population-letter will end up stuck in the buffer queue
    - it will never be picked up again so the final result sb will be shorter than orignal string: that is the error case
- Option0. Similar to `621. Task Scheduler`:
    - use a buffer to hold potential letter to add back, but NOT ADD BACK YET, until k slots have been filled.
- time: O(m), m = # of unique letters
- space: O(nmLogm), n = length, pq sorting requires mlogm, we will visit all n nodes.

#### Method2: HashMap<Num, # occurance>, Sort (Sanitize input)
- put all in map<char, count>
    - Sanitize the input: if certain popular char count is over (n + 1)/2, then it should fail right away, just return empty map.
    - Once the input is sanitized, when building results, we can be greedy and consume most popular char and then the rest 
- Int[2] can be used store char and count 
    - PriorityQueue can sort int[]. Okay to not specific length of int[] when defining pq.
    - Alternatively, can use a Letter {char c, int count} to represent



```
/*
Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.

If possible, output any possible result.  If not possible, return the empty string.

Example 1:

Input: S = "aab"
Output: "aba"
Example 2:

Input: S = "aaab"
Output: ""
Note:

S will consist of lowercase letters and have length in range [1, 500].
 
*/
// Method, Option0. Similar to `621. Task Scheduler`
class Solution {
    public String reorganizeString(String S) {
        if (S == null || S.length() == 0) return "";

        Map<Character, Letter> map = new HashMap<>();
        for (char c : S.toCharArray()) {
            map.putIfAbsent(c, new Letter(c, 0));
            map.get(c).count += 1;
            if (map.get(c).count > (S.length() + 1) / 2) return "";
        }

        PriorityQueue<Letter> pq = new PriorityQueue<>((a, b) -> (b.count - a.count));
        pq.addAll(map.values());

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            int k = 2; // two slots to fill
            Queue<Letter> buffer = new LinkedList<>();
            while (k > 0 && !pq.isEmpty()) {
                Letter letter = pq.poll();
                sb.append(letter.c);
                letter.count -= 1;
                if (letter.count > 0) buffer.offer(letter);
                k--;
            }
            
            pq.addAll(buffer);
        }
        return sb.length() == S.length() ? sb.toString() : "";
    }
    
    class Letter {
        char c;
        int count;
        public Letter(char c, int count) {
            this.c = c;
            this.count = count;
        }
    }
}


/**
Method1: K(k=2) seats apart problem
Option1
 */
class Solution {
    public String reorganizeString(String S) {
        if (S == null || S.length() == 0) return "";
        Map<Character, Integer> map = new HashMap<>();
        for (char c : S.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((a, b) -> (b.getValue() - a.getValue()));
        pq.addAll(map.entrySet());
        StringBuilder sb = new StringBuilder();
        Queue<Map.Entry<Character, Integer>> buffer = new LinkedList<>();
        while (!pq.isEmpty()) {
            Map.Entry<Character, Integer> entry = pq.poll();
            sb.append(entry.getKey());
            entry.setValue(entry.getValue() - 1);
            buffer.offer(entry);
            if (buffer.size() == 2) {
                Map.Entry<Character, Integer> temp = buffer.poll();
                if (temp.getValue() > 0) pq.offer(temp);
            }
        }
        return sb.length() == S.length() ? sb.toString() : "";
    }
}

// Method1.Option2: Slight change by using object
class Solution {
    public String reorganizeString(String S) {
        if (S == null || S.length() == 0) return "";

        Map<Character, Letter> map = new HashMap<>();
        for (char c : S.toCharArray()) {
            map.putIfAbsent(c, new Letter(c, 0));
            map.get(c).count += 1;
            if (map.get(c).count > (S.length() + 1) / 2) return "";
        }

        PriorityQueue<Letter> pq = new PriorityQueue<>((a, b) -> (b.count - a.count));
        pq.addAll(map.values());

        StringBuilder sb = new StringBuilder();
        Queue<Letter> buffer = new LinkedList<>();
        while (!pq.isEmpty()) {
            Letter letter = pq.poll();
            sb.append(letter.c);
            letter.count -= 1;
            buffer.offer(letter);
            if (buffer.size() > 1) {
                Letter temp = buffer.poll();
                if (temp.count > 0) pq.offer(temp);
            }
        }
        return sb.length() == S.length() ? sb.toString() : "";
    }
    
    class Letter {
        char c;
        int count;
        public Letter(char c, int count) {
            this.c = c;
            this.count = count;
        }
    }
}


// Method2: Sanitize the input when building the queue, and process it like merging k lists

// priority queue with comparator is a bit faster
PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>(){
    public int compare(int[] a, int[] b){
        return a[1] > b[1] ? -1 : 1;
    }
});



class Solution {
    public String reorganizeString(String S) {
        if (S == null || S.length() <= 1) return S;
        
        // convert and build queue (eleminate false)
        Map<Character, Integer> map = buildCountMap(S);
        PriorityQueue<int[]> queue = buildCharQueue(map);
        StringBuffer sb = new StringBuffer();
        
        // Assume the input is sanitized: largest population won't cause additional tail
        while(!queue.isEmpty()) {
            int[] curr = queue.poll();
            // not duplicating, add!
            if (sb.length() == 0 || curr[0] != sb.charAt(sb.length() - 1)) {
                sb.append((char) curr[0]);
                if (--curr[1] > 0) queue.add(curr);
            } else {
                // found duplicate, pick another one
                int[] next = queue.poll();
                sb.append((char) next[0]);
                if (--next[1] > 0) queue.add(next);
                
                // not used, add it back
                queue.add(curr);
            }
        }
        return sb.toString();
    }
    
    private Map<Character, Integer> buildCountMap(String S) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : S.toCharArray()) {
            int count = map.getOrDefault(c, 0) + 1;
            if (count > (S.length() + 1) / 2) return new HashMap<>(); // c appear more than half, fail
            map.put(c, count);
        }
        return map;
    }
    
    // Greedy: always consume the highest count first
    // Use priority queue to sort int[]{char, c}
    private PriorityQueue<int[]> buildCharQueue (Map<Character, Integer> map) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((a,b) -> b[1] - a[1]);
        for (char c : map.keySet()) {
            queue.add(new int[]{c, map.get(c)});
        }
        return queue;
    }
}

```