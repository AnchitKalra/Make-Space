package com.example.geektrust;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InitService {

    private List<Room> roomList;
    private List<String> bufferList;

    private Map<String, String> bufferMap;



    public InitService() {
        roomList = new ArrayList<>();
        bufferList = new ArrayList<>();
        bufferMap = new HashMap<>();
    }

    public void init() {
        Room room1 = new Room();
        room1.setName("C-Cave");
        room1.setCapacity(3);


        Room room2 = new Room();
        room2.setName("D-Tower");
        room2.setCapacity(7);

        Room room3 = new Room();
        room3.setCapacity(20);
        room3.setName("G-Mansion");

        roomList.add(room1);
        roomList.add(room2);
        roomList.add(room3);
    }

    public void initBuffer() {
        bufferList.add("09:00");
        bufferMap.put(bufferList.get(0), "09:15");

        bufferList.add("13:15");
        bufferMap.put(bufferList.get(1), "13:45");

        bufferList.add("18:45");
        bufferMap.put(bufferList.get(2), "19:00");

    }

    public boolean checkTimeSlot(String s1, String s2) {
        String s[] = s1.split(":");
        int time1 = Integer.parseInt(s[1]);
        s = s2.split(":");
        int time2 = Integer.parseInt(s[1]);
        if(time1 % 15 == 0 && time2 % 15 ==0){
            return true;
        }
        return false;

    }

    public boolean checkBuffer(int sTime, int eTime) {
        for (String s1 : bufferList) {
            int tempSTime = getTime(s1);
            String s = bufferMap.get(s1);
            int tempETime = getTime(s);
            if ((sTime >= tempSTime && sTime < tempETime) || (eTime > tempSTime && eTime <= tempETime)) {
                return true;
            }
        }
        return false;
    }

    public int getTime(String time) {
        String s[] = time.split(":");
        String string = s[0] + s[1];
        int t = Integer.parseInt(string);
        return t;
    }

    public void vacancy(String[] input) {
        String startTime = input[1];
        String endTime = input[2];
        int sTime = getTime(startTime);
        int eTime = getTime(endTime);
        boolean flag1 = false;

        if(checkTime(sTime, eTime)){
            System.out.println("INCORRECT_INPUT");
        }
        else if(checkBuffer(sTime, eTime)){
             System.out.println("NO_VACANT_ROOM");
        } else {
            for (Room room : roomList) {
                if (!room.isBooked()) {
                    flag1 = true;
                    System.out.print(room.getName() + " ");
                } else {
                    boolean flag = false;
                    for (String s1 : room.getBookedList()) {
                        int tempSTime = getTime(s1);
                        String s = room.getBookedMap().get(s1);
                        int tempETime = getTime(s);
                        if ((sTime >= tempSTime && sTime < tempETime) || (eTime > tempSTime && eTime <= tempETime)) {
                            flag = true;
                            break;
                        }

                    }
                    if(!flag) {
                        flag1 = true;
                        System.out.print(room.getName() + " ");
                        flag = false;
                    }
                }
            }
            if(!flag1) {
                System.out.print("NO_VACANT_ROOM");
            }
            System.out.println();
        }
    }

    public boolean checkTime(int startTime, int endTime) {
        if(endTime <= startTime) {
            return true;
        }
        return false;
    }

    public Boolean checkAvailability(Room room, int startTime, int endTime) {
        List<String> bookedList = room.getBookedList();
        Map<String, String> map = room.getBookedMap();
        for (String s : bookedList) {
            int sTime = getTime(s);
            int eTime = getTime(map.get(s));
            if((startTime >= sTime && startTime < eTime) || (endTime > sTime && endTime <= eTime)) {
               // System.out.println(startTime + " " + endTime + " " + sTime + " " + eTime);
                return false;
            }
        }
        return true;
    }

    public void book(String []input) {
        String startTime = input[1];
        int sTime = getTime(startTime);
        String endTime = input[2];
        int eTime = getTime(endTime);

        if(!checkTimeSlot(startTime, endTime)) {
            System.out.println("INCORRECT_INPUT");
        }
        else if(checkTime(sTime, eTime)) {
            System.out.println("INCORRECT_INPUT");
        } else {
            if(!checkBuffer(sTime, eTime)) {
                int noOfPeople = Integer.parseInt(input[3]);
                Room r1 = new Room();
                for (Room room : roomList) {
                    if(room.getCapacity() >= noOfPeople) {
                        if(checkAvailability(room, sTime, eTime)) {
                            r1 = room;
                            break;
                        }
                    }
                }
                if(r1.getCapacity() != 0) {
                    List<String> list = r1.getBookedList();
                    list.add(startTime);
                    Map<String, String> map = r1.getBookedMap();
                    map.put(startTime, endTime);
                    r1.setBooked(true);
                    r1.setBookedList(list);
                    r1.setBookedMap(map);
                    System.out.println(r1.getName());
                }
                else {
                    System.out.println("NO_VACANT_ROOM");
                }

            } else {
                System.out.println("NO_VACANT_ROOM");
            }

        }
    }
}




