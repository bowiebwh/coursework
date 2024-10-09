package uk.ac.newcastle.question;

import java.util.*;

public abstract class QuestionFactory implements Question{
    
    public static final String FREE_RESPONSE_QUESTION = "free_response:";
    
    public static final String MULTIPLE_CHOICE_QUESTION = "multiple_choice:";
    
    public static Question getInstance(String question){
        if (question.startsWith("free_response:")){
            String[] arr = question.split(":");
            if (arr.length == 3){
                return new FreeResponseQuestion(arr[1],arr[2]);// arr[1]-formulation , arr[2]-answer
            }
        }else if (question.startsWith("multiple_choice:")){
            String[] arr = question.split(":");
            if (arr.length >= 4){
                String formulation = arr[1];
                String[] optionsArr = arr[2].split(",");
                List<String> options = Arrays.asList(optionsArr);
                String[] answers = arr[3].split(",");
                Set<String> correctAnswers = Set.of(answers);
                return new MultipleChoiceQuestion(formulation,options,correctAnswers);
            }
        }
        throw new IllegalArgumentException("input is invalid");
    }
    
}
