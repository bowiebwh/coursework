package uk.ac.newcastle.question;

/**
 * @classname: FreeResponseQuestion
 * @description: FreeResponseQuestion Javabean and implements 2 methods from Question
 * @author: Wenhao Bao
 * @date: 2024/10/7 23:56
 */
public class FreeResponseQuestion implements Question{
    
    private final String formulation;
    private final String correctAnswer;

    public FreeResponseQuestion(String formulation, String correctAnswer) {
        this.formulation = formulation;
        this.correctAnswer = correctAnswer;
    }

    /**
     * @description: generate a question formulation
     * @param:
     * @return: String formulation
     */
    @Override
    public String getFormulation() {
        return formulation;
    }

    /**
     * @description: judge the answer is whether correct or not
     * @param: String answer
     * @return: boolean
     */
    @Override
    public boolean isCorrectNumber(String answer) {
        //req1: ignore case
        //req2: treat multiple joint whitespaces as a single whitespace
        return this.correctAnswer.equalsIgnoreCase(answer.trim().replaceAll("\\s+"," "));
    }

    //Override hashCode to generate hash based on the formulation
    @Override
    public int hashCode() {
        return formulation.toLowerCase().hashCode();
    }

    //Override equals to compare the formulation and correctAnswer of the question
    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj == null || getClass() != obj.getClass()){
            return false;
        }
        FreeResponseQuestion frq = (FreeResponseQuestion) obj;
        return formulation.equalsIgnoreCase(frq.formulation) && correctAnswer.equalsIgnoreCase(frq.correctAnswer);
    }

//    public String toString() {
//        return "FreeResponseQuestion{formulation = " + formulation + ", correctAnswer = " + correctAnswer + "}";
//    }
}
