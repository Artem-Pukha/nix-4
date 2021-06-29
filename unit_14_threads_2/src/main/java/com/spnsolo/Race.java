package com.spnsolo;

import org.apache.log4j.Logger;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Phaser;
import java.util.concurrent.atomic.AtomicInteger;

public class Race {
    private static final Logger logger = Logger.getLogger(Race.class);

    private final ConcurrentHashMap<Horse,Integer>finishers;
    private final Set<Horse> horses;
    private final int distance;
    private AtomicInteger places;
    private Phaser phaser;

    public Race(int distance){
        this.distance = distance;
        finishers = new ConcurrentHashMap<>();
        this.horses = ConcurrentHashMap.newKeySet();
        places = new AtomicInteger();
        phaser = new Phaser();
    }

    public synchronized void startRace(){
        finishers.clear();
        places.set(1);

        int numberHorses = horses.size();
        phaser.bulkRegister(numberHorses);
        int start = phaser.getPhase();

        for(Horse h:horses){
            new Thread(h).start();
        }
        int finish = phaser.awaitAdvance(start);
        phaser.awaitAdvance(finish);

        System.out.println("Race finished!");
    }
    public int distance(){return distance;}
    
    public Phaser getPhaser(){return phaser;}

    public void addFinisher(Horse horse, Integer place){ finishers.put(horse,place); }

    public void addHorse(Horse horse){horses.add(horse);}

    public void removeHorse(Horse horse){horses.remove(horse);}

    public int getPlaces(){return places.getAndIncrement();}

    public int getHorsePlace(Horse horse){
        int place = finishers.get(horse);
        if (place == 0) {
            throw new IllegalStateException("Horse " + horse.name() + " did not finish the race");
        }
        return place;
    }
}
