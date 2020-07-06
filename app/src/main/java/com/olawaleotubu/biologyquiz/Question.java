package com.olawaleotubu.biologyquiz;

import android.os.Parcel;
import android.os.Parcelable;

public class Question implements Parcelable {
    private int questNum;
    private String questText, questImg;
    private Answer answer;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    static class Answer {
        private String optA, optB, optC, optD, corrOpt, ansExpl;

        public Answer() {
        }

        public Answer(String optA, String optB, String optC, String optD, String corrOpt, String ansExpl) {
            this.optA = optA;
            this.optB = optB;
            this.optC = optC;
            this.optD = optD;
            this.corrOpt = corrOpt;
            this.ansExpl = ansExpl;
        }

        public String getOptA() {
            return optA;
        }

        public void setOptA(String optA) {
            this.optA = optA;
        }

        public String getOptB() {
            return optB;
        }

        public void setOptB(String optB) {
            this.optB = optB;
        }

        public String getOptC() {
            return optC;
        }

        public void setOptC(String optC) {
            this.optC = optC;
        }

        public String getOptD() {
            return optD;
        }

        public void setOptD(String optD) {
            this.optD = optD;
        }

        public String getCorrOpt() {
            return corrOpt;
        }

        public void setCorrOpt(String corrOpt) {
            this.corrOpt = corrOpt;
        }

        public String getAnsExpl() {
            return ansExpl;
        }

        public void setAnsExpl(String ansExpl) {
            this.ansExpl = ansExpl;
        }
    }

    public Question() {
    }

    public Question(int questNum, String questText, String questImg, Answer answer) {
        this.questNum = questNum;
        this.questText = questText;
        this.questImg = questImg;
        this.answer = answer;
    }

    public int getQuestNum() {
        return questNum;
    }

    public void setQuestNum(int questNum) {
        this.questNum = questNum;
    }

    public String getQuestText() {
        return questText;
    }

    public void setQuestText(String questText) {
        this.questText = questText;
    }

    public String getQuestImg() {
        return questImg;
    }

    public void setQuestImg(String questImg) {
        this.questImg = questImg;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }
}
