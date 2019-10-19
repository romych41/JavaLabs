package com.kpi.markushevskiy.lab1.model;

import java.util.Objects;

public class EntryModel {
    private String name;
    private Integer key;

    public EntryModel(String name, int key) {
        this.name = name;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntryModel that = (EntryModel) o;
        return key == that.key &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, key);
    }

    @Override
    public String toString() {
        return "EntryModel{" +
                "name='" + name + '\'' +
                ", key=" + key +
                '}';
    }
}
