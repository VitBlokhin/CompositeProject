package org.itstep.pps2701.blokhin;

/**
 * Created by Vit on 18.05.2017.
 */
public class City implements UnitComponent {
    private String name;

    public City(String name) {
        this.name = name;
    }



    @Override
    public String toString() {
        return "Город " + name;
    }

    @Override
    public String getItem() {
        return "Город " + name;
    }
} // class City
