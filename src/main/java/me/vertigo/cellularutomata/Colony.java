package me.vertigo.cellularutomata;

import com.google.common.collect.Range;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.Vector;

public class Colony {
    //settings
    private int xCoordsInWorld = 1000, yCoordsInWorld = 64, zCoordsInWorld = 1000;
    private int xSize = 100, ySize = 100, zSize = 100;

    //variables
    private Cell[][][] colonyOfCells = new Cell[ySize][xSize][zSize];

    //rules
    Vector<Range<Integer>> survivalIntervals;
    Vector<Range<Integer>> spawnIntervals;
    int states;

    public Colony(Vector<Range<Integer>> survivalIntervals, Vector<Range<Integer>> spawnIntervals, int states){
        this.survivalIntervals = survivalIntervals;
        this.spawnIntervals = spawnIntervals;
        this.states = states;
        colonyInit();
    }
    public Cell getCellAtCoords(int x,int y,int z){
        return colonyOfCells[y][x][z];
    }
    private void spawnCellAtCoords(Material color,int x,int y, int z){
        getCellAtCoords(x,y,z).cellSpawn(color,states);
    }
    private void removeCellAtCoords(int x, int y, int z){
        getCellAtCoords(x, y, z).cellKill();
    }

    public void spawnCubeInColony() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                for (int z = 0; z < 3; z++) {
                    spawnCellAtCoords(Material.STONE, x, y, z);
                }
            }
        }
    }
    public void colonyInit(){
        for(int x = 0; x< xSize;x++){
            for(int y = 0; y< ySize;y++){
                for(int z = 0; z< zSize;z++){
                    colonyOfCells[y][x][z] = new Cell(Material.AIR, states);
                }
            }
        }
        spawnCubeInColony();
    }
    private boolean checkCellSurvival(int x, int y, int z){
        for(Range<Integer> survivalRange : survivalIntervals){
            if(survivalRange.contains(getVonNeumannNeighbours(x,y,z))){
                return true;
            }
        }
        return false;
    }
    private boolean checkCellSpawn(int x, int y, int z){
        for(Range<Integer> spawnRange: spawnIntervals){
            if(spawnRange.contains(getVonNeumannNeighbours(x,y,z))){
                return true;
            }
        }
        return false;
    }

    public void iterateColony(){
        for(int x = 0; x< xSize;x++){
            for(int y = 0; y< ySize;y++){
                for(int z = 0; z< zSize;z++){
                    if(getCellAtCoords(x,y,z).getAlive()){
                        if(!checkCellSurvival(x,y,z)){
                            getCellAtCoords(x,y,z).setAlive(false);
                        }
                    }
                    else{
                        if(getCellAtCoords(x,y,z).getState()>0){
                            getCellAtCoords(x,y,z).setState(getCellAtCoords(x,y,z).getState() -1);
                            if(getCellAtCoords(x,y,z).getState() == 0){
                                removeCellAtCoords(x,y,z);
                            }
                        }
                        else{
                            if(checkCellSpawn(x,y,z)){
                                spawnCellAtCoords(Material.STONE,x,y,z);
                            }
                        }
                    }
                }
            }
        }
    }
    private void placeCellInWorld(World world, int x, int y, int z) {
        Block block = world.getBlockAt(x + xCoordsInWorld, y + yCoordsInWorld, z+ zCoordsInWorld);
        block.setType(getCellAtCoords(x,y,z).getColor());
    }


    public void displayColonyInWorld(){
        World world = Bukkit.getWorld("world");
        for(int x = 0; x< xSize;x++){
            for(int y = 0; y< ySize;y++){
                for(int z = 0; z< zSize;z++){
                    placeCellInWorld(world, x, y, z);
                }
            }
        }
    }
    private int getVonNeumannNeighbours(int x, int y, int z){
        int neighbours = 0;

        if(x != 0 && x <xSize-1){
            if(getCellAtCoords(x-1,y,z).getState()>0){
                neighbours++;
            }
            if(getCellAtCoords(x+1,y,z).getState()>0){
                neighbours++;
            }
        }
        if(y != 0 && y <ySize-1){
            if(getCellAtCoords(x,y-1,z).getState()>0){
                neighbours++;
            }
            if(getCellAtCoords(x,y+1,z).getState()>0){
                neighbours++;
            }
        }
        if(z != 0 && z <zSize-1){
            if(getCellAtCoords(x,y,z-1).getState()>0){
                neighbours++;
            }
            if(getCellAtCoords(y,x,z+1).getState()>0){
                neighbours++;
            }
        }
        return neighbours;
    }

}
