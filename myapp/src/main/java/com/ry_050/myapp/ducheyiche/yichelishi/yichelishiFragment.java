package com.ry_050.myapp.ducheyiche.yichelishi;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ry_050.myapp.R;

public class yichelishiFragment extends Fragment {

    private YichelishiViewModel mViewModel;

    public static yichelishiFragment newInstance() {
        return new yichelishiFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.yichelishi_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(YichelishiViewModel.class);
        // TODO: Use the ViewModel
    }

}