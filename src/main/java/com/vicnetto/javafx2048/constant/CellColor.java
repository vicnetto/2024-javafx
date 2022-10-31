package com.vicnetto.javafx2048.constant;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class CellColor {

    public static Paint getColorAccordingToNumber(int number) {
        return Paint.valueOf(switch (number) {
            case 0 -> ColorPalette.LIGHT_GREY;
            case 2 -> ColorPalette.RED_SUPER_GIANT;
            case 4 -> ColorPalette.ORANGE_SPACE_DEBRIS;
            case 8 -> ColorPalette.YELLOW_COMET_TRAIL;
            case 16 -> ColorPalette.GREEN_PLANETARIUM;
            case 32 -> ColorPalette.GREEN_XENOBIOLOGY;
            case 64 -> ColorPalette.GREEN_TERRAFORM;
            case 128 -> ColorPalette.BLUE_AERONAUTICS;
            case 256 -> ColorPalette.BLUE_VOYAGER;
            case 512 -> ColorPalette.BLUE_COSMONAUT;
            case 1024 -> ColorPalette.PURPLE_SOFT_NEBULA;
            case 2048 -> ColorPalette.PURPLE_ASTEROID_BELT;
            case 4096 -> ColorPalette.PINK_LUNA_BASE;
            case 8192 -> ColorPalette.PINK_MILK_STAR;
            default -> ColorPalette.PINK_BINARY_STAR;
        });
    }
}
