package com.androidfung.newsreader.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.androidfung.newsreader.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsrecord_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, mNewsRecord.getTtile() + " - " + mNewsRecord.getLink());
                sendIntent.setType("text/plain");
                startActivity(sendIntent);

//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

//        View recyclerView = findViewById(R.id.newsrecord_list);
//        assert recyclerView != null;
//        setupRecyclerView((RecyclerView) recyclerView);

        if (findViewById(R.id.newsrecord_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
    }

    @Override
    public void onListItemClicked(NewsRecord newsRecord) {
        mNewsRecord = newsRecord;
        if (mTwoPane) {
//            NewsRecordDetailFragment newsRecordDetailFragment = NewsRecordDetailFragment.newInstance()

            Bundle arguments = new Bundle();
            arguments.putParcelable(NewsRecordDetailFragment.ARG_ITEM_ID,
                    mNewsRecord);
            NewsRecordDetailFragment fragment = new NewsRecordDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.newsrecord_detail_container, fragment)
                    .commit();


        } else {
            Intent intent = new Intent(this, NewsRecordDetailActivity.class);
            intent.putExtra(NewsRecordDetailFragment.ARG_ITEM_ID, mNewsRecord);
            startActivity(intent);
        }
    }

//    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
//        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(DummyContent.ITEMS));
//    }

//    public class SimpleItemRecyclerViewAdapter
//            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {
//
//        private final List<DummyContent.DummyItem> mValues;
//
//        public SimpleItemRecyclerViewAdapter(List<DummyContent.DummyItem> items) {
//            mValues = items;
//        }
//
//        @Override
//        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.newsrecord_list_content, parent, false);
//            return new ViewHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(final ViewHolder holder, int position) {
//            holder.mItem = mValues.get(position);
//            holder.mIdView.setText(mValues.get(position).id);
//            holder.mContentView.setText(mValues.get(position).content);
//
//            holder.mView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (mTwoPane) {
//                        Bundle arguments = new Bundle();
//                        arguments.putString(NewsRecordDetailFragment.ARG_ITEM_ID, holder.mItem.id);
//                        NewsRecordDetailFragment fragment = new NewsRecordDetailFragment();
//                        fragment.setArguments(arguments);
//                        getSupportFragmentManager().beginTransaction()
//                                .replace(R.id.newsrecord_detail_container, fragment)
//                                .commit();
//                    } else {
//                        Context context = v.getContext();
//                        Intent intent = new Intent(context, NewsRecordDetailActivity.class);
//                        intent.putExtra(NewsRecordDetailFragment.ARG_ITEM_ID, holder.mItem.id);
//
//                        context.startActivity(intent);
//                    }
//                }
//            });
//        }
//
//        @Override
//        public int getItemCount() {
//            return mValues.size();
//        }
//
//        public class ViewHolder extends RecyclerView.ViewHolder {
//            public final View mView;
//            public final TextView mIdView;
//            public final TextView mContentView;
//            public DummyContent.DummyItem mItem;
//
//            public ViewHolder(View view) {
//                super(view);
//                mView = view;
//                mIdView = (TextView) view.findViewById(R.id.id);
//                mContentView = (TextView) view.findViewById(R.id.content);
//            }
//
//            @Override
//            public String toString() {
//                return super.toString() + " '" + mContentView.getText() + "'";
//            }
//        }
//    }
}