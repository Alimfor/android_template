package alim.dev.library;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference("Users");

        EditText fullNameEditText = findViewById(R.id.fullNameEditText);
        EditText ageEditText = findViewById(R.id.ageEditText);
        EditText emailEditText = findViewById(R.id.emailEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);
        Button registerButton = findViewById(R.id.registerButton);
        TextView loginRedirectTextView = findViewById(R.id.loginRedirectTextView);

        registerButton.setOnClickListener(v -> {
            String fullName = fullNameEditText.getText().toString();
            String age = ageEditText.getText().toString();
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if (!fullName.isEmpty() && !age.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        String userId = auth.getCurrentUser().getUid();
                        User user = new User(fullName, age, email);
                        database.child(userId).setValue(user).addOnCompleteListener(dbTask -> {
                            if (dbTask.isSuccessful()) {
                                Toast.makeText(this, "Регистрация успешна", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(this, LoginActivity.class));
                            } else {
                                Toast.makeText(this, "Ошибка сохранения данных", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        Toast.makeText(this, "Ошибка регистрации", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
            }
        });

        loginRedirectTextView.setOnClickListener(v -> startActivity(new Intent(this, LoginActivity.class)));
    }
}
