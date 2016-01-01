package com.deceax.gdq;

import java.util.Date;

public class Run {
    private Date date;
    private String game;
    private String runner;
    private String estimate;
    private String category;
    private String setup;
    private String incentive;

    public Run(Date date, String category) {
        this.date = date;
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public String getGame() { return game; }

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }

    public String getRunner() { return runner; }

    public String getIncentive() { return incentive; }
}
