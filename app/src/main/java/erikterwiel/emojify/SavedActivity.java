package erikterwiel.emojify;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SavedActivity
        extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ArrayList<String> mSavedStrings;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView mNavigationView;

    private RecyclerView mRecyclerView;
    private SavedAdapter mRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.saved_drawer);
        mToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mNavigationView = (NavigationView) findViewById(R.id.saved_navigation);
        mNavigationView.setNavigationItemSelectedListener(this);

        mSavedStrings = new ArrayList<String>();
        SharedPreferences sharedStorage = getSharedPreferences("saved", Context.MODE_PRIVATE);
        for (int i = 0; i < sharedStorage.getInt("size", 0); i++) {
            mSavedStrings.add(sharedStorage.getString("saved" + i, null));
        }
        mRecyclerView = (RecyclerView) findViewById(R.id.saved_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerAdapter = new SavedAdapter(mSavedStrings);
        mRecyclerView.setAdapter(mRecyclerAdapter);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.drawer_home) {
            Intent activityIntent = new Intent(this, MainActivity.class);
            mDrawerLayout.closeDrawer(GravityCompat.START);
            startActivity(activityIntent);
        } else if (item.getItemId() == R.id.drawer_saved) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class SavedAdapter extends RecyclerView.Adapter<SavedHolder> {
        private ArrayList<String> mStrings;

        public SavedAdapter(ArrayList<String> strings) {
            mStrings = strings;
        }

        @Override
        public SavedHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(SavedActivity.this);
            View view = layoutInflater.inflate(
                    R.layout.saved_list_item_saved, parent, false);
            return new SavedActivity.SavedHolder(view);
        }

        @Override
        public void onBindViewHolder(SavedHolder holder, int position)  {
            String string = mStrings.get(position);
            holder.bindSaved(string);
        }

        @Override
        public int getItemCount() {
            return mStrings.size();
        }

        public void itemAdded(int position) {
            notifyItemInserted(position);
        }

        public void itemRemoved(int position) {
            notifyItemRemoved(position);
        }
    }

    private class SavedHolder extends RecyclerView.ViewHolder  {
        private ImageView mUnsaveText;
        private TextView mSavedText;
        private ImageView mCopyText;

        public SavedHolder(View itemView) {
            super(itemView);
            mUnsaveText = (ImageView) itemView.findViewById(R.id.saved_unsave_text);
            mSavedText = (TextView) itemView.findViewById(R.id.saved_text);
            mCopyText = (ImageView) itemView.findViewById(R.id.saved_copy_text);
        }

        public void bindSaved(final String saved) {
            mUnsaveText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for (int i = 0; i < mSavedStrings.size(); i++) {
                        if (mSavedStrings.get(i).equals(saved)) {
                            final int position = i;
                            final String deletedString = mSavedStrings.get(i);
                            mSavedStrings.remove(i);
                            mRecyclerAdapter.notifyItemRemoved(i);
                            Snackbar notification = Snackbar.make(mRecyclerView,
                                    "Emojification deleted", Snackbar.LENGTH_LONG);
                            notification.setAction("UNDO", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Snackbar.make(mRecyclerView, "Emojification restored",
                                            Snackbar.LENGTH_LONG).show();
                                    mSavedStrings.add(position, deletedString);
                                    mRecyclerAdapter.notifyItemInserted(position);
                                    mRecyclerView.scrollToPosition(position);
                                }
                            });
                            notification.show();
                            break;
                        }
                    }
                }
            });
            mSavedText.setText(saved);
            mCopyText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ClipboardManager clipboardManager =
                            (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                    ClipData emojifiedText = ClipData.newPlainText("Emojified Text", saved);
                    clipboardManager.setPrimaryClip(emojifiedText);
                    Toast.makeText(SavedActivity.this,
                            "Emojification copied", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
