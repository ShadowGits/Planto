package com.example.edp_plantsystem;

public class IrrigationClass {


    private String IrrigationStatus;
    private String time;

    public String getIrrigationStatus() {
        return IrrigationStatus;
    }

    public void setIrrigationStatus(String irrigationStatus) {
        IrrigationStatus = irrigationStatus;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    //the data type of the date dependes on the type of data we get from the website
    //for now its string
    private int imageResource;


    /*
     * constructor
     */
    public IrrigationClass(String time, String irrigationStatus, int imageResource) {
        this.imageResource = imageResource;
        this.IrrigationStatus = irrigationStatus;
        this.time = time;
    }

}