package com.escaperooms.application;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Door {
    @JsonProperty("destination")
    private String destination;
    @JsonProperty("solution")
    private List<String> solution;
    private int x;
    private int y;
    private String filePath;
    public Icon icon;
    private static final int DIAMETER = 100;

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @JsonCreator
    public Door(@JsonProperty("destination") String destination, @JsonProperty("solution") List<String> solution,@JsonProperty("x") int x, @JsonProperty("y") int y, @JsonProperty("filePath")String filePath) {
        this.destination = destination;
        this.solution = solution;
        this.x = x;
        this.y = y;
        this.filePath = filePath;
        this.icon = (new ImageIcon(filePath));

    }

    public String getDestination() {
        return destination;
    }

    public List<String> getSolution() {
        return solution;
    }

    public void useDoor(){

        getSolution().forEach(System.out::println);

    }

    public void paint(Graphics2D g) {
        g.drawImage((new ImageIcon("src/resources/pictures/door.jpg").getImage()), x, y, null);
        }

    public Rectangle getBounds() {
        return new Rectangle(x, y,100,200);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getFilePath() {
        return filePath;
    }
}