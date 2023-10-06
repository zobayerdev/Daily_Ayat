package com.trodev.dailyayat.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.onesignal.OneSignal;
import com.trodev.dailyayat.R;
import com.trodev.dailyayat.fragments.AyatFragment;
import com.trodev.dailyayat.fragments.HomeFragment;
import com.trodev.dailyayat.fragments.BooksFragment;

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
                } else if (itemId == R.id.bottom_menu_ayat) {
                    loadAyatFragment();
                } else if (itemId == R.id.bottom_menu_wallpaper) {
                    loadWallpaperFragment();
                } else {
                    Toast.makeText(MainActivity.this, "Invalid Click", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }


    private void loadHomeFragment() {
        setTitle("কোরআনের আয়াত");
        HomeFragment homeFragment = new HomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, homeFragment, "homeFragment");
        fragmentTransaction.commit();
    }

    private void loadAyatFragment() {
        setTitle("হাদিস বানী");
        AyatFragment ayatFragment = new AyatFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, ayatFragment, "teacherFragment");
        fragmentTransaction.commit();
    }

    private void loadWallpaperFragment() {
        setTitle("ইসলামিক বুক");
        BooksFragment wallpaperFragment = new BooksFragment();
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
                Toast.makeText(this, "নোটিফিকেশন", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, FullNotificationActivity.class));
                break;

            case R.id.nav_developer:
                final Dialog dialog = new Dialog(this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.developer_bottomsheet_layout);

                dialog.show();
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                dialog.getWindow().setGravity(Gravity.BOTTOM);
                Toast.makeText(this, "ডেভেলপার", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_apps:
                Toast.makeText(this, "আমাদের সকল অ্যাপ", Toast.LENGTH_SHORT).show();
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/dev?id=6580660399707616800")));
                    Toast.makeText(this, "আমাদের সকল অ্যাপ", Toast.LENGTH_SHORT).show();
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/dev?id=6580660399707616800")));
                }
                break;

            case R.id.nav_policy:
                startActivity(new Intent(MainActivity.this, PrivacyPolicyActivity.class));
                Toast.makeText(this, "প্রাইভেসি পলিসি", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}