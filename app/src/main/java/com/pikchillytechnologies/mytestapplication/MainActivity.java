package com.pikchillytechnologies.mytestapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.service.autofill.UserData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity{

    private List<ExamQuestionModel> m_Question_List;
    private RecyclerView m_RecyclerView_Question_List;
    private ExamQuestionAdapter m_Question_List_Adapter;

    private String url = "https://pikchilly.com/api/exam_question.php";
    private String m_exam_id = "exam_1";

    private StringRequest stringRequest;
    private RequestQueue requestQueue;

    private TextView m_TextView_Question;
    private CheckBox m_TextView_Answer1;
    private CheckBox m_TextView_Answer2;
    private CheckBox m_TextView_Answer3;
    private CheckBox m_TextView_Answer4;
    private Button m_Button_Hindi;
    private Button m_Button_Eng;

    int currentQuestion;

    private ExamQuestionModel examQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_RecyclerView_Question_List = findViewById(R.id.recyclerView_question_List);

        this.m_TextView_Question = findViewById(R.id.textview_Question);
        this.m_TextView_Answer1 = findViewById(R.id.textview_Answer1);
        this.m_TextView_Answer2 = findViewById(R.id.textview_Answer2);
        this.m_TextView_Answer3 = findViewById(R.id.textview_Answer3);
        this.m_TextView_Answer4 = findViewById(R.id.textview_Answer4);
        this.m_Button_Hindi = findViewById(R.id.button_Hindi);
        this.m_Button_Eng = findViewById(R.id.button_Eng);

        Button next = findViewById(R.id.button_Next);
        Button prev = findViewById(R.id.button_Prev);

        m_RecyclerView_Question_List.setHasFixedSize(true);

        RecyclerView.LayoutManager m_Layout_Manager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        m_RecyclerView_Question_List.setLayoutManager(m_Layout_Manager);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(m_RecyclerView_Question_List);

        currentQuestion = 0;

        prepareExamListData();

        m_RecyclerView_Question_List.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), m_RecyclerView_Question_List, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                currentQuestion = position;
                updateUI(currentQuestion);

            }

            @Override
            public void onLongClick(View view, int position) {
                // Nothing
            }
        }));

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(currentQuestion < (m_Question_List.size() - 1)){
                    currentQuestion = currentQuestion+1;
                    updateUI(currentQuestion);
                }

            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(currentQuestion > 0){
                    currentQuestion = currentQuestion-1;
                    updateUI(currentQuestion);

                }
            }
        });

        m_Button_Hindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(examQuestion.getM_Question_Hindi().length() > 0){
                    m_TextView_Question.setText(examQuestion.getM_Question_Hindi());
                }else{
                    m_TextView_Question.setText("NA");
                }
            }
        });

        m_Button_Eng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(examQuestion.getM_Question_Eng().length() > 0){
                    m_TextView_Question.setText(examQuestion.getM_Question_Eng());
                }else{
                    m_TextView_Question.setText("NA");
                }
            }
        });

    }

    public void updateUI(int curQuestion){

        examQuestion = m_Question_List.get(curQuestion);
        m_Question_List_Adapter.row_index = curQuestion;

        examQuestion.setRead(true);

        m_Question_List_Adapter.notifyDataSetChanged();

        Toast.makeText(getApplicationContext(),"hindi:" + examQuestion.getM_Question_Hindi(), Toast.LENGTH_LONG).show();

        m_TextView_Question.setText(examQuestion.getM_Question_Eng());
        m_TextView_Answer1.setText(examQuestion.getM_Answer1_Eng());
        m_TextView_Answer2.setText(examQuestion.getM_Answer2_Eng());
        m_TextView_Answer3.setText(examQuestion.getM_Answer3_Eng());
        m_TextView_Answer4.setText(examQuestion.getM_Answer4_Eng());



    }

    public void prepareExamListData() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //Toast.makeText(getApplicationContext(),"Response:" + response, Toast.LENGTH_LONG).show();

                        prepareJson(response);
                        updateUI(currentQuestion);

                        progressDialog.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occur
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("exam_id", String.valueOf(m_exam_id));
                return params;
            }
        };

        //creating a request queue
        requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(stringRequest);

    }

    public void prepareJson(String response){
        try {

            if(m_Question_List==null){
                m_Question_List = new ArrayList<>();
            }else{
                m_Question_List.clear();
            }

            JSONObject obj = new JSONObject(response);
            JSONArray examArray = obj.getJSONArray("exam_question");

            for (int i = 0; i < examArray.length(); i++) {

                JSONObject examObject = examArray.getJSONObject(i);
                ExamQuestionModel exam = new ExamQuestionModel(String.valueOf(i + 1), examObject.getString("question_id"), examObject.getString("question_eng"), examObject.getString("question_hindi"),examObject.getString("answer1_eng"), examObject.getString("answer2_eng"), examObject.getString("answer3_eng"), examObject.getString("answer4_eng"), false);

                m_Question_List.add(exam);

            }

            if(m_Question_List_Adapter == null){
                m_Question_List_Adapter = new ExamQuestionAdapter(getApplicationContext(),m_Question_List);
                m_RecyclerView_Question_List.setAdapter(m_Question_List_Adapter);
            }else{
                m_Question_List_Adapter.notifyDataSetChanged();
                m_RecyclerView_Question_List.scrollToPosition(0);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
