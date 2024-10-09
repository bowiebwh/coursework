package test.ac.newcastle.question;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uk.ac.newcastle.question.FreeResponseQuestion;
import uk.ac.newcastle.question.MultipleChoiceQuestion;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class FreeResponseQuestionTest {
    FreeResponseQuestion frq = new FreeResponseQuestion(
            "What is the correct answer?",
            "correct answer");

    @Test
    void testGetFormulation() {
        // test the question formulation and options
        String expectedFormulation = "What is the correct answer?";
        assertEquals(expectedFormulation, frq.getFormulation());
    }

    @Test
    void testIsCorrectNumber() {
        boolean result = frq.isCorrectNumber("correct answer");
        assertTrue(result);
    }

    @Test
    void testIsCorrectNumber_IncorrectAnswer() {
        // test incorrect answer
        String incorrectAnswer = "incorrect answer";
        assertFalse(frq.isCorrectNumber(incorrectAnswer));
    }

    @Test
    void testIsCorrectNumber_IntegrateWhitespaces() {
        // test correct answer with multiple joint whitespaces
        String whiteSpacesCorrectAnswer = " correct     answer ";
        assertTrue(frq.isCorrectNumber(whiteSpacesCorrectAnswer));
    }

    @Test
    void testIsCorrectNumber_CaseInsensitive() {
        // test answer ignore case
        String caseInsensitiveAnswer = "coRRect ANSwer";
        assertTrue(frq.isCorrectNumber(caseInsensitiveAnswer));
    }

    @Test
    void testHashCode_SameContent() {
        // test the hashcode with same content object
        String correctAnswer = "correct answer";
        FreeResponseQuestion sameFrq = new FreeResponseQuestion("What is the correct answer?", correctAnswer);
        assertEquals(frq.hashCode(), sameFrq.hashCode());
    }

    @Test
    void testEquals_SameObject() {
        // test equals method with same object
        assertEquals(frq, frq);
    }

    @Test
    void testEquals_DifferentObjectSameContent() {
        // test different object but has same content
        String correctAnswer = "correct answer";
        FreeResponseQuestion sameFrq = new FreeResponseQuestion("What is the correct answer?", correctAnswer);
        assertEquals(frq, sameFrq);
    }

    @Test
    void testEquals_DifferentObjectDifferentContent() {
        // test different object and has different formulation
        String otherAnswer = "other answer";
        FreeResponseQuestion differentFrq = new FreeResponseQuestion("What is the other answer?", otherAnswer);
        assertNotEquals(frq, differentFrq);
    }

}