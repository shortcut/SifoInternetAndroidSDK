package mo.dyna.sifomobileanalyticssdkforandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import se.sifo.analytics.mobileapptagging.android.TSMobileAnalytics;

/**
 * Created by ahmetcengiz on 26/09/2017.
 */

public class NativeFragment extends Fragment {

    ArrayAdapter<String> mAdapter;
    String[] items;
    ListView mListView;

    public NativeFragment() {

    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.native_fragment, container, false);
        setIds(v);
        makeList();
        setupAdapter();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sendTag((String) parent.getItemAtPosition(position));
            }
        });
        return v;
    }

    private void setIds(View v) {
        mListView = v.findViewById(R.id.list);
    }

    private void makeList() {
        items = new String[30];
        for (int i = 0; i < 30; i++) {
            items[i] = i + " item";
        }
    }

    private void setupAdapter() {
        mAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, items);
        mListView.setAdapter(mAdapter);
    }


    private void sendTag(String category) {
        if (TSMobileAnalytics.getInstance() != null) {
            ApplicationImpl.tagInfo().setCategory(0, category);
            TSMobileAnalytics.getInstance().sendTag(category);
            Toast.makeText(getActivity(), getString(R.string.toast_sent_tag, category), Toast.LENGTH_SHORT).show();

            startActivity(new Intent(getActivity(), CategoryActivity.class));
        }
    }
}