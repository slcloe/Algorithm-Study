import java.util.*;
public class 0305_곽희웅 {

    /*
    접근 방식 : 문자열과 문자열의 비교이기 때문에 replace나 substring으로 비교하면 1,000,000 길이의 문자열에서는
               시간초과가 무조건 발생하기 때문에 슬라이딩 윈도우 방식으로 비교하며 진행
    자료 구조 : Deque을 사용하지 않고 Stack을 사용한 이유는 Deque은 get 메서드가 없어서 직접 꺼냈다 비교하고 담아야 하기 때문

    주안점
    1. stack을 사용하면 pop으로 꺼내야하기 때문에 reverse를 해줘야 함
    2. 슬라이딩 윈도우 방식을 사용할 때 범위 지정에 주의, 가장 끝의 문자들만 비교해주면 됨
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 비교 대상 문자열
        String S = sc.next();
        // 비교한 기준 문자 배열
        char[] target = sc.next().toCharArray();

        Stack<Character> store = new Stack<>();
        for(int i=0; i<S.length(); i++) {
            store.add(S.charAt(i));
            if(store.size() >= target.length) {
                int idx = 0;
                for(int j=store.size() - target.length; j<store.size(); j++) {
                    if(target[idx] != store.get(j)) break;
                    idx++;
                }
                if(idx == target.length) for(int k=0; k<target.length; k++) store.pop();
            }
        }
        if (store.isEmpty()) System.out.println("FRULA");
        else {
            StringBuilder sb = new StringBuilder();
            while(!store.isEmpty()) sb.append(store.pop());
            System.out.println(sb.reverse().toString());
        }
    }
}
