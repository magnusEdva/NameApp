package hvl.nameapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AddNewStudent extends AppCompatActivity {

    private Button addNewBtn;
    private Button cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_student);

        // Linking Elements in the layout to Java code.
        addNewBtn = (Button) findViewById(R.id.add_button);
        cancelBtn = (Button) findViewById(R.id.cancel_btn);

        // OnClickListener "Add New Student" button
        addNewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Ingen funksjonalitet ennå", Toast.LENGTH_SHORT).show();
            }
        });

        // OnClickListener "Cancel" button
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
