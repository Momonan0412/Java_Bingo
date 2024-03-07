package Bingo;

import static Bingo.BingoGame.resultLock;

public class BingoRowChecker extends BingoChecker{
    int row;
    public BingoRowChecker(BingoCard card, int row) {
        super(card);
        this.row = row-1;
    }

    @Override
    public void run() {
        for (int col = 0; col < 5; col++) {
            int num = card.nums[row][col];
            synchronized (resultLock) {
                while (!BingoGame.result[num]) {
                    try {
                        resultLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        System.out.println("Card " +card.id +" done:" +card);
    }
}