import java.lang.Math;

class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int bandageTime = bandage[0];
        int healPerHour = bandage[1];
        int additionalHeal = bandage[2];
        int bandageCount = 0;
        
        int attackCursor = 0;
        
        int maxHp = health;
        int curHp = health;
        
        for(int i = 1; i <= attacks[attacks.length - 1][0]; i++) {
                if(attacks[attackCursor][0] == i) {
                    curHp -= attacks[attackCursor][1];
                    attackCursor++;
                    bandageCount = 0;
                } else {
                    curHp += healPerHour;
                    bandageCount++;
                    if(bandageCount == bandageTime) {
                        bandageCount = 0;
                        curHp += additionalHeal;
                    }
                    curHp = Math.min(maxHp, curHp);
                }
            
            if(curHp <= 0) {
                return -1;
            }          
        }
        
        return curHp;
    }
}