import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이 과정
 *
 * 1. 맨 아래층부터 빗물의 총량을 계산한다.
 * 2. h층이 주어지면 그 층보다 높은 벽이 있을 경우와 없을 경우로 나뉜다.
 * 3. 빗물이 고이는 경우는 h층 보다 높은 벽들의 사이에 빗물이 고인다.
 * 4. 처음 빗물을 담을 수 있는 벽이 발견될 때 ( arr[j] >= H ): cnt = 0 으로 고정
 * 5. 빗물을 담을 수 있는 빈칸이 존재할 때 ( arr[j] < H ) : cnt ++
 * 6. 이후에 다른 벽이 존재할 때 ( result += cnt , cnt = 0) 
 * 4~6 을 각 층마다 반복한다. 
 */
public class Main {
    static int H, W;
    static int[] arr;
    static int result = 0;
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        arr = new int[W];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < W; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= H; i++) {
            int cnt = -1;
            for (int j = 0; j < W; j++) {
                if (cnt == -1){
                    if (arr[j] >= i){
                        cnt = 0;
                    }
                    continue;
                }
                if (arr[j] < i)
                    cnt++;
                else{
                    result += cnt;
                    cnt = 0;
                }
            }
        }
        System.out.println(result);
    }
}
