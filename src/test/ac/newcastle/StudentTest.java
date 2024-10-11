package test.ac.newcastle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ac.newcastle.question.Question;
import uk.ac.newcastle.statistics.Statistics;
import uk.ac.newcastle.student.Student;

import java.util.Date;
import java.util.Set;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @classname: StudentTest
 * @description: Test class for Student
 */
public class StudentTest {

    private Student student;
    private Student anotherStudent;
    private Statistics statistics;
    private Statistics anotherStatistics;
    private Question mockQuestion1;
    private Question mockQuestion2;
    private Date dateOfBirth;

    @BeforeEach
    void setUp() {
        // use Calendar set up date of birth
        Calendar calendar = Calendar.getInstance();
        calendar.set(1995, Calendar.OCTOBER, 15,0,0,0);
        calendar.set(Calendar.MILLISECOND, 0);
        dateOfBirth = calendar.getTime();

        // build up a Student instance
        student = new Student("Wenhao", "Bao", dateOfBirth);

        anotherStudent = new Student("John", "Doe", dateOfBirth);

        // build 2 Question objects
        mockQuestion1 = new Question() {
            @Override
            public String getFormulation() {
                return "Mock Question 1";
            }

            @Override
            public boolean isCorrectNumber(String answer) {
                return answer.equals("Mock Answer 1");
            }
        };

        mockQuestion2 = new Question() {
            @Override
            public String getFormulation() {
                return "Mock Question 2";
            }

            @Override
            public boolean isCorrectNumber(String answer) {
                return answer.equals("Mock Answer 2");
            }
        };
    }

    @Test
    void testGetFullName() {
        assertEquals("Wenhao Bao", student.getFullName(), "The full name should be Wenhao Bao.");
    }

    @Test
    void testAddAndGetAttemptedQuestions() {
        // test add and acquire attempted questions
        student.addAttemptedQuestion(mockQuestion1);
        Set<Question> attemptedQuestions = student.getAttemptedQuestions();

        assertNotNull(attemptedQuestions, "Attempted questions set should not be null.");
        assertTrue(attemptedQuestions.contains(mockQuestion1), "Attempted questions should contain the added question.");
        assertEquals(1, attemptedQuestions.size(), "Attempted questions should contain 1 question.");
    }

    @Test
    void testAddAndGetIncorrectQuestions() {
        // test add and acquire incorrect questions
        student.addIncorrectQuestion(mockQuestion2);
        Set<Question> incorrectQuestions = student.getIncorrectQuestions();

        assertNotNull(incorrectQuestions, "Incorrect questions set should not be null.");
        assertTrue(incorrectQuestions.contains(mockQuestion2), "Incorrect questions should contain the added question.");
        assertEquals(1, incorrectQuestions.size(), "Incorrect questions should contain 1 question.");
    }

    @Test
    void testRemoveIncorrectQuestion() {
        // test remove incorrect question
        student.addIncorrectQuestion(mockQuestion1);
        student.addIncorrectQuestion(mockQuestion2);

        student.removeIncorrectQuestion(mockQuestion1);
        Set<Question> incorrectQuestions = student.getIncorrectQuestions();

        assertFalse(incorrectQuestions.contains(mockQuestion1), "Incorrect questions should not contain the removed question.");
        assertTrue(incorrectQuestions.contains(mockQuestion2), "Incorrect questions should still contain the other question.");
        assertEquals(1, incorrectQuestions.size(), "Incorrect questions should contain 1 question after removal.");
    }

    @Test
    void testGetStatistics() {
        // test acquire statistics of the student
        statistics = student.getStatistics();
        assertNotNull(statistics, "Statistics should not be null.");
    }

    @Test
    void testHasPassedAndHasFailed() {
        // the updateVerdict method in Statistics class can indirect update the finalVerdict
        statistics = student.getStatistics();

        // simulate passed
        statistics.addQuizScores(0.8);  // assume that scores above 50, set up PASS
        assertTrue(student.hasPassed(), "The student should have passed.");
        assertFalse(student.hasFailed(), "The student should not have failed.");

        // simulate failed
        anotherStatistics = anotherStudent.getStatistics();
        anotherStatistics.addQuizScores(0.3);  // assume that after 2 consecutive failures, set up FAIL
        anotherStatistics.addQuizScores(0.4);  // second failure
        assertFalse(anotherStudent.hasPassed(), "The student should not have passed.");
        assertTrue(anotherStudent.hasFailed(), "The student should have failed.");
    }

    @Test
    void testEqualsAndHashCode() {
        // test equals and hashCode method
        Calendar calendar = Calendar.getInstance();
        calendar.set(1995, Calendar.OCTOBER, 15,0,0,0);
        //important: remove the millisecond portion of the Date object to ensure that consistent time precision
        calendar.set(Calendar.MILLISECOND, 0); 
        Date sameDateOfBirth = calendar.getTime();

        Student sameStudent = new Student("Wenhao", "Bao", sameDateOfBirth);
        Student differentStudent = new Student("John", "Doe", new Date());
        
        assertTrue(student.equals(sameStudent), "Students with the same name and birthdate should be equal.");
        assertNotEquals(student, differentStudent, "Students with different names or birthdates should not be equal.");

        assertEquals(student.hashCode(), sameStudent.hashCode(), "Equal students should have the same hash code.");
        assertNotEquals(student.hashCode(), differentStudent.hashCode(), "Different students should have different hash codes.");
    }
}