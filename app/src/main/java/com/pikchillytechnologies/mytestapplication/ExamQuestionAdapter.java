package com.pikchillytechnologies.mytestapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ExamQuestionAdapter extends RecyclerView.Adapter<ExamQuestionAdapter.MyViewHolder> {

    private List<ExamQuestionModel> m_Exam_Question_List;
    private Context context;

    public ExamQuestionAdapter(Context context, List<ExamQuestionModel> examList){
        this.context = context;
        this.m_Exam_Question_List = examList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_list_listview,parent,false);
        return new MyViewHolder(context,itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ExamQuestionModel question = m_Exam_Question_List.get(position);

        holder.m_TextView_Question_Number.setText(question.getM_Question_Number());
        holder.m_TextView_Question_Number.setBackgroundResource(R.drawable.black_circle);

    }

    @Override
    public int getItemCount() {
        return m_Exam_Question_List.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView m_TextView_Question_Number;
        private Context context;
        ExamQuestionModel exam;

        public MyViewHolder(Context context, View view){
            super(view);

            this.context = context;
            this.m_TextView_Question_Number = view.findViewById(R.id.textview_Question_Number);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            int position = getAdapterPosition();

            if(position != RecyclerView.NO_POSITION){
                 exam = m_Exam_Question_List.get(position);
                 m_TextView_Question_Number.setBackgroundResource(R.drawable.green_circle);
                 notifyItemChanged(position);
            }
        }
    }

}
