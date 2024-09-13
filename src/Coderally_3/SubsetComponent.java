package Coderally_3;
import java.util.*;
    public class SubsetComponent {

        // Function to find the number of connected components in a graph using DFS
        private static int countConnectedComponents(int n, boolean[][] graph) {
            boolean[] visited = new boolean[n];
            int count = 0;

            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    dfs(graph, visited, i);
                    count++;
                }
            }
            return count;
        }

        private static void dfs(boolean[][] graph, boolean[] visited, int node) {
            visited[node] = true;
            for (int i = 0; i < graph.length; i++) {
                if (graph[node][i] && !visited[i]) {
                    dfs(graph, visited, i);
                }
            }
        }

        // Function to find the sum of the number of connected components for all subsets
        public static int findConnectedComponents(int[] d) {
            int maxBits = 64;
            int n = d.length;
            boolean[][] bitGraph = new boolean[maxBits][maxBits];

            // Build the bit graph
            for (int number : d) {
                for (int i = 0; i < maxBits; i++) {
                    if ((number & (1L << i)) != 0) {
                        for (int j = i + 1; j < maxBits; j++) {
                            if ((number & (1L << j)) != 0) {
                                bitGraph[i][j] = true;
                                bitGraph[j][i] = true;
                            }
                        }
                    }
                }
            }

            int subsetCount = 1 << n;
            int totalConnectedComponents = 0;

            // Check all subsets
            for (int mask = 0; mask < subsetCount; mask++) {
                boolean[][] subGraph = new boolean[maxBits][maxBits];
                Set<Integer> bitsInSubset = new HashSet<>();

                for (int i = 0; i < n; i++) {
                    if ((mask & (1 << i)) != 0) {
                        for (int j = 0; j < maxBits; j++) {
                            if ((d[i] & (1L << j)) != 0) {
                                bitsInSubset.add(j);
                            }
                        }
                    }
                }

                for (int u : bitsInSubset) {
                    for (int v : bitsInSubset) {
                        if (u != v && bitGraph[u][v]) {
                            subGraph[u][v] = true;
                            subGraph[v][u] = true;
                        }
                    }
                }

                totalConnectedComponents += countConnectedComponents(maxBits, subGraph);
            }

            return totalConnectedComponents;
        }

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            int[] d = new int[n];
            for (int i = 0; i < n; i++) {
                d[i] = sc.nextInt();
            }
            sc.close();
            System.out.println(findConnectedComponents(d));
        }
    }

