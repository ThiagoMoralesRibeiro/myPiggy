
package com.tucandeira.myPiggy.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Piggybank {
    private int goalInCents;
    private String description;
    private LocalDateTime begindate;
    private LocalDateTime endDate;
   



    public Piggybank(int goalInCents, String description, 
                     LocalDateTime begindate, LocalDateTime endDate ) {
        this.setGoalInCents(goalInCents);
        this.setDescription(description);
        this.setBegindate(begindate);
        this.setEndDate(endDate);
       
    }

    public int getGoalInCents() {
        return goalInCents;
    }

    public void setGoalInCents(int goalInCents) {
        this.goalInCents = goalInCents;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getBegindate() {
        return begindate;
    }

    public void setBegindate(LocalDateTime begindate) {
        this.begindate = begindate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }


}

