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
        survivalInterval.add(Range.closed(5,6));

        Vector<Range<Integer>> spawnInterval = new Vector<Range<Integer>>();
        spawnInterval.add(Range.closed(1,3));

        int states = 7;

        Colony colonyOfCells = new Colony(survivalInterval, spawnInterval, states);


        for(int i = 0; i < 100; i++){
            colonyOfCells.iterateColony();
        }
        colonyOfCells.displayColonyInWorld();
        // Example usage: place a stone block at coordinates (10, 64, 10)
    }
}
