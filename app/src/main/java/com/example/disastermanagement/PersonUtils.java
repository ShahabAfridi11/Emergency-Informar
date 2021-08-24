package com.example.disastermanagement;


import android.app.Person;

public class PersonUtils {

    private String disasterId;
    private String date;
    private String address;
    private String city;
    private String engagement;
    private String disEngagement;

    private String deaths;
    private String injuries;

    private String saves;
    private String cars;
    private String buildings;

    public PersonUtils(String disasterId, String date, String address, String city, String engagement, String disEngagement) {
        this.disasterId = disasterId;
        this.date = date;
        this.address = address;
        this.city = city;
        this.engagement = engagement;
        this.disEngagement = disEngagement;
    }
    public PersonUtils(String disasterId, String date, String address, String city, String engagement, String disEngagement,String death1, String injuries1, String saves1, String cars1, String buildings1){
        this.disasterId = disasterId;
        this.date = date;
        this.address = address;
        this.city = city;
        this.engagement = engagement;
        this.disEngagement = disEngagement;
        this.deaths = death1;
        this.injuries=injuries1;
        this.saves = saves1;
        this.cars=cars1;
        this.buildings=buildings1;
    }

    public String getDisasterId() {
        return disasterId;
    }

    public void setDisasterId(String disasterId) {
        this.disasterId = disasterId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEngagement() {
        return engagement;
    }

    public void setEngagement(String engagement) {
        this.engagement = engagement;
    }

    public String getDisEngagement() {
        return disEngagement;
    }

    public void setDisEngagement(String disEngagement) {
        this.disEngagement = disEngagement;
    }

    public String getDeaths(){
        return deaths;
    }
    public void setDeaths(String deaths1){
        this.deaths=deaths1;
    }

    public String getInjuries(){
        return injuries;
    }
    public void setInjuries(String injuries1){
        this.injuries=injuries1;
    }
    public String getSaves(){
        return saves;
    }
    public void setSaves(String saves1){
        this.saves=saves1;
    }

    public String getCars(){
        return cars;
    }
    public void setCars(String cars1){
        this.cars=cars1;
    }
    public String getBuildings(){
        return buildings;
    }
    public void setBuildings(String buildings1){
        this.buildings=buildings1;
    }


}