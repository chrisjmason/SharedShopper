package login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sharedshopper.chris.sharedshopper.R;

import itemsoverview.ItemOverviewActivity;

public class RegisterFragment extends Fragment implements LoginInterface.View {
    TextView username;
    TextView password;
    TextView passwordConfirm;
    Button register;
    LoginInterface.Presenter presenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_register, container,false);

        username = (TextView) root.findViewById(R.id.usernameregister);
        password = (TextView) root.findViewById(R.id.passwordregister);
        passwordConfirm = (TextView) root.findViewById(R.id.passwordconfirmregister);
        register = (Button) root.findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameText = username.getText().toString();
                String passwordText = password.getText().toString();
                String passwordConfirmText = passwordConfirm.getText().toString();

                presenter.register(usernameText, passwordText);

                //if(usernameText!="" && passwordText!= ""){
                //    if(passwordText == passwordConfirmText) {
                //        presenter.register(usernameText, passwordText);
                //    }
                //}else{
                //    createToast("Please enter username and password");
                //}
            }
        });

        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new LoginPresenter(this);
    }

    public static RegisterFragment newInstance(){
        return new RegisterFragment();
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

