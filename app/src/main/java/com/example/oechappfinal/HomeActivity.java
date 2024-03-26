package com.example.oechappfinal;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class HomeActivity extends AppCompatActivity{
    BottomNavigationView bottomNavigationView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().hide();


        replaceFragment(new HomeFragment());


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isFirstLaunch", false);
        editor.apply();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);



        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int menuItemId =  item.getItemId();

                if (menuItemId ==  R.id.home) {
                    replaceFragment(new HomeFragment());
                    return true;
                }
                else if (menuItemId ==  R.id.wallet) {
                    replaceFragment(new WalletFragment());
                    return true;
                }
                else if (menuItemId ==  R.id.track) {
                    replaceFragment(new TrackFragment());
                    return true;
                }
                else if (menuItemId ==  R.id.profile) {
                    replaceFragment(new ProfileFragment());
                    return true;
                }


                return true;
            }
        });


    }



    private  void replaceFragment (Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.MainFragment, fragment).commit();



    }
    public void showToolbar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().show();
        }
    }

    // Метод для скрытия Toolbar
    public void hideToolbar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

}



