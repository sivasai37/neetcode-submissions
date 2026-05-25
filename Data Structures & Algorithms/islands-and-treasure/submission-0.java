class Solution {
    public void islandsAndTreasure(int[][] grid) {
        Queue<int[]> q = new LinkedList<>();
        int[] dx = {-1,0,1,0};
        int[] dy = {0,1,0,-1};
        int m = grid.length;
        int n = grid[0].length;
        int[][] dist = new int[m][n];

        boolean[][] vis = new boolean[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==0){
                    q.add(new int[]{i,j,0});
                    vis[i][j] = true;
                    dist[i][j] = 0;
                }else if(grid[i][j]==-1){
                    dist[i][j] = -1;
                }
            }
        }

        while(!q.isEmpty()){
            int[] arr = q.poll();
            int r = arr[0];
            int c = arr[1];
            int dis = arr[2];

            for(int d=0;d<4;d++){
                int nr = dx[d] +r;
                int nc = dy[d] +c;

                if(nr>=0 && nc>=0 && m>nr && n>nc && !vis[nr][nc] && grid[nr][nc]== 2147483647 ){
                    q.add(new int[]{nr,nc,dis+1});
                    vis[nr][nc] = true;
                    dist[nr][nc] = dis + 1;

                }
            }
        }

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                
                grid[i][j] = dist[i][j];

                
            }
        }
        
    }
}
