package com.example.smartcity.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.smartcity.R;
import com.example.smartcity.fragment.DangjianFragment;
import com.example.smartcity.util.PrefStore;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnCheckedChangeListener {

    private ViewPager main_viewPager;
    private RadioGroup main_tab_RadioGroup;
    private RadioButton radio_home, radio_service,
            radio_enviroment, radio_news, radio_me;
    private ArrayList<Fragment> fragmentList;
    private TextView t;

    private int[] radio_ids = new int[]{R.id.radio_home, R.id.radio_service, R.id.radio_enviroment, R.id.radio_news, R.id.radio_me};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.nobar);
        setContentView(R.layout.activity_main);
        t = findViewById(R.id.headerTitle);

        InitView();
        InitViewPager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //重写onMenuOpened()，通过反射使其图标可见
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (menu != null) {
            if (menu.getClass().getSimpleName().equalsIgnoreCase("MenuBuilder")) {
                try {
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            //case Menu.FIRST:
            case R.id.index:
                Toast.makeText(MainActivity.this, "点击主页菜单", Toast.LENGTH_SHORT).show();
                break;
            //case Menu.FIRST+1:
            case R.id.weather:
                Toast.makeText(MainActivity.this, "点击天气菜单", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void InitView() {
        main_tab_RadioGroup = findViewById(R.id.main_tab_RadioGroup);
        radio_home = findViewById(R.id.radio_home);
        radio_service = findViewById(R.id.radio_service);
        radio_enviroment = findViewById(R.id.radio_enviroment);
        radio_news = findViewById(R.id.radio_news);
        radio_me = findViewById(R.id.radio_me);
        main_tab_RadioGroup.check(R.id.radio_home);
        main_tab_RadioGroup.setOnCheckedChangeListener(this);//单选按钮的容器设置监听器
    }

    public void InitViewPager() {
        main_viewPager = findViewById(R.id.main_ViewPager);

        fragmentList = new ArrayList<Fragment>();
        Fragment DangjianFragment = new DangjianFragment();
        fragmentList.add(DangjianFragment);

        main_viewPager.setCurrentItem(0);
        main_viewPager.setAdapter(new MyAdapter(getSupportFragmentManager(), 0, fragmentList));
        main_viewPager.addOnPageChangeListener(new MyListner());

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        PrefStore prefStore = PrefStore.getInstance(this);
        prefStore.clearPref();
    }

    public class MyAdapter extends FragmentPagerAdapter {
        ArrayList<Fragment> list;
        FragmentManager fm;

        public MyAdapter(@NonNull FragmentManager fm, int behavior, ArrayList<Fragment> list) {
            super(fm, behavior);
            this.fm = fm;
            this.list = list;
        }

        @Override
        public Fragment getItem(int arg0) {
            return list.get(arg0);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }


    public class MyListner implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int arg0) {

            int current = main_viewPager.getCurrentItem();
            switch (current) {
                case 0:
                    main_tab_RadioGroup.check(R.id.radio_home);
                    break;
                case 1:
                    main_tab_RadioGroup.check(R.id.radio_service);
                    break;
                case 2:
                    main_tab_RadioGroup.check(R.id.radio_enviroment);
                    break;
                case 3:
                    main_tab_RadioGroup.check(R.id.radio_news);
                    break;
                case 4:
                    main_tab_RadioGroup.check(R.id.radio_me);
                    break;
            }
        }

    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
        int current = 0;
        switch (checkId) {
            case R.id.radio_home:
                t.setText("主页");
                current = 0;
                break;
            case R.id.radio_service:
                t.setText("全部服务");
                current = 1;
                break;
            case R.id.radio_enviroment:
                t.setText("党建");
                current = 2;
                break;
            case R.id.radio_news:
                t.setText("新闻");
                current = 3;
                break;
            case R.id.radio_me:
                t.setText("我的");
                current = 4;
                break;
        }
        if (main_viewPager.getCurrentItem() != current) {
            main_viewPager.setCurrentItem(current);
        }

    }


    public void select(int page) {

        main_tab_RadioGroup.check(radio_ids[page]);
    }
}
