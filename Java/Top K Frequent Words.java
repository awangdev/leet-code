M
1527835239
tags: Hash Table, Heap, Trie, PriorityQueue, MaxHeap, MinHeap
time: O(nlogk)
space: O(n)

给一串String. 找到top k frequent words.

#### PriorityQueue - Min Heap
- O(n) space of map, O(nlogk) to build queue.
- limit minHeap queue size to k: add to queue if found suitable item; always reduce queue if size > k

#### PriorityQueue - Max Heap
- 用HashMap存frequency, 用ArrayList存lists of words
- create一个Node class, 然后用PriorityQueue.   
- PriorityQueue里面用到了 String.compareTo(another String).巧妙。
- time: PQ uses O(nlogn), overall O(nlogn)
- slower, because the maxHeap needs to add all candidates

#### Trie && MinHeap屌炸天   
- 可以做一下
- http://www.geeksforgeeks.org/find-the-k-most-frequent-words-from-a-file/

#### HashMap + collections.sort()
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
// MaxHeap
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

// MinHeap: sort by ascending frequency; by reverse alphabetical order
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
        if (isInvalid(words, k)) return rst;
        //queue
        PriorityQueue<Node> queue = new PriorityQueue<>(k, new Comparator<Node>(){
            public int compare(Node a, Node b) {
                if (a.freq == b.freq) {
                    return b.str.compareTo(a.str);
                } else {
                    return a.freq - b.freq;
                }
            } 
        });
        // Lambda notation, slower:
        //PriorityQueue<Node> queue = new PriorityQueue<>((a, b) -> a.freq == b.freq ? b.str.compareTo(a.str) : a.freq - b.freq);
        //map
        HashMap<String, Node> map = new HashMap<>();
        for (String word: words) {
            map.putIfAbsent(word, new Node(word));
            map.get(word).freq += 1;
        }
        
        for (Map.Entry<String, Node> entry : map.entrySet()) {
            Node node = entry.getValue();
            if (queue.size() < k || node.freq >= queue.peek().freq) {
                queue.offer(node);
            }
            
            if (queue.size() > k) {
                queue.poll();
            }
        }
        //output
        while (!queue.isEmpty()) {
            rst.add(0, queue.poll().str);
        }
        
        return rst;
    }
    
    private boolean isInvalid(String[] words, int k) {
        return words == null || words.length == 0 || k <= 0;
    }
}

/*
	Attempt1, Thoughts:
	Brutle force
	HashMap<word, frequency>, size n. O(n)
	ArrayList<ArrayList<String>> lists: each entry has a list of strings with index as frequency. O(n)
	Adding to result String[], do a Collections.sort(), which cause O(nlogk) with a O(n) for loop on top; It becomes O(nLogk)on average.
	Return lists' top k.
*/
public class Solution {
    public String[] topKFrequentWords(String[] words, int k) {
        String[] rst = new String[k];
    	if (words == null || words.length == 0 || k <= 0) {
    		return rst;
    	}
    
    	HashMap<String, Integer> map = new HashMap<String, Integer>();
    	ArrayList<ArrayList<String>> lists = new ArrayList<ArrayList<String>>();
    	for (int i = 0; i <= words.length; i++) {
    		lists.add(new ArrayList<String>());
    	}
    	//Fill map
    	for (int i = 0; i < words.length; i++) {
    		if (!map.containsKey(words[i])) {
    			map.put(words[i], 0);
    		}
    		map.put(words[i], map.get(words[i]) + 1);
    	}
    	//Fill ArrayList
    	for (Map.Entry<String, Integer> entry : map.entrySet()) {
    		int freqIndex = entry.getValue();
    		lists.get(freqIndex).add(entry.getKey());
    	}

    	int count = 0;
    	for (int i = lists.size() - 1; i >= 0; i--) {
    		ArrayList<String> list = lists.get(i);
    		Collections.sort(list);
    		for (int j = 0; j < list.size(); j++) {
    			if (count < k) {
	    			rst[count] = list.get(j);
	    			count++;
    			} 
    			if (count >= k) {
    				return rst;
    			}
    		}
    	}

    	return rst;
    }
}
```