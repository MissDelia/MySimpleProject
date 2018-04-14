package com.example.myapplication.main.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.main.fragment.MainListFragment;
import com.example.myapplication.main.fragment.MineInfoFragment;
import com.example.myapplication.util.StatusBarUtil;
import com.example.myapplication.util.base.BaseCompatActivity;

public class MainActivity extends BaseCompatActivity {

    private FragmentManager fm;
    private FragmentTransaction ft;
    private Fragment mCurrent;

    private Button btnList, btnMine;

    static {
        System.loadLibrary("lib-c");
    }

    public native String getString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StatusBarUtil.getInstance().StatusBarLightMode(this);

        initView();

        fm = getFragmentManager();
        ft = fm.beginTransaction();

        MainListFragment mainListFragment = new MainListFragment();
        MineInfoFragment mineInfoFragment = new MineInfoFragment();

        ft.add(R.id.main_frame_layout, mainListFragment, "fragment1")
                .add(R.id.main_frame_layout, mineInfoFragment, "fragment2")
                .hide(mineInfoFragment)
                .commit();
    }

    private void initView() {
        btnList = findViewById(R.id.main_btn_list);
        btnMine = findViewById(R.id.main_btn_mine);
        btnList.setClickable(false);
        btnMine.setText(getString());
    }

    /**
     * 主activity进行控制不同的fragment
     *
     * @param fromTag
     * @param toTag
     */
    public void switchFragment(String fromTag, String toTag) {
        Fragment from = fm.findFragmentByTag(fromTag);
        Fragment to = fm.findFragmentByTag(toTag);
        if (mCurrent != to) {
            mCurrent = to;
            FragmentTransaction transaction = fm.beginTransaction();
            if (!to.isAdded()) {//判断是否被添加到了Activity里面去了
                transaction.hide(from).add(R.id.main_frame_layout, to).commit();
            } else {
                transaction.hide(from).show(to).commit();
            }
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_btn_list:
                switchFragment("fragment2", "fragment1");
                btnList.setClickable(false);
                btnMine.setClickable(true);
                break;
            case R.id.main_btn_mine:
                switchFragment("fragment1", "fragment2");
                btnList.setClickable(true);
                btnMine.setClickable(false);
                break;
            default:
                break;
        }
    }

}
