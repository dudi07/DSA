public class MaximumPeopleInParty {

    private boolean isPossible( int[] richer ,  int[] poorer, int mid){
        if(mid == 0)
            return true;
        int x = 0 ;
        int n = richer.length;
        for(int i = 0 ;i < n ;i++){
            if(richer[i] >= x && poorer[i] >= mid-x-1){
                x++;
            }
        }
        return x >= mid;
    }
    int maxPeopleInParty( int[] richer ,  int[] poorer ){
        int hi = richer.length;
        int lo = 0;
        while(lo < hi){
            int mid = lo + (hi - lo + 1)/2;

            if(isPossible(richer, poorer, mid)){
                lo = mid;
                System.out.println("mid "+ mid);
            }else{
                hi = mid -1;
            }
        }
        return lo;
    }
}
