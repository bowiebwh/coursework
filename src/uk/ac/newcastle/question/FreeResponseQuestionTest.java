package uk.ac.newcastle.question;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FreeResponseQuestionTest {
    FreeResponseQuestion freeResponseQuestion = new FreeResponseQuestion("formulation", "correctAnswer");

    @Test
    void testIsCorrectNumber() {
        boolean result = freeResponseQuestion.isCorrectNumber("answer");
        Assertions.assertEquals(true, result);
    }

    @Test
    void testHashCode() {
        int result = freeResponseQuestion.hashCode();
        Assertions.assertEquals(0, result);
    }

    @Test
    void testEquals() {
        boolean result = freeResponseQuestion.equals("obj");
        Assertions.assertEquals(true, result);
    }

    @Test
    void testToString() {
        String result = freeResponseQuestion.toString();
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme