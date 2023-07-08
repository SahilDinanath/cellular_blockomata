package me.vertigo.cellularutomata;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;

public class Cell {
    private Material color;
    private int state;
    private boolean alive;

    public Cell(Material color, int state){
        this.color = color;
        this.state = state;
        alive = true;
    }

    public Material getColor(){
        return color;
    }
    public int getState(){
        return state;
    }

    public boolean getAlive(){
        return alive;
    }
    public void setColor(Material color){
        this.color = color;
    }
    public void getState(int state){
        this.state = state;
    }
    public void getAlive(boolean alive){
        this.alive = alive;
    }

}
