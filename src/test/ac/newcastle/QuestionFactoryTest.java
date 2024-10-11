package test.ac.newcastle;

import org.junit.jupiter.api.Test;
import uk.ac.newcastle.question.FreeResponseQuestion;
import uk.ac.newcastle.question.MultipleChoiceQuestion;
import uk.ac.newcastle.question.Question;
import uk.ac.newcastle.question.QuestionFactory;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @classname: QuestionFactoryTest
 * @description: Test class for QuestionFactory
 */
public class QuestionFactoryTest {

    @Test
    public void testFreeResponseQuestionCreation() {
        // 测试自由回答问题的创建
        String input = "free_response:What is the capital of France?:Paris";
        Question question = QuestionFactory.getInstance(input);

        assertTrue(question instanceof FreeResponseQuestion, "The question should be a FreeResponseQuestion.");
        assertEquals("What is the capital of France?", question.getFormulation());
        assertTrue(question.isCorrectNumber("Paris"), "The answer should be correct.");
        assertFalse(question.isCorrectNumber("London"), "The answer should be incorrect.");
    }

    @Test
    public void testMultipleChoiceQuestionCreation() {
        // 测试多选问题的创建
        String input = "multiple_choice:Which are primary colors?:Red,Green,Blue,Yellow:Red,Blue,Yellow";
        Question question = QuestionFactory.getInstance(input);

        assertTrue(question instanceof MultipleChoiceQuestion, "The question should be a MultipleChoiceQuestion.");
        assertEquals("Which are primary colors? Red,Green,Blue,Yellow", question.getFormulation());
        assertTrue(question.isCorrectNumber("Red,Blue,Yellow"), "The answer should be correct.");
        assertFalse(question.isCorrectNumber("Red,Green"), "The answer should be incorrect.");
    }

    @Test
    public void testInvalidFreeResponseQuestion() {
        // 测试无效的自由回答问题输入
        String input = "free_response:What is the capital of France?";
        assertThrows(IllegalArgumentException.class, () -> {
            QuestionFactory.getInstance(input);
        }, "An IllegalArgumentException should be thrown for invalid free response question input.");
    }

    @Test
    public void testInvalidMultipleChoiceQuestion() {
        // 测试无效的多选问题输入
        String input = "multiple_choice:Which are primary colors?:Red,Green,Blue";
        assertThrows(IllegalArgumentException.class, () -> {
            QuestionFactory.getInstance(input);
        }, "An IllegalArgumentException should be thrown for invalid multiple-choice question input.");
    }

    @Test
    public void testInvalidFormat() {
        // 测试无效的输入格式
        String input = "invalid_format:This is not a valid format";
        assertThrows(IllegalArgumentException.class, () -> {
            QuestionFactory.getInstance(input);
        }, "An IllegalArgumentException should be thrown for unrecognized format.");
    }
}
