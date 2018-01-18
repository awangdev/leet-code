E

方法1:
按大小排序 -> 从最大的开始做contains()的比较 -> 结果再按照字母表顺序(lexicographically) sort一下.
但是Collections.sort()了两次, 而且再list.contains(), 比较慢

方法2:
先排序, 以最简单的size==1以及set.contains()找match.
如果找到, 因为已经按照字母表排序, 找到的这个肯定是这个长度里面最符合的解答.
然后brutally找下一个更大的.
法2比法1好, 因为只用了一次sort, nLog(n). 然后其余都是O(1)的contains.
法1有两个sort(), 然后在list上面contains(), 所以比较耗时.

方法3:
应该可以有一个用Trie的方式做的, 还没有考虑.


```
/*
Given a list of strings words representing an English Dictionary, find the longest word in words that can be built one character at a time by other words in words. If there is more than one possible answer, return the longest word with the smallest lexicographical order.

If there is no answer, return the empty string.
Example 1:
Input: 
words = ["w","wo","wor","worl", "world"]
Output: "world"
Explanation: 
The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".
Example 2:
Input: 
words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
Output: "apple"
Explanation: 
Both "apply" and "apple" can be built from other words in the dictionary. However, "apple" is lexicographically smaller than "apply".
Note:

All the strings in the input will only contain lowercase letters.
The length of words will be in the range [1, 1000].
The length of words[i] will be in the range [1, 30].

*/

/*
Thoughts:
1. Put arrays in to ArrayList, and Collections.sort(..) by the length
2. Starting from last element && cut off last index && check for exisitance 
3. Save result to ArrayList, and maintain the length of succeeded word as breaking point.
4. sort the result by it's lexicographically.
*/
class Solution {
    public String longestWord(String[] words) {
        if (words == null || words.length == 0) {
            return null;
        }
        final ArrayList<String> wordList = new ArrayList<>(Arrays.asList(words));
        Collections.sort(wordList, new Comparator<String>(){
            public int compare(String a, String b) {
                return a.length() - b.length();
            }
        });

        final ArrayList<String> result = matchWords(wordList);
        Collections.sort(result);
        
        if (result.size() != 0) {
            return result.get(0);
        }
        return null;
    }
    
    private ArrayList<String> matchWords(final ArrayList<String> wordList) {
        int maxWordLength = Integer.MIN_VALUE;
        final ArrayList<String> result = new ArrayList<>();
        for (int i = wordList.size() - 1; i >= 0; i--) {
            String word = wordList.get(i);
            // Validate if word can be built by with wordList
            while(word.length() != 0) {
                if (wordList.contains(word)) {
                    if (word.length() == 1) {
                        if (wordList.get(i).length() < maxWordLength) {
                            return result;
                        } else {
                            result.add(wordList.get(i));
                            maxWordLength = wordList.get(i).length();
                        }
                    }
                    word = word.substring(0, word.length() - 1);
                } else {
                    break;
                }
            }
        }
        return result;
    }
}

/*
Thoughts:
1. Sort lexicographically
2. Any new word.substring(0, length-1) should appear before itself in the sorted list
3. save the ongoing matched string in set such that it can be used to do set.contains()
4. maintain a longest result. Go through entire words array.

Note: bacause it's lexicographically sorted, the very first matched word will be the 
exact one we want in this length range. The following step becomes: only look for matched
ones in larger length.
*/
class Solution {
    public String longestWord(String[] words) {
        if (words == null || words.length == 0) {
            return null;
        }
        String result = "";
        Arrays.sort(words);
        
        final Set<String> set = new HashSet<>();
        
        for (String word : words) {
            if (word.length() == 1 || set.contains(word.substring(0, word.length() - 1))) {
                result = word.length() > result.length() ? word : result;
                set.add(word);
            }
        }
        
        return result;
    }
}


```