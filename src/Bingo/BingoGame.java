package Bingo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BingoGame implements Runnable{
    List<BingoCard> cards;
    static boolean[] result;
    static final Object resultLock = new Object();
    public BingoGame() {
        // Initialize the result array with a size of 76 (1-75)
        result = new boolean[76];
    }
    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        System.out.print("How many players? ");
        int cnt = sc.nextInt();
        cards = new ArrayList<>();
        for (int i = 0; i < cnt; i++) {
            cards.add(new BingoCard(i+1));
        }
        for (BingoCard card : cards) {
            System.out.println("Card " + card.id);
            System.out.println(card);
        }
        // TODO create your checker threads per card
        Thread[] checkerThrds = new Thread[cnt];
        for (int i = 0; i < cnt; i++) {
            checkerThrds[i] = new Thread(new BingoRowChecker(cards.get(i), 3));
        }
        // TODO start all threads
        for (int i = 0; i < cnt; i++) {
            checkerThrds[i].start();
        }
        // TODO RANDOM RESULTS
        // TODO randomly get number from 1-75 while not bingo
        Random random = new Random();
        while (true) { // You need to implement the isBingo() method
            int num = random.nextInt(75) + 1; // Generate a random number from 1 to 75
            System.out.println("Number chosen: " + num);

            // Set the result[num] to true and notify waiting threads
            synchronized (resultLock) {
                result[num] = true;
                resultLock.notifyAll();
            }

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    public static Object getResultLock() {
        return resultLock;
    }
}