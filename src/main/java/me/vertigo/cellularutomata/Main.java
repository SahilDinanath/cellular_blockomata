package me.vertigo.cellularutomata;

import com.google.common.collect.Range;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

public  class Main {
    //rules
    private Vector<Range<Integer>> survivalInterval = new Vector<>(){{
        add(Range.closed(0,6));
    }};
    private Vector<Range<Integer>> spawnInterval = new Vector<>(){{
        add(Range.singleton(1));
        add(Range.singleton(3));
    }};
    private int states = 2;
    //colony
    private Colony colonyOfCells = new Colony(survivalInterval, spawnInterval, states);
    public void runCellularAutomata() {
            colonyOfCells.iterateColony();
    }
}
