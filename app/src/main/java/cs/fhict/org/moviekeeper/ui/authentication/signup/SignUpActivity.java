package cs.fhict.org.moviekeeper.ui.authentication.signup;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import cs.fhict.org.moviekeeper.R;
import cs.fhict.org.moviekeeper.ui.authentication.signin.SignInActivity;
import cs.fhict.org.moviekeeper.ui.main.MainActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class SignUpActivity extends AppCompatActivity implements SignUpContract.View {

    private SignUpPresenter signUpPresenter;
    private Button btnSignUp;
    private EditText editTextName;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignIn;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnSignUp = (Button) findViewById(R.id.btn_sign_up);
        editTextName = (EditText) findViewById(R.id.editTextTextPersonName);
        editTextEmail = (EditText) findViewById(R.id.editTextTextEmailAddress);
        editTextPassword = (EditText) findViewById(R.id.editTextTextPassword);
        textViewSignIn = (TextView) findViewById(R.id.textViewSignIn);

        signUpPresenter = new SignUpPresenter();
        signUpPresenter.onViewActive(this);

        signUpPresenter.onViewLoad();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signUpPresenter.onSignUp(editTextName.getText().toString(),editTextEmail.getText().toString(),editTextPassword.getText().toString());
            }
        });

        textViewSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUpPresenter.onSignInClicked();
            }
        });



    }

    @Override
    public void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void goToLogInActivity() {
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }

    @Override
    public void updateUI(FirebaseUser user) {

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