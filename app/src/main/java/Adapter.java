import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assone.R;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private String[] courses;

    public Adapter(String[] courses) {
        this.courses = courses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String course = courses[position];
        holder.bind(course);
    }

    @Override
    public int getItemCount() {
        return courses.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView courseNameTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            courseNameTextView = itemView.findViewById(R.id.textViewCourseName);
        }

        public void bind(String course) {
            courseNameTextView.setText(course);
        }
    }
}
