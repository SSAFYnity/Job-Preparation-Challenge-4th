import java.util.*;

public class 캐시_김수빈 {
    class Node {
        String key;
        Node prev, next;

        public Node(String key) {
            this.key = key;
        }
    }

    public class LRUCache {
        private HashMap<String, Node> map;
        private Node head, tail;
        private int capacity;
        private int time;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.map = new HashMap<>();
            time = 0;
            this.head = null;
            this.tail = null;
        }
        // 노드 삭제
        private void remove(Node node) {
            if (node.prev != null) {
                node.prev.next = node.next;
            } else {
                head = node.next;
            }
            if (node.next != null) {
                node.next.prev = node.prev;
            } else {
                tail = node.prev;
            }
        }

        private void moveToHead(Node node) {
            if (head == null) {
                head = tail = node;
                node.prev = node.next = null;
            } else {
                node.next = head;
                node.prev = null;
                head.prev = node;
                head = node;
            }
        }

        public void put(String key) {
            Node node;
            if (map.containsKey(key)) {
                node = map.get(key);
                remove(node);
                time += 1;
            } else {
                if (map.size() == capacity) {
                    if (tail != null) {
                        map.remove(tail.key);
                        remove(tail);
                    }
                }
                node = new Node(key);
                time += 5;
            }
            // 만약 용량이 0이 아니면 집어넣기
            if (capacity != 0) {
                moveToHead(node);
                map.put(key, node);
            }
        }

        public int getTime() {
            return time;
        }
    }

    class Solution {
        // DB 캐시를 적용할 때 캐시 크기에 따른 실행시간 측정 프로그램
        // 캐시히트일 때 1, 캐시미스일 때 5
        // LRU 가장 최근에 쓰이지 않은 것부터 교체
        public int solution(int cacheSize, String[] cities) {
            LRUCache cache = new LRUCache(cacheSize);

            for (String city : cities) {
                cache.put(city.toLowerCase());  // 대소문자 통일
            }

            return cache.getTime();
        }
    }

    public static void main(String[] args) {
        캐시_김수빈 outer = new 캐시_김수빈();
        Solution inner = outer.new Solution();

        int cacheSize = 0;
        String[] cities = {"Jeju", "Jeju"};
        int result = inner.solution(cacheSize, cities);
        System.out.println(result);
    }
}
