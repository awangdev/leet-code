M
tags: Binary Search, Hash Table, TreeMap
time: set O(1), get(logn)
space: O(n)

#### Method1: Binary Search
- use hash to store <key, list of values>
- binary serach on list of values

#### Method2: TreeMap
- use hash to store <key, TreeMap<Timestamp, Value>>
- treemap.floorKey(timestamp) finds the top item below certain timestamp

```
/*
Create a timebased key-value store class TimeMap, that supports two operations.

1. set(string key, string value, int timestamp)

Stores the key and value, along with the given timestamp.
2. get(string key, int timestamp)

Returns a value such that set(key, value, timestamp_prev) was called previously, with timestamp_prev <= timestamp.
If there are multiple such values, it returns the one with the largest timestamp_prev.
If there are no values, it returns the empty string ("").
 

Example 1:

Input: inputs = ["TimeMap","set","get","get","set","get","get"], inputs = [[],["foo","bar",1],["foo",1],["foo",3],["foo","bar2",4],["foo",4],["foo",5]]
Output: [null,null,"bar","bar",null,"bar2","bar2"]
Explanation:   
TimeMap kv;   
kv.set("foo", "bar", 1); // store the key "foo" and value "bar" along with timestamp = 1   
kv.get("foo", 1);  // output "bar"   
kv.get("foo", 3); // output "bar" since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 ie "bar"   
kv.set("foo", "bar2", 4);   
kv.get("foo", 4); // output "bar2"   
kv.get("foo", 5); //output "bar2"   

Example 2:

Input: inputs = ["TimeMap","set","set","get","get","get","get","get"], inputs = [[],["love","high",10],["love","low",20],["love",5],["love",10],["love",15],["love",20],["love",25]]
Output: [null,null,null,"","high","high","low","low"]
 

Note:

All key/value strings are lowercase.
All key/value strings have length in the range [1, 100]
The timestamps for all TimeMap.set operations are strictly increasing.
1 <= timestamp <= 10^7
TimeMap.set and TimeMap.get functions will be called a total of 120000 times (combined) per test case.
*/

/*
- the same key maps to multiple values, marked by timestamp.
    - input timestamp always increasing: we have a increasing list
    - query: O(logn) binary search to find target timstamp
- record {value; timestamp}
- sort collection by timestamp;
- find with binary search
- time: set O(1), get(logn)
*/
class TimeMap {
    class Record {
        String value;
        int timestamp;
        public Record (String val, int time) {
            this.value = val;
            this.timestamp = time;
        }
    }

    Map<String, List<Record>> map;
    /** Initialize your data structure here. */
    public TimeMap() {
        map = new HashMap<>();
    }
   
    public void set(String key, String value, int timestamp) {
        List<Record> list = map.getOrDefault(key, new ArrayList<>());
        list.add(new Record(value, timestamp));
        map.put(key, list);
    }
   
    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) return "";
        List<Record> list = map.get(key);
        return binarySearch(list, timestamp);
    }
   
   
   /**
   // Optional, if not using :
   int i = Collections.binarySearch(list, new Record("{", timestamp),
            (a, b) -> Integer.compare(a.timestamp, b.timestamp));
    if (i >= 0) return list.get(i).value;
    else if (i == -1) return "";
    else return list.get(-i-2).value; // item not exist i = (-(insertion point) - 1); want best available which is insertion point - 1 = - i - 1 - 1 = -i - 2             
   */
    private String binarySearch(List<Record> list, int timestamp) {
        int start = 0, end = list.size() - 1;
        if (timestamp < list.get(start).timestamp) return "";
        if (timestamp > list.get(end).timestamp) return list.get(end).value;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (list.get(mid).timestamp <= timestamp) {
                if (mid + 1 > end) return list.get(mid).value;
                if (mid + 1 <= end) {
                    if (timestamp < list.get(mid + 1).timestamp) return list.get(mid).value;
                    start = mid;
                }
            } else end = mid;
        }
        if (list.get(end).timestamp <= timestamp) return list.get(end).value;
        return list.get(start).value;
    }
}


// method2 tree map
class TimeMap {
    Map<String, TreeMap<Integer, String>> map;
    /** Initialize your data structure here. */
    public TimeMap() {
        map = new HashMap<>();
    }
   
    public void set(String key, String value, int timestamp) {
        TreeMap<Integer, String> treeMap = map.getOrDefault(key, new TreeMap<>());
        treeMap.put(timestamp, value);
        map.put(key, treeMap);
    }
   
    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) return "";
        TreeMap<Integer, String> treeMap = map.get(key);
        Integer t = treeMap.floorKey(timestamp);
        return t != null ? treeMap.get(t) : "";
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */
```