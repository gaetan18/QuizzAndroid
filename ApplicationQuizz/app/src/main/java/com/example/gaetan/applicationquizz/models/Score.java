package com.example.gaetan.applicationquizz.models;

/**
 * Created by gilian on 28/01/2018.
 */

public class Score {
    private int id;
    private int score;

    public Score(int id, int score) {
        this.id = id;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }



}
