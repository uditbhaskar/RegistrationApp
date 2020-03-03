package com.example.firebasetutuorial.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebasetutuorial.R;
import com.example.firebasetutuorial.services.model.StudentReg;
import com.example.firebasetutuorial.view.ui.StudentListActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class StudentRegAdapter extends RecyclerView.Adapter<StudentRegAdapter.StudentRegHolder> {


    private List<StudentReg> studentRegList = new ArrayList<>();
    private Context context;

    public StudentRegAdapter(Context context) {
        this.context = context;
    }

    public void setItems(List<StudentReg> studentRegList) {
        this.studentRegList = studentRegList;
        Log.d("check", "setItems called");
        Collections.sort(studentRegList, new Comparator<StudentReg>() {
            public int compare(StudentReg o1, StudentReg o2) {
                if (o1.getTimeStamp() == null || o2.getTimeStamp() == null)
                    return 0;
                return Long.valueOf(o1.getTimeStamp()).compareTo(Long.valueOf(o2.getTimeStamp()));
            }
        });
        Collections.reverse(this.studentRegList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StudentRegHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.register_item_view, parent, false);

        return new StudentRegHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull StudentRegHolder holder, final int position) {
        final StudentReg regData = studentRegList.get(position);
        String name = studentRegList.get(position).getStudent().getName();
        String id = studentRegList.get(position).getId();
        String roll = studentRegList.get(position).getStudent().getRoll();
        holder.tv_studentName.setText("NAME: " + name);
        holder.tv_studentId.setText("ID: " + id);
        holder.tv_studentRoll.setText("ROLL NO: " + roll);

        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((StudentListActivity) context).deletePost(regData.getId(), studentRegList.get(position).getTimeStamp());
            }
        });
        holder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    @Override
    public int getItemCount() {
        return studentRegList.size();
    }

    public class StudentRegHolder extends RecyclerView.ViewHolder {
        TextView tv_studentName;
        TextView tv_studentId;
        TextView tv_studentRoll;
        ImageView btn_delete;
        ImageView btn_edit;

        public StudentRegHolder(@NonNull View itemView) {
            super(itemView);
            tv_studentId = itemView.findViewById(R.id.tv_studentId);
            tv_studentName = itemView.findViewById(R.id.tv_studentName);
            tv_studentRoll = itemView.findViewById(R.id.tv_studentRoll);
            btn_delete = itemView.findViewById(R.id.btn_delete);
            btn_edit = itemView.findViewById(R.id.btn_edit);
        }
    }
}
