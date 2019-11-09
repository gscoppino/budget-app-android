package com.budgetapp.main.updateSalary;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.budgetapp.R;
import com.budgetapp.api.ApiServiceSingleton;
import com.budgetapp.api.models.UpdateSalaryPayload;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UpdateSalaryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link UpdateSalaryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpdateSalaryFragment extends Fragment {
    public static final String USER_ID_KEY = "USER_ID";

    private int userId;

    private EditText salaryAmountWidget;

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
        // TODO: Update argument type and name
        void onUserSalaryUpdated(final View view, int updatedSalary);
    }

    public UpdateSalaryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param userId The user id.
     * @return A new instance of fragment UpdateSalaryFragment.
     */
    public static UpdateSalaryFragment newInstance(int userId) {
        UpdateSalaryFragment fragment = new UpdateSalaryFragment();
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
        View view = inflater.inflate(R.layout.fragment_update_salary, container, false);
        salaryAmountWidget = view.findViewById(R.id.updateSalaryAmountInput);

        view.findViewById(R.id.submitUpdatedSalaryButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) { updateUserSalary(view); }
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

    private void updateUserSalary(final View view) {
        UpdateSalaryPayload updateSalaryPayload = new UpdateSalaryPayload();
        final int updatedSalary = Integer.parseInt(salaryAmountWidget.getText().toString(), 10);
        updateSalaryPayload.setSalary(updatedSalary);
        ApiServiceSingleton.getInstance().salaryService
                .updateSalary(updateSalaryPayload)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> Call, Response<Void> response) {
                        if (mListener != null) {
                            mListener.onUserSalaryUpdated(view, updatedSalary);
                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) { }
                });
    }
}
