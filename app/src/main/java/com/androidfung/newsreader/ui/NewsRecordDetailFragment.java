package com.androidfung.newsreader.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidfung.newsreader.R;


/**
 * A fragment representing a single NewsRecord detail screen.
 * This fragment is either contained in a {@link NewsRecordListActivity}
 * in two-pane mode (on tablets) or a {@link NewsRecordDetailActivity}
 * on handsets.
 */
public class NewsRecordDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    private NewsRecord mNewsRecord;

    private TextView mTextViewDatetime;
    private TextView mTextViewContent;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NewsRecordDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
//            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
            mNewsRecord = getArguments().getParcelable(ARG_ITEM_ID);

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mNewsRecord.getTtile());

            }
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.newsrecord_detail, container, false);

        // Show the dummy content as text in a TextView.
//        if (mItem != null) {
//            ((TextView) rootView.findViewById(R.id.newsrecord_detail)).setText(mItem.details);
//        }

        mTextViewContent = (TextView) rootView.findViewById(R.id.textview_content);
        mTextViewDatetime = (TextView) rootView.findViewById(R.id.textview_datetime);

        if (mNewsRecord != null){
            mTextViewContent.setText(Html.fromHtml(mNewsRecord.getContent()));
            mTextViewDatetime.setText(mNewsRecord.getDatetime());
        }

        return rootView;
    }
}
