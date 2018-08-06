H
1533502615
tags: Array, String, Backtracking, BFS, DFS, Hash Table

给一串string, start word, end word. 找到所有从 startWord -> endWord的最短路径list. 

变化方式: mutate 1 letter at a time.

#### BFS + Reverse Search
- 用BFS找最短路径.
- 问题: how to effectively store the path, if the number of paths are really large? 
- If we store Queue<List<String candidates>>: all possibilities will very large and not maintainable
- 用BFS做出一个反向structure, 然后再reverse search

##### BFS Prep Step
- BFS 找到所有start string 可以走到的地方 s, 放在一个overall structure里面: 注意, 存的方式 Map<s, list of sources>
- BFS时候每次都变化1step, 所以记录一次distance, 其实就是最短路径candidate (止步于此)
- 1. 反向mutation map: `destination/end string -> all source candidates` using queue: `Mutation Map`
- Mutation Map<s, List<possible src>>: list possible source strings to mutate into target key string.
- 2. 反向distance map: `destination/end string -> shortest distance to reach dest`
- Distance Map<s, possible/shortest distance>: shortest distance from to mutate into target key string.
- BFS prep step 并没解决问题, 甚至都没有用到end string. 我们要用BFS建成的反向mapping structure, 做search

##### Search using DFS
- 从结尾end string 开始扫, 找所有可以reach的candidate && only visit candidate that is 1 step away
- dfs 直到找到start string.

##### Bi-directional BFS: Search using BFS
- reversed structure 已经做好了, 现在做search 就可以: 也可以选用bfs.
- `Queue<List<String>>` to store candidates, searching from end-> start

```
/**
LeetCode: interface has List<string>, and input dict has to contain end word, but it does not contain start word.
Given two words (beginWord and endWord), and a dictionary's word list, 
find all shortest transformation sequence(s) from beginWord to endWord, such that:

Only one letter can be changed at a time
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:

Return an empty list if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output:
[
  ["hit","hot","dot","dog","cog"],
  ["hit","hot","lot","log","cog"]
]
Example 2:

Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: []

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.

*/
// 69%
/*

- Distance Map<s, possible/shortest distance>: shortest distance from to mutate into target key string.
- Mutation Map<s, List<possible src>>: list possible source strings to mutate into target key string.
*/
/*
- Distance Map<s, possible/shortest distance>: shortest distance from to mutate into target key string.
- Mutation Map<s, List<possible src>>: list possible source strings to mutate into target key string.
*/
// Bi-directional search
class Solution {
    Set<String> dict;
    Map<String, List<String>> mutation = new HashMap<>();
    Map<String, Integer> distance = new HashMap<>();

    public List<List<String>> findLadders(String start, String end, List<String> wordList) {
        List<List<String>> rst = new ArrayList<>();
        dict = new HashSet<>(wordList);
        if (!dict.contains(end)) return rst;

        prep(start);
        //dfs(start, end, new ArrayList<>(), rst); // option1
        bfs(start, end, rst); // option2
        return rst;
    }

    //BFS/Prep to populate mutation and distance map.
    public void prep(String start) {
        //Init
        Queue<String> queue = new LinkedList<>();
        dict.add(start);
        queue.offer(start);
        distance.put(start, 0);
        for (String s : dict) {
            mutation.put(s, new ArrayList<>());
        }
        // process queue
        while(!queue.isEmpty()) {
            String str = queue.poll();
            List<String> list = transform(str);

            for (String s : list) {
                mutation.get(s).add(str);
                if (!distance.containsKey(s)) { // only record dist->s once, as shortest
                    distance.put(s, distance.get(str) + 1);
                    queue.offer(s);
                }
            }
        }
    }
    
    // Option2: bfs, bi-directional search
    public void bfs(String start, String end, List<List<String>> rst) {
        Queue<List<String>> queue = new LinkedList<>();
        List<String> list = new ArrayList<>();
        list.add(end);
        queue.offer(new ArrayList<>(list));
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                list = queue.poll();
                String str = list.get(0);
                
                for (String s : mutation.get(str)) {//All prior-mutation sources
                    list.add(0, s);
                    if (s.equals(start)) {
                        rst.add(new ArrayList<>(list));
                    } else if (distance.containsKey(s) && distance.get(str) - 1 == distance.get(s)) {
                        //Only pick those that's 1 step away: keep minimum steps for optimal solution
                        queue.offer(new ArrayList<>(list));
                    }
                    list.remove(0);
                }
                size--;
            }
        }
    }

    // Option1: DFS to trace back from end string to start string. If reach start string, save result.
    // Use distance<s, distance> to make sure only trace to 1-unit distance candidate
    public void dfs(String start, String str, List<String> path, List<List<String>> rst) {
        path.add(0, str);
        if (str.equals(start)) {
            rst.add(new ArrayList<String>(path));
            path.remove(0);
            return;
        } 
        //Trace 1 step towards start via dfs
        for (String s : mutation.get(str)) {//All prior-mutation sources
            //Only pick those that's 1 step away: keep minimum steps for optimal solution
            if (distance.containsKey(s) && distance.get(str) - 1 == distance.get(s)) {
                dfs(start, s, path, rst);
            }
        }
        path.remove(0);
    }

    //Generate all possible mutations for word. Check against dic and skip word itself.
    private List<String> transform(String word) {
        List<String> candidates = new ArrayList<>();
        StringBuffer sb = new StringBuffer(word);
        for (int i = 0; i < sb.length(); i++) {
            char temp = sb.charAt(i);
            for (char c = 'a'; c <= 'z'; c++) {
                if (temp == c) continue;
                sb.setCharAt(i, c);
                String newWord = sb.toString();
                if (dict.contains(newWord)) {
                    candidates.add(newWord);
                }
            }
            sb.setCharAt(i, temp);//backtrack
        }    
        return candidates;
    }
}

/*
LintCode: interface has Set<String>, also, input dict does not contain end word.
Given two words (start and end), and a dictionary, 
find all shortest transformation sequence(s) from start to end, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the dictionary

Example
Given:
start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
Return
  [
    ["hit","hot","dot","dog","cog"],
    ["hit","hot","lot","log","cog"]
  ]
Note
All words have the same length.
All words contain only lowercase alphabetic characters.
Tags Expand 
Backtracking Depth First Search Breadth First Search

Attempt1 is by me: however it exceeds time/memory limit.
Some other good sources can be found online:
//http://www.jiuzhang.com/solutions/word-ladder-ii/
//http://www.cnblogs.com/shawnhue/archive/2013/06/05/leetcode_126.html
Adjacency List, Prefix ... etc. Let's look at them one after another. First get it through with a NineChapter solution
*/

//Attempt2: Use Nine Chapter solution, BFS + DFS. It works, very nicely, using backtracking.
/*
BFS:
1. For all mutations in dict, create pastMap: all possible mutations that can turn into each particular str in dict.
2. For all mutations in dict, create distance: distance to start point.
DFS:
3. Find minimum path by checking distance different of just 1. Use a List<String> to do DFS

Note: 
Map uses containsKey. Set uses contains
In DFS, add new copy: new ArrayList<String>(path)
BFS: queue, while loop
DFS: recursion, with a structure to go deeper, remember to add/remove element when passing alone
*/
public class Solution {
    
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        List<List<String>> rst = new ArrayList<List<String>>();
        Map<String, ArrayList<String>> pastMap = new HashMap<String, ArrayList<String>>();
        Map<String, Integer> distance = new HashMap<String, Integer>();
        Queue<String> queue = new LinkedList<String>();
        
        //Initiate the variables
        dict.add(start);
        dict.add(end);
        queue.offer(start);
        distance.put(start, 0);
        for (String s : dict) {
            pastMap.put(s, new ArrayList<String>());
        }

        //BFS
        BFS(start, end, distance, pastMap, dict, queue);

        //DFS
        ArrayList<String> path = new ArrayList<String>();
        DFS(start, end, distance, pastMap, path, rst);

        return rst;
    }
    //BFS to populate map and distance:
    //Distance: distance from each str in dict, to the starting point.
    //Map: all possible ways to mutate into each str in dict.
    public void BFS(String start, String end, Map<String, Integer> distance, Map<String, ArrayList<String>> pastMap, Set<String> dict, Queue<String> queue) {
        while(!queue.isEmpty()) {
            String str = queue.poll();
            List<String> list = expand(str, dict);

            for (String s : list) {
                pastMap.get(s).add(str);
                if (!distance.containsKey(s)) {
                    distance.put(s, distance.get(str) + 1);
                    queue.offer(s);
                }
            }
        }
    }
    //DFS on the map, where map is the all possible ways to mutate into a particular str. Backtracking from end to start
    public void DFS(String start, String str, Map<String, Integer> distance, Map<String, ArrayList<String>> pastMap, ArrayList<String> path, List<List<String>> rst) {
        path.add(str);
        if (str.equals(start)) {
            Collections.reverse(path);
            rst.add(new ArrayList<String>(path));
            Collections.reverse(path);
        } else {//next step, trace 1 step towards start
            for (String s : pastMap.get(str)) {//All previous-mutation options that we have with str:
                if (distance.containsKey(s) && distance.get(str) == distance.get(s) + 1) {//Only pick those that's 1 step away: keep minimum steps for optimal solution
                    DFS(start, s, distance, pastMap, path, rst);
                }
            }
        }
        path.remove(path.size() - 1);
    }
    //Populate all possible mutations for particular str, skipping the case that mutates back to itself.
    public ArrayList<String> expand(String str, Set<String> dict) {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < str.length(); i++) {//Alternate each letter position
            for (int j = 0; j < 26; j++) {//Alter 26 letters
                if (str.charAt(i) != (char)('a' + j)) {
                    String newStr = str.substring(0, i) + (char)('a' + j) + str.substring(i + 1);
                    if (dict.contains(newStr)) {
                        list.add(newStr);
                    }
                }
            }
        }
        return list;
    }
}



//Attempt1: probably works, however:
//Testing against input: "qa", "sq", ["si","go","se","cm","so","ph","mt","db","mb","sb","kr","ln","tm","le","av","sm","ar","ci","ca","br","ti","ba","to","ra","fa","yo","ow","sn","ya","cr","po","fe","ho","ma","re","or","rn","au","ur","rh","sr","tc","lt","lo","as","fr","nb","yb","if","pb","ge","th","pm","rb","sh","co","ga","li","ha","hz","no","bi","di","hi","qa","pi","os","uh","wm","an","me","mo","na","la","st","er","sc","ne","mn","mi","am","ex","pt","io","be","fm","ta","tb","ni","mr","pa","he","lr","sq","ye"]
//0. Could be backtrackList exceed memory limit.
//1. If use HashSet<String> set to check if particular sequence exist, then exceed memory
//2. If use StringBuffer strCheck to check if particular sequence exist, then exceed time limit.
//It looks like we'd use DFS for final results.
public class Solution {
    private Queue<String> q = new LinkedList<String>();
    private Queue<ArrayList<String>> backtrackList = new LinkedList<ArrayList<String>>();
    private Set<String> dict;
    private String end;
    private int level = 1;
    private int len = Integer.MAX_VALUE;
    private List<List<String>> rst = new ArrayList<List<String>>();

    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        if (start == null || end == null || dict == null || start.length() != end.length()) {
            return rst;
        }
        this.dict = dict;
        this.end = end;
        ArrayList<String> head = new ArrayList<String>();
        head.add(start);
        q.offer(start);
        backtrackList.offer(head);
        while(!q.isEmpty()) {//BFS
            int size = q.size();//Fix size
            level++;
            for (int k = 0; k < size; k++) {//LOOP through existing queue: for this specific level
                String str = q.poll();
                ArrayList<String> list = backtrackList.poll();
                validateMutations(str, list);
            }//END FOR K
        }//END WHILE

        List<List<String>> minRst = new ArrayList<List<String>>();
        for (int i = 0; i < rst.size(); i++) {
            if (rst.get(i).size() == len) {
                minRst.add(rst.get(i));
            }
        }
        return minRst;
    }


    public void validateMutations(String str, ArrayList<String> list) {
        if (list.size() > len) {//No need to digger further if list is already greater than min length
            return;
        }
        for (int i = 0; i < str.length(); i++) {//Alternate each letter position
            for (int j = 0; j < 26; j++) {//Alter 26 letters
                if (str.charAt(i) == (char)('a' + j)) {
                    continue;
                }
                String newStr = str.substring(0, i) + (char)('a' + j) + str.substring(i + 1);

                ArrayList<String> temp = (ArrayList<String>)list.clone();
                temp.add(newStr);
                if (dict.contains(newStr)) {
                    if (newStr.equals(end)) {//Found end
                        len = Math.min(len, level);
                        rst.add(temp);
                    } else {
                        q.offer(newStr);
                        backtrackList.offer(temp);
                    }
                }
            }//END FOR J
        }//END FOR I
    }
}

```