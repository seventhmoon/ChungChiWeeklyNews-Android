package com.androidfung.newsreader.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.androidfung.newsreader.MyApplication;
import com.androidfung.newsreader.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

/**
 * An activity representing a list of NewsRecords. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link NewsRecordDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class NewsRecordListActivity extends AppCompatActivity
        implements NewsRecordListFragment.OnFragmentInteractionListener {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private NewsRecord mNewsRecord;
    private static final int MY_PERMISSIONS_REQUEST = 0x01;
    private FloatingActionButton mFabShare;
//    private Tracker mTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsrecord_list);

//        MyApplication application = (MyApplication) getApplication();
//        mTracker = application.getDefaultTracker();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        mFabShare = (FloatingActionButton) findViewById(R.id.fab);
        mFabShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, mNewsRecord.getTtile() + " - " + mNewsRecord.getLink());
                sendIntent.setType("text/plain");
                startActivity(sendIntent);

            }
        });


        if (findViewById(R.id.newsrecord_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        AdView adView = (AdView) findViewById(R.id.adView);
        if (adView != null){
            AdRequest adRequest = new AdRequest.Builder().build();
            adView.loadAd(adRequest);
        }

//        AnalyticsTrackers.initialize(this);
        startTracking();
    }


    @Override
    public void onListItemClicked(NewsRecord newsRecord) {
        mNewsRecord = newsRecord;
        if (mTwoPane) {

            Bundle arguments = new Bundle();
            arguments.putParcelable(NewsRecordDetailFragment.ARG_ITEM_ID,
                    mNewsRecord);
            NewsRecordDetailFragment fragment = new NewsRecordDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.newsrecord_detail_container, fragment)
                    .commit();

            mFabShare.setVisibility(View.VISIBLE);

        } else {
            Intent intent = new Intent(this, NewsRecordDetailActivity.class);
            intent.putExtra(NewsRecordDetailFragment.ARG_ITEM_ID, mNewsRecord);
            startActivity(intent);
        }
    }

    private void startTracking() {
        // Get tracker.
        Tracker t = ((MyApplication) this.getApplication()).getDefaultTracker();

        // Set screen name.
        // Where path is a String representing the screen name.
        t.setScreenName(this.getLocalClassName());

        // Send a screen view.
        t.send(new HitBuilders.ScreenViewBuilder().build());
    }
}
