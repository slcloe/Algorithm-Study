import java.util.*;
class Solution {
    /*
    접근 방식 : 주어지는 사람들의 배열은 최대 10_000, seller는 100_000이기 때문에 시간을 줄이는 제한 조건이 있을 것이라고 생각.
               Node들의 연관관계를 설정해줘야하기 때문에 Node 클래스와 HashMap 이용
    주안점
    1. 부모가 "-"이거나 부모에게 줄 amount 값이 0이면 그대로 탈출.
    2. map에 객체(Node class)를 넣은 후 꺼내줄 때는 명시적 형변환을 해주어야 함
    */
    
    class Node {
        int amount;
        String name;
        String parent;
        public Node(String name, String parent) {
            this.name = name;
            this.parent = parent;
        }
    }
    
    static void divide(Node target, int amount) {
        if(target.name.equals("-")) {
            target.amount += amount;
            return;
        }
        // 위에 사람이 먹을 양
        int parent_amount = amount / 10;
        // 내가 먹을 양
        int my_amount = amount - parent_amount;
        target.amount += my_amount;
        if(parent_amount == 0) return;
        Node parent = (Node) map.get(target.parent);
        divide(parent, parent_amount);
    }
    
    static Map<String, Object> map;
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        map = new HashMap<>();
        map.put("-", new Node("-", "-"));
        int[] answer = new int[enroll.length];
        for(int i=0; i<enroll.length; i++) {
            Node child = new Node(enroll[i], referral[i]);
            map.put(enroll[i], child);
        }
        
        for(int i=0; i<seller.length; i++) {
            Node target = (Node) map.get(seller[i]);
            int target_amount = amount[i] * 100;
            divide(target, target_amount);
        }
        
        for(int i=0; i<enroll.length; i++) {
            Node node = (Node) map.get(enroll[i]);
            answer[i] = node.amount;
        }
        return answer;
    }
}