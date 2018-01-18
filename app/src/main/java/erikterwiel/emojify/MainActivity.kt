package erikterwiel.emojify

import android.content.*
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.*
import java.util.ArrayList

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val TAG = "MainActivity.kt"

    private lateinit var mDatabase: EmojiHashMap
    private lateinit var mRecentTexts: FixedArrayList<String>
    private lateinit var mRecentAdapter: RecentAdapter
    private lateinit var mSavedStorage: SharedPreferences
    private lateinit var mRecentEditor: SharedPreferences.Editor
    private lateinit var mSavedEditor: SharedPreferences.Editor

    private lateinit var mDrawerLayout: DrawerLayout
    private lateinit var mToggle: ActionBarDrawerToggle
    private lateinit var mNavigationView: NavigationView

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

        // Loads navigation drawer
        mDrawerLayout = findViewById(R.id.main_drawer) as DrawerLayout
        mToggle = ActionBarDrawerToggle(
                this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close)
        mDrawerLayout.addDrawerListener(mToggle)
        mToggle.syncState()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        mNavigationView = findViewById(R.id.main_navigation) as NavigationView
        mNavigationView.setNavigationItemSelectedListener(this)

        // Loads recent texts
        mRecentTexts = FixedArrayList()
        val recentStorage = getSharedPreferences("recent", Context.MODE_PRIVATE)
        mRecentEditor = recentStorage.edit()
        if (recentStorage.contains("size")) {
            for (i in 0 until recentStorage.getInt("size", 0)) {
                mRecentTexts.enqueue(recentStorage.getString("recent" + i, null))
            }
        } else {
            mRecentEditor.putInt("size", 0)
            mRecentEditor.apply()
        }

        // Loads saved texts editor
        mSavedStorage = getSharedPreferences("saved", Context.MODE_PRIVATE)
        mSavedEditor = mSavedStorage.edit()

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
            if (mTextInput.text.toString() != "") {
                mTextOutput.text = emojifyString(mTextInput.text.toString())
                mRecentTexts.enqueue(mTextOutput.text.toString())
                if (mRecentTexts.size == 10) {
                    updateUI()
                } else {
                    mRecentAdapter.itemAdded(mRecentTexts.size - 1)
                }
                mSaveText.setImageResource(R.drawable.ic_star_border_white_48dp)
            } else {
                Toast.makeText(this, "Please enter input", Toast.LENGTH_LONG).show()
            }
        }

        mSaveText.setOnClickListener {
            for (i in 0 until mSavedStorage.getInt("size", 0)) {
                if (mSavedStorage.getString("saved" + i, null) == mTextOutput.text.toString()) {
                    mSavedEditor.putInt("size", mSavedStorage.getInt("size", 0) - 1)
                    mSavedEditor.apply()
                    mSaveText.setImageResource(R.drawable.ic_star_border_white_48dp)
                    break
                }
                if (i == mSavedStorage.getInt("size", 0) - 1) {
                    mSavedEditor.putString(
                            "saved" + mSavedStorage.getInt("size", 0), mTextOutput.text.toString())
                    mSavedEditor.putInt("size", mSavedStorage.getInt("size", 0) + 1)
                    mSavedEditor.apply()
                    mSaveText.setImageResource(R.drawable.ic_star_white_48dp)
                }
            }
        }

        mCopyText.setOnClickListener {
            val clipboardManager: ClipboardManager =
                    getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val emojifiedText = ClipData.newPlainText("Emojified Text", mTextOutput.text)
            clipboardManager.primaryClip = emojifiedText
            Toast.makeText(this, "Emojification copied", Toast.LENGTH_LONG).show()
        }

        mTextInput.minLines = 3
        mTextInput.maxLines = 1000
        mTextInput.isHorizontalScrollBarEnabled = false
        mTextInput.setOnEditorActionListener { v, actionId, event ->
            var handled = false
            if (actionId == EditorInfo.IME_ACTION_GO) {
                val output = emojifyString(mTextInput.text.toString())
                handled = true
            }
            handled
        }

        mRecentTextList = findViewById(R.id.main_recent_text_list) as RecyclerView
        mRecentTextList.layoutManager = LinearLayoutManager(this)
        updateUI()

        val testButton: Button = findViewById(R.id.test_button) as Button
        testButton.setOnClickListener {
            for (i in 0 until recentStorage.getInt("size", 0)) {
                Log.i(TAG, recentStorage.getString("recent" + i, null))
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.drawer_home) {
            mDrawerLayout.closeDrawer(GravityCompat.START)
        } else if (item.itemId == R.id.drawer_saved) {
            val activityIntent = Intent(this, SavedActivity::class.java)
            mDrawerLayout.closeDrawer(GravityCompat.START)
            startActivity(activityIntent)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (mToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
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
                        if (!emojiList!!.isEmpty())
                            output += emojiList!!.get((Math.random() * emojiList!!.size).toInt())
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

    private inner class RecentAdapter(texts: FixedArrayList<String>) : RecyclerView.Adapter<RecentHolder>(){
        private var recentTexts: FixedArrayList<String>

        init {
            recentTexts = texts
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentHolder {
            val layoutInflater = LayoutInflater.from(this@MainActivity)
            var view: View = layoutInflater.inflate(R.layout.main_list_item_recent, parent, false)
            return RecentHolder(view)
        }

        override fun onBindViewHolder(holder: RecentHolder, position: Int) {
            val string: String = recentTexts[position]
            holder.bindRecent(string)

        }

        override fun getItemCount(): Int {
            return recentTexts.size
        }

        fun itemAdded(position: Int) {
            notifyItemInserted(position)
        }
    }

    private inner class RecentHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var mString: String
        private lateinit var mRecentText: TextView
        private lateinit var mSave: ImageView

        init {
            mRecentText = itemView.findViewById(R.id.mainlist_recent_text) as TextView
            mSave = itemView.findViewById(R.id.mainlist_save_recent) as ImageView
        }

        fun bindRecent(string: String) {
            mString = string
            mRecentText.text = mString
            mSave.setOnClickListener {
                for (i in 0 until mSavedStorage.getInt("size", 0)) {
                    if (mSavedStorage.getString("saved" + i, null) == mString) {
                        mSavedEditor.putInt("size", mSavedStorage.getInt("size", 0) - 1)
                        mSavedEditor.apply()
                        mSave.setImageResource(R.drawable.ic_star_border_black_48dp)
                        break
                    }
                    if (i == mSavedStorage.getInt("size", 0) - 1) {
                        mSavedEditor.putString(
                                "saved" + mSavedStorage.getInt("size", 0), mString)
                        mSavedEditor.putInt("size", mSavedStorage.getInt("size", 0) + 1)
                        mSavedEditor.apply()
                        mSave.setImageResource(R.drawable.ic_star_black_48dp)
                    }
                }
            }
            for (i in 0 until mSavedStorage.getInt("size", 0)) {
                if (mSavedStorage.getString("saved" + i, null) == mString) {
                    mSave.setImageResource(R.drawable.ic_star_black_48dp)
                }
            }
        }
    }

    override fun onStop() {

        Log.i(TAG, "onStop() called")
        super.onStop()

        for (i in 0 until mRecentTexts.size) {
            mRecentEditor.putString("recent" + i, mRecentTexts[i])
        }
        mRecentEditor.putInt("size", mRecentTexts.size)
        mRecentEditor.commit()

    }
}