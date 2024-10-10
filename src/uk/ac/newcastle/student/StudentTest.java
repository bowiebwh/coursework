package uk.ac.newcastle.student;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import uk.ac.newcastle.question.FreeResponseQuestion;
import uk.ac.newcastle.question.Question;
import uk.ac.newcastle.statistics.Statistics;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.SimpleTimeZone;

import static org.mockito.Mockito.*;

class StudentTest {
    Student student = new Student("wenhao","bao", Calendar.getInstance().getTime());

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetFullName() {
        String result = student.getFullName();
        Assertions.assertEquals("wenhao bao", result);
    }

}
