package models;

public class Location{
    private Integer locationId;
    private Double longitude;
    private Double latitude;
    public Location(){}

    public Location(Double longitude, Double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }



}