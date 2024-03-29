import java.util.*;
class Solution {
    
    /*
    접근 방식 : 조건이 5개, 최대 info 개수가 5만, 쿼리 개수가 10만이기 때문에 단순히
               반복문만 사용한다면 무조건 시간 초과. Map과 이분 탐색을 사용해서 풀이.
               
    주안점
    1. 미리 Map에 등록해놓고, info를 등록할 때 "-" 까지 고려해서 저장
    2. 쿼리를 돌면서 정렬하는 것이 아닌 전에 정렬해줘야 함
    3. 중복값이 있는 이분탐색이기 때문에 lower bound를 찾는 방법으로 구현
    */
    
    public int[] solution(String[] info, String[] query) {
        String[] lang = {"cpp", "java", "python", "-"};
        String[] part = {"backend", "frontend", "-"};
        String[] exp = {"junior", "senior", "-"};
        String[] food = {"chicken", "pizza", "-"};
        String[] orders = new String[108];
        Map<String, Object> map = new HashMap<>();
        int idx = 0;
        for(int i=0; i<4; i++) {
            for(int j=0; j<3; j++) {
                for(int k=0; k<3; k++) {
                    for(int m=0; m<3; m++) {
                        orders[idx++] = lang[i] + part[j] + exp[k] + food[m];
                    }
                }
            }
        }
        
        for(String s : orders) map.put(s, new ArrayList<>());
        
        for(int t=0; t<info.length; t++) {
            String[] infos = info[t].split(" ");
            StringBuilder sb = new StringBuilder();
            
            List<String> new_lang = new ArrayList<>();
            List<String> new_part = new ArrayList<>();
            List<String> new_exp = new ArrayList<>();
            List<String> new_food = new ArrayList<>();
            
            if(infos[0].equals("-")) {
                for(String l : lang) new_lang.add(l);
            } else {
                new_lang.add("-");
                new_lang.add(infos[0]);
            }
            
            if(infos[1].equals("-")) {
                for(String p : part) new_part.add(p);
            } else {
                new_part.add("-");
                new_part.add(infos[1]);
            }
            
            if(infos[2].equals("-")) {
                for(String e : exp) new_exp.add(e);
            } else {
                new_exp.add("-");
                new_exp.add(infos[2]);
            }
            
            if(infos[3].equals("-")) {
                for(String f : food) new_food.add(f);
            } else {
                new_food.add("-");
                new_food.add(infos[3]);
            }

            int now_score = Integer.parseInt(infos[4]);
            for(int i=0; i<new_lang.size(); i++) {
                for(int j=0; j<new_part.size(); j++) {
                    for(int k=0; k<new_exp.size(); k++) {
                        for(int m=0; m<new_food.size(); m++) {
                            ArrayList<Integer> list = (ArrayList<Integer>) map.get(new_lang.get(i) + new_part.get(j) + new_exp.get(k) + new_food.get(m));
                            list.add(now_score);
                        }
                    }
                }
            }
        }
        
        for(String s : orders) {
            ArrayList<Integer> scoreList = (ArrayList<Integer>) map.get(s);
            Collections.sort(scoreList);
        }
        
        int[] result = new int[query.length];
        
        for(int t=0; t<query.length; t++) {
            String[] queries = query[t].split(" and ");
            StringBuilder sb = new StringBuilder();
            for (int i=0; i<3; i++) {
                sb.append(queries[i]);
            }
            String[] last = queries[3].split(" ");
            sb.append(last[0]);
            int now_score = Integer.parseInt(last[1]);
            
            ArrayList<Integer> nowList = (ArrayList<Integer>) map.get(sb.toString());
            int left = 0;
            int right = nowList.size();
            while(right > left) {
                int mid = (left + right) / 2;
                int mid_score = nowList.get(mid);
                if(mid_score >= now_score) {
                    right = mid;
                }
                else if(mid_score < now_score) {
                    left = mid + 1;
                }
            }
            result[t] = nowList.size() - right;
        }
        return result;
    }
}