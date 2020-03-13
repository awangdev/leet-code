H
1520728398
tags: Backtracking, Trie

可以开Trie class, 里面用到TrieNode. 开Trie(words) 可以直接initalize with for loop
TrieNode 里面可以有一个 List<String> startWith: 记录可以到达这个点的所有string: 有点像树形, ancestor形状的存储.

神操作:
根据square的性质, 如果选中了list of words, 设定 int prefixIndex = list.size().
取出list里面的所有word[prefixedIndex], 并且加在一起, 就是下一个word candidate的 prefix.

形象一点:
list = ["ball", "area"];
prefixIndex = list.size(); ball[prefixIndex] = 'l'; area[prefixIndex] = 'e';
//then
candidatePrefix = ball[prefixIndex] + area[prefixIndex] = "le";
这里就可以用到Trie的那个 findByPrefix function, 在每个点, 都存有所有这个点能产生的candidate.
这时, 试一试所有candidate: dfs

能想到这种倒转的结构来存prefix candidates 在 Trie里面, 这个想法非常值得思考.

```
/*
Given a set of words (without duplicates), 
find all word squares you can build from them.

A sequence of words forms a valid word square if the kth row and column 
read the exact same string, where 0 ≤ k < max(numRows, numColumns).

For example, the word sequence ["ball","area","lead","lady"] 
forms a word square because each word reads the same both horizontally and vertically.

b a l l
a r e a
l e a d
l a d y
Note:
There are at least 1 and at most 1000 words.
All words will have the exact same length.
Word length is at least 1 and at most 5.
Each word contains only lowercase English alphabet a-z.
Example 1:

Input:
["area","lead","wall","lady","ball"]

Output:
[
  [ "wall",
    "area",
    "lead",
    "lady"
  ],
  [ "ball",
    "area",
    "lead",
    "lady"
  ]
]

Explanation:
The output consists of two word squares. The order of output does not matter 
(just the order of words in each word square matters).
Example 2:

Input:
["abat","baba","atan","atal"]

Output:
[
  [ "baba",
    "abat",
    "baba",
    "atan"
  ],
  [ "baba",
    "abat",
    "baba",
    "atal"
  ]
]

Explanation:
The output consists of two word squares. The order of output does not matter 
(just the order of words in each word square matters).

*/

/*
Thoughts:
Characteristic:
1. When 1st row/word is picked, the 2nd position in the string will be the next wordCandidate's prefix.
2. When 2 words are picked, the 3rd postion of both strings will add together as next wordCandidate's prefix

Build Trie that stores all possible candidates at current node.
This time, put trie creation logic in Trie itself, since we are building the structure with input

DFS:
1. Pick list of candidates
2. Based on size of the list, find the prefix, and find all futurer candidates.
3. add each candidate (backtracking), and DFS
*/
class Solution {
    // Define TrieNode
    class TrieNode {
        TrieNode [] children = new TrieNode[26];
        List<String> startWith = new ArrayList<>();
    }

    // Define Trie
    class Trie {
        TrieNode root = new TrieNode();

        // Build the trie and add list of startWith/candidates on node.
        public Trie(String[] words) {
            for (String word: words) {
                TrieNode node = root;
                for (char c: word.toCharArray()) {
                    int index = c - 'a';
                    if (node.children[index] == null) {
                        node.children[index] = new TrieNode();
                    }
                    node.children[index].startWith.add(word);
                    node = node.children[index];
                }
            }
        }
        
        // Get list of candidates for given prefix
        public List<String> findByPrefix(String prefix) {
            List<String> candidates = new ArrayList<>();
            if (prefix == null || prefix.length() == 0) {
                return candidates;
            }
            TrieNode node = root;
            for (char c : prefix.toCharArray()) {
                int index = c - 'a';
                if (node.children[index] == null) {
                    return candidates;
                }
                node = node.children[index];
            }
            candidates.addAll(node.startWith);
            return node.startWith;
        }
    }
    
    public Trie trie;
    public int length;
    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> rst = new ArrayList<>();
        if (words == null || words.length == 0) {
            return rst;
        }
        length = words[0].length();
        trie = new Trie(words);
        for (String word: words) {
            List<String> selected = new ArrayList<>();
            selected.add(word);
            dfs(rst, selected);
        }
        return rst;
    }
    
    // Find the word 
    private void dfs(List<List<String>> rst, List<String> selected) {
        if (selected.size() == length) {
            rst.add(new ArrayList<>(selected));
            return;
        }
        
        int prefixIndex = selected.size();
        StringBuffer sb = new StringBuffer();
        for (String word: selected) {
            sb.append(word.charAt(prefixIndex));
        }
        List<String> newCandidates = trie.findByPrefix(sb.toString());

        for (String candidate: newCandidates) {
            selected.add(candidate);
            dfs(rst, selected);
            //Backtracking
            selected.remove(selected.size() - 1);
        }
    }
    
}

```