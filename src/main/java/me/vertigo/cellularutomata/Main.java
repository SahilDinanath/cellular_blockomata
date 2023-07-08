package me.vertigo.cellularutomata;

import com.google.common.collect.Range;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

public  class Main {
    public void calculateNewState(){

    }
    public void runCellularAutomata() {

        //rules
        Vector<Range<Integer>> survivalInterval = new Vector<Range<Integer>>();
        survivalInterval.add(Range.closed(4,4));

        Vector<Range<Integer>> spawnInterval = new Vector<Range<Integer>>();
        spawnInterval.add(Range.closed(4,4));

        int states = 5;
        Colony colonyOfCells = new Colony(survivalInterval, spawnInterval, states);


        for(int i = 0; i < 1000; i++){
            colonyOfCells.displayColonyInWorld();
            colonyOfCells.iterateColony();
        }
        // Example usage: place a stone block at coordinates (10, 64, 10)
    }
}
