package uk.ac.newcastle;

import uk.ac.newcastle.question.Question;
import uk.ac.newcastle.quiz.QuizService;
import uk.ac.newcastle.quiz.QuizServiceImpl;
import uk.ac.newcastle.student.Student;

import java.util.*;

/**
 * @classname: MyApplication
 * @description: Test function
 * @author: Wenhao Bao
 * @date: 2024/10/8 1:59
 */
public class MyApplication {
    public static void main(String[] args) {
        Student student = new Student("Wenhao", "Bao", new Date(1995, Calendar.NOVEMBER, 15));
        QuizService quiz = new QuizServiceImpl();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            List<String> answers = new ArrayList<>();
            if (student.hasPassed() || student.hasFailed()) {
                break;
            }
            System.out.println("1.regular quiz 2.revision quiz");
            int quizType = scanner.nextInt();
            // Consume the leftover newline
            scanner.nextLine();
            
            if (quizType == 1) {
                List<Question> attemptedQuestions = quiz.generateQuiz(5);
                for (Question question : attemptedQuestions) {
                    System.out.println(question.getFormulation());
                    System.out.print("text your answer:");
                    String answer = scanner.nextLine();
                    answers.add(answer);
                }

                Double score = quiz.takeQuiz(student, attemptedQuestions, answers);
                System.out.println("Your this regular quiz score is:" + score);

                String statistics = quiz.generateStatistics(student);
                System.out.println(statistics);
            } else if (quizType == 2) {
                List<Question> revisionQuestions;
                try {
                    revisionQuestions = quiz.revise(student, 5);
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }
                for (Question question : revisionQuestions) {
                    System.out.println(question.getFormulation());
                    System.out.print("text your answer:");
                    String answer = scanner.nextLine();
                    answers.add(answer);
                }

                Double score = quiz.takeRevisionAttempt(student, revisionQuestions, answers);
                System.out.println("Your this revision quiz score is:" + score);

                String statistics = quiz.generateStatistics(student);
                System.out.println(statistics);

            }
        }
        
        /*Set<String> a = Set.of("a,B,c".toLowerCase().split(","));
        Set<String> b = new HashSet<>();
        b.add("A");
        b.add("B");
        b.add("C");
        
        List<String> correctAnswersList = a.stream()
                .map(String::trim)
                .map(String::toLowerCase)
                .sorted().toList();

        List<String> answersList = a.stream()
                .map(String::trim)
                .map(String::toLowerCase)
                .sorted().toList();
        
        System.out.println(correctAnswersList.equals(answersList));*/
    }
}
