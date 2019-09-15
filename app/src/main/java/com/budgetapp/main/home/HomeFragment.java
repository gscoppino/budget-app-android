package com.budgetapp.main.home;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.budgetapp.api.models.Purchase;
import com.budgetapp.R;
import com.budgetapp.api.ApiServiceSingleton;
import com.budgetapp.api.models.BudgetMonth;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    public static final String USER_ID_KEY = "USER_ID";
    public static final String USER_SALARY_KEY = "USER_SALARY";

    private int mColumnCount = 1;

    private ExpenseListRecyclerViewAdapter viewAdapter;
    private int userId;
    private int userMonthlySalary;

    private TextView incomeValueLabel;
    private TextView expenseValueLabel;
    private TextView balanceValueLabel;

    private OnFragmentInteractionListener mListener;

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson
     * <a href="http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     * </p>
     */
    public interface OnFragmentInteractionListener {
        void onAddExpense(View view);
        void onListFragmentInteraction(Purchase item);
    }

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param userId The user id.
     * @param userMonthlySalary The user's monthly salary.
     * @return A new instance of fragment HomeFragment.
     */
    public static HomeFragment newInstance(int userId, int userMonthlySalary) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putInt(USER_ID_KEY, userId);
        args.putInt(USER_SALARY_KEY, userMonthlySalary);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userId = getArguments().getInt(USER_ID_KEY);
            userMonthlySalary = getArguments().getInt(USER_SALARY_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        expenseValueLabel = view.findViewById(R.id.expenseValueLabel);
        balanceValueLabel = view.findViewById(R.id.balanceValueLabel);
        incomeValueLabel = view.findViewById(R.id.incomeValueLabel);

        view.findViewById(R.id.floatingActionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { onClickAddExpense(view); }
        });

        // Set the adapter
        RecyclerView recyclerView = view.findViewById(R.id.list);
        Context context = view.getContext();
        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }

        viewAdapter = new ExpenseListRecyclerViewAdapter(new ArrayList<Purchase>(), mListener);
        recyclerView.setAdapter(viewAdapter);

        incomeValueLabel.setText("$" + userMonthlySalary);
        getSummary(userId, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH) + 1);
        MonthPickerView monthPickerView = view.findViewById(R.id.monthPickerView);
        monthPickerView.setOnMonthChangedListener(new MonthPickerView.OnMonthChangeListener() {
            @Override
            public void onSelectedMonthChange(int year, int month) { getSummary(userId, year, month + 1); }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void getSummary(int userId, int year, int month) {
        String sYear = Integer.toString(year);
        String sMonth = Integer.toString(month);
        String monthFormatString = "%1$" + 2 + "s";

        ApiServiceSingleton.getInstance().budgetService
                .getOverviewForMonth(userId, sYear, String.format(monthFormatString, sMonth).replace(' ', '0'))
                .enqueue(new Callback<BudgetMonth>() {
                    @Override
                    public void onResponse(Call<BudgetMonth> call, Response<BudgetMonth> response) { displaySummary(response.body()); }

                    @Override
                    public void onFailure(Call<BudgetMonth> call, Throwable t) { }
                });
    }

    private void displaySummary(BudgetMonth budgetMonth) {
        expenseValueLabel.setText("$" + budgetMonth.getSum());
        balanceValueLabel.setText("$" + (userMonthlySalary - budgetMonth.getSum()));
        if (viewAdapter != null) {
            viewAdapter.setItems(budgetMonth.getPurchaseList());
        }
    }

    private void onClickAddExpense(View view) {
        if (mListener != null) {
            mListener.onAddExpense(view);
        }
    }
}
