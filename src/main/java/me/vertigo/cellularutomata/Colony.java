package me.vertigo.cellularutomata;

import com.google.common.collect.Range;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.Random;
import java.util.Vector;

public class Colony {
    //settings
    private int xCoordsInWorld = 1000, yCoordsInWorld = 64, zCoordsInWorld = 1000;
    private int xSize = 200, ySize = 200, zSize = 200;

    //variables
    World world = Bukkit.getWorld("world");
    private Cell[][][] colonyOfCells = new Cell[ySize][xSize][zSize];
    private int neighbouringSize;
    int[][] coordsToCheck = {
            //von neumann neighbouring
            {0, 0, -1},
            {0, 0, 1},
            {0, -1, 0},
            {0, 1, 0},
            {-1, 0, 0},
            {1, 0, 0},
            //moore neighbouring
            {-1, -1, -1},
            {-1, -1, 0},
            {-1, 0, -1},
            {0, -1, -1},
            {1, 1, 1},
            {1, 1, 0},
            {1, 0, 1},
            {0, 1, 1},
            {-1, 1, 1},
            {1, -1, 1},
            {1, 1, -1},
            {-1, 1, 0},
            {1, -1, 0},
            {1, 0, -1},
            {-1, 0, 1},
            {0, -1, 1},
            {0, 1, -1},
            {-1, -1, 1},
            {-1, 1, -1},
            {1, -1, -1},
    };
    Coloring coloring = new Coloring();
    //rules of the colony
    private Vector<Range<Integer>> survivalIntervals;
    private Vector<Range<Integer>> spawnIntervals;
    private int states;

    public Colony(Vector<Range<Integer>> survivalIntervals, Vector<Range<Integer>> spawnIntervals, int states, boolean neighbouringMethod) {
        this.survivalIntervals = survivalIntervals;
        this.spawnIntervals = spawnIntervals;
        this.states = states;
        //determines whether to use Moore or Von Neumann neighbouring
        this.neighbouringSize = neighbouringMethod ? 26 : 6;
        //clears the previous run and spawns the starting blocks
        colonyInit();

    }

    public Cell getCellAtCoords(int x, int y, int z) {
        return colonyOfCells[y][x][z];
    }

    private void placeCellInWorld(int x, int y, int z) {
        Block block = world.getBlockAt(x + xCoordsInWorld, y + yCoordsInWorld, z + zCoordsInWorld);
        block.setType(getCellAtCoords(x, y, z).getColor());
    }
    private void placeCellInWorldWithColor(Material color, int x, int y, int z) {
        Block block = world.getBlockAt(x + xCoordsInWorld, y + yCoordsInWorld, z + zCoordsInWorld);
        block.setType(color);
    }
    private void spawnCellAtCoords(Material color, int x, int y, int z) {
        getCellAtCoords(x, y, z).cellSpawn(color, states);
        placeCellInWorld(x, y, z);
    }

    private void removeCellAtCoords(int x, int y, int z) {
        getCellAtCoords(x, y, z).cellKill();
        placeCellInWorld(x, y, z);
    }

    public void spawnRandomCellsInColony() {
        Random rand = new Random();
        int random_number;
        double multiplier = 1.4;
        int start = (int) multiplier * xSize / 4;
        int end = (int) ((3 * xSize / 4) * (2 - multiplier));
        for (int x = start; x < end; x++) {
            for (int y = start; y < end; y++) {
                for (int z = start; z < end; z++) {
                    random_number = rand.nextInt(10);
                    if (random_number > 5) {
                        spawnCellAtCoords(Material.BLACK_GLAZED_TERRACOTTA, x, y, z);
                    }
                }
            }
        }
    }

    public void colonyInit() {
        for (int x = 0; x < 400; x++) {
            for (int y = 0; y < 400; y++) {
                for (int z = 0; z < 400; z++) {
                    Block block = world.getBlockAt(x + xCoordsInWorld, y + yCoordsInWorld, z + zCoordsInWorld);
                    block.setType(Material.AIR);
                }
            }
        }
        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                for (int z = 0; z < zSize; z++) {
                    colonyOfCells[y][x][z] = new Cell();
                }
            }
        }
        //spawn cube of random blocks in center

        //spawnRandomCellsInColony();

        //spawn single block in center
        spawnCellAtCoords(Material.RED_STAINED_GLASS, xSize / 2, ySize / 2, zSize / 2);
    }

    private boolean checkCellSurvival(int neighbours, int x, int y, int z) {
        for (Range<Integer> survivalRange : survivalIntervals) {
            if (survivalRange.contains(neighbours)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkCellSpawn(int neighbours, int x, int y, int z) {
        for (Range<Integer> spawnRange : spawnIntervals) {
            if (spawnRange.contains(neighbours)) {
                return true;
            }
        }
        return false;
    }

    public void iterateColony() {
        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                for (int z = 0; z < zSize; z++) {
                    getCellAtCoords(x, y, z).setSpawnedThisGeneration(false);
                }
            }
        }

        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                for (int z = 0; z < zSize; z++) {
                    Cell currentCell = getCellAtCoords(x, y, z);
                    int neighbours = getNeighbours(x, y, z);
                    if (currentCell.getAlive()) {
                        if (!checkCellSurvival(neighbours,x, y, z)) {
                            currentCell.setAlive(false);
                        }
                    } else {
                        if (currentCell.getState() > 0) {
                            currentCell.setState(currentCell.getState() - 1);
                            if (currentCell.getState() == 0) {
                                removeCellAtCoords(x, y, z);
                            }else{
                                placeCellInWorldWithColor(coloring.densityShading(neighbours),x,y,z);
                            }

                        } else {
                            if (checkCellSpawn(neighbours,x, y, z)) {
                                spawnCellAtCoords(coloring.densityShading(neighbours), x, y, z);
                            }
                        }
                    }
                }
            }
        }
    }

    private int getNeighbours(int x, int y, int z) {
        int neighbours = 0;

        for (int i = 0; i < neighbouringSize; i++) {
            try {
                Cell currentCell = getCellAtCoords(x+ coordsToCheck[i][0], y+coordsToCheck[i][1], z+coordsToCheck[i][2]);
                if (currentCell.getState() > 0 && !currentCell.getSpawnedThisGeneration()) {
                    neighbours++;
                }
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
        }
        return neighbours;
    }
}
