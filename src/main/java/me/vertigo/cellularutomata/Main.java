package me.vertigo.cellularutomata;

import com.google.common.collect.Range;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

public  class Main {
    public void runCellularAutomata() {

        //rules
        Vector<Range<Integer>> survivalInterval = new Vector<>();
        survivalInterval.add(Range.closed(0,6));

        Vector<Range<Integer>> spawnInterval = new Vector<>();
        spawnInterval.add(Range.singleton(1));

        spawnInterval.add(Range.singleton(3));
        int states = 2;

        Colony colonyOfCells = new Colony(survivalInterval, spawnInterval, states);


        for(int i = 0; i < 500; i++){
            colonyOfCells.iterateColony();
        }
    }
}
