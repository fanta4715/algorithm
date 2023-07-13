import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;


class Solution {
    public int solution(int N, int number) {
        
        //number를 N으로 표현하는 방법보다
        //N으로 표현 가능한 모든 수를 구해서 비교하면 됨!
        List<Set<Integer>> list = new ArrayList<>();
        
        //0번 인덱스 안 쓸 예정
        for (int i=0;i<9;i++){
            list.add(new HashSet<Integer>());
        }
        
        //1번써서 가능한 경우는 N밖에 없음
        list.get(1).add(N);
        
        //2번 이상 쓰는 경우
        // ex) 4번을 사용해서 만드는 경우
        //      (1,3), (2,2), (3,1)  3가지 경우 가능 
        // 1,3과 3,1은 다른 경우 존재함 (연산을 + - * / 를 하기 때문에 - or / 구분이 됨)
        for (int i=2;i<9;i++){
            Set<Integer> countSet = list.get(i);
            countSet.add(Integer.parseInt(String.valueOf(N).repeat(i)));
            //j와 i-j 끼리 연산해서 나온 모든 결과를 countSet에 넣기
            for (int j=1;j<=i-1;j++){
                
                //j개를 사용한 Set, i-j개를 사용한 Set 가져오기
                Set<Integer> preSet = list.get(j);
                Set<Integer> postSet = list.get(i-j);
                
                // j개 Set x i-j개 Set collaborate
                for (int preNum : preSet){
                    for (int postNum : postSet){
                        countSet.add(preNum+postNum);
                        countSet.add(preNum-postNum);
                        countSet.add(preNum*postNum);
                        //0은 항상 나누기 조심할 것
                        //나누기는 항상 0 조심할 것
                        if (postNum!=0) countSet.add(preNum/postNum);
                    }
                }
            }
        }
        
        int answer = 0;
        for (Set<Integer> s : list){
            if (s.contains(number)) return list.indexOf(s);
        }
        
        return -1;
    }
}