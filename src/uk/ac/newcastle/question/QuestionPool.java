package uk.ac.newcastle.question;

import java.util.HashSet;
import java.util.Set;

/**
 * @description: TODO
 * @author: Wenhao Bao
 * @date: 2024/9/29 23:07
 * @param:
 * @return:
 */
public class QuestionPool{
    
    private static final Set<Question> questions = new HashSet<>();
    
    static {
        // Free Response Questions
        /*questions.add(new FreeResponseQuestion("What's the capital of the UK?", "London"));
        questions.add(new FreeResponseQuestion("What's the capital of China?", "Beijing"));
        questions.add(new FreeResponseQuestion("What's the capital of the US?", "WashingtonDC"));
        questions.add(new FreeResponseQuestion("What's the capital of Canada?", "Ottawa"));
        questions.add(new FreeResponseQuestion("What's the capital of Czech?", "Prague"));
        questions.add(new FreeResponseQuestion("What's the capital of Australia?", "Canberra"));
        questions.add(new FreeResponseQuestion("What's the capital of Korea?", "Seoul"));
        questions.add(new FreeResponseQuestion("What's the capital of Japan?", "Tokyo"));
        questions.add(new FreeResponseQuestion("What's the capital of France?", "Paris"));
        questions.add(new FreeResponseQuestion("What's the capital of Germany?", "Berlin"));*/
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
        /*questions.add(new MultipleChoiceQuestion("Which of the following are primary colors? a.Red b.Green c.Blue d.Yellow",Set.of("a","c","d")));
        questions.add(new MultipleChoiceQuestion("Which of these animals are mammals? a.Dolphin b.Shark c.Whale d.Crocodile",Set.of("a","c")));
        questions.add(new MultipleChoiceQuestion("Which of the following countries are in Europe? a.France b.Brazil c.Germany d.Japan",Set.of("a","c")));
        questions.add(new MultipleChoiceQuestion("Which of these are renewable energy sources? a.Solar energy b.Coal c.Wind energy d.Natural gas",Set.of("a","c")));
        questions.add(new MultipleChoiceQuestion("Which of the following are parts of a computer? a.Monitor b.Processor c.Keyboard d.Printer",Set.of("a","b","c","d")));
        questions.add(new MultipleChoiceQuestion("Which of these foods are high in protein? a.Chicken b.Rice c.Fish d.Beans",Set.of("a","c","d")));
        questions.add(new MultipleChoiceQuestion("Which of the following are programming languages? a.Python b.HTML c.Java d.SQL",Set.of("a","c","d")));
        questions.add(new MultipleChoiceQuestion("Which of these elements are noble gases? a.Oxygen b.Helium c.Neon d.Hydrogen",Set.of("b","c")));
        questions.add(new MultipleChoiceQuestion("Which of the following planets have rings? a.Mars b.Saturn c.Jupiter d.Uranus",Set.of("b","c","d")));
        questions.add(new MultipleChoiceQuestion("Which of these are chemical elements? a.Hydrogen b.Water c.Oxygen d.Carbon",Set.of("a","c","d")));*/
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
