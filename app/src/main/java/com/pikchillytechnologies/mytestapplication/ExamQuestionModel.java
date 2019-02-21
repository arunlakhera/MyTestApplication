package com.pikchillytechnologies.mytestapplication;

import com.android.volley.toolbox.StringRequest;

public class ExamQuestionModel {

    private String m_Question_Number;
    private String m_Question_Id;
    private String m_Question_Eng;
    private String m_Answer1_Eng;
    private String m_Answer2_Eng;
    private String m_Answer3_Eng;
    private String m_Answer4_Eng;


    public ExamQuestionModel(String questionNumber,String question_id, String question_eng, String answer1, String answer2,String answer3,String answer4){
        this.m_Question_Number = questionNumber;
        this.m_Question_Id = question_id;
        this.m_Question_Eng = question_eng;
        this.m_Answer1_Eng = answer1;
        this.m_Answer2_Eng = answer2;
        this.m_Answer3_Eng = answer3;
        this.m_Answer4_Eng = answer4;

    }

    public String getM_Question_Number() {
        return m_Question_Number;
    }

    public void setM_Question_Number(String m_Question_Number) {
        this.m_Question_Number = m_Question_Number;
    }

    public String getM_Question_Id() {
        return m_Question_Id;
    }

    public void setM_Question_Id(String m_Question_Id) {
        this.m_Question_Id = m_Question_Id;
    }

    public String getM_Question_Eng() {
        return m_Question_Eng;
    }

    public void setM_Question_Eng(String m_Question_Eng) {
        this.m_Question_Eng = m_Question_Eng;
    }


    public String getM_Answer1_Eng() {
        return m_Answer1_Eng;
    }

    public void setM_Answer1_Eng(String m_Answer1_Eng) {
        this.m_Answer1_Eng = m_Answer1_Eng;
    }

    public String getM_Answer2_Eng() {
        return m_Answer2_Eng;
    }

    public void setM_Answer2_Eng(String m_Answer2_Eng) {
        this.m_Answer2_Eng = m_Answer2_Eng;
    }

    public String getM_Answer3_Eng() {
        return m_Answer3_Eng;
    }

    public void setM_Answer3_Eng(String m_Answer3_Eng) {
        this.m_Answer3_Eng = m_Answer3_Eng;
    }

    public String getM_Answer4_Eng() {
        return m_Answer4_Eng;
    }

    public void setM_Answer4_Eng(String m_Answer4_Eng) {
        this.m_Answer4_Eng = m_Answer4_Eng;
    }

}
