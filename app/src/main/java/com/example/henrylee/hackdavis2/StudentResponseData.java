package com.example.henrylee.hackdavis2;

import java.util.List;

/**
 * Created by Daniel on 1/21/2018.
 */

public class StudentResponseData {

    private List<Boolean> mcOrShortAnswer; //true for mc, false for sa
    private List<String> shortAnswers;
    private List<Integer> multipleChoiceIndexes; //-1 means null

    public String first,last,email;

    public List<Boolean> getMcOrShortAnswer() {
        return mcOrShortAnswer;
    }

    public void setMcOrShortAnswer(List<Boolean> mcOrShortAnswer) {
        this.mcOrShortAnswer = mcOrShortAnswer;
    }

    public List<String> getShortAnswers() {
        return shortAnswers;
    }

    public void setShortAnswers(List<String> shortAnswers) {
        this.shortAnswers = shortAnswers;
    }

    public List<Integer> getMultipleChoiceIndexes() {
        return multipleChoiceIndexes;
    }

    public void setMultipleChoiceIndexes(List<Integer> multipleChoiceIndexes) {
        this.multipleChoiceIndexes = multipleChoiceIndexes;
    }



    public StudentResponseData(List<Boolean> mcOrShortAnswer, List<String> shortAnswers, List<Integer> multipleChoiceIndexes){
        this.mcOrShortAnswer=mcOrShortAnswer;
        this.shortAnswers=shortAnswers;
        this.multipleChoiceIndexes=multipleChoiceIndexes;
    }


}
