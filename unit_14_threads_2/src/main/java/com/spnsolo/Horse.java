package com.spnsolo;

import org.apache.log4j.Logger;

import java.util.Random;

public class Horse implements Runnable {

    private static final Logger logger = Logger.getLogger(Horse.class);

    private final Random random;

    private static final int MIN_MOVE = 100;
    private static final int MAX_MOVE = 200;

    private static final int MIN_SLEEP = 400;
    private static final int MAX_SLEEP = 500;

    private int current_position;
    private final String name;
    private Race race;

    public Horse(String name) {
        this.name = name;
        random = new Random();
    }

    @Override
    public void run() {
        if(race != null){
            race.getPhaser().arriveAndAwaitAdvance();
            logger.info(name + " started!");
            do {
                move();
                toSleep();
            } while (current_position < race.distance());
            logger.info(name + "finished");
            race.addFinisher(this,race.getPlaces());
            race.getPhaser().arriveAndDeregister();
        }
    }

    public String name() { return "Horse" + name; }
    void addToRace(Race race) {
        removeFromRace();
        this.race = race;
        race.addHorse(this);
    }

    void removeFromRace() {
        if (race == null) return;
        race.removeHorse(this);
    }

    private void toSleep() {
        int delay = random(MIN_SLEEP,MAX_SLEEP);
        try{
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            logger.error(e);
        }
    }

    private void move() {
        int move = Math.min(
                random(MIN_MOVE, MAX_MOVE),
                race.distance() - current_position
        );;
        current_position += move;
        System.out.println(name + " ran " + move + " meters, total of " + current_position);
    }

    private int random(int min,int max){return random.nextInt(max - min + 1) + min;}


}
