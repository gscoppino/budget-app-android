package com.budgetapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.budgetapp.dummy.DummyContent;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements
        LandingFragment.OnFragmentInteractionListener,
        FirstLoginFragment.OnFragmentInteractionListener,
        HomeFragment.OnFragmentInteractionListener,
        AddExpenseFragment.OnFragmentInteractionListener,
        ExpenseFragment.OnListFragmentInteractionListener {

    AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout);

        appBarConfiguration = new AppBarConfiguration.Builder(R.id.homeFragment)
                .setDrawerLayout(drawerLayout)
                .build();

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        NavigationView navView = findViewById(R.id.nav_view);

        NavigationUI.setupWithNavController(navView, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

    public void onLogin(View view) {
        Navigation.findNavController(view).navigate(R.id.action_landingFragment_to_homeFragment);
    }

    public void onRegister(View view) {
        Navigation.findNavController(view).navigate(R.id.action_landingFragment_to_firstLoginFragment);
    }

    public void onBegin(View view) {
        Navigation.findNavController(view).navigate(R.id.action_firstLoginFragment_to_homeFragment);
    }

    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }

    public void onAddExpense(View view) {
        Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_addExpenseFragment2);
    }

    public void onSubmitExpense(View view) {
        Navigation.findNavController(view).navigate(R.id.action_addExpenseFragment_to_homeFragment2);
    }
}
