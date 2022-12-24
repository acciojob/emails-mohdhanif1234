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
        int c=0;
        Meeting temp = calendar.get(0);
        int n = calendar.size();
        // System.out.println(calendar.get(0).getEndTime());
        for(int i=0;i<n-1;i++){
            LocalTime start =calendar.get(0).getStartTime();
            LocalTime end = calendar.get(0).getEndTime();
            for(int j=i+1;j<n;j++){
                if(
                        start.compareTo(calendar.get(j).getStartTime()) >0 && start.compareTo(calendar.get(j).getEndTime()) >0 &&end.compareTo(calendar.get(j).getStartTime()) > 0 && end.compareTo(calendar.get(j).getEndTime()) >0
                                || start.compareTo(calendar.get(j).getStartTime()) <0 && end.compareTo(calendar.get(j).getStartTime()) <0 &&start.compareTo(calendar.get(j).getEndTime()) < 0 && end.compareTo(calendar.get(j).getEndTime()) <0
                ){
                    c++;
                }

            }
        }
        return c;
    }
}
