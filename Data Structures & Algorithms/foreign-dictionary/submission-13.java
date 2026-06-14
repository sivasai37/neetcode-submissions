public class Solution {
    public String foreignDictionary(String[] words) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        Map<Character, Integer> indegree = new HashMap<>();

        for (String word : words) {
            for (char c : word.toCharArray()) {
                indegree.putIfAbsent(c, 0);
                graph.putIfAbsent(c, new HashSet<>());
            }
        }

        for (int i = 0; i < words.length - 1; i++) {
            String w1 = words[i];
            String w2 = words[i + 1];
            if (w1.length() > w2.length() && w1.startsWith(w2)) {
                return "";
            }
            for (int j = 0; j < Math.min(w1.length(), w2.length()); j++) {
                if (w1.charAt(j) != w2.charAt(j)) {
                    if (!graph.get(w1.charAt(j)).contains(w2.charAt(j))) {
                        indegree.put(w2.charAt(j), indegree.get(w2.charAt(j)) + 1);
                        graph.get(w1.charAt(j)).add(w2.charAt(j));
                       
                    }
                     break;
                }
            }
        }

        Queue<Character> q = new LinkedList<>();

        for (Map.Entry<Character, Integer> mp : indegree.entrySet()) {
            if (mp.getValue() == 0) {
                q.add(mp.getKey());
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            char c = q.poll();
            sb.append(c);
            for (char x : graph.get(c)) {
                indegree.put(x, indegree.get(x) - 1);
                if(indegree.get(x)==0){
                    q.add(x);
                }
            }
        }
         if (sb.length() != indegree.size()) {
            return "";
        }

        return sb.toString();
    }
}