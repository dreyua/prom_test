package com.example.drey.testforpromua;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.drey.testforpromua.R;

/**
 * Created by drey on 14.04.15.
 */
public class SplashFragment extends Fragment {

    private int SPLASH_TIMEOUT = 5000;
    // private int SPLASH_TIMEOUT = 0;
    public SplashFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Activity a = getActivity();
                if (a!=null)
                    a.getFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE).replace(R.id.main_container, new MainFragment()).commit();
            }
        }, SPLASH_TIMEOUT);

    }


    @Override
    public Animator onCreateAnimator(int transit, boolean enter, int nextAnim) {
        Animator an =  super.onCreateAnimator(transit, enter, nextAnim);
        if (an==null && enter)
            an = AnimatorInflater.loadAnimator(getActivity(), R.animator.rotation);
        return an;
    }
}
