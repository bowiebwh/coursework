package uk.ac.newcastle.question;

import java.util.HashSet;
import java.util.Set;

/**
 * @description: provide questions use a static Set
 * @author: Wenhao Bao
 * @date: 2024/9/29 23:07
 * @param:
 * @return:
 */
public class QuestionPool{
    
    private static final Set<Question> questions = new HashSet<>();
    
    static {
        // Free Response Questions
        questions.add(QuestionFactory.getInstance(QuestionFactory.FREE_RESPONSE_QUESTION + "What's the capital of the UK?" + ":London"));
        questions.add(QuestionFactory.getInstance(QuestionFactory.FREE_RESPONSE_QUESTION + "What's the capital of China?" + ":Beijing"));
        questions.add(QuestionFactory.getInstance(QuestionFactory.FREE_RESPONSE_QUESTION + "What's the capital of the US?" + ":WashingtonDC"));
        questions.add(QuestionFactory.getInstance(QuestionFactory.FREE_RESPONSE_QUESTION + "What's the capital of Canada?" + ":Ottawa"));
        questions.add(QuestionFactory.getInstance(QuestionFactory.FREE_RESPONSE_QUESTION + "What's the capital of Czech?" + ":Prague"));
        questions.add(QuestionFactory.getInstance(QuestionFactory.FREE_RESPONSE_QUESTION + "What's the capital of Australia?" + ":Canberra"));
        questions.add(QuestionFactory.getInstance(QuestionFactory.FREE_RESPONSE_QUESTION + "What's the capital of Korea?" + ":Seoul"));
        questions.add(QuestionFactory.getInstance(QuestionFactory.FREE_RESPONSE_QUESTION + "What's the capital of Japan?" + ":Tokyo"));
        questions.add(QuestionFactory.getInstance(QuestionFactory.FREE_RESPONSE_QUESTION + "What's the capital of France?" + ":Paris"));
        questions.add(QuestionFactory.getInstance(QuestionFactory.FREE_RESPONSE_QUESTION + "What's the capital of Germany?" + ":Berlin"));
        
        
        // Multiple Choice Questions
        questions.add(QuestionFactory.getInstance(QuestionFactory.MULTIPLE_CHOICE_QUESTION 
                + "Which of the following are primary colors?" + ":Red,Green,Blue,Yellow" + ":Red,Blue,Yellow"));
        questions.add(QuestionFactory.getInstance(QuestionFactory.MULTIPLE_CHOICE_QUESTION 
                + "Which of these animals are mammals?" + ":Dolphin,Shark,Whale,Crocodile" + ":Dolphin,Whale"));
        questions.add(QuestionFactory.getInstance(QuestionFactory.MULTIPLE_CHOICE_QUESTION 
                + "Which of the following countries are in Europe?" + ":France,Brazil,Germany,Japan" + ":France,Germany"));
        questions.add(QuestionFactory.getInstance(QuestionFactory.MULTIPLE_CHOICE_QUESTION 
                + "Which of these are renewable energy sources?" + ":Solar energy,Coal,Wind energy,Natural gas" + ":Solar energy,Wind energy"));
        questions.add(QuestionFactory.getInstance(QuestionFactory.MULTIPLE_CHOICE_QUESTION 
                + "Which of the following are parts of a computer?" + ":Monitor,Processor,Keyboard,Printer" + ":Monitor,Processor,Keyboard,Printer"));
        questions.add(QuestionFactory.getInstance(QuestionFactory.MULTIPLE_CHOICE_QUESTION 
                + "Which of these foods are high in protein?" + ":Chicken,Rice,Fish,Beans" + ":Chicken,Fish,Beans"));
        questions.add(QuestionFactory.getInstance(QuestionFactory.MULTIPLE_CHOICE_QUESTION 
                + "Which of the following are programming languages?" + ":Python,HTML,Java,SQL" + ":Python,Java,SQL"));
        questions.add(QuestionFactory.getInstance(QuestionFactory.MULTIPLE_CHOICE_QUESTION 
                + "Which of these elements are noble gases?" + ":Oxygen,Helium,Neon,Hydrogen" + ":Helium,Neon"));
        questions.add(QuestionFactory.getInstance(QuestionFactory.MULTIPLE_CHOICE_QUESTION 
                + "Which of the following planets have rings?" + ":Mars,Saturn,Jupiter,Uranus" + ":Saturn,Jupiter,Uranus"));
        questions.add(QuestionFactory.getInstance(QuestionFactory.MULTIPLE_CHOICE_QUESTION 
                + "Which of these are chemical elements?" + ":Hydrogen,Water,Oxygen,Carbon" + ":Hydrogen,Oxygen,Carbon"));
    }
    
    public static Set<Question> getQuestionsPool(){
        // Return a copy to avoid modification
        return new HashSet<>(questions);
    }
}
