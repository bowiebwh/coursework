package uk.ac.newcastle.question;

import java.util.*;

/**
 * @classname: MultipleChoiceQuestion
 * @description: MultipleChoiceQuestion Javabean and implements 2 methods from Question
 * @author: Wenhao Bao
 * @date: 2024/10/7 23:56
 */
public class MultipleChoiceQuestion implements Question{
    
    private final String formulation;
    private final List<String> options;
    private final Set<String> correctAnswers;
    
    public MultipleChoiceQuestion(String formulation, List<String> options, Set<String> correctAnswers) {
        if (options.size() < 2 || options.size() > 4) {
            throw new IllegalArgumentException("Number of options must be between 2 and 4.");
        }
        this.formulation = formulation;
        this.options = options;
        this.correctAnswers = correctAnswers;
    }

    /**
     * @description: generate a question formulation and options
     * @param:
     * @return: String
     */
    @Override
    public String getFormulation() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < options.size(); i++) {
            if (i == options.size() - 1){
                sb.append(options.get(i));
            }else {
                sb.append(options.get(i)).append(",");
            }
        }
        return formulation + " " + sb;
    }

    /**
     * @description: judge the answer is whether correct or not
     * @param: String answer
     * @return: boolean
     */
    @Override
    public boolean isCorrectNumber(String answer) {
        String[] answers = answer.toLowerCase().split(",");

        List<String> correctAnswersList = this.correctAnswers.stream()
                .map(String::trim)              
                .map(String::toLowerCase)          
                .sorted()
                .toList();

        List<String> answersList = Arrays.stream(answers)
                .map(String::trim)
                .map(String::toLowerCase)
                .sorted()
                .toList();

        return correctAnswersList.equals(answersList);
    }

    /**
     * @return options
     */
    public List<String> getOptions() { return options; }
    
    /**
     * @return correctAnswers
     */
    public Set<String> getCorrectAnswers() {
        return correctAnswers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MultipleChoiceQuestion that = (MultipleChoiceQuestion) o;
        return Objects.equals(formulation, that.formulation) && Objects.equals(options, that.options) && Objects.equals(correctAnswers, that.correctAnswers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(formulation, options, correctAnswers);
    }

    @Override
    public String toString() {
        return "MultipleChoiceQuestion{" +
                "formulation='" + formulation + '\'' +
                ", options=" + options +
                ", correctAnswers=" + correctAnswers +
                '}';
    }
}
