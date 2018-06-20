package com.cornflower;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OpenAccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OpenAccountFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OpenAccount openAccount;
    private static final String TAG = OpenAccountFragment.class.getCanonicalName();

    public void setOpenAccount(OpenAccount openAccount) {
        this.openAccount = openAccount;
    }
    public OpenAccountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OpenAccountFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OpenAccountFragment newInstance(String param1, String param2) {
        OpenAccountFragment fragment = new OpenAccountFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        Log.i(TAG,"onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_open_account, container, false);
        // Inflate the layout for this fragment
        final TextView textView =  view.findViewById(R.id.tv_open_account);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAccount.openAccountOk(textView.getText().toString());
            }
        });
        Log.i(TAG,"onCreateView");
        return view;
    }

    public interface OpenAccount{
        void openAccountOk(String msg);
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG,"onResume");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG,"onDestroyView");
    }
}
