package c.example.owner.crimson;


import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static java.lang.System.err;


public class LoginActivity extends AppCompatActivity {
    //ALWAYS authenticate
    private FirebaseAuth mAuth;

    private TextInputLayout mEmail;
    private TextInputLayout mPassword;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private Toolbar mToolbar;
    private Button mCreateBtn;


    //creating a ProgressDialog
    private ProgressDialog mLogProgress;


    protected void onCreate(Bundle savedInstanceState) {

        //do a login check to check if the user is logged in
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //we created fields  we defined in xml
        //we cast fields ,find xml

        mToolbar = (Toolbar) findViewById(R.id.reg_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mLogProgress = new ProgressDialog(this);

        mEmail = (TextInputLayout) findViewById(R.id.reg_em);
        mPassword = (TextInputLayout) findViewById(R.id.reg_pass);
        mCreateBtn = (Button) findViewById(R.id.reg_create_btn);
        //you can call onclick listener on button objects
        mCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //gets edittext from Input layout,get the string and make a string

                String email = mEmail.getEditText().getText().toString().trim();
                String password = mPassword.getEditText().getText().toString().trim();
                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
                    mLogProgress.setTitle("Login in user");
                    mLogProgress.setCanceledOnTouchOutside(false);
                    mLogProgress.show();
                    loginUser(email, password);

                }


            }


        });
        mAuth = FirebaseAuth.getInstance();


    }

    private void loginUser(String email,String password){

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //NEED TO MAKE WIFI FEATURE and make keyboard dynamic
                    mLogProgress.dismiss();
                    Intent mainIntent=new Intent(LoginActivity.this,MainActivity.class);
                    mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(mainIntent);
                    finish();

                }else{
                    mLogProgress.hide();
                    Toast.makeText (LoginActivity.this , "Cannot sign in.Please check form", Toast.LENGTH_LONG).show();
                }

            }
        });
    }


}


