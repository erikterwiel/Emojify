package erikterwiel.emojify

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity.kt"

    private lateinit var mDatabase: EmojiHashMap
    private lateinit var mRecentTexts: PriorityQueue<String>
    private lateinit var mRecentAdapter: RecentAdapter

    private lateinit var mTextInput: EditText
    private lateinit var mSaveText: ImageView
    private lateinit var mCopyText: ImageView
    private lateinit var mEmojifyText: Button
    private lateinit var mSaveBar: LinearLayout
    private lateinit var mTextOutput: TextView
    private lateinit var mCopyBar: LinearLayout
    private lateinit var mRecentTextList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle) {

        Log.i(TAG, "onCreate() called")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Loads recent texts
        mRecentTexts = ArrayList<String>()

        // Creates the database
        mDatabase = EmojiHashMap()

        // Assigns widgets to objects
        mTextInput = findViewById(R.id.main_text_input) as EditText
//        mSaveText = (Button) findViewById(R.id.main_save_text);
        mCopyText = findViewById(R.id.main_copy_text) as ImageView
        mEmojifyText = findViewById(R.id.main_emojify_text) as Button
        mSaveBar = findViewById(R.id.main_save_bar) as LinearLayout
        mTextOutput = findViewById(R.id.main_text_output) as TextView
        mCopyBar = findViewById(R.id.main_copy_bar) as LinearLayout
        mRecentTextList = findViewById(R.id.main_recent_text_list) as RecyclerView

        mSaveText.setOnClickListener {
            var clipboardManager: ClipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            var emojifiedText: ClipData = ClipData.newPlainText("Emojified Text", mTextOutput.text)
            clipboardManager.primaryClip(emojifiedText)
            Toast.makeText(MainActivity.this, "Emojification copied", Toast.LENGTH_LONG).show()
        }

        mTextInput.minLines = 3
        mTextInput.maxLines = 1000
        mTextInput.isHorizontalScrollBarEnabled = false
        mTextInput.setOnEditorActionListener { v, actionId, event ->
            var handled: = false
            if (actionId == EditorInfo.IME_ACTION_GO) {
                val output = emojifyString(mTextInput.text.toString())
                handled = true
            }
            handled
        }

        mEmojifyText.setOnClickListener {
            var output = emojifyString(mTextInput.text.toString())
        }
    }

    private fun updateUI() {
        mRecentAdapter = RecentAdapter(mRecentTexts)
        mRecentTextList.adapter = mRecentAdapter
    }

    private fun emojifyString(input: String): String {
        var output = ""
        val inputSplit = input.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        for (upperCaseInput in inputSplit) {
            val lowerCaseInput = upperCaseInput.toLowerCase()
            if (mDatabase.containsKey(lowerCaseInput)) {
                val emojiList = mDatabase[lowerCaseInput]
                val numOfEmoji = (Math.random() * 4 + 1).toInt()
                val wordPosition = (Math.random() * numOfEmoji).toInt()
                for (i in 0 until numOfEmoji) {
                    if (i == wordPosition) {
                        output += upperCaseInput
                    } else {
                        if (!emojiList.isEmpty())
                            output += emojiList.get((Math.random() * emojiList.size).toInt())
                    }
                    if (i == numOfEmoji - 1) output += " "
                }
            } else {
                output += upperCaseInput + " "
            }
        }
        if (input != "") {
            mSaveBar.visibility = View.VISIBLE
            mTextOutput.visibility = View.VISIBLE
            mCopyBar.visibility = View.VISIBLE
            mTextOutput.text = output
        }
        return output
    }



    private inner class RecentAdapter(val texts: ArrayList<String>) : RecyclerView.Adapter<RecentHolder>(){
        private var recentTexts: ArrayList<String>

        init {
            recentTexts = texts
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) {
            var layoutInflater: LayoutInflater = LayoutInflater.from(MainActivity.this)
            var view: View = layoutInflater.inflate(R.layout)
        }
    }

    private inner class RecentHolder : RecyclerView.ViewHolder {

    }
}