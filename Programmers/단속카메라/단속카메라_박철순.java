import java.util.*; 

class Car implements Comparable<Car> {
    int start; 
    int end; 
    
    public Car(int start, int end) {
        this.start = start; 
        this.end = end; 
    }
    
    @Override 
    public int compareTo(Car o) {
        return this.end - o.end;     
    }
}
class Solution {
    public int solution(int[][] routes) {
        int N = routes.length;
        List<Car> cars = new ArrayList<>(); 
        
        for (int i=0;i<N;i++) {
            cars.add(new Car(routes[i][0], routes[i][1]));     
        }
        Collections.sort(cars);
        
        int count = 0; 
        int camera = Integer.MIN_VALUE; 
        for (Car car : cars) {
            if (car.start > camera) {
                camera = car.end; 
                count++; 
            }
        }
        return count;         
    }
    
}
