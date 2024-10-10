package uk.ac.newcastle.student;

import uk.ac.newcastle.question.Question;
import uk.ac.newcastle.statistics.Statistics;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @classname: Student
 * @description: Student Javabean
 * @author: Wenhao Bao
 * @date: 2024/10/7 23:43
 */
public class Student {
    private final String firstName;
    private final String lastName;
    private final Date dateOfBirth;
    private Statistics statistics;
    
    private Set<Question> attemptedQuestions;
    
    private Set<Question> incorrectQuestions;

    public Student(String firstName, String lastName, Date dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.statistics = new Statistics();
        this.attemptedQuestions = new HashSet<>();
        this.incorrectQuestions = new HashSet<>();
    }

    /**
     * @return fullName
     */
    public String getFullName(){
        return firstName + " " + lastName; 
    }

    /**
     * @return dateOfBirth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @return attemptedQuestions
     */
    public Set<Question> getAttemptedQuestions() {
        return attemptedQuestions;
    }

    //add attempted question
    public void addAttemptedQuestion(Question question){
        attemptedQuestions.add(question);
    }

    /**
     * @return incorrectQuestions
     */
    public Set<Question> getIncorrectQuestions() {
        return incorrectQuestions;
    }
    
    //add incorrect question
    public void addIncorrectQuestion(Question question){
        incorrectQuestions.add(question);
    }
    
    //remove incorrect question if answer correctly in revision attempt
    public void removeIncorrectQuestion(Question question){
        incorrectQuestions.remove(question);
    }

    /**
     * @return statistics
     */
    public Statistics getStatistics() {
        return statistics;
    }
    
    //judge if this student passed or failed
    public boolean hasPassed(){
        return statistics.getFinalVerdict() == Statistics.Verdict.PASS;
    }
    
    public boolean hasFailed(){
        return statistics.getFinalVerdict() == Statistics.Verdict.FAIL;
    }

    /*public String toString() {
        return "Student{firstName = " + firstName + ", lastName = " + lastName + ", dateOfBirth = " + dateOfBirth + "}";
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(firstName, student.firstName) && Objects.equals(lastName, student.lastName) && Objects.equals(dateOfBirth, student.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, dateOfBirth);
    }
}
