package erikterwiel.emojify

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.*

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity.kt"

    private lateinit var mDatabase: EmojiHashMap
    private lateinit var mRecentTexts: Queue
    private lateinit var mRecentAdapter: RecentAdapter
    private lateinit var mRecentEditor: SharedPreferences.Editor
    private lateinit var mSavedEditor: SharedPreferences.Editor

    private lateinit var mTextInput: EditText
    private lateinit var mEmojifyText: Button
    private lateinit var mSaveBar: LinearLayout
    private lateinit var mSaveText: ImageView
    private lateinit var mTextOutput: TextView
    private lateinit var mCopyBar: LinearLayout
    private lateinit var mCopyText: ImageView
    private lateinit var mRecentTextList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {

        Log.i(TAG, "onCreate() called")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Loads recent texts
        mRecentTexts = Queue(10)
        val recentStorage = getSharedPreferences("recent", Context.MODE_PRIVATE)
        mRecentEditor = recentStorage.edit()
        if (recentStorage.contains("size")) {
            for (i in 0 until recentStorage.getInt("size")) {
                mRecentTexts.enqueue(recentStorage.getString("storage" + i, null))
            }
        } else {
            mRecentEditor.putInt("size", 0)
            mRecentEditor.apply()
        }

        // Loads saved texts editor
        val savedStorage = getSharedPreferences("saved", Context.MODE_PRIVATE)
        mSavedEditor = savedStorage.edit()

        // Creates the database
        mDatabase = EmojiHashMap()

        // Assigns widgets to objects
        mTextInput = findViewById(R.id.main_text_input) as EditText
        mEmojifyText = findViewById(R.id.main_emojify_text) as Button
        mSaveBar = findViewById(R.id.main_save_bar) as LinearLayout
        mSaveText = findViewById(R.id.main_save_text) as ImageView
        mTextOutput = findViewById(R.id.main_text_output) as TextView
        mCopyBar = findViewById(R.id.main_copy_bar) as LinearLayout
        mCopyText = findViewById(R.id.main_copy_text) as ImageView
        mRecentTextList = findViewById(R.id.main_recent_text_list) as RecyclerView

        mEmojifyText.setOnClickListener {
            mTextOutput.text = emojifyString(mTextInput.text.toString())
            mRecentTexts.enqueue(mTextOutput.text.toString())
        }

        mSaveText.setOnClickListener {
        }

        mCopyText.setOnClickListener {
            val clipboardManager: ClipboardManager =
                    getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val emojifiedText = ClipData.newPlainText("Emojified Text", mTextOutput.text)
            clipboardManager.primaryClip(emojifiedText)
            Toast.makeText(this, "Emojification copied", Toast.LENGTH_LONG).show()
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

    private fun updateUI() {
        mRecentAdapter = RecentAdapter(mRecentTexts)
        mRecentTextList.adapter = mRecentAdapter
    }

    private inner class RecentAdapter(val texts: Queue) : RecyclerView.Adapter<RecentHolder>(){
        private var recentTexts: Queue

        init {
            recentTexts = texts
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) {
            val layoutInflater: LayoutInflater = LayoutInflater.from(MainActivity.this)
            var view: View = layoutInflater.inflate(R.layout)
        }
    }

    private inner class RecentHolder : RecyclerView.ViewHolder {

    }

    override fun onStop() {

        Log.i(TAG, "onStop() called")
        super.onStop()

        for (i in 0 until mRecentTexts.size()) {
            mRecentEditor.putString("storage" + i, mRecentTexts.dequeue())
        }
        mRecentEditor.commit()

        mSavedEditor.commit()

    }
}