package com.example.myapplication;

public class OXWrongQuiz {
    private int stageNum;
    private int quizNum;
    private String quiz;
    private int answer;

    public OXWrongQuiz(int stageNum, int quizNum, String quiz, int answer) {
        this.stageNum = stageNum;
        this.quizNum = quizNum;
        this.quiz = quiz;
        this.answer = answer;
    }

    public int getStageNum() {
        return stageNum;
    }

    public int getQuizNum() {
        return quizNum;
    }

    public String getQuiz() {
        return quiz;
    }

    public int getAnswer() {
        return answer;
    }

    public void setStageNum(int stageNum) {
        this.stageNum = stageNum;
    }

    public void setQuizNum(int quizNum) {
        this.quizNum = quizNum;
    }

    public void setQuiz(String quiz) {
        this.quiz = quiz;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

}
