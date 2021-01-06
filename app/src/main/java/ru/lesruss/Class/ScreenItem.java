package ru.lesruss.Class;

public class ScreenItem {
    String title,Description;
    int img;

    public ScreenItem(String title, String description, int img) {
        this.title = title;
        Description = description;
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
