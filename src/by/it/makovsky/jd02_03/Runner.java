package by.it.makovsky.jd02_03;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Runner {
    public static void main(String[] args) {
        Dispatcher dispatcher=new Dispatcher(100);
        System.out.println("Магазин открыт");
        ExecutorService executorServiceForCashier = Executors.newFixedThreadPool(5);
        for (int i = 1; i <= 2; i++) {
            Cashier cashier = new Cashier(i, dispatcher);
            executorServiceForCashier.execute(cashier);
        }
        executorServiceForCashier.shutdown();

        ExecutorService executorServiceForBuyers= Executors.newFixedThreadPool(dispatcher.totalBuyersCount);
        int number = 0;
        for (; ; ) {
            int countBuyer = Rnd.getRandom(2);
            for (int i = 0; i < countBuyer && dispatcher.marketIsOpenedForBuyers(); i++) {
                Buyer buyer = new Buyer(++number, dispatcher);
                buyer.start();
            }
            if (!dispatcher.marketIsOpenedForBuyers())
                break;
            Rnd.mySleep(1000);
        }
        executorServiceForBuyers.shutdown();
        try {
            //noinspection StatementWithEmptyBody
            while (!executorServiceForCashier.awaitTermination(10, TimeUnit.DAYS)){

            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Магазин закрыт");
    }
}
