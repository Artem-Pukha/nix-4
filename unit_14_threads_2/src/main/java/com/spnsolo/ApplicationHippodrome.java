package com.spnsolo;

import java.util.*;

public class ApplicationHippodrome {
    public static void main(String[] args) {
        List<Horse> horses = new ArrayList<>();
        horses.add(new Horse("First"));
        horses.add(new Horse("Second"));
        horses.add(new Horse("Third"));
        horses.add(new Horse("Fourth"));
        horses.add(new Horse("Fifth"));
        horses.add(new Horse("Sixth"));

        Race race = new Race(1000);
        horses.forEach(h -> {h.addToRace(race);});

        int i = 0;
        for(Horse h:horses){
            System.out.println("("+i+") "+ "Horse " + h.name());
            i++;
        }

        System.out.print("Select your horse (enter number in \"()\"):");
        int horseNumber;
        Scanner scanner = new Scanner(System.in);
        while ((horseNumber = scanner.nextInt()) > horses.size() || horseNumber < 0) {
            System.out.println("No horse with such number is present. Choose once again!");
            System.out.print("Select your horse (enter number in \"()\"):");
        }

        Horse chosen = horses.get(horseNumber);
        race.startRace();

        int place = race.getHorsePlace(chosen);
        System.out.println("Your horse got place " + place);
    }
}
