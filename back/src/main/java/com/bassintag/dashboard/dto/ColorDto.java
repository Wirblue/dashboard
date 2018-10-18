package com.bassintag.dashboard.dto;

import lombok.Data;

import java.awt.*;

@Data
public class ColorDto {

    private int red;

    private int green;

    private int blue;

    public ColorDto(Color color)
    {
        red = color.getRed();
        green = color.getGreen();
        blue = color.getBlue();
    }
}
