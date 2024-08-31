import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    1 걸음일 때의 최적해 -> 1 (1)
    2 걸음일 때의 최적해 -> 1 1 (2)
    3 걸음일 때의 최적해 -> 1 2 1 (4)
    4 걸음일 때의 최적해 -> 1 2 2 1 (6)
    5 걸음일 때의 최적해 -> 1 2 3 2 1 (9)
    6 걸음일 때의 최적해 -> 1 2 3 3 2 1 (12)
    ...
    N 걸음일 때의 최적해 -> ?

    규칙을 찾아보면
    N이 홀수일 경우, 최적해의 값은 (N/2 + 1)(N/2) - (N/2)
    N이 짝수일 경우, 최적해의 값은 (N/2 + 1)(N/2)

    거리 값을 d라고 하면 d는 항상 (floor(sqrt(d)))^2 <= d <= (ceiling(sqrt(d)))^2 을 만족하므로
    두 가지의 기준을 통해서 필요한 걸음 수를 구하면 된다.
 */

public class 멍멍이쓰다듬기_0830_전영빈 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());

        int distance = Y - X;
        int step = (int) Math.sqrt(distance);

        int middle = (step + 1) * step;
        int bottom = middle - step;

        if (distance == bottom) {
            if (distance == 0) {
                System.out.println(0);
            } else {
                System.out.println(step * 2 - 1);
            }
        } else if (distance <= middle) {
            System.out.println(step * 2);
        } else {
            System.out.println(step * 2 + 1);
        }
    }
}
