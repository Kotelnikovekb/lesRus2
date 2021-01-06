package ru.lesruss.Class;

import android.graphics.Bitmap;

public class CatalogClass {
    private int Id;
    private String From;
    private String To;
    private String Name;
    private String Url;
    private String Description;
    private Bitmap Background;

    public CatalogClass(int id, String from, String to, String name, String url, String description, Bitmap background) {
        Id = id;
        From = from;
        To = to;
        Name = name;
        Url = url;
        Description = description;
        Background = background;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getFrom() {
        return From;
    }

    public void setFrom(String from) {
        From = from;
    }

    public String getTo() {
        return To;
    }

    public void setTo(String to) {
        To = to;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Bitmap getBackground() {
        return Background;
    }

    public void setBackground(Bitmap background) {
        Background = background;
    }
}
