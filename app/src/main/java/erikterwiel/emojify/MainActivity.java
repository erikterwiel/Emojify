package erikterwiel.emojify;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EmojiHashMap mDatabase;
    private ArrayList<String> mRecentTexts;
    private RecentAdapter mRecentAdapter;

    private EditText mTextInput;
    private ImageView mSaveText;
    private ImageView mCopyText;
    private Button mEmojifyText;
    private LinearLayout mSaveBar;
    private TextView mTextOutput;
    private LinearLayout mCopyBar;
    private RecyclerView mRecentTextList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Loads recent texts
        mRecentTexts = new ArrayList<String>();

        // Creates the database
        mDatabase = new EmojiHashMap();

        // Assigns widgets to objects
        mTextInput = (EditText) findViewById(R.id.main_text_input);
//        mSaveText = (Button) findViewById(R.id.main_save_text);
        mCopyText = (ImageView) findViewById(R.id.main_copy_text);
        mEmojifyText = (Button) findViewById(R.id.main_emojify_text);
        mSaveBar = (LinearLayout) findViewById(R.id.main_save_bar);
        mTextOutput = (TextView) findViewById(R.id.main_text_output);
        mCopyBar = (LinearLayout) findViewById(R.id.main_copy_bar);
        mRecentTextList = (RecyclerView) findViewById(R.id.main_recent_text_list);

/*        // Saves texts to saved texts list on click
        mSaveText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSavedTexts.add(mTextOutput.getText().toString());
            }
        });
*/

        // Copies text to clipboard on click
        mCopyText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboardManager =
                        (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData emojifiedText =
                        ClipData.newPlainText("Emojified Text", mTextOutput.getText());
                clipboardManager.setPrimaryClip(emojifiedText);
                Toast.makeText(MainActivity.this, "Emojification copied", Toast.LENGTH_LONG).show();
            }
        });


        // Emojifies string and displays output on click
        mTextInput.setMinLines(3);
        mTextInput.setMaxLines(1000);
        mTextInput.setHorizontallyScrolling(false);
        mTextInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    String output = emojifyString(mTextInput.getText().toString());
                    handled = true;
                }
                return handled;
            }
        });

        mEmojifyText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String output = emojifyString(mTextInput.getText().toString());
            }
        });
    }

    private void updateUI() {
        mRecentAdapter = new RecentAdapter(mRecentTexts);
        mRecentTextList.setAdapter(mRecentAdapter);
    }

    private String emojifyString(String input) {
        String output = "";
        String[] inputSplit = input.split(" ");
        for (String upperCaseInput : inputSplit) {
            String lowerCaseInput = upperCaseInput.toLowerCase();
            if (mDatabase.containsKey(lowerCaseInput)) {
                ArrayList<String> emojiList = mDatabase.get(lowerCaseInput);
                int numOfEmoji = (int) ((Math.random() * 4) + 1);
                int wordPosition = (int) (Math.random() * numOfEmoji);
                for (int i = 0; i < numOfEmoji; i++) {
                    if (i == wordPosition) {
                        output += upperCaseInput;
                    } else {
                        if (!emojiList.isEmpty())
                            output += emojiList.get((int) (Math.random() * emojiList.size()));
                    }
                    if (i == numOfEmoji - 1) output += " ";
                }
            } else {
                output += upperCaseInput + " ";
            }
        }
        if (!input.equals("")) {
            mSaveBar.setVisibility(View.VISIBLE);
            mTextOutput.setVisibility(View.VISIBLE);
            mCopyBar.setVisibility(View.VISIBLE);
            mTextOutput.setText(output);
        }
        return output;
    }

    private class RecentAdapter extends RecyclerView.Adapter<RecentHolder> {
        private ArrayList<String> recentTexts;

        private RecentAdapter(ArrayList<String> texts) {
            recentTexts = texts;
        }

        @Override
        public RecentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
            View view = layoutInflater.inflate(R.layout);
        }
    }

    private class RecentHolder extends RecyclerView.ViewHolder {

    }
}
