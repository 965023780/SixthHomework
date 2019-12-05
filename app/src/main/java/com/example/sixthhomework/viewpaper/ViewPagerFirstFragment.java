package com.example.sixthhomework.viewpaper;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.provider.CallLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sixthhomework.R;
import com.example.sixthhomework.recyclerview.MyRecyclerAdapt;

import java.util.ArrayList;

public class ViewPagerFirstFragment extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private MyRecyclerAdapt myRecyclerAdapt;
    private ArrayList<String> number = new ArrayList<>();
    private ArrayList<String> name = new ArrayList<>();
    private ArrayList<String> date = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_first, container, false);
        initRecyclerView();
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_CALL_LOG}, 1);
        } else {
            getData();
        }
        myRecyclerAdapt.setData(name, number, date);
        return view;
    }

    private void initRecyclerView() {
        myRecyclerAdapt = new MyRecyclerAdapt(getActivity(), getActivity());
        recyclerView = (RecyclerView) view.findViewById(R.id.rc_first);
        recyclerView.setAdapter(myRecyclerAdapt);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), OrientationHelper.VERTICAL));
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                Intent mIntent;
                mIntent = data;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && getActivity().checkSelfPermission(Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_CALL_LOG}, 1);
                } else {
                    getData();
                }
                break;
        }
    }

    private void getData() {
        Cursor cursor = null;
        try {
            if (getActivity().checkSelfPermission(Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            cursor = getActivity().getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, CallLog.Calls.DEFAULT_SORT_ORDER);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    if (cursor.getInt(cursor.getColumnIndex(CallLog.Calls.TYPE)) == 1) {
                        String phoneName = cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME));
                        String phoneNumber = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
                        String phoneDate = cursor.getString(cursor.getColumnIndex(CallLog.Calls.DATE));
                        phoneDate = new SimpleDateFormat("hh:mm").format(Long.parseLong(phoneDate));
                        if (phoneName == null)
                            phoneName = "未命名";
                        name.add(phoneName);
                        number.add(phoneNumber);
                        date.add(phoneDate);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

}
