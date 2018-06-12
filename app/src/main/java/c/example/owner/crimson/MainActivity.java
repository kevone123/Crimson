package c.example.owner.crimson;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.support.v7.widget.Toolbar;

import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private FirebaseAuth mAuth;
    private EditText email;
    private Button button_register;
    private Button button_login;
    private EditText password;
    private Toolbar mToolbar;

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    private SectionPagerAdopter mSectionsPagerAdapter;
    private TabLayout mTabLayout;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        mToolbar=(Toolbar)findViewById(R.id.main_page_toolbar);
       // mToolbar.bringToFront();
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Crimson");

        //How to create Tabs Tabs
        //You make an adapter class with different fragments
        //You connect the tab pagers xml with the objects

        //set the Layout up with the View Pager
       mViewPager=(ViewPager)findViewById(R.id.main_tabPager);
       mSectionsPagerAdapter=new SectionPagerAdopter(getSupportFragmentManager());
       mViewPager.setAdapter(mSectionsPagerAdapter);
       mTabLayout=(TabLayout)findViewById(R.id.main_tabs);
       mTabLayout.setupWithViewPager(mViewPager);
    }
    public void sendtStart(){
        Intent startIntent=new Intent(  MainActivity.this,StartActivity.class);
        startActivity(startIntent);
        finish();
    }
    public void onStart(){
         super.onStart();
         FirebaseUser currentUser=mAuth.getCurrentUser();
         if(currentUser==null){
             sendtStart();

         }

    }

    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);
        if(item.getItemId()==R.id.main_logout_btn){
            FirebaseAuth.getInstance().signOut();
            sendtStart();
        }


        if(item.getItemId()==R.id.main_settings_btn){
        Intent settingIntent=new Intent(MainActivity.this,SettingsActivity.class);
        startActivity(settingIntent);
        }
        return true;
    }






}
