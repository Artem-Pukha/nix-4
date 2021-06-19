package com.spnsolo.io;

import org.apache.log4j.Logger;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class OutputFileString implements Runnable{
    private final Logger logger = Logger.getLogger(InputConsoleString.class);

    private static final String OUTPUT = "output/output.txt";
    private final InputConsoleString output;

    public OutputFileString(InputConsoleString output){this.output = output;}

    @Override
    public void run() {
        Thread console = new Thread(output);
        console.start();

        while (console.isAlive()){
            try {
                Thread.sleep(1000);
                if(output.isChanged()){
                    write();
                    output.setChanged(false);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void write(){
        Path path = Paths.get(OUTPUT);
        try {
            Files.write(path, output.get());
        } catch (IOException e) {
            logger.error(e);
        }
    }
}
