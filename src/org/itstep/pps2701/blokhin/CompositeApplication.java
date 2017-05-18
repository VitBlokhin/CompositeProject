package org.itstep.pps2701.blokhin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vit on 18.05.2017.
 */
public class CompositeApplication {
    private List<Country> countries;

    public CompositeApplication() {
        countries = new ArrayList<>();
    }

    // геттеры
    public List<Country> getCountries() {
        return countries;
    }

    // создание административных единиц
    public void createCountry(String name){
        countries.add(new Country(name));
    }

    public void createRegion(String name, Country country){
        country.addUnit(new Region(name));
    }

    public void createCity(String name, Region region){
        region.addUnit(new City(name));
    }

    // --- удаление ---
    public void removeCountry(Country country){
        countries.remove(country);
    }

    public void removeRegion(Region region, Country country){
        country.removeUnit(region);
    }

    public void removeCity(City city, Region region){
        region.removeUnit(city);
    }



} // class CompositeApplication
