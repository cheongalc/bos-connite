package com.connite;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    public RelativeLayout rootLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);
        rootLayout = viewGroup.findViewById(R.id.rl_HomeFragmentContainer);

//        Initializing the list of activity Items.
        ArrayList<ActivityItemData> arrayList = fetchActivityQueue();
        if (arrayList.size() == 0) {
//            there are no items in the activity queue
//            no need to display the listview even
            TextView tv_Placeholder = viewGroup.findViewById(R.id.tv_PlaceholderHomeFragment);
            tv_Placeholder.setVisibility(View.VISIBLE);
            ScrollView sv_HomeFragmentScrollView = viewGroup.findViewById(R.id.sv_HomeFragmentScrollView);
            sv_HomeFragmentScrollView.setVisibility(View.GONE);
        } else {
//            there are items in the activity queue
//            init them and display them
            ArrayAdapter<ActivityItemData> adapter = new ActivityItemDataAdapter(getContext(), 0, arrayList);
            ExpandableHeightListView ehlv_ActivityItemList = viewGroup.findViewById(R.id.ehlv_HomeFragmentActivityItemList);
            ehlv_ActivityItemList.setAdapter(adapter);
            ehlv_ActivityItemList.setExpanded(true);
        }
        return viewGroup;
    }

    private ArrayList<ActivityItemData> fetchActivityQueue() {
//        make the request to firebase to fetch the user's activity queue
        ArrayList<ActivityItemData> arrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arrayList.add(
                    new ActivityItemData(
                            "Swimming",
                            "Swimming builds endurance, muscle strength, and maintains a healthy heart and lungs.",
                            "Lorong 6 Toa Payoh, Swimming Complex, Singapore 319392",
                            1.330613,
                            103.850156,
                            "https://lh5.googleusercontent.com/p/AF1QipNG0TFYMjChYBEpanHyTTffOBF-UQkPAvB7E9zi=w203-h114-k-no",
                            "6969696"));
        }
        return arrayList;
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
