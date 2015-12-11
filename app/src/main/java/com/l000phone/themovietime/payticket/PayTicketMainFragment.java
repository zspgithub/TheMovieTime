package com.l000phone.themovietime.payticket;


import android.graphics.Color;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.l000phone.themovietime.R;
import com.l000phone.themovietime.utils.FragmentChangeHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class PayTicketMainFragment extends Fragment {


    private FragmentTransaction transaction;
    private FragmentManager fragmentManager;

    public PayTicketMainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pay_ticket_main, container, false);
        fragmentManager = getChildFragmentManager();
        transaction = fragmentManager.beginTransaction();
        Bundle bundle = getArguments();
        if (bundle!=null){
            int index = bundle.getInt("index");
            if (index == 1){
                transaction.replace(R.id.payticket_firstpage_fragment_layout, new PayTicketFirstRightFragment());
                transaction.commit();
            }
        }else{
            transaction.replace(R.id.payticket_firstpage_fragment_layout, new PayTicketFirstLeftFragment());
            transaction.commit();
        }

        initView(view);

        return view;
    }

    private void initView(View view) {
        final RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.payticket_title_layout);
        final TextView leftLine = (TextView) view.findViewById(R.id.payticket_main_btn_bottomline_left);
        final TextView rightLine = (TextView) view.findViewById(R.id.payticket_main_btn_bottomline_right);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){
                    case R.id.payticket_main_title_btnleft:
                        leftLine.setBackgroundResource(R.color.royalblue);
                        rightLine.setBackgroundResource(R.color.white);
                        fragmentManager = getChildFragmentManager();
                        transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.payticket_firstpage_fragment_layout, new PayTicketFirstLeftFragment());
                        FragmentChangeHelper changeHelper = new FragmentChangeHelper();
                        changeHelper.setFragmentTag("PayTicketFirstLeftFragment");
                        break;
                    case R.id.payticket_main_title_btnright:
                        rightLine.setBackgroundResource(R.color.royalblue);
                        leftLine.setBackgroundResource(R.color.white);
                        fragmentManager = getChildFragmentManager();
                        transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.payticket_firstpage_fragment_layout, new PayTicketFirstRightFragment());
                        FragmentChangeHelper changeHelper1 = new FragmentChangeHelper();
                        changeHelper1.setFragmentTag("PayTicketFirstRightFragment");
                        break;
                }
                transaction.commit();
            }
        });
    }


}
