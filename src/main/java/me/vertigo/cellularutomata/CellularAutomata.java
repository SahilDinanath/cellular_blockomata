package me.vertigo.cellularutomata;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;

public final class CellularAutomata extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Cellular Automata Starting");

        someOtherMethod();
    }
    public void placeBlock(World world, int x, int y, int z, Material blockType) {
        Block block = world.getBlockAt(x, y, z);
        block.setType(blockType);
    }
    public void someOtherMethod() {
        // Get the world
        World world = Bukkit.getWorld("world");

        // Example usage: place a stone block at coordinates (10, 64, 10)
        placeBlock(world, 10, 64, 10, Material.STONE);
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Cellular Automata Start");
    }
}
