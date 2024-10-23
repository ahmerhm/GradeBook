package Gradebook;

public class Student {

    private String name;
    private String letterGrade;
    private double percentage;
    private double homeworks;
    private double quizzes;
    private double midTerm1;
    private double midTerm2;
    private double midTerm3;
    private double finalTest;
    private String[] possibleGrades = {"A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "F"};

     public Student() {
        this.name = "";
        this.letterGrade = "n/a";
        this.percentage = -1;
        this.homeworks = -1;
        this.quizzes = -1;
        this.midTerm1 = -1;
        this.midTerm2 = -1;
        this.midTerm3 = -1;
        this.finalTest= -1;
    }

    public Student(String name, String letterGrade, double percentage, double homeworks, double quizzes, double midTerm1, double midTerm2, double midTerm3, double finalTest) {
        if(name.length() > 0) {
            this.name = name;
        }
        for(int i = 0; i < possibleGrades.length; i++) {
            if(letterGrade.equals(possibleGrades[i])) {
                this.letterGrade = letterGrade;
            }
        }  // all these numbers cannot be negative (except -1 which means that the assignment was not submitted)
        if(percentage >= -1  && percentage <= 100)
            this.percentage = percentage;
        if(homeworks >= -1 && homeworks <= 100)
            this.homeworks = homeworks;
        if(quizzes >= -1 && quizzes <= 100)
            this.quizzes = quizzes;
        if(midTerm1 >= -1 && midTerm1 <= 100)
            this.midTerm1 = midTerm1;
        if(midTerm2 >= -1 && midTerm2 <= 100)
            this.midTerm2 = midTerm2;
        if(midTerm3 >= -1 && midTerm3 <= 100)
            this.midTerm3 = midTerm3;
        if(finalTest >= -1 && finalTest <= 100)
            this.finalTest = finalTest;
            
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name.length() > 0) {
            this.name = name;
        }
    }

    public String getLetterGrade() {
        return letterGrade;
    }

    public void setLetterGrade(String letterGrade) {
        for(int i = 0; i < possibleGrades.length; i++) {
            if(letterGrade.equals(possibleGrades[i])) {
                this.letterGrade = letterGrade;
            }
        }
    }

    public double getPercentage() {
        return percentage;
    }

    public void setFinalGrade(double percentage) {
        this.percentage = percentage;
    }

    public double getHomeworks() {
        return homeworks;
    }

    public void setHomeworks(double homeworks) {
        if(homeworks >= -1 && homeworks <= 100)
            this.homeworks = homeworks;
    }

    public double getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(double quizzes) {
        if(quizzes >= -1 && quizzes <= 100)
            this.quizzes = quizzes;
    }

    public double getMidTerm1() {
        return midTerm1;
    }

    public void setMidTerm1(double midTerm1) {
        if(midTerm1 >= -1 && midTerm1 <= 100)
            this.midTerm1 = midTerm1;
    }

    public double getMidTerm2() {
        return midTerm2;
    }

    public void setMidTerm2(double midTerm2) {
        if(midTerm2 >= -1 && midTerm2 <= 100)
            this.midTerm2 = midTerm2;
    }

    public double getMidTerm3() {
        return midTerm3;
    }

    public void setMidTerm3(double midTerm3) {
        if(midTerm3 >= -1 && midTerm3 <= 100)
            this.midTerm3 = midTerm3;
    }

    public double getFinalTest() {
        return finalTest;
    }

    public void setFinalTest(double finalTest) {
        if(finalTest >= -1 && finalTest <= 100)
            this.finalTest = finalTest;
    }

    public String toString() {
        return "Name: " + name + "\t" + "Grade: " + letterGrade + "\t" + "% " + "\t" + "Hoemworks: "  + homeworks + "\t" + "Quizzes: " + quizzes + "\t" +
        "Midterm 1: " + midTerm1 + "\t" + "Midterm 2: " + midTerm2 + "\t" + "Midterm 3: " + midTerm3 + "\t" + "Final: " + finalTest;
    }

}
