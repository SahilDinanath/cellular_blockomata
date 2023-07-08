package me.vertigo.cellularutomata;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.Vector;

public  class Main {
    //settings
    private int xSize = 100;
    private int ySize = 100;
    private int zSize = 100;
    //settings stop
    private Cell[][][] colonyOfCells = new Cell[ySize][xSize][zSize];


    public void init(){
        for(Cell[][] row: colonyOfCells){
           for(Cell[] column: row){
               for(Cell cell: column){
                   cell = new Cell(Material.STONE,5);
               }
           }
        }
    }

    public void placeBlock(World world, int x, int y, int z, Material blockType) {
        Block block = world.getBlockAt(x, y, z);
        block.setType(blockType);
    }

    public void displayColony(){
        World world = Bukkit.getWorld("world");
        for(int y = 0; y< ySize;y++){
            for(int x = 0; x< xSize;x++){
                for(int z = 0; z< zSize;z++){
                    placeBlock(world, 10+x, 64+y, 10+z, Material.STONE);
                }
            }
        }
    }

    public void calculateNewState(){

    }
    public void runCellularAutomata() {
        // Get the world

        displayColony();
        // Example usage: place a stone block at coordinates (10, 64, 10)
    }
}
