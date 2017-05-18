package org.itstep.pps2701.blokhin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vit on 18.05.2017.
 */
public class Region implements UnitComponent {
    private String name;
    private List<UnitComponent> cities;

    public Region(String name) {
        this.name = name;
        cities = new ArrayList<>();
    }

    public List<UnitComponent> getCities() {
        return cities;
    }

    public void addUnit(UnitComponent city){
        if(city.getClass().equals(City.class))
            cities.add(city);
        else throw new IllegalArgumentException("В область можно добавлять только города! ¯\\_(ツ)_/¯");
    }

    public void removeUnit(UnitComponent city){
        if(city.getClass().equals(City.class))
            cities.remove(city);
        else throw new IllegalArgumentException("Вы пытаетесь удалить из области не-город! ¯\\_(ツ)_/¯");
    }

    @Override
    public String toString() {
        return "Регион " + name;
    }

    @Override
    public String getItem() {
        String result = "Регион " + name;
        for(UnitComponent city : cities) {
            result += "\n\t" + city;
        }

        return result;
    }
} // class Region
