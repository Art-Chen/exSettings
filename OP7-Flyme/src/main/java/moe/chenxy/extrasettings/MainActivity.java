package moe.chenxy.extrasettings;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity{

    @InjectView(R.id.toolbar_preference)
    Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        initToolbar();
        getFragmentManager().beginTransaction().replace(R.id.content_frame, new SettingFragment()).commit();
    }

    /**
     * 初始化Toolbar
     */
    private void initToolbar() {
        if (mToolbar == null)
            mToolbar = findViewById(R.id.toolbar_preference);
        mToolbar.setTitle(getResources().getString(R.string.app_name));
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_left_back);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * 选项菜单
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return true;
    }
}
