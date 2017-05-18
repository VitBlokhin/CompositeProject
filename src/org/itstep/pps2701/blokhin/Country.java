package org.itstep.pps2701.blokhin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vit on 18.05.2017.
 */
public class Country implements UnitComponent{
    private String name;
    private List<UnitComponent> regions;

    public Country(String name) {
        this.name = name;
        regions = new ArrayList<>();
    }

    public List<UnitComponent> getRegions() {
        return regions;
    }

    public void addUnit(UnitComponent region){
        if(region.getClass().equals(Region.class))
            regions.add(region);
        else throw new IllegalArgumentException("В страну можно добавлять только регионы! ¯\\_(ツ)_/¯");
    }

    public void removeUnit(UnitComponent region){
        if(region.getClass().equals(Region.class))
            regions.remove(region);
        else throw new IllegalArgumentException("Вы пытаетесь удалить из страны не-регион! ¯\\_(ツ)_/¯");
    } // removeUnit

    @Override
    public String toString() {
        return "Страна " + name;
    } // toString

    @Override
    public String getItem() {
        String result = "Страна " + name;
        for(UnitComponent region : regions) {
            result += "\n\t" + region;
        }

        return result;
    }
} // class Country
