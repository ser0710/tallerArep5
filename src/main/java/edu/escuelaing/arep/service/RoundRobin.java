package edu.escuelaing.arep.service;

import java.util.ArrayList;
import java.util.List;

public class RoundRobin {
    private List<String> serverList = new ArrayList<>();
    private int currentIndex;

    private static RoundRobin instance;

    private RoundRobin() {
        this.serverList.add("http://172.31.27.96:34000");
        this.serverList.add("http://172.31.27.96:34001");
        this.serverList.add("http://172.31.27.96:34002");
    }

    public static RoundRobin getInstance(){
        if(instance == null){
            instance = new RoundRobin();
        }
        return instance;
    }

    public String getNextServer() {
        synchronized(serverList) {
            if (currentIndex >= serverList.size()) {
                currentIndex = 0;
            }

            String server = serverList.get(currentIndex);
            currentIndex++;

            return server;
        }
    }
}
