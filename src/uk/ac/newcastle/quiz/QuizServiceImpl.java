package uk.ac.newcastle.quiz;

import uk.ac.newcastle.question.FreeResponseQuestion;
import uk.ac.newcastle.question.MultipleChoiceQuestion;
import uk.ac.newcastle.question.Question;
import uk.ac.newcastle.question.QuestionPool;
import uk.ac.newcastle.statistics.Statistics;
import uk.ac.newcastle.student.Student;

import java.util.*;

/**
 * @classname: QuizServiceImpl
 * @description: implements from interface and execute the logic of generating quiz questions and taking quiz
 * @author: Wenhao Bao
 * @date: 2024/10/7 22:50
 */
public class QuizServiceImpl implements QuizService{
    
    private static final Set<Question> questionsPool = QuestionPool.getQuestionsPool();

    /**
     * @description: generate regular quiz questions
     * @param: int numberOfQuestions param description
     * @return: List<Question> return description
     */
    public List<Question> generateQuiz(int numberOfQuestions){
        //record 2 types
        boolean hasFreeResponse = false;
        boolean hasMultipleChoice = false;

        //shuffle questions in order that random questions
        List<Question> shuffledQuestions = new ArrayList<>(questionsPool);
        Collections.shuffle(shuffledQuestions);
        
        //check the number of questions is whether correct 
        if (shuffledQuestions.size() < numberOfQuestions){
            throw new IllegalStateException("The number of questions is invalid!");
        }
        
        //build a list to save quiz question
        List<Question> quizAttempt = new ArrayList<>();
        for (Question question : shuffledQuestions) {
            if (quizAttempt.size() >= numberOfQuestions){
                break;
            }
            //assure that no duplicate question
            if (!quizAttempt.contains(question)){
                quizAttempt.add(question);
                //check the type of the question
                if (question instanceof FreeResponseQuestion){
                    hasFreeResponse = true;
                }else if (question instanceof MultipleChoiceQuestion){
                    hasMultipleChoice = true;
                }
            }
        }
        
        //if just contain 1 type of question
        if (!hasFreeResponse || !hasMultipleChoice){
            //remove the last question and make room for another type question
            Iterator<Question> iterator = quizAttempt.iterator();
            while (iterator.hasNext()){
                Question question = iterator.next();
                //hasFreeResponse = false and question is multiple choice question == all question is mcq
                //hasMultipleChoice = false and question is free response question == all question is frq
                if ((!hasFreeResponse && question instanceof MultipleChoiceQuestion) ||
                        (!hasMultipleChoice && question instanceof FreeResponseQuestion)){
                    iterator.remove();
                    break;
                }
            }

            //fill the missing question type
            for (Question question : shuffledQuestions) {
                if (quizAttempt.size() >= numberOfQuestions){
                    break;
                }

                //add only 1 type question
                if (!quizAttempt.contains(question)) {
                    if (!hasFreeResponse && question instanceof FreeResponseQuestion) {
                        quizAttempt.add(question);
                        hasFreeResponse = true;
                    } else if (!hasMultipleChoice && question instanceof MultipleChoiceQuestion) {
                        quizAttempt.add(question);
                        hasMultipleChoice = true;
                    }
                }
            }
            
            //if it still can not meet the requirements, throw the exception
            if (!hasFreeResponse || !hasMultipleChoice) {
                throw new IllegalStateException("Not enough questions of each type to generate a valid quiz.");
            }
        }
        
        return quizAttempt;
    }

    /**
     * @description: generate revision quiz questions
     * @param: Student student, int numberOfQuestions
     * @return: List<Question> revisionQuestions
    */
    public List<Question> revise(Student student, int numberOfQuestions){
        if (!studentCanTakeRevisionQuiz(student)){
            throw new RuntimeException("This student can not take the revision quiz!");
        }
        
        Set<Question> attemptedQuestions = student.getAttemptedQuestions();
        Set<Question> incorrectQuestions = student.getIncorrectQuestions();

        //record 2 types
        boolean hasFreeResponse = false;
        boolean hasMultipleChoice = false;
        
        //build a list of questions of revision quiz 
        List<Question> revisionQuestions = new ArrayList<>();

        //add incorrect questions to the list
        for (Question incorrectQuestion : incorrectQuestions) {
            if (revisionQuestions.size() < numberOfQuestions){
                revisionQuestions.add(incorrectQuestion);
                //check the type of the question
                if (incorrectQuestion instanceof FreeResponseQuestion){
                    hasFreeResponse = true;
                }else if (incorrectQuestion instanceof MultipleChoiceQuestion){
                    hasMultipleChoice = true;
                }
            }else {
                break;
            }
        }
        
        //add unattempted questions to the list
        for (Question unAttemptedQuestion : questionsPool) {
            if (!attemptedQuestions.contains(unAttemptedQuestion)){
                if (revisionQuestions.size() < numberOfQuestions){
                    revisionQuestions.add(unAttemptedQuestion);
                    //check the type of the question
                    if (unAttemptedQuestion instanceof FreeResponseQuestion){
                        hasFreeResponse = true;
                    }else if (unAttemptedQuestion instanceof MultipleChoiceQuestion){
                        hasMultipleChoice = true;
                    }
                }else {
                    break;
                }
            }
        }

        //if just contain 1 type of question
        if (!hasFreeResponse || !hasMultipleChoice){
            //remove the last question and make room for another type question
            Iterator<Question> iterator = revisionQuestions.iterator();
            while (iterator.hasNext()){
                Question question = iterator.next();
                //hasFreeResponse = false and question is multiple choice question == all question is mcq
                //hasMultipleChoice = false and question is free response question == all question is frq
                if ((!hasFreeResponse && question instanceof MultipleChoiceQuestion) ||
                        (!hasMultipleChoice && question instanceof FreeResponseQuestion)){
                    iterator.remove();
                    break;
                }
            }

            //fill the missing question type
            for (Question question : revisionQuestions) {
                if (revisionQuestions.size() >= numberOfQuestions){
                    break;
                }

                //add only 1 type question
                if (!revisionQuestions.contains(question)) {
                    if (!hasFreeResponse && question instanceof FreeResponseQuestion) {
                        revisionQuestions.add(question);
                        hasFreeResponse = true;
                    } else if (!hasMultipleChoice && question instanceof MultipleChoiceQuestion) {
                        revisionQuestions.add(question);
                        hasMultipleChoice = true;
                    }
                }
            }

            //if it still can not meet the requirements, throw the exception
            if (!hasFreeResponse || !hasMultipleChoice) {
                throw new IllegalStateException("Not enough questions of each type to generate a valid quiz.");
            }
        }
        
        return revisionQuestions;
    }

    /**
     * @description: take the regular quiz
     * @param: Student student, List<Question> questions, List<String> answers
     * @return: Double score
    */
    public Double takeQuiz(Student student, List<Question> questions, List<String> answers){
        if (student.hasPassed()){
            throw new RuntimeException("This student is passed!");
        }
        
        int correctCount = 0;

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            String answer = answers.get(i);
            
            //record attempted question
            student.addAttemptedQuestion(question);
            
            if (question.isCorrectNumber(answer)){
                correctCount++;
                //if answer is correct, invoke remove method
                student.removeIncorrectQuestion(question);
            }else {
                //if answer is incorrect, add it to the incorrect list
                student.addIncorrectQuestion(question);
            }
        }

        //calculate the score
        double score = (double) correctCount / questions.size();
        
        //update statistics
        //student.getStatistics().incrementQuizAttemptCount();
        student.getStatistics().addQuizScores(score);

        return score;
    }

    /**
     * @description: take the revision quiz
     * @param: Student student, List<Question> questions, List<String> answers
     * @return: Double score
    */
    public Double takeRevisionAttempt(Student student, List<Question> questions, List<String> answers){
        
        int correctCount = 0;

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            String answer = answers.get(i);
            
            student.addAttemptedQuestion(question);
            
            if (question.isCorrectNumber(answer)){
                correctCount++;
                //if answer is correct, invoke remove method
                student.removeIncorrectQuestion(question);
                System.out.println("remove success!");
            }else {
                //if answer is incorrect, add it to the incorrect list
                student.addIncorrectQuestion(question);
            }
        }
        
        //calculate the score
        double score = (double) correctCount / questions.size();

        //update statistics
        student.getStatistics().addRevisionScores(score);

        return score;
    }

    /**
     * @description: generate the statistics of any student
     * @param: Student student
     * @return: String
    */
    public String generateStatistics(Student student){
        Statistics statistics = student.getStatistics();
        
        StringBuilder sb = new StringBuilder();
        sb.append("Statistics for ").append(student.getFullName()).append(":\n");
        sb.append("Quiz Attempts: ").append(statistics.getQuizAttemptCount()).append("\n");
        sb.append("Revision Attempts: ").append(statistics.getRevisionAttemptCount()).append("\n");
        sb.append("Quiz Scores: ").append(statistics.getQuizAttemptScores()).append("\n");
        sb.append("Revision Scores: ").append(statistics.getRevisionAttemptScores()).append("\n");
        sb.append("Final Verdict: ").append(statistics.getFinalVerdict()).append("\n");

        return sb.toString();
    }

    //requirement1:the finalVerdict is whether passed or failed
    //requirement2:the number of revision quiz times can not over 2
    private boolean studentCanTakeRevisionQuiz(Student student){
        if (student.hasPassed() || student.hasFailed() || student.getStatistics().getQuizAttemptCount() == 0){
            return false;
        }
        return student.getStatistics().getRevisionAttemptCount() < 2;
    }
}
