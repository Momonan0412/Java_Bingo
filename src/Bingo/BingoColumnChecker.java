package Bingo;

public class BingoColumnChecker extends BingoChecker{
    int col;

    public BingoColumnChecker(BingoCard card, int col) {
        super(card);
        this.col = col-1;
    }

    @Override
    public void run() {
        // Change this one
        int row=0;
        for (int col = 0; col < 5; col++) {
            int num = card.nums[row][col];
            synchronized (BingoGame.getResultLock()){
                while (!BingoGame.result[num]) {
                    try {
                        BingoGame.getResultLock().wait();
                    } catch (InterruptedException e) {

                    }
                }
            }
        }
        System.out.println("Card " +card.id +" done:" +card);
    }
}