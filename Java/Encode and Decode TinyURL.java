M
1516430518
tags: Hash Table, Math

其实想到了切入点, 是个可难可简单的题目. 这里的encode就是想办法把url存起来, 然后给个 key.
那么具体怎么做这个key, 简单就可以用一个map, 然后counting. 复杂一点就可以做random letter/number组成key.

```
/*
TinyURL is a URL shortening service where you enter a URL such as 
https://leetcode.com/problems/design-tinyurl and it returns a short URL such as http://tinyurl.com/4e9iAk.

Design the encode and decode methods for the TinyURL service. 
There is no restriction on how your encode/decode algorithm should work. 
You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.
*/

/*
Use a hashmap to store. Come up with a method to store keys.
Most easies way, use size of the map as keys
*/
public class Codec {
    final String PREFIX = "http://tinyurl.com/";
    final Map<String, String> map = new HashMap<>(); 
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        final String key = PREFIX + (map.size() + 1);
        map.put(key, longUrl);
        return key;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return map.get(shortUrl);
    }
}

// Use hashcode, and store integer key
public class Codec {
    final String PREFIX = "http://tinyurl.com/";
    final Map<Integer, String> map = new HashMap<>(); 
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        map.put(longUrl.hashCode(), longUrl);
        return PREFIX + longUrl.hashCode();
    }

    // Decodes a shortened URL to its original URL.     
    public String decode(String shortUrl) {
        return map.get(Integer.parseInt(shortUrl.replace(PREFIX, "")));
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));
```