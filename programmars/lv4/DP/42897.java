import java.lang.Math;

class Solution {
    public int solution(int[] money) {
        int answer = 0;
        
        
        //제한사항으로 보아, (money.length)^2 이상은 시간초과
        
        //DP는 "무조건" 2중 for문을 정직하게 돌아도 성립하게끔 되어야 함
        // 결국 특정 열 또는 행이 수행되고, 그걸 활용해야 함
        
        // 원형의 가장 큰 문제점 : 마지막을 선택하면 0번 인덱스 선택하면 안 됨 (반대도 마찬가지)
        // 그것을 제외하고는 선형에서 쉽게 표현 가능
        // 따라서 그 부분을 2개의 배열로 나눠서 각각 DP를 돌리면 선형으로 풀이 가능
        // 마지막을 무조건 제외하는 배열 DP
        // 첫 번째를 무조건 제외하는 배열 DP
        // 최대값 구하면 OK (마지막과 첫번째가 동시에 선택되는 경우 없앴다)
        
        // 1st : totalMoney[len-1] 2개 생성 및 초기값 설정
        int len = money.length;
        int [] moneyNoFirst= new int[len];
        int [] moneyNoLast= new int[len];
        moneyNoFirst[0]=0; //앞에 0을 넣는 이유는 2번 인덱스부터 비교를 해야 하기 때문
        moneyNoFirst[1]=money[1]; 
        
        moneyNoLast[0]=0;
        moneyNoLast[1]=money[0];
        
        
        // 2nd : 각각 배열에서 DP진행
        // for : len
        //  money[i]+moneyNoFirst[i-2] or moneyNoFirst[i-1]
        // money의 인덱스를 조심하자
        for (int i=2;i<len;i++){
            // 0 1 2 3 4 index
            // 1 2 3 4 5 money
            // 0 1 2 3 4 NoLast
            // 0 2 3 4 5 NoFirst
            moneyNoFirst[i]=Math.max(moneyNoFirst[i-1],money[i]+moneyNoFirst[i-2]);
            moneyNoLast[i]=Math.max(moneyNoLast[i-1],money[i-1]+moneyNoLast[i-2]);
        }
        
        
        // 3rd : 둘 중 큰 값 return!
        return Math.max(moneyNoFirst[len-1],moneyNoLast[len-1]);

    }
}