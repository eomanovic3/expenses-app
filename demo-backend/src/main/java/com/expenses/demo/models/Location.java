package com.expenses.demo.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer locationId;

    @Column(name = "longitude")
    private Float longitude;

    @Column(name = "latitude")
    private Float latitude;

    public Location(){}

    public Location(Float longitude, Float latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }
}
