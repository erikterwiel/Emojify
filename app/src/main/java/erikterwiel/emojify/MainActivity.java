package erikterwiel.emojify;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> mSavedTexts;

    private EditText mTextInput;
    private Button mSaveText;
    private Button mCopyText;
    private Button mEmojifyText;
    private TextView mTextOutput;
    private RecyclerView mSavedTextList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextInput = (EditText) findViewById(R.id.main_text_input);
        mSaveText = (Button) findViewById(R.id.main_save_text);
        mCopyText = (Button) findViewById(R.id.main_copy_text);
        mEmojifyText = (Button) findViewById(R.id.main_emojify_text);
        mTextOutput = (TextView) findViewById(R.id.main_text_output);
        mSavedTextList = (RecyclerView) findViewById(R.id.main_saved_text_list);

        mSaveText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSavedTexts.add(mTextOutput.getText().toString());
            }
        });

        mCopyText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboardManager =
                        (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData emojifiedText =
                        ClipData.newPlainText("Emojified Text", mTextOutput.getText());
                clipboardManager.setPrimaryClip(emojifiedText);
            }
        });

        mEmojifyText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emojifyString(mTextInput.getText().toString());
            }
        });


    }

    private String emojifyString(String input) {
        return input;
    }
}
