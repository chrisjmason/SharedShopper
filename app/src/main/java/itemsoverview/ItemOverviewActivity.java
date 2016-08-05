package itemsoverview;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.sharedshopper.chris.sharedshopper.R;

import data.sharedpref.SharedPrefHelper;
import login.LoginActivity;
import share.ShareActivity;
import utility.MyApplication;

public class ItemOverviewActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_overview);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setStatusBarBackground(R.color.colorPrimaryDark);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if(navigationView!=null){
            drawerContentInit(navigationView);
        }

        if(savedInstanceState==null){
            initFragment(ItemOverviewFragment.newInstance());
        }
    }

    public void initFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.contentFrame,fragment)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void drawerContentInit(NavigationView navigationView){
        final SharedPrefHelper sharedPrefHelper = new SharedPrefHelper(MyApplication.getContext());

        TextView shareCode = (TextView) findViewById(R.id.share_code_text);
        shareCode.setText("Share code: "+sharedPrefHelper.getDataid());

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {

                        switch(item.getItemId()){
                            case R.id.action_changedataid:
                                Intent intent = new Intent(getBaseContext(), ShareActivity.class);
                                startActivity(intent);
                                break;

                            case R.id.action_logout:
                                sharedPrefHelper.clearData();
                                goToLogin();
                                break;

                        }

                        drawerLayout.closeDrawers();
                        return false;
                    }
                }
        );
    }

    private void goToLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        this.startActivity(intent);
    }
}
