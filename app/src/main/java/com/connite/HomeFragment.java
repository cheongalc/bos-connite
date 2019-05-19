package com.connite;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    public RelativeLayout rootLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);
        rootLayout = viewGroup.findViewById(R.id.rl_HomeFragmentContainer);

//        make the request to firebase to fetch the user's activity queue
        ArrayList<ActivityItemData> arrayList = new ArrayList<>();
        FirebaseHandler.userRef.child("activityQueue").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    arrayList.add(child.getValue(ActivityItemData.class));
                }
                if (arrayList.size() == 0) {
//            there are no items in the activity queue
//            no need to display the listview even
                    TextView tv_Placeholder = rootLayout.findViewById(R.id.tv_PlaceholderHomeFragment);
                    tv_Placeholder.setVisibility(View.VISIBLE);
                    ScrollView sv_HomeFragmentScrollView = rootLayout.findViewById(R.id.sv_HomeFragmentScrollView);
                    sv_HomeFragmentScrollView.setVisibility(View.GONE);
                } else {
//            there are items in the activity queue
//            init them and display them
                    ArrayAdapter<ActivityItemData> adapter = new ActivityItemDataAdapter(getContext(), 0, arrayList);
                    ExpandableHeightListView ehlv_ActivityItemList = rootLayout.findViewById(R.id.ehlv_HomeFragmentActivityItemList);
                    ehlv_ActivityItemList.setAdapter(adapter);
                    ehlv_ActivityItemList.setExpanded(true);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return viewGroup;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
            FragmentInterface fragmentInterface = (FragmentInterface) getActivity();
            fragmentInterface.onHomeFragmentCreated(rootLayout);
        } catch (ClassCastException e) {
            Log.e("ERROR", getActivity() + " must implement FragmentInterface");
        }
    }
}
