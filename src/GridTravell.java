public class GridTravell {
    int n;
    int m ;
    int[][]grid;


    public GridTravell(int n, int m){
        this.n = n;
        this.m = m;
        grid = new int[n][m];
    }
    private boolean isSafe(int i , int j){
        return i >=0 && i < n && j >=0 && j < m;
    }

    private int recurrsion(int[][]grid, int i , int j, boolean[][]visited){
        if(i == n-1 && j == m-1){
            return 1;
        }
        
        if(!isSafe(i, j) || visited[i][j] || grid[i][j] == 1){
            return 0;
        }

        return recurrsion(grid, i+1, j, visited) + recurrsion(grid, i , j+1, visited);
    }
    public int countWays(int[][]grid, int i , int j){
        boolean[][]visited = new boolean[n][m];
        for(int r = 0 ; r < n ; r++){
            for(int c = 0 ; c < m ; c++){
                visited[r][c] = false;
            }
        }

        return recurrsion(grid, 0 , 0, visited);
    }
}
