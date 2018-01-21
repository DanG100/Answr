package com.example.henrylee.hackdavis2;

/**
 * Created by Henry Lee on 1/21/2018.
 */

public class QuizQuestionsForStudent {

    private MCQuestion[] multipleChoiceQuestions;//Fill with MC objects, null if index position is a short answer
    private boolean[] mcOrShortAnswer;//TRUE for Short Answer, FALSE for Multiple Choice
    public boolean[] getMcOrShortAnswer() {
        return mcOrShortAnswer;
    }

    public void setMcOrShortAnswer(boolean[] mcOrShortAnswer) {
        this.mcOrShortAnswer = mcOrShortAnswer;
    }

    public void setMultipleChoiceQuestions(MCQuestion[] questions){
        this.multipleChoiceQuestions = questions;
    }

    public MCQuestion[] getMultipleChoiceQuestions(){
        return multipleChoiceQuestions;
    }





}
