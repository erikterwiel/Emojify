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

        stringList.add(getEmoji(0x1F4AF)); // 100
        put("be", stringList);
        put("real", stringList);
        put("genuine", stringList);
        put("100", stringList);
        put("legit", stringList);

        stringList.add(getEmoji(0x1F64C)); // raising hands
        stringList.add(getEmoji(0x270A));  // raised fist


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
        put("great", stringList);

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
        put("quickly", stringList);
        put("go", stringList);
        put("rush", stringList);
        put("rushed", stringList);
        put("go", stringList);


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
        put("forever", stringList);
        put("when", stringList);

        clearList();
        stringList.add(getEmoji(0x1F602)); // laughing crying face
        stringList.add(getEmoji(0x1F606)); // laughing closed eye face
        stringList.add(getEmoji(0x1F601)); // laughing face
        put("lol", stringList);
        put("laugh", stringList);
        put("laughed", stringList);
        put("lmao", stringList);
        put("lmfao", stringList);
        put("funny", stringList);
        put("hilarious", stringList);
        put("hilariously", stringList);

        put("leaked", stringList);

        put("yesterday", stringList);

        put("interest", stringList);
        put("interesting", stringList);

        put("stupid", stringList);
        put("stupidly", stringList);
        put("idiot", stringList);
        put("dumb", stringList);

        put("work", stringList);

        put("annoying", stringList);

        put("both", stringList);

        put("road", stringList);

        put("closed", stringList);

        put("marine", stringList);

        put("detour", stringList);

        put("stop", stringList);
        put("dont", stringList);
        put("don't", stringList);
        put("no", stringList);
        put("bad", stringList);
        put("not", stringList);
        put("scam", stringList);
        put("nothing", stringList);

        put("dirty", stringList);
        put("gross", stringList);
        put("disguisting", stringList);

        put("garbage", stringList);

        put("phone", stringList);

        put("car", stringList);

        put("pretty", stringList);
        put("beautiful", stringList);

        put("residential", stringList);
        put("residence", stringList);
        put("home", stringList);
        put("house", stringList);
        put("place", stringList);
        put("condo", stringList);
        put("apartment", stringList);

        put("prison", stringList);
        put("jail", stringList);
        put("penitentiary", stringList);
        put("dogpound", stringList);

        put("train", stringList);

        put("smoke", stringList);
        put("fire", stringList);
        put("flame", stringList);
        put("burn", stringList);

        put("look", stringList);
        put("scope", stringList);
        put("find", stringList);

        put("money", stringList);
        put("cash", stringList);
        put("sell", stringList);
        put("buy", stringList);
        put("trade", stringList);
        put("tax", stringList);
        put("rich", stringList);
        put("loaded", stringList);
        put("free", stringList);

        put("store", stringList);

        put("down", stringList);

        put("laptop", stringList);
        put("computer", stringList);

        put("tree", stringList);
        put("trees", stringList);
        put("nature", stringList);
        put("green", stringList);

        put("light", stringList);
        put("lights", stringList);
        put("idea", stringList);

        put("hit", stringList);
        put("bang", stringList);
        put("pow", stringList);
        put("smack", stringList);
        put("decked", stringList);
        put("smacked", stringList);
        put("crash", stringList);
        put("crashed", stringList);

        put("bottle", stringList);
        put("liq", stringList);
        put("liquor", stringList);
        put("drink", stringList);
        put("juice", stringList);
        put("water", stringList);
        put("liquid", stringList);
        put("alcohol", stringList);

        put("eat", stringList);
        put("restaurant", stringList);
        put("munch", stringList);
        put("food", stringList);

        put("message", stringList);
        put("text", stringList);

        put("bike",stringList);
        put("bicycle", stringList);

        put("hood", stringList);
        put("trap", stringList);
    }

    private String getEmoji(int unicode) {
        return new String(Character.toChars(unicode));
    }

    private void clearList() {
        stringList = new ArrayList<String>();
    }
}
