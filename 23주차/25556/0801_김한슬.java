import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/*
[문제풀이]

1. 4개의 스택을 생성한다.
2. 배열을 순회하면서 각 스택에 넣을 수 있는지 판단한다.
    a. 스택에 넣기 위해서는 스택의 첫번째 요소보다 클 때만 가능하다.
    만약 작은 원소가 들어갈 경우 순열을 "청소할 때" 내림차순으로 나오게 된다.

 */

public class Main {

    static int N;
    static int[] arr;
    static ArrayDeque<Integer>[] s = new ArrayDeque[4];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        for (int i = 0; i < 4; i++) {
            s[i] = new ArrayDeque<>();
        }

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        boolean result = true;
        for(int n : arr){
            boolean v = false;
            for (int i = 0; i < 4; i++) {
                if (s[i].isEmpty() || s[i].peek() < n) {
                    v = true;
                    s[i].offerFirst(n);
                    break;
                }
            }
            if (!v) {
                result =  false;
                break;
            }
        }
        if (result)
            System.out.print("YES");
        else
            System.out.print("NO");

    }

}


