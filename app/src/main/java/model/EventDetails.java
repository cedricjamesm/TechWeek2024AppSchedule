package model;

public class EventDetails {
    private int day;
    private String masterCeremony;
    private String theme;

    public EventDetails(int day, String masterCeremony, String theme) {
        this.day = day;
        this.masterCeremony = masterCeremony;
        this.theme = theme;
    }

    public EventDetails() {
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getMasterCeremony() {
        return masterCeremony;
    }

    public void setMasterCeremony(String masterCeremony) {
        this.masterCeremony = masterCeremony;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
