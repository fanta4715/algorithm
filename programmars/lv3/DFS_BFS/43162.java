import java.util.Stack;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        int[] visit = new int[n];
        for (int i=0;i<n;i++){
            visit[i]=i;
        }
        // connected conponents 찾는 거다
        // scc찾는 거랑 똑같다. 방향이 없으니 오히려 쉽다.
        // DFS로 갈 수 있는 애들 다 잡아놓으면 됨
        
            //visit[i]==i라는 것은 결국 cc를 못 찾은 것임
         
        Stack<Integer> stack = new Stack<>();
        
                //stack이 비어있지 않으면,
                // vertex빼내고
                // computers[vertex][0~n-1] 순회하기
                // ==1 이면 visit[]=i로 만들기
        for (int i=0;i<n;i++){
        if (visit[i]==i) stack.push(i);
        while (!stack.empty()){
            int vertex = stack.pop();
            for (int j=0;j<n;j++){
                if ( j!=vertex &&computers[vertex][j]==1 &&visit[vertex]!=visit[j] ){
                    visit[j]=visit[vertex];
                    stack.push(j);
                }
            }
        }
        }
        for (int i=0;i<n;i++){
            if (visit[i]==i) answer++;
        }
        return answer;
    }
           
}
   