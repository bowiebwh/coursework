package uk.ac.newcastle.question;


/**
 * @classname: Question
 * @description: the interface define a contract for these methods
 * @author: Wenhao Bao
 * @date: 2024/10/7 23:56
 */
public interface Question {

    /**
     * @description: generate a question formulation
     * @param: 
     * @return: String
    */
    String getFormulation();

    /**
     * @description: judge the answer is whether correct or not
     * @param: String answer
     * @return: boolean
     */
    boolean isCorrectNumber(String answer);
}
