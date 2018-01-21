package com.example.henrylee.hackdavis2;

/**
 * Created by Henry Lee on 1/20/2018.
 */

public class MCQuestion {

    private String[] choices;
    private int correctAnswerIndex;

    public MCQuestion(String[] c, int index){
        choices = c;
        correctAnswerIndex = index;
    }

    public void setChoices(String[] choicesInput){
        choices = choicesInput;
    }

    public void setCorrectAnswerIndex(int correctAnswerIndexInput){
        correctAnswerIndex = correctAnswerIndexInput;
    }

    public String[] getChoices() {
        return choices;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

}
