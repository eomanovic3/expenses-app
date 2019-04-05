package models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CategoryPerMonth {
    public String name;
    public Float value;


    public CategoryPerMonth(String name, Float value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }
}

