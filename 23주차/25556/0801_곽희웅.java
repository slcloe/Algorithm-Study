import java.io.*;
import java.util.*;

public class 0801_곽희웅 {

    /*

        접근방식 : 뽑아서 오른쪽부터 나열할 때 오름차순으로 정리할 수 있으려면 스택에 오름차순으로 정렬되어야 함
                      이 점을 활용해서 만약 들어가지 못하면 실패하는 것
        주안점
        1. sb.isEmpty()는 java11에서 안되기 때문에 컴파일 오류 시 유의할 것

    */

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] num = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<N; i++) num[i] = Integer.parseInt(st.nextToken());

        Stack<Integer>[] stacks = new Stack[4];
        for(int i=0; i<4; i++) stacks[i] = new Stack<>();

        for(int i=0; i<N; i++) {
            boolean flag = false;
            for(int j=0; j<4; j++) {
                if(stacks[j].isEmpty() || stacks[j].peek() < num[i]) {
                    stacks[j].add(num[i]);
                    flag = true;
                    break;
                }
            }
            if(!flag) {
                sb.append("NO");
                break;
            }
        }
        if(sb.length() < 1) sb.append("YES");
        System.out.print(sb.toString());
    }
}
