import java.util.HashMap;
import java.util.List;

public class SplitwiseAlgoCode {
    


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
