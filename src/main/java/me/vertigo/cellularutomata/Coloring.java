package me.vertigo.cellularutomata;

import org.bukkit.Material;

public class Coloring {
    private static Material[] colors =
            {
                    Material.AIR,
                    Material.RED_GLAZED_TERRACOTTA,
                    Material.WHITE_GLAZED_TERRACOTTA,
                    Material.YELLOW_GLAZED_TERRACOTTA,
                    Material.PINK_GLAZED_TERRACOTTA,
                    Material.GREEN_GLAZED_TERRACOTTA,
                    Material.BLACK_GLAZED_TERRACOTTA,
                    Material.LIGHT_GRAY_GLAZED_TERRACOTTA,
                    Material.CYAN_GLAZED_TERRACOTTA,
                    Material.ORANGE_GLAZED_TERRACOTTA,
                    Material.BLUE_GLAZED_TERRACOTTA,
                    Material.PURPLE_GLAZED_TERRACOTTA,
                    Material.GRAY_GLAZED_TERRACOTTA,
            };

    public static Material stateShading(int state){
        try{
            return colors[state];
        }
        catch(ArrayIndexOutOfBoundsException ignored){
            return colors[colors.length-1];
        }
    }
    public static Material densityShading(int density){
       return stateShading(density);
    }
}
