package erikterwiel.emojify;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Erik on 8/17/2017.
 */

public class EmojiHashMap extends HashMap<String, ArrayList<String>> {

    private ArrayList<String> stringList = new ArrayList<String>();

    public EmojiHashMap() {
        super();

        stringList.add(getEmoji(0x1F64C)); // raising hands
        stringList.add(getEmoji(0x270A));  // raised fist
        stringList.add(getEmoji(0x1F4AF)); // 100
        put("be", stringList);


        clearList();
        stringList.add(getEmoji(0x1F426)); // bird
        stringList.add(getEmoji(0x1F424)); // baby bird
        put("bird", stringList);
        put("birds", stringList);


        clearList();
        stringList.add(getEmoji(0x2705));  // green check box
        stringList.add(getEmoji(0x2611));  // blue check box
        stringList.add(getEmoji(0x2714));  // green check mark
        put("check", stringList);
        put("checked", stringList);

        stringList.add(getEmoji(0x1F44C)); // ok hand
        stringList.add(getEmoji(0x1F44D)); // thumbs up
        put("good", stringList);
        put("okay", stringList);
        put("ok", stringList);
        put("yes", stringList);
        put("correct", stringList);
        put("extremely", stringList);

        stringList.add(getEmoji(0x1F449)); // pointing right
        put("right", stringList);


        clearList();
        stringList.add(getEmoji(0x1F3C3)); // running man
        put("run", stringList);
        put("running", stringList);

        stringList.add(getEmoji(0x1F4A8)); // smoke
        put("rapid", stringList);
        put("fast", stringList);
        put("quick", stringList);


        clearList();
        stringList.add(getEmoji(0x23F0));  // alarm clock
        stringList.add(getEmoji(0x1F550)); // 1 clock
        stringList.add(getEmoji(0x1F551)); // 2 clock
        stringList.add(getEmoji(0x1F552)); // 3 clock
        stringList.add(getEmoji(0x1F553)); // 4 clock
        stringList.add(getEmoji(0x1F554)); // 5 clock
        stringList.add(getEmoji(0x1F555)); // 6 clock
        stringList.add(getEmoji(0x1F556)); // 7 clock
        stringList.add(getEmoji(0x1F557)); // 8 clock
        stringList.add(getEmoji(0x1F558)); // 9 clock
        stringList.add(getEmoji(0x1F559)); // 10 clock
        stringList.add(getEmoji(0x1F55A)); // 11 clock
        stringList.add(getEmoji(0x1F55B)); // 12 clock
        put("wait", stringList);
        put("waiting", stringList);
        put("time", stringList);
        put("clock", stringList);
        put("years", stringList);


        clearList();
    }

    private String getEmoji(int unicode) {
        return new String(Character.toChars(unicode));
    }

    private void clearList() {
        stringList = new ArrayList<String>();
    }
}
