package com.androidfung.newsreader.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.androidfung.newsreader.MyApplication;
import com.androidfung.newsreader.R;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

/**
 * An activity representing a single NewsRecord detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link NewsRecordListActivity}.
 */
public class NewsRecordDetailActivity extends AppCompatActivity {

    private static  final String TAG = NewsRecordDetailActivity.class.getSimpleName();
    private NewsRecord mNewsRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsrecord_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();

            mNewsRecord = getIntent().getParcelableExtra(NewsRecordDetailFragment.ARG_ITEM_ID);
//            Log.d(TAG, String.valueOf(newsRecord == null));

            arguments.putParcelable(NewsRecordDetailFragment.ARG_ITEM_ID,
                    mNewsRecord);
            NewsRecordDetailFragment fragment = new NewsRecordDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.newsrecord_detail_container, fragment)
                    .commit();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, mNewsRecord.getTtile() + " - " + mNewsRecord.getLink());
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        startTracking();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, NewsRecordListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
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
