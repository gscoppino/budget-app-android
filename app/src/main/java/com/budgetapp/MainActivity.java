package com.budgetapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements
        HomeFragment.OnFragmentInteractionListener,
        AddExpenseFragment.OnFragmentInteractionListener {

    AppBarConfiguration appBarConfiguration;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);

        NavController navController = Navigation.findNavController(this, R.id.main_nav_host_fragment);

        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout);

        appBarConfiguration = new AppBarConfiguration.Builder(R.id.homeFragment)
                .setDrawerLayout(drawerLayout)
                .build();

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        NavigationView navView = findViewById(R.id.nav_view);

        NavigationUI.setupWithNavController(navView, navController);

        Intent intent = getIntent();
        userId = intent.getIntExtra(LandingActivity.LOGIN_USER_ID_KEY, -1);

        Bundle bundle = new Bundle();
        bundle.putInt(HomeFragment.USER_ID_KEY, userId);

        navController.setGraph(R.navigation.main_navigation_graph, bundle);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.main_nav_host_fragment);
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

    public void onListFragmentInteraction(Purchase item) {

    }

    public void onAddExpense(View view) {
        Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_addExpenseFragment);
    }

    public void onSubmitExpense(final View view, final int cost) {
        NewPurchasePayload newPurchasePayload = new NewPurchasePayload();
        newPurchasePayload.setCost(cost);
        ApiServiceSingleton.getInstance().purchaseService
                .createPurchase(this.userId, newPurchasePayload)
                .enqueue(new Callback<Purchase>() {
                    @Override
                    public void onResponse(Call<Purchase> call, Response<Purchase> response) {
                        Bundle bundle = new Bundle();
                        bundle.putInt(HomeFragment.USER_ID_KEY, userId);
                        Navigation.findNavController(view)
                                .navigate(R.id.action_addExpenseFragment_to_homeFragment, bundle);
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {

                    }
                });
    }
}
