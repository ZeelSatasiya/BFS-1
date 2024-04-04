//Problem2
//207. Course Schedule

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, Integer> dependenciesFrequency = new HashMap();
        Map<Integer, ArrayList<Integer>> edges = new HashMap();

        Queue<Integer> queue = new LinkedList();

        for (int i = 0; i < prerequisites.length; i++) {
            int val = dependenciesFrequency.getOrDefault(prerequisites[i][0], 0);
            dependenciesFrequency.put(prerequisites[i][0], ++val);
            if (!edges.containsKey(prerequisites[i][1])) {
                edges.put(prerequisites[i][1], new ArrayList());
            }
            edges.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }

        for (int i = 0; i < numCourses; i++) {
            if (!dependenciesFrequency.containsKey(i)) {
                queue.add(i);
            }
        }

        int count = 0;
        while (!queue.isEmpty()) {
            count++;
            int current = queue.poll();
            if (!edges.containsKey(current))
                continue;

            for (int i = 0; i < edges.get(current).size(); i++) {
                int dependency = edges.get(current).get(i);
                int indegree = dependenciesFrequency.get(dependency);
                dependenciesFrequency.put(dependency, --indegree);
                if (indegree == 0) {
                    queue.add(dependency);
                }
            }
        }
        return count == numCourses;
    }
}