package com.example.myspringcontainer.model;

public class Movie {
    private Long id;
    private String title;
    private Integer year;
    private Boolean watched;
    private String notes;

    public Movie() {}

    public Movie(Long id, String title, Integer year, Boolean watched, String notes) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.watched = watched;
        this.notes = notes;
    }

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }

    public Boolean getWatched() { return watched; }
    public void setWatched(Boolean watched) { this.watched = watched; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
