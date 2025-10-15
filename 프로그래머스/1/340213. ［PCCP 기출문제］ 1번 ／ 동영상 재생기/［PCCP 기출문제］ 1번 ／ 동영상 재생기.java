class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = "";
        String[] videoLenStr = video_len.split(":");
        int[] videoLen = new int[2]; 
        videoLen[0] = convert(videoLenStr[0]);
        videoLen[1] = convert(videoLenStr[1]);
        String[] posStr = pos.split(":");
        int[] curPos = new int[2];
        curPos[0] = convert(posStr[0]);
        curPos[1] = convert(posStr[1]);
        String[] opStartStr = op_start.split(":");
        int[] opStart = new int[2];
        opStart[0] = convert(opStartStr[0]);
        opStart[1] = convert(opStartStr[1]);
        String[] opEndStr = op_end.split(":");
        int[] opEnd = new int[2];
        opEnd[0] = convert(opEndStr[0]);
        opEnd[1] = convert(opEndStr[1]);
        
        curPos = skipOp(curPos, opStart, opEnd);
        for(int i = 0; i < commands.length; i++) {    
            if(commands[i].equals("prev")) {
                curPos = prev(curPos);
            } else {
                curPos = next(curPos, videoLen);
            }
            curPos = skipOp(curPos, opStart, opEnd);
        }
        
        return convert(curPos[0]) + ":" + convert(curPos[1]);
    }
    
    private int[] prev(int[] pos) {
        int[] newPos = new int[2];
        int[] start = new int[2];
        start[0] = 0;
        start[1] = 0;
        newPos[0] = pos[0];
        newPos[1] = pos[1] - 10;
        if(newPos[1] < 0) {
            newPos[0] -= 1;
            newPos[1] += 60;
        }
        
        if(compare(newPos, start) == -1) {
            newPos[0] = start[0];
            newPos[1] = start[1];
        }
        return newPos;
    }
    
    private int[] next(int[] pos, int[] videoLen) {
        int[] newPos = new int[2];
        int[] end = new int[2];
        end[0] = videoLen[0];
        end[1] = videoLen[1];
        newPos[0] = pos[0];
        newPos[1] = pos[1] + 10;        
        if(newPos[1] >= 60) {
            newPos[0]++;
            newPos[1] -= 60;
        }

        if(compare(newPos, end) == 1) {
            newPos[0] = end[0];
            newPos[1] = end[1];
        }
        return newPos;
    }
    
    private int[] skipOp(int[] pos, int[] opStart, int[] opEnd) {
        int[] newPos = new int[2];
        newPos[0] = pos[0];
        newPos[1] = pos[1];
        if(compare(pos, opStart) != -1 && compare(pos, opEnd) !=  1) {
            newPos[0] = opEnd[0];
            newPos[1] = opEnd[1];
        }
        
        return newPos;
    }
    
    private int compare(int[] time1, int[] time2) {
        if(time1[0] < time2[0] || (time1[0] == time2[0] && time1[1] < time2[1])) {
            return -1;
        } else if(time1[0] > time2[0] || (time1[0] == time2[0] && time1[1] > time2[1])) {
            return 1;
        }
        
        return 0;
    }
    
    private String convert(int num) {
        if(num < 10) {
            return "0" + num;
        } else {
            return String.valueOf(num);
        }
    }
    
    private int convert(String str) {
        return Integer.valueOf(str);
    }
}