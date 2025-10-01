import java.util.Random;

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
    }
}
