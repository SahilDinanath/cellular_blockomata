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

        Main main = new Main();
        main.runCellularAutomata();
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Cellular Automata Start");
    }
}
