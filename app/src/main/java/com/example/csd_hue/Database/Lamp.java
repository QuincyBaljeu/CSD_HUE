package com.example.csd_hue.Database;

public class Lamp {

    private String lampID;
    private String lampHue;
    private String lampBrightness;
    private String lampSaturation;

    public Lamp(String lampID, String lampHue, String lampBrightness, String lampSaturation) {
        this.lampID = lampID;
        this.lampHue = lampHue;
        this.lampBrightness = lampBrightness;
        this.lampSaturation = lampSaturation;
    }

    public String getLampID() {
        return lampID;
    }

    public void setLampID(String lampID) {
        this.lampID = lampID;
    }

    public String getLampHue() {
        return lampHue;
    }

    public void setLampHue(String lampHue) {
        this.lampHue = lampHue;
    }

    public String getLampBrightness() {
        return lampBrightness;
    }

    public void setLampBrightness(String lampBrightness) {
        this.lampBrightness = lampBrightness;
    }

    public String getLampSaturation() {
        return lampSaturation;
    }

    public void setLampSaturation(String lampSaturation) {
        this.lampSaturation = lampSaturation;
    }
}
