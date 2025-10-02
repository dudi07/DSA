import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        // 5 people: 0, 1, 2, 3, 4
        int numPeople = 5;
        int numTxns = 30;
        int[][] transactions = new int[numTxns][4];
        Random rand = new Random(42); // fixed seed for reproducibility
        for (int i = 0; i < numTxns; i++) {
            int from = rand.nextInt(numPeople);
            int to = rand.nextInt(numPeople);
            while (to == from) {
                to = rand.nextInt(numPeople);
            }
            int amt = rand.nextInt(100) + 1; // 1 to 100
            transactions[i][0] = from;
            transactions[i][1] = to;
            transactions[i][2] = 0; // unused, for compatibility
            transactions[i][3] = amt;
        }

        /*
        // Calculate and print net balances
        int[] net = new int[numPeople];
        for (int[] txn : transactions) {
            int from = txn[0];
            int to = txn[1];
            int amt = txn[3];
            net[from] -= amt;
            net[to] += amt;
        }
        System.out.println("Net balances:");
        for (int i = 0; i < numPeople; i++) {
            if (net[i] < 0) {
                System.out.println("Person " + i + " owes " + (-net[i]));
            } else if (net[i] > 0) {
                System.out.println("Person " + i + " is owed " + net[i]);
            } else {
                System.out.println("Person " + i + " is settled");
            }
        }

        SplitwiseAlgoCode splitwise = new SplitwiseAlgoCode();
        int minTransfers = splitwise.minTransfer(transactions);
        System.out.println("Minimum number of transactions to settle debts: " + minTransfers);
        */

        // Example for CurrencyExchange arbitrage detection
        // Small test
        List<CurrencyExchange.Edge> edges = new ArrayList<>();
        int nCurrencies = 3;
        CurrencyExchange ce = new CurrencyExchange(edges, nCurrencies);
        ce.addRate(0, 1, 0.9); // USD to EUR
        ce.addRate(1, 2, 0.8); // EUR to GBP
        ce.addRate(2, 0, 1.5); // GBP to USD

        List<Integer> cycle = ce.detectArbitrageCycle();
        if (cycle == null || cycle.isEmpty()) {
            System.out.println("No arbitrage opportunity detected (small test).");
        } else {
            System.out.print("Arbitrage cycle detected (small test): ");
            for (int i = 0; i < cycle.size(); i++) {
                System.out.print(cycle.get(i));
                if (i < cycle.size() - 1) System.out.print(" -> ");
            }
            System.out.println();
        }

        // Large test case: 20 currencies, custom rates
        int bigN = 20;
        List<CurrencyExchange.Edge> bigEdges = new ArrayList<>();
        CurrencyExchange detector = new CurrencyExchange(bigEdges, bigN);
    detector.addRate(0, 1, 0.92);   // USD -> EUR
    detector.addRate(1, 2, 0.86);   // EUR -> GBP
    detector.addRate(2, 3, 180.0);  // GBP -> JPY
    detector.addRate(3, 4, 0.012);  // JPY -> AUD
    detector.addRate(4, 5, 0.91);   // AUD -> CAD
    detector.addRate(5, 6, 0.67);   // CAD -> CHF
    detector.addRate(6, 7, 7.95);   // CHF -> CNY
    detector.addRate(7, 8, 11.2);   // CNY -> INR
    detector.addRate(8, 9, 0.055);  // INR -> BRL
    detector.addRate(9, 10, 17.4);  // BRL -> RUB
    detector.addRate(10, 11, 0.18); // RUB -> ZAR
    detector.addRate(11, 12, 0.075);// ZAR -> SGD
    detector.addRate(12, 13, 5.6);  // SGD -> HKD
    detector.addRate(13, 14, 165.0);// HKD -> KRW
    detector.addRate(14, 15, 0.018);// KRW -> MXN
    detector.addRate(15, 16, 0.54); // MXN -> SEK
    detector.addRate(16, 17, 1.5);  // SEK -> NOK (changed from 0.95)
    detector.addRate(17, 18, 1.5);  // NOK -> DKK (changed from 0.99)
    detector.addRate(18, 19, 2.0);  // DKK -> TRY (changed from 1.45)
    detector.addRate(19, 0, 0.5);   // TRY -> USD (changed from 0.034)

        List<Integer> bigCycle = detector.detectArbitrageCycle();
        if (bigCycle == null || bigCycle.isEmpty()) {
            System.out.println("No arbitrage opportunity detected (big test).");
        } else {
            System.out.print("Arbitrage cycle detected (big test): ");
            for (int i = 0; i < bigCycle.size(); i++) {
                System.out.print(bigCycle.get(i));
                if (i < bigCycle.size() - 1) System.out.print(" -> ");
            }
            System.out.println();
        }
    }
}
