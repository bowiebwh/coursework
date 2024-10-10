package uk.ac.newcastle.quiz;

import uk.ac.newcastle.question.Question;
import uk.ac.newcastle.student.Student;

import java.util.List;

/**
 * @classname: QuizService
 * @description: the interface define a contract for these methods
 * @author: Wenhao Bao
 * @date: 2024/10/7 23:33
 */
public interface QuizService {

    /**
     * @description: generate regular quiz questions
     * @param: int numberOfQuestions param description
     * @return: List<Question> return description
     */
    List<Question> generateQuiz(int numberOfQuestions);

    /**
     * @description: generate revision quiz questions
     * @param: Student student, int numberOfQuestions
     * @return: List<Question> revisionQuestions
     */
    List<Question> revise(Student student, int numberOfQuestions);

    /**
     * @description: take the regular quiz
     * @param: Student student, List<Question> questions, List<String> answers
     * @return: Double score
     */
    Double takeQuiz(Student student, List<Question> questions, List<String> answers);

    /**
     * @description: take the revision quiz
     * @param: Student student, List<Question> questions, List<String> answers
     * @return: Double score
     */
    Double takeRevisionAttempt(Student student, List<Question> questions, List<String> answers);

    /**
     * @description: generate the statistics of any student
     * @param: Student student
     * @return: String
     */
    String generateStatistics(Student student);
}
