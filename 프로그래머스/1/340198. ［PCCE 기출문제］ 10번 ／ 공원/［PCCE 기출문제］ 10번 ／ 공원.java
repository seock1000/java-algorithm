import java.util.Arrays;
import java.util.Comparator;
import java.lang.Math;

class Solution {
    public int solution(int[] mats, String[][] park) {
        int maxParkLength = calculateMaxEmptyLength(park);
        Arrays.sort(mats);
        return findFitMat(mats, maxParkLength);
    }
    
    private int calculateMaxEmptyLength(String[][] park) {
        if(isFull(park)) {
            return 0;
        }
        
        int windowSize = Math.min(park.length, park[0].length);
        
        while(windowSize > 1) {
            for(int i = 0; i <= park.length - windowSize; i++) {
                for(int j = 0; j <= park[0].length - windowSize; j++) {
                    if(isEmptySpace(park, i, j, windowSize)) {
                        return windowSize;
                    }
                }
            }
            windowSize--;
        }
        
        return windowSize;
    }
    
    private boolean isEmptySpace(String[][] park, int x, int y, int windowSize) {
        for(int i = x; i < x + windowSize; i++) {
            for(int j = y; j < y + windowSize; j++) {
                if(!park[i][j].equals("-1")) {
                    return false;
                } 
            }
        }
        
        return true;
    }
    
    private boolean isFull(String[][] park) {
        for(int i = 0; i < park.length; i++) {
            for(int j = 0; j < park[0].length; j++) {
                if(park[i][j].equals("-1")) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private int findFitMat(int[] matsInOrder, int maxEmptyLength) {
        for(int i = matsInOrder.length - 1; i >= 0; i--) {
            if(matsInOrder[i] <= maxEmptyLength) {
                return matsInOrder[i];
            }
        }
        
        return -1;
    }
}