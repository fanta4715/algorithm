import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj2631 {
    public static void main(String[] args) throws IOException {
        // LIS를 바탕으로 하면 될 듯
        //DP + LIS
        //DP가 꼭 2차원일 필요는 없다!

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //N읽기 + 배열 크기 설정하기
        final int N = Integer.parseInt(br.readLine());
        int arr[] = new int[N];

        //N번 읽어서 배열에 넣기
        for (int i=0;i<N;i++){
            arr[i]=Integer.parseInt(br.readLine());
        }
        //N번 반복하면서, LIS배열 생성
        int lis[]=new int[N];
        for (int i=0;i<N;i++){

            lis[i]=1;
            for (int j=0;j<i;j++){
                if (arr[j]<arr[i] && lis[j]+1>lis[i]) lis[i]=lis[j]+1;
            }
        }

        //N-LIS배열 최대값
        Arrays.sort(lis);

        System.out.println(N-lis[N-1]);

    }
}
