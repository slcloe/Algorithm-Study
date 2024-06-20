import java.util.*;
public class 0619_곽희웅 {

    /*
    접근 방식 : 처음 완전 탐색을 변형한 풀이를 활용하니 시간초과가 발생.
              그래서 dp로 다시 생각해봤는데, 점화식 구현이 어려워 풀이를 보고 solve

    주안점
    1. A일 전까지는 개체가 1로 유지, A <= i < B에는 현재보다 A일 전에 있는 개체들이 번식하기 때문에 전날 + (오늘-A).
    2. B <= i에는 더 이상 번식하지 않는 친구들은 빼줘야 하기 때문에 전날 + (오늘-A) + (오늘 - B)
    3. 마지막에는 오늘-D를 빼주면 됨. 이때 살아있던 친구들은 지금은 다 죽었기 때문
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt();
        int B = sc.nextInt();
        int D = sc.nextInt();
        int N = sc.nextInt();

        long[] life = new long[N+1];
        life[0] = 1;
        for(int i=1; i<=N; i++) {
            if(i < A) life[i] = life[i-1];
            else if(i < B) life[i] = (life[i-1] + life[i - A]) % 1000;
            else life[i] = (life[i-1] + life[i - A] - life[i - B] + 1000) % 1000;
        }
        System.out.println(N-D >= 0 ? (life[N] - life[N-D] + 1000) % 1000 : life[N] % 1000);
    }
}
