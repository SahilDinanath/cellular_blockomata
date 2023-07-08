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

    public void cellSpawn(Material color, int state){
        setColor(color);
        setState(state);
        setAlive(true);
    }

    public void cellKill(){
        setColor(Material.AIR);
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
    public void setState(int state){
        this.state = state;
    }
    public void setAlive(boolean alive){
        this.alive = alive;
    }

}
