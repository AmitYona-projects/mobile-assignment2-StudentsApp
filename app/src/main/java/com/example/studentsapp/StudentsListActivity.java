package com.example.studentsapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

public class StudentsListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private StudentAdapter adapter;
    private StudentsRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_list);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.students_list);
        }

        repository = StudentsRepository.getInstance();

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        
        List<Student> students = repository.getAllStudents();
        adapter = new StudentAdapter(students);
        recyclerView.setAdapter(adapter);

        adapter.setOnStudentClickListener(student -> {
            Intent intent = new Intent(StudentsListActivity.this, StudentDetailsActivity.class);
            intent.putExtra("student_id", student.getId());
            startActivity(intent);
        });

        adapter.setOnCheckBoxClickListener(student -> {
            repository.toggleCheckStatus(student.getId());
            adapter.updateStudents(repository.getAllStudents());
        });

        FloatingActionButton fab = findViewById(R.id.fab_add_student);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(StudentsListActivity.this, NewStudentActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh the list when returning to this activity
        adapter.updateStudents(repository.getAllStudents());
    }
}
