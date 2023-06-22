package com.example.assone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.preference.PreferenceManager;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    Spinner sp;
    Button btn;
    ImageView img;
    String Courses[] = {"Chemestry", "Math", "Science", "Arabic", "English"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = findViewById(R.id.spinner);
        btn = findViewById(R.id.button2);
        img=findViewById(R.id.recyclerView);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, Courses);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(arrayAdapter);
        RecyclerView recyclerView;
        MainActivity.CourseAdapter adapter;


        // Retrieve the selected course from SharedPreferences
        String selectedCourse = sharedPreferences.getString("selectedCourse", "");
        if (!selectedCourse.isEmpty()) {
            int position = arrayAdapter.getPosition(selectedCourse);
            sp.setSelection(position);
        }
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MainActivity.CourseAdapter(Courses);
        recyclerView.setAdapter(adapter);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedCourse = sp.getSelectedItem().toString();

                // Save the selected course to SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("selectedCourse", selectedCourse);
                editor.apply();

                AlertDialog.Builder alt = new AlertDialog.Builder(MainActivity.this);
                alt.setTitle("THANK'S");
                alt.setCancelable(true);
                alt.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }

                });
                alt.setMessage("your registration is completed");
                alt.show();
            }

        });

    }
    public static class CourseAdapter extends RecyclerView.Adapter<MainActivity.CourseAdapter.ViewHolder> {
        private String[] courses;

        public CourseAdapter(String[] courses) {
            this.courses = courses;
        }

        @NonNull
        @Override
        public MainActivity.CourseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main, parent, false);
            return new MainActivity.CourseAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MainActivity.CourseAdapter.ViewHolder holder, int position) {
            String course = courses[position];
            holder.bind(course);
        }

        @Override
        public int getItemCount() {
            return courses.length;
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            private TextView textViewCourseName;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                textViewCourseName = itemView.findViewById(R.id.textViewCourseName);
            }

            public void bind(String course) {
                textViewCourseName.setText(course);
            }
        }
    }

}
}
