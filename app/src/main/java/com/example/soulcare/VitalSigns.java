package com.example.soulcare;

public class VitalSigns {
    private int id;
    private String date_field;
    private int temperature;
    private int pulse;
    private String bloodPressure;
    private String glucoseLevel;
    private String cholesterol;

    public VitalSigns(int id, String date_field,int temperature, int pulse, String bloodPressure, String glucoseLevel, String cholesterol) {
        this.id = id;
        this.date_field = date_field;
        this.temperature = temperature;
        this.pulse = pulse;
        this.bloodPressure = bloodPressure;
        this.glucoseLevel = glucoseLevel;
        this.cholesterol = cholesterol;
    }
    //default constructor
    public VitalSigns(){}

    //to string
    @Override
    public String toString() {
        return
                "Date: " + date_field + '\n' +
                        "temperature: " + temperature + "\u2109" +
                        "\nPulse: " + pulse + " BPM" +
                        "\nBlood Pressure: " + bloodPressure + " mm/Hg\n" +
                        "Glucose Level: " + glucoseLevel + " mg/dL\n" +
                        "Cholesterol: " + cholesterol + " mg/dL";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate_field() {
        return date_field;
    }

    public void setDate_field(String date_field) {
        this.date_field = date_field;
    }
    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getPulse() {
        return pulse;
    }

    public void setPulse(int pulse) {
        this.pulse = pulse;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public String getGlucoseLevel() {
        return glucoseLevel;
    }

    public void setGlucoseLevel(String glucoseLevel) {
        this.glucoseLevel = glucoseLevel;
    }

    public String getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(String cholesterol) {
        this.cholesterol = cholesterol;
    }
}
