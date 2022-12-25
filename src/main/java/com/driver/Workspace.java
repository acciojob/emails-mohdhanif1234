package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

public class Workspace extends Gmail{

    private ArrayList<Meeting> calendar; // Stores all the meetings

    public Workspace(String emailId) {
        // The inboxCapacity is equal to the maximum value an integer can store.
        super(emailId, Integer.MAX_VALUE);
        this.calendar=new ArrayList<>();
    }

    public void addMeeting(Meeting meeting){
        //add the meeting to calendar
        calendar.add(meeting);
    }

    public int findMaxMeetings(){
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am
        int cnt = 1;
        ArrayList<pair> list = new ArrayList<>();
        for(int i=0; i<calendar.size(); i++){
            Meeting m = calendar.get(i);
            list.add(new pair(m.getStartTime(), m.getEndTime()));
        }
        Collections.sort(list);
        LocalTime temp = list.get(0).end;
        for(int i=1; i<list.size(); i++){
            pair p = list.get(i);
            if(p.start.compareTo(temp) > 0){
                cnt++;
                temp = p.end;
            }
        }
        return cnt;
    }
}

class pair implements Comparable<pair>{
    LocalTime start, end;

    pair(LocalTime start, LocalTime end){
        this.start = start;
        this.end = end;
    }

    public  int compareTo(pair p){
        if(this.end.compareTo(p.end) > 0){
            return 1;
        }
        else{
            return -1;
        }
    }
}
