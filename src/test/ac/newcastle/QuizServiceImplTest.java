package test.ac.newcastle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ac.newcastle.question.FreeResponseQuestion;
import uk.ac.newcastle.question.MultipleChoiceQuestion;
import uk.ac.newcastle.question.Question;
import uk.ac.newcastle.question.QuestionPool;
import uk.ac.newcastle.quiz.QuizServiceImpl;
import uk.ac.newcastle.student.Student;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @classname: QuizServiceImplTest
 * @description: Test class for QuizServiceImpl
 */
public class QuizServiceImplTest {

    private QuizServiceImpl quizService;
    private Student student;
    private List<Question> questions;
    private List<String> answers;

    @BeforeEach
    void setUp() {
        // initial QuizServiceImpl
        quizService = new QuizServiceImpl();

        // initial Student object
        student = new Student("Wenhao", "Bao", new Date(1995, Calendar.OCTOBER, 15));

        // simulate questions and answers
        questions = new ArrayList<>();
        answers = new ArrayList<>();

        // add some simulated FreeResponseQuestion and MultipleChoiceQuestion
        questions.add(new FreeResponseQuestion("What is the capital of France?", "Paris"));
        
        questions.add(new MultipleChoiceQuestion("Which are primary colors?", List.of("Red", "Green", "Blue", "Yellow"), Set.of("Red", "Blue", "Yellow")));

        // simulate correct and incorrect answer
        answers.add("Paris");  // correct
        answers.add("Red,Green");  // incorrect
    }

    @Test
    void testGenerateQuiz() {
        // test generate quiz
        List<Question> quizQuestions = quizService.generateQuiz(2);
        
        assertNotNull(quizQuestions, "Quiz questions should not be null.");
        assertEquals(2, quizQuestions.size(), "Quiz should contain 2 questions.");
        assertTrue(quizQuestions.stream().anyMatch(q -> q instanceof FreeResponseQuestion), "Quiz should contain at least one FreeResponseQuestion.");
        assertTrue(quizQuestions.stream().anyMatch(q -> q instanceof MultipleChoiceQuestion), "Quiz should contain at least one MultipleChoiceQuestion.");
    }

    @Test
    void testTakeQuiz() {
        // test take quiz function
        Double score = quizService.takeQuiz(student, questions, answers);

        assertNotNull(score, "Quiz score should not be null.");
        assertEquals(0.5, score, "The student should score 0.5 (50%).");
        assertEquals(2, student.getAttemptedQuestions().size(), "Student should have 2 attempted question.");
        assertEquals(1, student.getIncorrectQuestions().size(), "Student should have 1 incorrect question.");
    }
    
    @Test
    void testRevise() {
        // add another type of question and its incorrect answer  
        questions.add(new FreeResponseQuestion("What is the capital of China?", "Beijing"));
        answers.add("Nanjing");  // incorrect
        
        // simulate that student take quiz and give incorrect answers
        student.addAttemptedQuestion(questions.get(0));
        student.addAttemptedQuestion(questions.get(1));
        student.addAttemptedQuestion(questions.get(2));
        student.addIncorrectQuestion(questions.get(1));
        student.addIncorrectQuestion(questions.get(2));

        Student newStudent = new Student("John", "Doe", new Date(1995,Calendar.NOVEMBER, 15));
        quizService.takeQuiz(newStudent, questions, answers);
        List<Question> revisionQuestions = quizService.revise(newStudent, 2);
        assertNotNull(revisionQuestions, "Revision questions should not be null.");
        assertEquals(2, revisionQuestions.size(), "There should be 2 revision question.");
        assertEquals(List.of(questions.get(1), questions.get(2)), revisionQuestions, "The revision questions should be the one the student got wrong.");
    }
    
    @Test
    void testTakeRevisionAttempt() {
        // simulate a regular quiz first
        quizService.takeQuiz(student, questions, answers);

        // take revision quiz and answer correctly
        answers.set(1, "Red,Blue,Yellow");  // 修改答案为正确答案
        Double score = quizService.takeRevisionAttempt(student, questions, answers);

        assertNotNull(score, "Revision score should not be null.");
        assertEquals(1.0, score, "The student should score 1.0 (100%) on the revision quiz.");
        assertTrue(student.getIncorrectQuestions().isEmpty(), "All incorrect questions should be removed after correct revision.");
    }

    @Test
    void testGenerateStatistics() {
        // simulate a regular quiz
        quizService.takeQuiz(student, questions, answers);

        // get statistics information
        String statistics = quizService.generateStatistics(student);
        assertNotNull(statistics, "Statistics should not be null.");
        assertTrue(statistics.contains("Quiz Attempts: 1"), "Statistics should show 1 quiz attempt.");
        assertTrue(statistics.contains("Quiz Scores: [0.5]"), "Statistics should show a score of 0.5.");
    }

    @Test
    void testReviseWhenStudentCannotTakeRevisionQuiz() {
        // simulate that student pass the quiz
        student.getStatistics().addQuizScores(0.8);

        // the student can not take revision quiz any more since finalVerdict is PASS
        Exception exception = assertThrows(RuntimeException.class, () -> {
            quizService.revise(student, 1);
        });

        assertEquals("This student can not take the revision quiz!", exception.getMessage(), "Expected exception when trying to revise after passing the quiz.");
    }

    @Test
    void testReviseWhenStudentCanTakeRevisionQuiz() {
        // simulate that student fail the quiz
        student.getStatistics().addQuizScores(0.3);

        // the student can take revision quiz 
        List<Question> revisionQuestions = quizService.revise(student, 3);
        assertNotNull(revisionQuestions, "Student should be able to take revision quiz.");
    }

}
