package uk.ac.newcastle.question;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

class QuestionPoolTest {
    @Mock
    Set<Question> questions;
    @InjectMocks
    QuestionPool questionPool;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetQuestionsPool() {
        Set<Question> result = QuestionPool.getQuestionsPool();
        Assertions.assertEquals(Set.of(
                        new FreeResponseQuestion("What's the capital of China?", "Beijing"),
                        new FreeResponseQuestion("What's the capital of the UK?", "London"),
                        new FreeResponseQuestion("What's the capital of the US?", "WashingtonDC"),
                        new FreeResponseQuestion("What's the capital of Canada?", "Ottawa"),
                        new FreeResponseQuestion("What's the capital of Czech?", "Prague"),
                        new FreeResponseQuestion("What's the capital of Australia?", "Canberra"),
                        new FreeResponseQuestion("What's the capital of Korea?", "Seoul"),
                        new FreeResponseQuestion("What's the capital of Japan?", "Tokyo"),
                        new FreeResponseQuestion("What's the capital of France?", "Paris"),
                        new FreeResponseQuestion("What's the capital of Germany?", "Berlin"),
                new MultipleChoiceQuestion( "Which of the following are primary colors?" , List.of("Red,Green,Blue,Yellow") , Set.of("Red,Blue,Yellow")),
                new MultipleChoiceQuestion( "Which of these animals are mammals?" , List.of("Dolphin,Shark,Whale,Crocodile") , Set.of("Dolphin,Whale")),
                new MultipleChoiceQuestion( "Which of the following countries are in Europe?" , List.of("France,Brazil,Germany,Japan") , Set.of("France,Germany")),
                new MultipleChoiceQuestion( "Which of these are renewable energy sources?" , List.of("Solar energy,Coal,Wind energy,Natural gas") , Set.of("Solar energy,Wind energy")),
                new MultipleChoiceQuestion( "Which of the following are parts of a computer?" , List.of("Monitor,Processor,Keyboard,Printer") , Set.of("Monitor,Processor,Keyboard,Printer")),
                new MultipleChoiceQuestion( "Which of these foods are high in protein?" , List.of("Chicken,Rice,Fish,Beans") , Set.of("Chicken,Fish,Beans")),
                new MultipleChoiceQuestion( "Which of the following are programming languages?" , List.of("Python,HTML,Java,SQL") , Set.of("Python,Java,SQL")),
                new MultipleChoiceQuestion( "WWhich of these elements are noble gases?" , List.of("Oxygen,Helium,Neon,Hydrogen") , Set.of("Helium,Neon")),
                new MultipleChoiceQuestion( "Which of the following planets have rings?" , List.of("Mars,Saturn,Jupiter,Uranus") , Set.of("Saturn,Jupiter,Uranus")),
                new MultipleChoiceQuestion( "Which of these are chemical elements?" , List.of("Hydrogen,Water,Oxygen,Carbon") , Set.of("Hydrogen,Oxygen,Carbon"))
                ), result);
    }
}