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
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run(){
                main.runCellularAutomata();
            }
        },300,5);
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Cellular Automata Stopping");
    }
}
