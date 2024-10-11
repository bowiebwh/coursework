package test.ac.newcastle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ac.newcastle.question.MultipleChoiceQuestion;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MultipleChoiceQuestionTest {

    private MultipleChoiceQuestion mcq;

    @BeforeEach
    void setUp() {
        // initialize MultipleChoiceQuestion example
        List<String> options = List.of("Option 1", "Option 2", "Option 3", "Option 4");
        Set<String> correctAnswers = Set.of("Option 1", "Option 3");
        mcq = new MultipleChoiceQuestion("What are the correct options?", options, correctAnswers);
    }

    @Test
    void testConstructor_InvalidOptionSize() {
        // test exception case, the number of options less than 2
        List<String> options = List.of("Option 1");
        Set<String> correctAnswers = Set.of("Option 1");
        assertThrows(IllegalArgumentException.class, () ->
                new MultipleChoiceQuestion("Test question", options, correctAnswers));

        // test exception case, the number of options more than 4
        List<String> optionsTooMany = List.of("Option 1", "Option 2", "Option 3", "Option 4", "Option 5");
        assertThrows(IllegalArgumentException.class, () ->
                new MultipleChoiceQuestion("Test question", optionsTooMany, correctAnswers));
    }

    @Test
    void testGetFormulation() {
        // test the question formulation and options
        String expectedFormulation = "What are the correct options? Option 1,Option 2,Option 3,Option 4";
        assertEquals(expectedFormulation, mcq.getFormulation());
    }

    @Test
    void testIsCorrectNumber_CorrectAnswer() {
        // test correct answer
        String correctAnswer = "Option 1,Option 3";
        assertTrue(mcq.isCorrectNumber(correctAnswer));
    }

    @Test
    void testIsCorrectNumber_IncorrectAnswer() {
        // test incorrect answer
        String incorrectAnswer = "Option 2,Option 4";
        assertFalse(mcq.isCorrectNumber(incorrectAnswer));
    }

    @Test
    void testIsCorrectNumber_ShuffledAnswer() {
        // test correct answer in different sequence
        String shuffledCorrectAnswer = "Option 3,Option 1";
        assertTrue(mcq.isCorrectNumber(shuffledCorrectAnswer));
    }

    @Test
    void testIsCorrectNumber_CaseInsensitive() {
        // test answer ignore case
        String caseInsensitiveAnswer = "option 1,OPTION 3";
        assertTrue(mcq.isCorrectNumber(caseInsensitiveAnswer));
    }

    @Test
    void testEquals_SameObject() {
        // test equals method with same object
        assertEquals(mcq, mcq);
    }

    @Test
    void testEquals_DifferentObjectSameContent() {
        // test different object but has same content
        List<String> options = List.of("Option 1", "Option 2", "Option 3", "Option 4");
        Set<String> correctAnswers = Set.of("Option 1", "Option 3");
        MultipleChoiceQuestion sameMcq = new MultipleChoiceQuestion("What are the correct options?", options, correctAnswers);
        assertEquals(mcq, sameMcq);
    }

    @Test
    void testEquals_DifferentObjectDifferentContent() {
        // test different object and has different content
        List<String> options = List.of("Option 1", "Option 2", "Option 3", "Option 4");
        Set<String> correctAnswers = Set.of("Option 1", "Option 2");
        MultipleChoiceQuestion differentMcq = new MultipleChoiceQuestion("What are the correct options?", options, correctAnswers);
        assertNotEquals(mcq, differentMcq);
    }

    @Test
    void testHashCode_SameContent() {
        // test the hashcode with same content object
        List<String> options = List.of("Option 1", "Option 2", "Option 3", "Option 4");
        Set<String> correctAnswers = Set.of("Option 1", "Option 3");
        MultipleChoiceQuestion sameMcq = new MultipleChoiceQuestion("What are the correct options?", options, correctAnswers);
        assertEquals(mcq.hashCode(), sameMcq.hashCode());
    }
}
