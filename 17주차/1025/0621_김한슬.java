import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/*
먼저 시작 점을 이중 포문으로 선택
이후 공차를 설정함
공차대로 제곱근 수를 생성
제곱근인지 체크 => 맞자면 result에 저장

*/
public class Main {

    static int[][] map;

    static int N, M;
    static int result = -1;
    
        public static void solution(int x, int y){
        for(int i = -N; i < N; i++){
            for(int j = -M; j < M; j++){
                if(i == 0 && j == 0) continue;
                int sqrt = 0;

                while (0 <= x && x < N && 0 <= y && y < M){
                    sqrt *= 10;
                    sqrt += map[x][y];

                    int root = (int)Math.sqrt(sqrt);
                    if (Math.pow(root, 2) == sqrt)
                        result = Math.max(result, sqrt);
                    x += i;
                    y += j;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i=0; i < N; i++){
            String str = br.readLine();
            for(int j=0; j<M; ++j){
                map[i][j] = str.charAt(j) - '0';
            }
        }

        for(int i=0; i < N; i++){ 
            for(int j=0; j < M; j++){
                solution(i, j);
            }
        }

        System.out.println(result);

    }

}
