public class Solution {
    public String foreignDictionary(String[] words) {
         Map<Character, Set<Character>> graph =
                new HashMap<>();

        // Indegree
        Map<Character, Integer> indegree =
                new HashMap<>();


        // STEP 1:
        // Add all unique characters
        for (String word : words) {

            for (char ch : word.toCharArray()) {

                graph.putIfAbsent(ch,
                        new HashSet<>());

                indegree.putIfAbsent(ch, 0);
            }
        }


        // STEP 2:
        // Build graph using adjacent words
        for (int i = 0; i < words.length - 1; i++) {

            String w1 = words[i];
            String w2 = words[i + 1];

            // Invalid case
            // Example: ["abc","ab"]
            if (w1.length() > w2.length() &&
                w1.startsWith(w2)) {

                return "";
            }

            int len =
                    Math.min(w1.length(),
                             w2.length());

            for (int j = 0; j < len; j++) {

                char c1 = w1.charAt(j);
                char c2 = w2.charAt(j);

                // First mismatch
                if (c1 != c2) {

                    // Avoid duplicate edges
                    if (!graph.get(c1).contains(c2)) {

                        graph.get(c1).add(c2);

                        indegree.put(
                                c2,
                                indegree.get(c2) + 1
                        );
                    }

                    break;
                }
            }
        }


        // STEP 3:
        // Topological Sort
        Queue<Character> q =
                new LinkedList<>();

        // Push indegree 0 chars
        for (char ch : indegree.keySet()) {

            if (indegree.get(ch) == 0) {

                q.offer(ch);
            }
        }


        StringBuilder ans =
                new StringBuilder();

        while (!q.isEmpty()) {

            char ch = q.poll();

            ans.append(ch);

            for (char neighbor :
                    graph.get(ch)) {

                indegree.put(
                        neighbor,
                        indegree.get(neighbor) - 1
                );

                if (indegree.get(neighbor) == 0) {

                    q.offer(neighbor);
                }
            }
        }


        // STEP 4:
        // Cycle detection
        if (ans.length() != indegree.size()) {

            return "";
        }

        return ans.toString();
    }
}