package id.rrdev.crudfirebase.data.model;

import java.util.HashMap;
import java.util.Map;

public class Note {
    private String id;
    private String title;
    private String deskripsi;

    public Note(String id, String title, String deskripsi) {
        this.id = id;
        this.title = title;
        this.deskripsi = deskripsi;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", deskripsi='" + deskripsi + '\'' +
                '}';
    }

    public Note(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}
