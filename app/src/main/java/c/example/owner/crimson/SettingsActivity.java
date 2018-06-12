package c.example.owner.crimson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SettingsActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private FirebaseUser mCurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        mCurrentUser= FirebaseAuth.getInstance().getCurrentUser();
        String User_ID=mCurrentUser.getUid();
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Users").child(User_ID);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Toast.makeText(SettingsActivity.this,dataSnapshot.toString(),Toast.LENGTH_LONG).show();
                String name=FirebaseDatabase.getInstance().getReference().child("Users").child(User_ID);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
