package cs.fhict.org.moviekeeper.ui.authentication.signin;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import cs.fhict.org.moviekeeper.R;
import cs.fhict.org.moviekeeper.ui.authentication.signup.SignUpActivity;
import cs.fhict.org.moviekeeper.ui.main.MainActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class SignInActivity extends AppCompatActivity implements SignInContract.View {

    private Button btnSignIn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignUp;

    private SignInPresenter signInPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        btnSignIn = findViewById(R.id.btnSignIn);
        editTextEmail = findViewById(R.id.editTextTextEmailAddress2) ;
        editTextPassword = findViewById(R.id.editTextTextPassword2);
        textViewSignUp = findViewById(R.id.textViewSignUp);

        signInPresenter = new SignInPresenter();
        signInPresenter.onViewActive(this);
        signInPresenter.onViewLoaded();

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInPresenter.onLogIn(editTextEmail.getText().toString(),editTextPassword.getText().toString());
            }
        });

        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInPresenter.onSignUpClicked();
            }
        });


    }

    @Override
    public void goToSignUpActivity() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    @Override
    public void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void setTitleToolbar(int resId) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_2);
        toolbar.setTitle(resId);
        toolbar.setTitleTextAppearance(this,R.style.RobotoTitleStyle);
    }

    @Override
    public void ShowToast(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }
}