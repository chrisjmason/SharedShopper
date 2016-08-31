package share;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sharedshopper.chris.sharedshopper.R;

import data.repos.share.ShareRepository;
import data.rx.Interactor;
import data.sharedpref.SharedPrefHelper;
import itemsoverview.ItemOverviewActivity;
import utility.MyApplication;

public class ShareActivity extends Activity implements ShareInterface.View {
    ShareInterface.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        ShareRepository shareRepository = new ShareRepository(new Interactor(), new SharedPrefHelper(MyApplication.getContext()));
        presenter = new SharePresenter(this,shareRepository);

        Button button = (Button) findViewById(R.id.share_button);
        final TextView newCodeView = (TextView) findViewById(R.id.share_code_text);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newCode = newCodeView.getText().toString();
                presenter.updateCode(newCode);
            }
        });
    }

    @Override
    public void codeChanged() {
        Intent intent = new Intent(this, ItemOverviewActivity.class);
        startActivity(intent);
    }

    @Override
    public void codeNotChanged(String error) {
        Toast.makeText(ShareActivity.this, error, Toast.LENGTH_SHORT).show();
    }
}
