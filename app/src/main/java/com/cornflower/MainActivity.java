package com.cornflower;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RegistFragment registFragment;
    private RiskFragment riskFragment;
    private OpenAccountFragment openAccountFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registFragment = findOrCreateFragment(RegistFragment.class);
        openAccountFragment = findOrCreateFragment(OpenAccountFragment.class);
        riskFragment = findOrCreateFragment(RiskFragment.class);

        if(savedInstanceState==null){
          push(registFragment,RegistFragment.class.getCanonicalName());
        }else {
            //如果activity被回收，恢复数据
//            savedInstanceState.getS
        }
        registFragment.setRegist(new RegistFragment.Regist() {
            @Override
            public void registOk(String msg) {
                //如果要传递参数需要在此setArgment
                push(openAccountFragment,OpenAccountFragment.class.getCanonicalName());
            }
        });
        openAccountFragment.setOpenAccount(new OpenAccountFragment.OpenAccount() {
            @Override
            public void openAccountOk(String msg) {
                push(riskFragment,RiskFragment.class.getCanonicalName());
            }
        });
    }

    public  <T extends Fragment> T findOrCreateFragment(@NonNull Class<T> fragmentClass) {
        String tag = fragmentClass.getCanonicalName();
        FragmentManager fragmentManager = getSupportFragmentManager();
        T fragment = (T) fragmentManager.findFragmentByTag(tag);
        if (fragment == null) {
            try {
                fragment = fragmentClass.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return fragment;
    }

    protected void push(Fragment fragment, String tag) {
        List<Fragment> currentFragments = getSupportFragmentManager().getFragments();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (currentFragments.size() != 0) {
            // 流程中，第一个步骤的 Fragment 进场不需要动画，其余步骤需要
            transaction.setCustomAnimations(
                    R.anim.push_in_left,
                    R.anim.push_out_left,
                    R.anim.push_in_right,
                    R.anim.push_out_right
            );
        }
        transaction.add(R.id.fragment_container, fragment, tag);
        if (currentFragments.size() != 0) {
            // 从流程的第二个步骤的 Fragment 进场开始，需要同时隐藏上一个 Fragment，这样才能看到切换动画
            transaction
                    .hide(currentFragments.get(currentFragments.size() - 1))
                    .addToBackStack(tag);
        }
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (!BackHandlerHelper.handleBackPress(this)) {
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //保存数据，
    }
}
