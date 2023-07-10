package me.vertigo.cellularutomata;

import org.bukkit.Material;

public class Cell {
    private Material color;
    private int state;
    private boolean alive;

    public Cell(){
        this.color = Material.AIR;
        this.state = 0;
        this.alive = false;
    }

    public void cellSpawn(Material color, int state){
        setColor(color);
        setState(state);
        setAlive(true);
    }

    public void cellKill(){
        setColor(Material.AIR);
        setState(0);
        setAlive(false);
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
