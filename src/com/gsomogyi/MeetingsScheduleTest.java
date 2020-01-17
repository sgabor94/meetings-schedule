package com.gsomogyi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MeetingsScheduleTest {

    public static void main(String[] args) {
        String meetings1 = "Sun 10:00-20:00\n" +
                "Fri 05:00-10:00\n" +
                "Fri 16:30-23:50\n" +
                "Sat 10:00-24:00\n" +
                "Sun 01:00-04:00\n" +
                "Sat 02:00-06:00\n" +
                "Tue 03:30-18:15\n" +
                "Tue 19:00-20:00\n" +
                "Wed 04:25-15:14\n" +
                "Wed 15:14-22:40\n" +
                "Thu 00:00-23:59\n" +
                "Mon 05:00-13:00\n" +
                "Mon 15:00-21:00";
        String meetings2 = "Mon 01:00-23:00\n" +
                "Tue 01:00-23:00\n" +
                "Wed 01:00-23:00\n" +
                "Thu 01:00-23:00\n" +
                "Fri 01:00-23:00\n" +
                "Sat 01:00-23:00\n" +
                "Sun 01:00-21:00";

        Schedule solution = new Schedule();
        System.out.println(new Date());
        System.out.println(solution.solution(meetings1));
        System.out.println(new Date());
        System.out.println(solution.solution(meetings2));
        System.out.println(new Date());

    }

}

class Schedule {

    public int solution(String S) {
        int result = 0;
        List<String> listStr = new ArrayList<>(Arrays.asList(S.split("\n")));
        List<Integer> startMinutesList = new ArrayList<>();
        List<Integer> endMinutesList = new ArrayList<>();
        for (String line : listStr) {
            String day = line.substring(0, 3);
            String startTime = line.substring(4, 9);
            String endTime = line.substring(10, 15);
            startMinutesList.add(getMinutesOfTheWeek(day, startTime));
            endMinutesList.add(getMinutesOfTheWeek(day, endTime));
        }
        startMinutesList.sort(Integer::compareTo);
        endMinutesList.sort(Integer::compareTo);
        for (int i = 0; i < startMinutesList.size() - 1; i++) {
            int restTime = startMinutesList.get(i + 1) - endMinutesList.get(i);
            if (restTime > result) {
                result = restTime;
            }
        }
        int weekInMinutes = 10_080;
        int untilEndOfWeek = weekInMinutes - endMinutesList.get(endMinutesList.size() - 1);
        if (untilEndOfWeek > result) {
            result = untilEndOfWeek + startMinutesList.get(0);
        }
        return result;
    }

    private int getMinutesOfTheWeek(String day, String time) {
        String[] hourAndMinutes = time.split(":");
        int result = Integer.parseInt(hourAndMinutes[0]) * 60 + Integer.parseInt(hourAndMinutes[1]);
        int dayInMinutes = 1440;
        switch (day) {
            case "Mon":
                break;
            case "Tue":
                result += dayInMinutes;
                break;
            case "Wed":
                result += 2 * dayInMinutes;
                break;
            case "Thu":
                result += 3 * dayInMinutes;
                break;
            case "Fri":
                result += 4 * dayInMinutes;
                break;
            case "Sat":
                result += 5 * dayInMinutes;
                break;
            case "Sun":
                result += 6 * dayInMinutes;
                break;
        }
        return result;
    }
}
