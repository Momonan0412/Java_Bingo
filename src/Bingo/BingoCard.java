package Bingo;

import java.util.Arrays;
import java.util.Random;

public class BingoCard {
    int[][] nums;
    int id;
    Random rand = new Random();

    public BingoCard(int id) {
        this.id = id;
        nums = new int[5][5];

        // Fill in the numbers based on the specified ranges
        randomizeNumbers();

    }

    private void randomizeNumbers() {
        fillColumnWithRandomNumbers(0, 1, 15);
        fillColumnWithRandomNumbers(1, 16, 30);
        fillColumnWithRandomNumbers(2, 31, 45);
        nums[2][2] = 0; // Middle cell
        fillColumnWithRandomNumbers(3, 46, 60);
        fillColumnWithRandomNumbers(4, 61, 75);
    }

    private void fillColumnWithRandomNumbers(int colIndex, int min, int max) {
        for (int row = 0; row < 5; row++) {
            int randomNum = rand.nextInt((max - min) + 1) + min;
            nums[row][colIndex] = randomNum;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                sb.append(nums[row][col]).append("\t");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
