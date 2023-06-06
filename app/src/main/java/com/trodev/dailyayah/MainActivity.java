package com.trodev.dailyayah;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.onesignal.OneSignal;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    /*import all package*/
    private BottomNavigationView bottomNavigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private long pressedTime;

    private static final String ONESIGNAL_APP_ID = "6c25722b-8dba-4e32-b0d2-747f07c52f10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigation_view);

        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);

        // promptForPushNotifications will show the native Android notification permission prompt.
        // We recommend removing the following code and instead using an In-App Message to prompt for notification permission (See step 7)
        OneSignal.promptForPushNotifications();


        // #######################
        // Drawer Layout implement
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.start, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // #################################################################
        // navigation view work process
        navigationView.setNavigationItemSelectedListener(this::onOptionsItemSelected);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        loadHomeFragment();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId();
                if (itemId == R.id.bottom_menu_home) {
                    loadHomeFragment();
                }  else if (itemId == R.id.bottom_menu_ayat) {
                    loadAyatFragment();
                } else if (itemId == R.id.bottom_menu_wallpaper) {
                    loadWallpaperFragment();
                }else {
                    Toast.makeText(MainActivity.this, "Invalid Click", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }


    private void loadHomeFragment() {
        setTitle("Daily Ayah");
        HomeFragment homeFragment = new HomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, homeFragment, "homeFragment");
        fragmentTransaction.commit();
    }

    private void loadAyatFragment() {
        setTitle("Ayat");
        AyatFragment ayatFragment = new AyatFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, ayatFragment, "teacherFragment");
        fragmentTransaction.commit();
    }

    private void loadWallpaperFragment() {
        setTitle("Islamic Wallpaper");
        WallpaperFragment wallpaperFragment = new WallpaperFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, wallpaperFragment, "aboutFragment");
        fragmentTransaction.commit();
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.nav_notification:
                // startActivity(new Intent(MainActivity.this, AdminActivity.class));
                Toast.makeText(this, "Notification", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, FullNotificationActivity.class));
                break;

            case R.id.nav_developer:
                // startActivity(new Intent(MainActivity.this, AdminActivity.class));
                Toast.makeText(this, "Developer", Toast.LENGTH_SHORT).show();
                // startActivity(new Intent(MainActivity.this, MakeTeacherActivity.class));
                break;

            case R.id.nav_policy:
                Toast.makeText(this, "Privacy Policy", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}