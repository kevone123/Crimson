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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import static java.lang.System.err;


public class RegisterActivity extends AppCompatActivity {
    //ALWAYS authenticate
    private FirebaseAuth mAuth;
    private  DatabaseReference mdatabase;

    private TextInputLayout mDisplayName;
    private TextInputLayout mEmail;
    private TextInputLayout mPassword;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private Toolbar mToolbar;
    private Button mCreateBtn;


    //creating a ProgressDialog
    private ProgressDialog mRegProgress;
    protected void onCreate(Bundle savedInstanceState) {

        //do a login check to check if the user is logged in
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //we created fields  we defined in xml
        //we cast fields ,find xml
        mToolbar=(Toolbar)findViewById(R.id.reg_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Create Account");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRegProgress=new ProgressDialog(this);

        mDisplayName=(TextInputLayout)findViewById(R.id.reg_display_n);
        mEmail=(TextInputLayout)findViewById(R.id.reg_em);
        mPassword=(TextInputLayout)findViewById(R.id.reg_pass);
        mCreateBtn=(Button)findViewById(R.id.reg_create_btn);
        //you can call onclick listener on button objects
        mCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //gets edittext from Input layout,get the string and make a string
                String display_name=mDisplayName.getEditText().getText().toString().trim();
                String email=mEmail.getEditText().getText().toString().trim();
                String password=mPassword.getEditText().getText().toString().trim();
                if(!TextUtils.isEmpty(display_name)&&!TextUtils.isEmpty(email)&&!TextUtils.isEmpty(password)){
                    mRegProgress.setTitle("Registering user");
                    mRegProgress.setCanceledOnTouchOutside(false);
                    mRegProgress.show();
                    register_user(display_name,email,password);
                }









            }
        });
        mAuth = FirebaseAuth.getInstance();

    }


    private void register_user(final String display_name, String email, String password){

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //Interacting with realtime firebase/get user
                    FirebaseUser current_user=FirebaseAuth.getInstance().getCurrentUser();
                    String userId=current_user.getUid();


                    // Write a message to the database
                    //Create a user child and a child ID
                    mdatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);
                    //we are addping complex data,Hashmap
                    HashMap<String,String>userMap=new HashMap<>();
                    userMap.put("name",display_name);
                    userMap.put("stats","Hello this is "+display_name+" using Crimson");
                    userMap.put("image","default");
                    userMap.put("thumb_image","default");
                    mdatabase.setValue(userMap);




                    //NEED TO MAKE WIFI FEATURE and make keyboard dynamic

                    mRegProgress.dismiss();
                    Intent mainIntent=new Intent(RegisterActivity.this,MainActivity.class);
                    mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(mainIntent);
                    finish();

               }else{
                    mRegProgress.hide();
                    Toast.makeText (RegisterActivity.this , "Cannot sign in.Please check form", Toast.LENGTH_LONG).show();
               }

            }
        });
    }



}
