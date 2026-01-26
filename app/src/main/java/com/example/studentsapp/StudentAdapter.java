package com.example.studentsapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
    private List<Student> students;
    private OnStudentClickListener clickListener;
    private OnCheckBoxClickListener checkBoxClickListener;

    public interface OnStudentClickListener {
        void onStudentClick(Student student);
    }

    public interface OnCheckBoxClickListener {
        void onCheckBoxClick(Student student);
    }

    public StudentAdapter(List<Student> students) {
        this.students = students;
    }

    public void setOnStudentClickListener(OnStudentClickListener listener) {
        this.clickListener = listener;
    }

    public void setOnCheckBoxClickListener(OnCheckBoxClickListener listener) {
        this.checkBoxClickListener = listener;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = students.get(position);
        holder.bind(student);
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public void updateStudents(List<Student> newStudents) {
        this.students = newStudents;
        notifyDataSetChanged();
    }

    class StudentViewHolder extends RecyclerView.ViewHolder {
        private ImageView studentPic;
        private TextView studentName;
        private TextView studentId;
        private CheckBox checkBox;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            studentPic = itemView.findViewById(R.id.student_pic);
            studentName = itemView.findViewById(R.id.student_name);
            studentId = itemView.findViewById(R.id.student_id);
            checkBox = itemView.findViewById(R.id.checkbox);

            itemView.setOnClickListener(v -> {
                if (clickListener != null) {
                    clickListener.onStudentClick(students.get(getAdapterPosition()));
                }
            });

            checkBox.setOnClickListener(v -> {
                if (checkBoxClickListener != null) {
                    checkBoxClickListener.onCheckBoxClick(students.get(getAdapterPosition()));
                }
            });
        }

        public void bind(Student student) {
            studentName.setText(student.getName());
            studentId.setText(student.getId());
            checkBox.setChecked(student.isChecked());
        }
    }
}
