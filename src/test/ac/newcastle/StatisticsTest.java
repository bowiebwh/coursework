package test.ac.newcastle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ac.newcastle.statistics.Statistics;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @classname: StatisticsTest
 * @description: Test class for Statistics
 */
public class StatisticsTest {

    private Statistics statistics;

    @BeforeEach
    void setUp() {
        statistics = new Statistics();
    }

    @Test
    void testAddQuizScoresAndGetQuizAttemptCount() {
        // the test count should be 0 initially 
        assertEquals(0, statistics.getQuizAttemptCount(), "Initial quiz attempt count should be 0.");
        
        // add a test score and quiz attempt count should plus 1
        statistics.addQuizScores(0.6);
        assertEquals(1, statistics.getQuizAttemptCount(), "Quiz attempt count should increase to 1 after adding a score.");
        
        // add the second test score and quiz attempt count should plus to 2
        statistics.addQuizScores(0.3);
        assertEquals(2, statistics.getQuizAttemptCount(), "Quiz attempt count should increase to 2 after adding another score.");
    }

    @Test
    void testAddQuizScoresAndGetQuizAttemptScores() {
        // there is no test score in the initial state
        assertTrue(statistics.getQuizAttemptScores().isEmpty(), "Initial quiz attempt scores should be empty.");
        
        // add first time test score
        statistics.addQuizScores(0.6);
        List<Double> scores = statistics.getQuizAttemptScores();
        assertEquals(1, scores.size(), "There should be 1 score after adding one score.");
        assertEquals(0.6, scores.get(0), "The first score should be 0.6.");
        
        // add second time test score
        statistics.addQuizScores(0.3);
        scores = statistics.getQuizAttemptScores();
        assertEquals(2, scores.size(), "There should be 2 scores after adding another score.");
        assertEquals(0.3, scores.get(1), "The second score should be 0.3.");
    }

    @Test
    void testAddRevisionScoresAndGetRevisionAttemptCount() {
        // the initial revision count should be 0
        assertEquals(0, statistics.getRevisionAttemptCount(), "Initial revision attempt count should be 0.");

        // add a revision score and the revision count should plus 1
        statistics.addRevisionScores(0.7);
        assertEquals(1, statistics.getRevisionAttemptCount(), "Revision attempt count should increase to 1 after adding a score.");
    }

    @Test
    void testAddRevisionScoresAndGetRevisionAttemptScores() {
        // the initial revision scores list should be empty
        assertTrue(statistics.getRevisionAttemptScores().isEmpty(), "Initial revision attempt scores should be empty.");

        // add a revision scores
        statistics.addRevisionScores(0.7);
        List<Double> scores = statistics.getRevisionAttemptScores();
        assertEquals(1, scores.size(), "There should be 1 revision score after adding one score.");
        assertEquals(0.7, scores.get(0), "The first revision score should be 0.7.");
    }

    @Test
    void testFinalVerdictPass() {
        // the initial final verdict should be TBD
        assertEquals(Statistics.Verdict.TBD, statistics.getFinalVerdict(), "Initial final verdict should be TBD.");

        // add a pass quiz score and the finalVerdict should be updated to PASS
        statistics.addQuizScores(0.6);
        assertEquals(Statistics.Verdict.PASS, statistics.getFinalVerdict(), "Final verdict should be PASS after passing a quiz.");
    }

    @Test
    void testFinalVerdictFail() {
        // the initial final verdict should be TBD
        assertEquals(Statistics.Verdict.TBD, statistics.getFinalVerdict(), "Initial final verdict should be TBD.");
        
        // add a fail quiz score and the finalVerdict still should be TBD 
        statistics.addQuizScores(0.4);
        assertEquals(Statistics.Verdict.TBD, statistics.getFinalVerdict(), "Final verdict should remain TBD after one failed quiz.");

        // add a fail quiz score again and the finalVerdict should be updated to FAIL
        statistics.addQuizScores(0.3);
        assertEquals(Statistics.Verdict.FAIL, statistics.getFinalVerdict(), "Final verdict should be FAIL after failing two quizzes.");
    }

    @Test
    void testUpdateVerdictWithMultipleScores() {
        // add a fail quiz score and the finalVerdict still should be TBD 
        statistics.addQuizScores(0.2);
        assertEquals(Statistics.Verdict.TBD, statistics.getFinalVerdict(), "Final verdict should be TBD after one failed quiz.");

        // add a pass quiz score and the finalVerdict should be updated to PASS
        statistics.addQuizScores(0.6);
        assertEquals(Statistics.Verdict.PASS, statistics.getFinalVerdict(), "Final verdict should be PASS after passing a quiz.");
    }
}
