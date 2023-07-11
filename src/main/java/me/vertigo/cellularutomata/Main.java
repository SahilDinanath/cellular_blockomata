package me.vertigo.cellularutomata;

import com.google.common.collect.Range;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

public  class Main {
    //rules
    private Vector<Range<Integer>> survivalInterval = new Vector<>(){{
        add(Range.closed(4,6));
    }};
    private Vector<Range<Integer>> spawnInterval = new Vector<>(){{
        add(Range.singleton(3));
    }};
    private int states = 2;

    //moore = true, von neumann = false
    private boolean neighbouringMethod = true;
    //colony
    private Colony colonyOfCells = new Colony(survivalInterval, spawnInterval, states,neighbouringMethod);
    public void runCellularAutomata() {
            colonyOfCells.iterateColony();
    }
}
