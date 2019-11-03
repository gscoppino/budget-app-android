package com.budgetapp.main.addExpense;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.budgetapp.api.ApiServiceSingleton;
import com.budgetapp.api.models.NewPurchasePayload;
import com.budgetapp.api.models.Purchase;
import com.budgetapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddExpenseFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddExpenseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddExpenseFragment extends Fragment {
    public static final String USER_ID_KEY = "USER_ID";

    private int userId;

    private EditText expenseAmountWidget;

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
        void onExpenseItemCreated(final View view);
    }

    public AddExpenseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param userId The user id.
     * @return A new instance of fragment AddExpenseFragment.
     */
    public static AddExpenseFragment newInstance(int userId) {
        AddExpenseFragment fragment = new AddExpenseFragment();
        Bundle args = new Bundle();
        args.putInt(USER_ID_KEY, userId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userId = getArguments().getInt(USER_ID_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_expense, container, false);
        expenseAmountWidget = view.findViewById(R.id.expenseAmountInput);

        view.findViewById(R.id.submitNewExpenseButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) { createNewExpenseItem(view); }
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

    private void createNewExpenseItem(final View view) {
        NewPurchasePayload newPurchasePayload = new NewPurchasePayload();
        newPurchasePayload.setCost(Integer.parseInt(expenseAmountWidget.getText().toString(), 10));
        ApiServiceSingleton.getInstance().purchaseService
                .createPurchase(newPurchasePayload)
                .enqueue(new Callback<Purchase>() {
                    @Override
                    public void onResponse(Call<Purchase> call, Response<Purchase> response) {
                        if (mListener != null) {
                            mListener.onExpenseItemCreated(view);
                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) { }
                });
    }
}
