package com.connite;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ConnectFragment extends Fragment {
    public RelativeLayout rootLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_connect, container, false);
        rootLayout = viewGroup.findViewById(R.id.rl_ConnectFragmentContainer);

        final ArrayList<UserClass> userList = new ArrayList<>();

        FirebaseHandler.reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot child : dataSnapshot.getChildren()){
                    if(!(child.child("email").getValue() + "").equals(GlobalVariables.user.getEmail())) {
                        Log.d("curEmail", GlobalVariables.user.getEmail());
                        Log.d("gottenEmail", (child.child("email").getValue()+"" != GlobalVariables.user.getEmail())+"");
                        userList.add(new UserClass(
                                child.child("name").getValue()+"",
                                child.child("email").getValue()+"",
                                child.child("profileUrl").getValue()+""
                                ));
                    }
                }

                UserItemAdapter userItemAdapter = new UserItemAdapter(getContext(), 0 ,userList);
                ExpandableHeightListView ehlv_ActivityItemList = rootLayout.findViewById(R.id.ehlv_ConnectFragmentUserList);
                ehlv_ActivityItemList.setAdapter(userItemAdapter);
                ehlv_ActivityItemList.setExpanded(true);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return viewGroup;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
            FragmentInterface fragmentInterface = (FragmentInterface) getActivity();
            fragmentInterface.onConnectFragmentCreated(rootLayout);
        } catch (ClassCastException e) {
            Log.e("ERROR", getActivity() + " must implement FragmentInterface");
        }

    }
}
