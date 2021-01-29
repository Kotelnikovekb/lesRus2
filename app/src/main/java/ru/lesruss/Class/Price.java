package ru.lesruss.Class;

public class Price {
    private int Id;
    private String Name;
    private String Description;
    private int Cost;
    private String Page;
    private String Unit;
    private String Title;

    public Price(int id, String name, String description, int cost, String page, String unit, String title) {
        Id = id;
        Name = name;
        Description = description;
        Cost = cost;
        Page = page;
        Unit = unit;
        Title = title;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getCost() {
        return Cost;
    }

    public void setCost(int cost) {
        Cost = cost;
    }

    public String getPage() {
        return Page;
    }

    public void setPage(String page) {
        Page = page;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
