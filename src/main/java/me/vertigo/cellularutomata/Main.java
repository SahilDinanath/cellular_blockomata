package me.vertigo.cellularutomata;

import com.google.common.collect.Range;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

public  class Main {
    //rules
    private Vector<Range<Integer>> survivalInterval = new Vector<>(){{
        add(Range.closed(5,6));
    }};
    private Vector<Range<Integer>> spawnInterval = new Vector<>(){{
        add(Range.closed(1,3));
    }};
    private int states = 7;

    //moore = true, von neumann = false
    private boolean neighbouringMethod = false;
    //colony
    private Colony colonyOfCells = new Colony(survivalInterval, spawnInterval, states,neighbouringMethod);
    public void runCellularAutomata() {
            colonyOfCells.iterateColony();
    }
}
