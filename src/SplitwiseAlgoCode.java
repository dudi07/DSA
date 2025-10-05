    // Helper to print the final settlement log

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SplitwiseAlgoCode {
    public static void main(String[] args) {
        int numPeople = 8;
        int numTxns = 30;
        int[][] transactions = new int[numTxns][4];
        java.util.Random rand = new java.util.Random(42);
        for (int i = 0; i < numTxns; i++) {
            int from = rand.nextInt(numPeople);
            int to = rand.nextInt(numPeople);
            while (to == from) {
                to = rand.nextInt(numPeople);
            }
            int amt = rand.nextInt(100) + 1;
            transactions[i][0] = from;
            transactions[i][1] = to;
            transactions[i][2] = 0;
            transactions[i][3] = amt;
        }
        SplitwiseAlgoCode splitwise = new SplitwiseAlgoCode();
        // Calculate balances for settlement log
        HashMap<Integer, Integer> balanceMap = new HashMap<>();
        for (int[] txn : transactions) {
            int from = txn[0];
            int to = txn[1];
            int amt = txn[3];
            balanceMap.put(from, balanceMap.getOrDefault(from, 0) - amt);
            balanceMap.put(to, balanceMap.getOrDefault(to, 0) + amt);
        }
        System.out.println("--- Final Settlement Log ---");
        printFinalSettlement(balanceMap);
        int minTransfers = splitwise.minTransfer(transactions);
        System.out.println("Minimum number of transactions to settle debts: " + minTransfers);
    }
    
    private static void printFinalSettlement(HashMap<Integer, Integer> balanceMap) {
        List<int[]> debtors = new ArrayList<>();
        List<int[]> creditors = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : balanceMap.entrySet()) {
            int person = entry.getKey();
            int balance = entry.getValue();
            if (balance < 0) debtors.add(new int[]{person, -balance});
            else if (balance > 0) creditors.add(new int[]{person, balance});
        }
        int i = 0, j = 0;
        while (i < debtors.size() && j < creditors.size()) {
            int debtor = debtors.get(i)[0], debt = debtors.get(i)[1];
            int creditor = creditors.get(j)[0], credit = creditors.get(j)[1];
            int settled = Math.min(debt, credit);
            System.out.println("Person " + debtor + " pays Person " + creditor + ": " + settled);
            debtors.get(i)[1] -= settled;
            creditors.get(j)[1] -= settled;
            if (debtors.get(i)[1] == 0) i++;
            if (creditors.get(j)[1] == 0) j++;
        }
    }

    public int minTransfer(int[][]transactions){
        HashMap<Integer, Integer> balanceMap = new HashMap<>();
        for(int[]txn : transactions){
            int from = txn[0];
            int to = txn[1];
            int amt = txn[3];

            balanceMap.put(from, balanceMap.getOrDefault(from, 0) - amt);
            balanceMap.put(to, balanceMap.getOrDefault(to, 0) + amt);
        }
        // Use mutable ArrayList to avoid UnsupportedOperationException
        List<Integer> balances = new java.util.ArrayList<>(balanceMap.values().stream().filter(b -> b != 0).toList());

        System.out.println(balances);

        return dfs(0, balances);
    }

    private int dfs(int start, List<Integer> balances){
        if(balances.size() == 0 || start >= balances.size()){
            return 0;
        }

        if(balances.get(start) == 0){
            return dfs(start + 1, balances);
        }


        int currVal = balances.get(start);
        int minTransfer = Integer.MAX_VALUE;

        for(int i = start+1 ; i < balances.size(); i++){
            int nextVal = balances.get(i);

            if(currVal* nextVal < 0){
                balances.set(i, nextVal + currVal);
                minTransfer = Math.min(minTransfer, 1+ dfs(start+1, balances));
                balances.set(i, nextVal);

                if((currVal + nextVal) == 0){
                    break;
                }
            }
        }

        return minTransfer == Integer.MAX_VALUE ? 0 : minTransfer;
    }
}
