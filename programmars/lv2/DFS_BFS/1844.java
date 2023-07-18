import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

class Solution {
    static int n=0; //n은 세로, m은 가로
    static int m=0;
    boolean outOfBound(int x, int y){
        if (x<0 || y<0 || x>=m || y>=n){
            return true;
        }
        return false;
    }    
    
    public int solution(int[][] maps) {
        int answer = 0;
        //not weighted graph -> so, JUST BFS
        // or DP도 가능
        n=maps.length;
        m=maps[0].length;
        // 왼 위 오 아래
        int[] dirX = {-1,0,1,0};
        int[] dirY = {0,1,0,-1};
        //for : 
        //List<Set<>>이 떠오른다
        // Set 2개로 비우고 옮기고하면 되나??
        
        //visited하자 제발 재현아
        boolean[][] visited = new boolean[n][m];
        for (int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                visited[i][j]=false;
            }
        }
        
        //2개로 비우는 형식으로 가보자
        //무조건 인덱스 기준으로 작성할거임
        Set<mapPoint> preSet =new HashSet<>();
        Set<mapPoint> postSet = new HashSet<>();
        postSet.add(new mapPoint(0,0));
        visited[0][0]=true;
        answer++;
        //비어있으면 false임
        while (!postSet.isEmpty()){
            preSet.addAll(postSet);
            postSet.clear();
            //preSet에서 dirX,dirY의 적절한 조화를 활용해서, 
            //가능한 좌표 postSet에 add
            answer++;
            for (mapPoint mp : preSet){
                int x=mp.x;
                int y=mp.y;
                
                //postSet에 add
                for (int i=0;i<4;i++){
                    if (!outOfBound(x+dirX[i],y+dirY[i])
                       && maps[y+dirY[i]][x+dirX[i]]==1
                       && !visited[y+dirY[i]][x+dirX[i]]) {
                        postSet.add(new mapPoint(x+dirX[i],y+dirY[i]));
                        visited[y+dirY[i]][x+dirX[i]]=true;
                    }
                        
                }
                if (postSet.contains(new mapPoint(m-1,n-1))) return answer;
                
            }
            
            System.out.println(answer);
            if (postSet.contains(new mapPoint(m-1,n-1))) return answer;
        }
        
        
        
        return -1;
    }
    
    // map의 좌표를 담는 클래스 생성
    public class mapPoint{
        int x;
        int y;
        
        //범위 밖 체크
        
        
        //생성자
        mapPoint(int x, int y){
            this.x=x;
            this.y=y;
        }
        
        @Override
        public boolean equals(Object object) {
            mapPoint mp = (mapPoint) object;
 
            // name은 상관없이, id만 같으면 true를 리턴합니다.
            if (mp.x == this.x && mp.y==this.y) {
                return true;
            }
            return false;
        }
        
        @Override
        public int hashCode() {
          return Objects.hash(x, y);
        }
        
    }
    
}