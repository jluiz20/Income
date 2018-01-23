package br.com.jluiz20.baseproject.presentation.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import javax.inject.Inject;

import br.com.jluiz20.baseproject.BaseProjectApplication;
import br.com.jluiz20.baseproject.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author João Luiz Vieira <vieira.jluiz@gmail.com>.
 */
public class MainActivity extends AppCompatActivity implements MainActivityContract.View {
    @Inject
    MainActivityContract.Presenter presenter;
    @BindView(R.id.app_version)
    TextView textView;

    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    @BindView(R.id.message)
    TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        ((BaseProjectApplication) getApplication()).component().inject(this);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onViewResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onViewPause(this);
    }

    @Override
    public void showAppVersion(String appVersion) {
        textView.setText(appVersion);
    }
}
