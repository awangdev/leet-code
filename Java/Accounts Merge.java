M
1532588768
tags: DFS, Union Find, Hash Table, Hash Table

给一串account in format `[[name, email1, email2, email3], [name2, email,..]]`. 

要求把所有account merge起来 (可能多个record记录了同一个人, by common email)


#### Union Find
- 构建 `Map<email, email parent>`, 然后再反向整合: parent -> list of email
- init with <email, email> for all emails
- 因为不同account可能串email, 那么把所有email union的时候, 不同account 的email也会被串起来
- 最终: 所有的email都被union起来, 指向一个各自union的 parent email
- UnionFind 的 parent map 可以反向输出所有  child under parent.
- 同时要维护一个 <email -> account name> 的map, 最终用来输出.

#### Hash Table solution, passed but very slow
- Definitely need iterate over accounts: merge them by email.
- Account object {name, list of email}
- map<email, account>
- 1. iterate over accounts
- 2. find if 'account' exist;  if does, add emails
- 3. if not, add account to list and to map. map all emails to accounts.
- output -> all accounts, and sort emails
- space O(mn): m row, n = emails
- time O(mn)

```
/*
Given a list accounts, each element accounts[i] is a list of strings, 
where the first element accounts[i][0] is a name, 
and the rest of the elements are emails representing emails of the account.

Now, we would like to merge these accounts. Two accounts definitely belong to the same person 
if there is some email that is common to both accounts. 
Note that even if two accounts have the same name, 
they may belong to different people as people could have the same name. 
A person can have any number of accounts initially, but all of their accounts definitely have the same name.

After merging the accounts, return the accounts in the following format: 
the first element of each account is the name, and the rest of the elements are emails in sorted order. 
The accounts themselves can be returned in any order.

Example 1:
Input: 
accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
Explanation: 
The first and third John's are the same person as they have the common email "johnsmith@mail.com".
The second John and Mary are different people as none of their email addresses are used by other accounts.
We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'], 
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
Note:

The length of accounts will be in the range [1, 1000].
The length of accounts[i] will be in the range [1, 10].
The length of accounts[i][j] will be in the range [1, 30].

*/

// Hash Table
class Solution {
    class Account {
        String name;
        Set<String> emails;
        public Account(String name, Set<String> emails) {
            this.name = name;
            this.emails = emails;
        }
    }
    
    private boolean validate(List<List<String>> accounts) {
        return accounts == null || accounts.size() == 0 || accounts.get(0) == null || accounts.get(0).size() == 0;
    }
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> rst = new ArrayList<>();
        if (validate(accounts)) return rst;
        
        // map, list, populate
        Map<String, Account> map = new HashMap<>();
        Set<Account> mergedAccounts = new HashSet<>();
        for (List<String> row : accounts) {
            Account account = new Account(row.get(0), new HashSet<>());
            Set<Account> existingAccounts = new HashSet<>();
            for (int i = 1; i < row.size(); i++) {
                account.emails.add(row.get(i));
                if (map.containsKey(row.get(i))) {
                    Account existingAccount = map.get(row.get(i));
                    mergedAccounts.remove(existingAccount);
                    account.emails.addAll(existingAccount.emails);
                }
            }
            mergedAccounts.add(account);
            for (String email: account.emails) {
                map.put(email, account);
            }
        }
        
        // output as needed
        for (Account account : mergedAccounts) {
            List<String> row = new ArrayList<>();
            List<String> emails = new ArrayList<>(account.emails);
            Collections.sort(emails);
            row.add(account.name);
            row.addAll(emails);
            rst.add(row);
        }
        
        return rst;
    }
}


/*
Union Find
Approach similar to LintCode(Find the weak connected component in directed graph)
UnionFind: Map<currEmail, parentEmail>
1. Group account into union. ( don't forget to preserve email -> name mapping)
2. Group parent -> children 
3. output
*/
class Solution {
    Map<String, String> accountMap = new HashMap<>();
    Map<String, String> parentMap = new HashMap<>();
    
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> rst = new ArrayList<>();
        if (validate(accounts)) return rst;
        
        buildUnionFind(accounts);
        
        for (List<String> account : accounts) {
            for (int i = 1; i < account.size() - 1; i++) {
                union(account.get(i), account.get(i + 1));  // email across acounts will be auto-merged/linked in unionFind
            }
        }
        
        Map<String, List<String>> result = new HashMap<>();
        for (String email : parentMap.keySet()) {
    		String parent = find(email);
    		result.putIfAbsent(parent, new ArrayList<>());
    		result.get(parent).add(email);
    	}

    	for (List<String> list: result.values()) {
    		Collections.sort(list);
            list.add(0, accountMap.get(list.get(0)));
    		rst.add(list);
    	}
        
        return rst;
    }
    
    private boolean validate(List<List<String>> accounts) {
        return accounts == null || accounts.size() == 0 || accounts.get(0) == null || accounts.get(0).size() == 0;
    }
    
    private void buildUnionFind(List<List<String>> accounts) {
        for (List<String> account : accounts) {
            String name = account.get(0);
            for (int i = 1; i < account.size(); i++) {
                accountMap.put(account.get(i), name); // email -> name mapping
                parentMap.put(account.get(i), account.get(i)); // union parent map
            }
        }
    }
    
    private String find(String email) {
        String parent = parentMap.get(email);
        if (parent.equals(parentMap.get(parent))) {
            return parent;
        }
        return find(parent);
    }
    
    private void union(String a, String b) {
        String parentA = find(a);
        String parentB = find(b);
        if (!parentA.equals(parentB)) {
            parentMap.put(parentA, parentB);
        }
    }
}
```