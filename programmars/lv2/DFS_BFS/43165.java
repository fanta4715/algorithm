class Solution {
    int answer=0;
    
    public int solution(int[] numbers, int target) {
        dfs(numbers,target,0,0);
        
        return answer;
    }
    
    //dfs로 - or + 를 사용해서 판단
    public void dfs(int[] numbers, int target, int depth, int result){
        //depth와 numbers.length 이 같으면 
        // result와 target이 같은 지 확인후 answer++
        if (depth==numbers.length) {
            if (result==target) answer++;
            return ;
         }
        
        
        // 다르면
        // result에 현재 depth의 값을 + - 버전으로 두 개 dfs 돌리기
        dfs(numbers,target,depth+1,result+numbers[depth]);
        dfs(numbers,target,depth+1,result-numbers[depth]);
    }
}