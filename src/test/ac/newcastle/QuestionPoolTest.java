package test.ac.newcastle;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import uk.ac.newcastle.question.Question;
import uk.ac.newcastle.question.QuestionFactory;
import uk.ac.newcastle.question.QuestionPool;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @classname: QuestionPoolTest
 * @description: Test class for QuestionPool
 * @author: Wenhao Bao
 * @date: 2024/10/07
 */
public class QuestionPoolTest {

    private static Set<Question> questionsPool;

    @BeforeAll
    static void setup() {
        questionsPool = QuestionPool.getQuestionsPool();
    }

    @Test
    void testGetQuestionsPoolSize() {
        // Check if the pool contains the expected number of questions
        assertEquals(20, questionsPool.size(), "Question pool should contain 20 questions.");
    }

    @Test
    void testGetQuestionsPoolNotNull() {
        // Ensure the question pool is not null
        assertNotNull(questionsPool, "The question pool should not be null.");
    }

    @Test
    void testQuestionPoolContent() {
        // Test that specific questions are in the pool
        Question question = QuestionFactory.getInstance(QuestionFactory.FREE_RESPONSE_QUESTION + "What's the capital of the UK?" + ":London");
        assertTrue(questionsPool.contains(question), "The question pool should contain the specified question.");
    }

    @Test
    void testMultipleChoiceQuestionInPool() {
        // Test that a specific multiple-choice question is present in the pool
        Question multipleChoiceQuestion = QuestionFactory.getInstance(QuestionFactory.MULTIPLE_CHOICE_QUESTION
                + "Which of the following are primary colors?" + ":Red,Green,Blue,Yellow" + ":Red,Blue,Yellow");
        assertTrue(questionsPool.contains(multipleChoiceQuestion), "The question pool should contain the specified multiple-choice question.");
    }
}
