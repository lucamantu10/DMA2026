package ro.ase.ie.g1097.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.IntentCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.snackbar.Snackbar;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import ro.ase.ie.g1097.R;
import ro.ase.ie.g1097.model.Movie;

public class ItemActivity extends AppCompatActivity {

    private Button btnSave;
    private int position = -1;   // -1 = insert mode
    private Calendar calendar = Calendar.getInstance();
    private final SimpleDateFormat dateFormat =
            new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_item);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnSave = findViewById(R.id.btnMovieAction);

        Intent intent = getIntent();
        Movie existing = IntentCompat.getSerializableExtra(intent, MainActivity.MOVIE_EXTRA, Movie.class);
        if (existing != null) {
            position = intent.getIntExtra(MainActivity.POSITION_EXTRA, -1);
//            fillForm(existing);
            btnSave.setText("Update Movie");
            setTitle("Edit movie");
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(findViewById(R.id.main), "Respond to MainActivity?", Snackbar.LENGTH_LONG)
                        .setAction("Yes", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Movie movie = null;
                                if (movie == null) return;

                                Intent result = new Intent();
                                result.putExtra(MainActivity.MOVIE_EXTRA, movie);
                                result.putExtra(MainActivity.POSITION_EXTRA, position);
                                setResult(RESULT_OK, result);
                                finish();
                            }
                        })
                        .show();
            }
        });
    }
}