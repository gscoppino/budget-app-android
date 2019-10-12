package com.budgetapp.landing.registration;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.auth0.android.jwt.Claim;
import com.auth0.android.jwt.JWT;
import com.budgetapp.R;
import com.budgetapp.api.ApiServiceSingleton;
import com.budgetapp.api.models.NewUserPayload;

import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegistrationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegistrationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistrationFragment extends Fragment {
    public static final String USER_USERNAME = "USER_USERNAME";
    public static final String USER_PASSWORD = "USER_PASSWORD";

    private EditText monthlySalaryWidget;

    private String userUsername;
    private String userPassword;

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
        void onUserAuthenticated(View view, int userId, String userUsername, int monthlySalary);
    }

    public RegistrationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param userUsername The user's username.
     * @param userPassword The user's password.
     * @return A new instance of fragment RegistrationFragment.
     */
    public static RegistrationFragment newInstance(String userUsername, String userPassword) {
        RegistrationFragment fragment = new RegistrationFragment();
        Bundle args = new Bundle();
        args.putString(USER_USERNAME, userUsername);
        args.putString(USER_PASSWORD, userPassword);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userUsername = getArguments().getString(USER_USERNAME);
            userPassword = getArguments().getString(USER_PASSWORD);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registration, container, false);

        monthlySalaryWidget = view.findViewById(R.id.appMonthlySalaryInput);

        view.findViewById(R.id.appBeginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { registerUser(view); }
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

    private void registerUser(final View view) {
        NewUserPayload newUserPayload = new NewUserPayload();
        newUserPayload.setUsername(userUsername);
        newUserPayload.setPw(userPassword);
        newUserPayload.setSalary(Integer.parseInt(monthlySalaryWidget.getText().toString(), 10));
        ApiServiceSingleton.getInstance().userService.createUser(newUserPayload).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                final String AUTH_PREFIX = "Bearer ";

                Headers headers = response.headers();
                String authorizationHeader = headers.get("Authorization");

                if (!authorizationHeader.startsWith(AUTH_PREFIX)) {
                    return;
                }

                JWT jwt = new JWT(authorizationHeader.substring(AUTH_PREFIX.length()));

                Claim id = jwt.getClaim("id");
                Claim username = jwt.getClaim("username");
                Claim monthlySalary = jwt.getClaim("monthlysalary");

                if (mListener != null) {
                    mListener.onUserAuthenticated(view, id.asInt(), username.asString(), monthlySalary.asInt());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) { }
        });
    }
}
