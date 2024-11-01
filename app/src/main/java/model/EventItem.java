package model;

public class EventItem {
    private String title;
    private String description;
    private String presenter;
    private String time;
    private String language;
    private String mode;
    private String photo;
    private String biography;
    private String host;
    private String cancel;

    public EventItem(String title, String description, String speaker, String time, String language, String mode, String photo, String biography, String host, String cancel) {
        this.title = title;
        this.description = description;
        this.presenter = speaker;
        this.time = time;
        this.language = language;
        this.mode = mode;
        this.photo = photo;
        this.biography = biography;
        this.host = host;
        this.cancel = cancel;
    }

    //constructor without the description
    public EventItem(String title, String presenter, String time, String language, String mode, String photo) {
        this.title = title;
        this.presenter = presenter;
        this.time = time;
        this.language = language;
        this.mode = mode;
        this.photo = photo;
    }

    public EventItem() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPresenter() {
        return presenter;
    }

    public void setPresenter(String presenter) {
        this.presenter = presenter;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getCancel() {
        return cancel;
    }

    public void setCancel(String cancel) {
        this.cancel = cancel;
    }

    @Override
    public String toString() {
        return "EventItem{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", presenter='" + presenter + '\'' +
                ", time='" + time + '\'' +
                ", language='" + language + '\'' +
                ", mode=" + mode +
                ", biography=" + biography +
                ", host=" + host +
                '}';
    }
}
