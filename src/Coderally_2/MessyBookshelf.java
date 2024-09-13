package Coderally_2;
import java.util.*;

public class MessyBookshelf {
        public static int minSwapsToElegant(int[] shelf) {
            int n = shelf.length;
            // Create a pair of (element, index)
            List<int[]> sortedShelf = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                sortedShelf.add(new int[] { shelf[i], i });
            }

            // Sort based on the element values
            sortedShelf.sort(Comparator.comparingInt(a -> a[0]));

            // To keep track of visited elements
            boolean[] visited = new boolean[n];
            int swapCount = 0;

            // Find cycles in the unsorted array
            for (int i = 0; i < n; i++) {
                // If already visited or already in correct position, skip
                if (visited[i] || sortedShelf.get(i)[1] == i) {
                    continue;
                }

                // Count the size of the cycle
                int cycleSize = 0;
                int j = i;
                while (!visited[j]) {
                    visited[j] = true;
                    j = sortedShelf.get(j)[1]; // Move to the index of the current element in the sorted array
                    cycleSize++;
                }

                // If there is a cycle of size k, it takes (k-1) swaps to arrange it
                if (cycleSize > 1) {
                    swapCount += (cycleSize - 1);
                }
            }

            return swapCount;
        }

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            int[] shelf = new int[n];
            for (int i = 0; i < n; i++) {
                shelf[i] = sc.nextInt();
            }
            System.out.println(minSwapsToElegant(shelf));
        }
    }

