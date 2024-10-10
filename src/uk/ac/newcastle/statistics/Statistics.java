package uk.ac.newcastle.statistics;

import java.util.ArrayList;
import java.util.List;

/**
 * @classname: Statistics
 * @description: Statistics Javabean
 * @author: Wenhao Bao
 * @date: 2024/10/7 23:45
 */
public class Statistics {
    private int quizAttemptCount;
    private int revisionAttemptCount;
    private List<Double> quizAttemptScores;
    private List<Double> revisionAttemptScores;
    private Verdict finalVerdict;

    public enum Verdict{
        PASS, FAIL, TBD
    }

    public Statistics() {
        this.quizAttemptCount = 0;
        this.revisionAttemptCount = 0;
        this.quizAttemptScores = new ArrayList<>();
        this.revisionAttemptScores = new ArrayList<>();
        this.finalVerdict = Verdict.TBD;
    }
    
    // increase regular quiz count
    public void incrementQuizAttemptCount(){
        this.quizAttemptCount++;
    }
    
    //increase revision quiz count
    public void incrementRevisionAttemptCount(){
        this.revisionAttemptCount++;
    }

    //add regular quiz score
    public void addQuizScores(double score) {
        quizAttemptScores.add(score);
        //update finalVerdict
        updateVerdict();
    }
    
    //add revision quiz count
    public void addRevisionScores(double score){
        revisionAttemptScores.add(score);
    }

    /**
     * @return quizAttemptCount
     */
    public int getQuizAttemptCount() {
        return quizAttemptCount;
    }

    /**
     * @return revisionAttemptCount
     */
    public int getRevisionAttemptCount() {
        return revisionAttemptCount;
    }

    /**
     * @return quizAttemptScores
     */
    public List<Double> getQuizAttemptScores() {
        return quizAttemptScores;
    }

    /**
     * @return revisionAttemptScores
     */
    public List<Double> getRevisionAttemptScores() {
        return revisionAttemptScores;
    }

    /*
    update the verdict according to the scores
        requirement1:A student is considered to have passed the assessment 
        if they score 50% or more on a regular quiz
        requirement2:If the student fails two regular quizzes, 
        they are considered to have failed the assessment.
    */
    private void updateVerdict(){
        long passCount = quizAttemptScores.stream().filter(score -> score >= 0.5).count();
        if (passCount >= 1){
            this.finalVerdict = Verdict.PASS;
        }else if (quizAttemptCount >= 2){
            this.finalVerdict = Verdict.FAIL;
        }
    }
    
    /**
     * @return finalVerdict
     */
    public Verdict getFinalVerdict() {
        return finalVerdict;
    }

    public String toString() {
        return "Statistics{quizAttemptCount = " + quizAttemptCount + ", revisionAttemptCount = " + revisionAttemptCount + ", quizAttemptScores = " + quizAttemptScores + ", revisionAttemptScores = " + revisionAttemptScores + ", finalVerdict = " + finalVerdict + "}";
    }
}
