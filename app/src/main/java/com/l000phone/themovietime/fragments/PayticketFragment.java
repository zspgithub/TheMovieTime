
package com.l000phone.themovietime.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.l000phone.themovietime.MainActivity;
import com.l000phone.themovietime.R;
import com.l000phone.themovietime.SearchTitleActivity;
import com.l000phone.themovietime.payticket.PayTicketFirstLeftFragment;
import com.l000phone.themovietime.payticket.PayTicketFirstRightFragment;
import com.l000phone.themovietime.payticket.PayTicketMainFragment;
import com.l000phone.themovietime.payticket.PayTicketYingyuanFragment;
import com.l000phone.themovietime.utils.FragmentChangeHelper;
import com.l000phone.themovietime.utils.LogUtil;

//import com.l000phone.themovietime.payticket.PayTicketMainRightFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class PayticketFragment extends Fragment {
    private MainActivity activity;
    private FragmentTransaction transaction;
    private FragmentManager manager;
    private FragmentChangeHelper changeHelper;
    private RadioButton button1;
    private RadioButton button2;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = ((MainActivity) context);
    }

    public PayticketFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_payticket, container, false);
        initView(view);
        Bundle bundle = getArguments();
        FragmentManager manager = getChildFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (bundle!=null){
            int index = bundle.getInt("index");
            LogUtil.log("jump",index+"---");
            if (index == 0){
                PayTicketMainFragment payTicketMainFragment = new PayTicketMainFragment();
                transaction.replace(R.id.payticket_framelayout, payTicketMainFragment);
            }else if (index == 1){
                PayTicketMainFragment mainFragment = new PayTicketMainFragment();
                Bundle bundle1 = new Bundle();
                bundle1.putInt("index",1);
                mainFragment.setArguments(bundle1);
                transaction.replace(R.id.payticket_framelayout,mainFragment);
            }else if (index == 2){
                PayTicketYingyuanFragment yingyuanFragment = new PayTicketYingyuanFragment();
                transaction.replace(R.id.payticket_framelayout,yingyuanFragment);
            }
        }else{
            PayTicketMainFragment fragment = new PayTicketMainFragment();
            transaction.replace(R.id.payticket_framelayout, fragment);
        }
        transaction.commit();

        return view;
    }

    private void initView(final View view) {

        button1 = (RadioButton) view.findViewById(R.id.payticket_title_leftRadiobtn);
        button2 = (RadioButton) view.findViewById(R.id.payticket_title_rightRadiobtn);

        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.payticket_title_Radiogroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.payticket_title_leftRadiobtn:
                        PayTicketMainFragment fragment = new PayTicketMainFragment();
                        manager = getChildFragmentManager();
                        transaction = manager.beginTransaction();
                        transaction.replace(R.id.payticket_framelayout, fragment);
                        changeHelper = new FragmentChangeHelper();
                        changeHelper.setFragmentTag("PayTicketMainFragment");
                        break;
                    case R.id.payticket_title_rightRadiobtn:
                        //TODO   影院Fragment
                        PayTicketYingyuanFragment yingyuanFragment = new PayTicketYingyuanFragment();
                        manager = getChildFragmentManager();
                        transaction = manager.beginTransaction();
                        transaction.replace(R.id.payticket_framelayout, yingyuanFragment);
                        changeHelper = new FragmentChangeHelper();
                        changeHelper.setFragmentTag("PayTicketYingyuanFragment");
                        break;
                }
                transaction.commit();
            }
        });
        ImageView imageView = (ImageView) view.findViewById(R.id.payticket_titlebar_search_image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchTitleActivity.class);
                startActivity(intent);
            }
        });
    }

}

