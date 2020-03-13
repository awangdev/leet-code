M
tags: Hash Table, Heap, Trie, PriorityQueue, MaxHeap, MinHeap
time: O(n)
space: O(n)

给一串String. 找到top k frequent words.

#### Method1: Bucket Sort
- 1) Build frequency map, 2) use frequency map to build freq bucket
- Loop from largest bucket freq -> 0, and output.
- Time: Solid O(n)
- Space: O(n)

#### Method2: PriorityQueue - Min Heap
- O(n) space of map, O(nlogk) to build queue.
- limit minHeap queue size to k: add to queue if found suitable item; always reduce queue if size > k

#### Method3: PriorityQueue - Max Heap
- 用HashMap存frequency, 用ArrayList存lists of words
- create一个Node class, 然后用PriorityQueue.   
- PriorityQueue里面用到了 String.compareTo(another String).巧妙。
- time: PQ uses O(nlogn), overall O(nlogn)
- slower, because the maxHeap needs to add all candidates

#### Trie && MinHeap屌炸天   
- 可以做一下
- http://www.geeksforgeeks.org/find-the-k-most-frequent-words-from-a-file/


#### Deleted Attempt: HashMap + collections.sort()
- 用HashMap存frequency, 用ArrayList存lists of words。最后返回从尾部向前数的k个。   
- 注意排序时Collection.sort()的cost是O(nLogk)
- not efficient


```
/*
Given a list of words and an integer k, return the top k frequent words in the list.

Example
Given

[
    "yes", "lint", "code",
    "yes", "code", "baby",
    "you", "baby", "chrome",
    "safari", "lint", "code",
    "body", "lint", "code"
]
for k = 3, return ["code", "lint", "baby"].

for k = 4, return ["code", "lint", "baby", "yes"],

Note
You should order the words by the frequency of them in the return list, 
the most frequent one comes first. If two words has the same frequency, 
the one with lower alphabetical order come first.

Challenge
Do it in O(nlogk) time and O(n) extra space.

Extra points if you can do it in O(n) time with O(k) extra space.

Tags Expand 
Hash Table Heap Priority Queue
*/

// Method1: Bucket Sort

class Solution {
    class Node {
        int freq = 0;
        String str;
        public Node(String str){
            this.str = str;
        }
    }
    public List<String> topKFrequent(String[] words, int k) {
        List<String> rst = new ArrayList<>();
        if (words == null) return rst;
        List<String>[] bucket = buildBucket(words);

        for (int i = bucket.length -1; i > 0 && k > 0; i--) {
            if (bucket[i] != null) {
                Collections.sort(bucket[i]);
                for (int j = 0; j < bucket[i].size() && k > 0; j++, k--) {
                    rst.add(bucket[i].get(j));
                }
            } 
        }
        return rst;
    }
    
    private List<String>[] buildBucket(String[] words) {
        HashMap<String, Node> map = buildFreqMap(words);
        List<String>[] bucket = new List[map.size() + 1];
        for (Node node: map.values()) {
            if(bucket[node.freq] == null) bucket[node.freq] = new ArrayList<>();
            bucket[node.freq].add(node.str);
        }
        return bucket;
    }
    
    private HashMap<String, Node> buildFreqMap(String[] words) {
        HashMap<String, Node> map = new HashMap<>();
        for (String word: words) {
            map.putIfAbsent(word, new Node(word));
            map.get(word).freq += 1;
        }
        return map;
    }
}

// Method2: MinHeap: sort by ascending frequency; by reverse alphabetical order
class Solution {
    class Node {
        int freq = 0;
        String str;
        public Node(String str){
            this.str = str;
        }
    }
    public List<String> topKFrequent(String[] words, int k) {
        List<String> rst = new ArrayList<>();
        if (words == null) return rst;
        PriorityQueue<Node> queue = configQueue(k); // minHeap
        HashMap<String, Node> map = buildFreqMap(words);
        
        for (Map.Entry<String, Node> entry : map.entrySet()) {
            Node node = entry.getValue();
            if (queue.size() < k || node.freq >= queue.peek().freq) queue.offer(node);
            if (queue.size() > k) queue.poll();
        }

        while (!queue.isEmpty()) rst.add(0, queue.poll().str);// output
        return rst;
    }
    
    private PriorityQueue<Node> configQueue(int k) {
        return new PriorityQueue<>(k, new Comparator<Node>(){
            public int compare(Node a, Node b) {
                if (a.freq == b.freq) {
                    return b.str.compareTo(a.str);
                } else {
                    return a.freq - b.freq;
                }
            } 
        });
    }
    
    private HashMap<String, Node> buildFreqMap(String[] words) {
        HashMap<String, Node> map = new HashMap<>();
        for (String word: words) {
            map.putIfAbsent(word, new Node(word));
            map.get(word).freq += 1;
        }
        return map;
    }
}

// Method3: MaxHeap
class Solution {
    class Node {
        int freq;
        String str;
        public Node(String str, int freq){
            this.str = str;
            this.freq = freq;
        }
    }
    public List<String> topKFrequent(String[] words, int k) {
        List<String> rst = new ArrayList<>();
        if (words == null || words.length == 0 || k <= 0) {
            return rst;
        }
        //queue
        PriorityQueue<Node> queue = new PriorityQueue<>(k, new Comparator<Node>(){
            public int compare(Node a, Node b) {
                if (a.freq == b.freq) {
                    return a.str.compareTo(b.str);
                } else {
                    return b.freq - a.freq;
                }
            } 
        });
        //map
        HashMap<String, Node> map = new HashMap<>();
        for (String word: words) {
            if (!map.containsKey(word)) {
                map.put(word, new Node(word, 0));
            }
            map.get(word).freq = map.get(word).freq + 1;
        }
        
        for (Map.Entry<String, Node> entry : map.entrySet()) {
            queue.offer(entry.getValue());
        }
        //output
        for (int i = 0; i < k; i++) {
            rst.add(queue.poll().str);
        }
        
        return rst;
    }
}
```