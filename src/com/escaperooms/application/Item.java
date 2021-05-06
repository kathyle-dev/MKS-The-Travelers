package com.escaperooms.application;

public abstract class Item {
    private String name;
    private String description;
    private String itemType;

    abstract public void use(String command);

    public String getDescription(){
        return this.description;
    }

    public String getItemType() {
        return itemType;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public void setName(String name) {
        this.name = name;
    }
}
