package com.pikchillytechnologies.mytestapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ExamQuestionAdapter extends RecyclerView.Adapter<ExamQuestionAdapter.MyViewHolder> {

    private List<ExamQuestionModel> m_Exam_Question_List;


    public ExamQuestionAdapter(List<ExamQuestionModel> examList){
        this.m_Exam_Question_List = examList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_list_listview,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ExamQuestionModel question = m_Exam_Question_List.get(position);

        holder.m_TextView_Question_Number.setText(question.getM_Question_Number());
        holder.m_TextView_Question_Number.setBackgroundResource(R.drawable.green_circle);
       /* holder.m_TextView_Question.setText(question.getM_Question_Eng());
        holder.m_TextView_Answer1.setText(question.getM_Answer1_Eng());
        holder.m_TextView_Answer2.setText(question.getM_Answer1_Eng());
        holder.m_TextView_Answer3.setText(question.getM_Answer3_Eng());
        holder.m_TextView_Answer4.setText(question.getM_Answer4_Eng());
        */
    }

    @Override
    public int getItemCount() {
        return m_Exam_Question_List.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView m_TextView_Question_Number;
        /*private TextView m_TextView_Question;
        private TextView m_TextView_Answer1;
        private TextView m_TextView_Answer2;
        private TextView m_TextView_Answer3;
        private TextView m_TextView_Answer4;
        */

        public MyViewHolder(View view){
            super(view);

            this.m_TextView_Question_Number = view.findViewById(R.id.textview_Question_Number);
            /*this.m_TextView_Question = view.findViewById(R.id.textview_Question);
            this.m_TextView_Answer1 = view.findViewById(R.id.textview_Answer1);
            this.m_TextView_Answer2 = view.findViewById(R.id.textview_Answer2);
            this.m_TextView_Answer3 = view.findViewById(R.id.textview_Answer3);
            this.m_TextView_Answer4 = view.findViewById(R.id.textview_Answer4);
            */
        }
    }
}
