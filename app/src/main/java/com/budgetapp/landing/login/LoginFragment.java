package com.budgetapp.landing.login;

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
import com.budgetapp.api.SessionSingleton;
import com.budgetapp.api.models.UserCredentials;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoginFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {
    private EditText usernameWidget;
    private EditText passwordWidget;

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
        void onUserAuthenticated(View view, int userId, String userUsername, int userMonthlySalary);
        void onRegisterUserSelected(View view, String username, String password);
    }

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LoginFragment.
     */
    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_login, container, false);

        usernameWidget = view.findViewById(R.id.appUsernameInput);
        passwordWidget = view.findViewById(R.id.appPasswordInput);

        view.findViewById(R.id.appRegisterButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { onClickRegister(view); }
        });
        view.findViewById(R.id.appLoginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { authenticateUser(view); }
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

    private void onClickRegister(View view) {
        if (mListener != null) {
            String username = usernameWidget.getText().toString();
            String password = passwordWidget.getText().toString();
            mListener.onRegisterUserSelected(view, username, password);
        }
    }

    private void authenticateUser(final View view) {
        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setUsername((usernameWidget.getText().toString()));
        userCredentials.setPw((passwordWidget.getText().toString()));

        ApiServiceSingleton.getInstance().loginService.authenticateUser(userCredentials).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response){
                JWT jwt = SessionSingleton.getInstance().getJwt();
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
