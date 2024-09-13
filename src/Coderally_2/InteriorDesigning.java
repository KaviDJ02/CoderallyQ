package Coderally_2;
import java.util.Scanner;

public class InteriorDesigning {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            // Read number of rooms and prices for yellow and white rolls
            int R = sc.nextInt(); // Number of rooms
            int Ly = sc.nextInt(); // Price of one yellow roll (10 sheets)
            int Lw = sc.nextInt(); // Price of one white roll (10 sheets)

            int totalYellowSheets = 0;
            int totalWhiteSheets = 0;

            // Loop through each room
            for (int i = 0; i < R; i++) {
                int Yn = sc.nextInt(); // Yellow sheets needed for the room
                int Wn = sc.nextInt(); // White sheets needed for the room
                totalYellowSheets += Yn;
                totalWhiteSheets += Wn;
            }

            // Calculate the number of yellow and white rolls needed
            int yellowRolls = (totalYellowSheets + 9) / 10; // Ceiling of (totalYellowSheets / 10)
            int whiteRolls = (totalWhiteSheets + 9) / 10;   // Ceiling of (totalWhiteSheets / 10)

            // Calculate total cost
            int totalCost = (yellowRolls * Ly) + (whiteRolls * Lw);

            // Output the result
            System.out.println(totalCost);
        }
    }

