package login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sharedshopper.chris.sharedshopper.R;

import itemsoverview.ItemOverviewActivity;

public class LoginFragment extends Fragment implements LoginInterface.View {
    TextView username;
    TextView password;
    Button login;
    Button register;
    LoginInterface.Presenter presenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container,false);

        username = (TextView) root.findViewById(R.id.username);
        password = (TextView) root.findViewById(R.id.password);
        login = (Button) root.findViewById(R.id.login);
        register = (Button) root.findViewById(R.id.register);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameText = username.getText().toString();
                String passwordText = password.getText().toString();

                if(usernameText!="" && passwordText!= ""){
                    presenter.login(usernameText,passwordText);
                }else{
                    createToast("Please enter username and password");
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity activity = (LoginActivity) getActivity();
                activity.goToRegister();
            }
        });

        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new LoginPresenter(this);
        presenter.checkLogin();
    }

    public static LoginFragment newInstance(){
        return new LoginFragment();
    }

    @Override
    public void loginOk(){
        Intent intent = new Intent(this.getActivity(), ItemOverviewActivity.class);
        startActivity(intent);
    }

    public void createToast(String text){
        Toast.makeText(this.getActivity(), text, Toast.LENGTH_SHORT).show();
    }
}
