import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import java.util.Queue;

import java.util.LinkedList;
class Solution {
    static int n=0; //n은 세로, m은 가로
    static int m=0;
    
    boolean outOfBound(int x, int y){
        if (x<0 || y<0 || x>=m || y>=n){
            return true;
        }
        return false;
    }    
    int[] dirX = {-1,0,1,0};
    int[] dirY = {0,1,0,-1};
    public int solution(int[][] maps) {
       
        //not weighted graph -> so, JUST BFS
        // or DP도 가능
        n=maps.length;
        m=maps[0].length;
        // 왼 위 오 아래
      
        //for : 
        //List<Set<>>이 떠오른다
        // Set 2개로 비우고 옮기고하면 되나??
        
        //visited하자 제발 재현아
        int[][] visit = new int[n][m];
        for (int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                visit[i][j]=0;
            }
        }
        bfs(maps,visit);
        if (visit[n-1][m-1]==0) return -1;
        else return visit[n-1][m-1];
    }
    
    public void bfs(int[][] map, int[][] visit){
        int x=0;
        int y=0;
        visit[x][y]=1;
        Queue<int[]>queue = new LinkedList<>();
        queue.add(new int[]{x,y});
        
        while (!queue.isEmpty()){
            int[] cur = queue.remove();
            int cX=cur[0];
            int cY=cur[1];
            
            for (int i=0;i<4;i++){
                int nX =cX+dirX[i];
                int nY =cY+dirY[i];
                if (!outOfBound(nX,nY) 
                   &&visit[nY][nX]==0 
                    &&map[nY][nX]==1)
                {   visit[nY][nX]=visit[cY][cX]+1;
                    queue.add(new int[]{nX,nY});}
            
            }
        }
        
        
    }
}
    
    
