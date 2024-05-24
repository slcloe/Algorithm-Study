/*
1. 제일 급한일 (제일 마지막 마감일에 끝나는 일) 부터 처리해야한다.
2. 따라서 마감일에 따라 정렬한다.
3. 제일 급한일부터 처리하면서 마지막으로 일을 끝낸 날짜를 저장하면 최대로 놀 수 있는 날 도출 가능.
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][2];
      
        StringTokenizer st;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, (o1, o2) -> Integer.compare(o2[1], o1[1]}); 

        int lastEndDay = arr[0][1];
        for(int i=0; i<N; ++i){
            if(arr[i][1] <= lastEndDay) // 미리 일을 끝낼 수 있을 때
                lastEndDay = arr[i][1] - arr[i][0];
            else // 바로 새로운 일을 끝낼 수 있을 때 
                lastEndDay -= arr[i][0]; 
        }

        System.out.print(lastEndDay);
    }
}
