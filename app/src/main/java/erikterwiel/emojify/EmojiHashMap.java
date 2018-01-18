package erikterwiel.emojify;

import java.util.ArrayList;
import java.util.HashMap;

public class EmojiHashMap extends HashMap<String, ArrayList<String>> {

    private ArrayList<String> emojiList = new ArrayList<String>();

    public EmojiHashMap() {
        super();

        emojiList.add(getEmoji(0x1F602));
        emojiList.add(getEmoji(0x1F639));
        emojiList.add(getEmoji(0x1F44C));
        emojiList.add(getEmoji(0x1F64C));
        emojiList.add(getEmoji(0x1F4A5));
        emojiList.add(getEmoji(0x1F4A3));
        emojiList.add(getEmoji(0x1F525));
        emojiList.add(getEmoji(0x1F525));
        emojiList.add(getEmoji(0x1F4AF));
        emojiList.add(getEmoji(0x1F4AF));
        emojiList.add(getEmoji(0x1F605));
        emojiList.add(getEmoji(0x1F606));
        put("******", emojiList);


        clearList();
        emojiList.add(getEmoji(0x1F4AF)); // 100
        put("be", emojiList);
        put("real", emojiList);
        put("genuine", emojiList);
        put("100", emojiList);
        put("legit", emojiList);

        emojiList.add(getEmoji(0x1F64C)); // raising hands
        emojiList.add(getEmoji(0x270A));  // raised fist


        clearList();
        emojiList.add(getEmoji(0x1F426)); // bird
        emojiList.add(getEmoji(0x1F424)); // baby bird
        put("bird", emojiList);
        put("birds", emojiList);


        clearList();
        emojiList.add(getEmoji(0x2705));  // green check box
        emojiList.add(getEmoji(0x2611));  // blue check box
        emojiList.add(getEmoji(0x2714));  // green check mark
        put("check", emojiList);
        put("checked", emojiList);

        emojiList.add(getEmoji(0x1F44C)); // ok hand
        emojiList.add(getEmoji(0x1F44D)); // thumbs up
        put("good", emojiList);
        put("okay", emojiList);
        put("ok", emojiList);
        put("yes", emojiList);
        put("correct", emojiList);
        put("extremely", emojiList);
        put("great", emojiList);

        emojiList.add(getEmoji(0x1F449)); // pointing right
        put("right", emojiList);


        clearList();
        emojiList.add(getEmoji(0x1F3C3)); // running man
        put("run", emojiList);
        put("running", emojiList);

        emojiList.add(getEmoji(0x1F4A8)); // smoke
        put("rapid", emojiList);
        put("fast", emojiList);
        put("quick", emojiList);
        put("quickly", emojiList);
        put("go", emojiList);
        put("rush", emojiList);
        put("rushed", emojiList);
        put("go", emojiList);


        clearList();
        emojiList.add(getEmoji(0x23F0));  // alarm clock
        emojiList.add(getEmoji(0x1F550)); // 1 clock
        emojiList.add(getEmoji(0x1F551)); // 2 clock
        emojiList.add(getEmoji(0x1F552)); // 3 clock
        emojiList.add(getEmoji(0x1F553)); // 4 clock
        emojiList.add(getEmoji(0x1F554)); // 5 clock
        emojiList.add(getEmoji(0x1F555)); // 6 clock
        emojiList.add(getEmoji(0x1F556)); // 7 clock
        emojiList.add(getEmoji(0x1F557)); // 8 clock
        emojiList.add(getEmoji(0x1F558)); // 9 clock
        emojiList.add(getEmoji(0x1F559)); // 10 clock
        emojiList.add(getEmoji(0x1F55A)); // 11 clock
        emojiList.add(getEmoji(0x1F55B)); // 12 clock
        put("wait", emojiList);
        put("waiting", emojiList);
        put("time", emojiList);
        put("clock", emojiList);
        put("years", emojiList);
        put("forever", emojiList);
        put("when", emojiList);

        clearList();
        emojiList.add(getEmoji(0x1F602)); // laughing crying face
        emojiList.add(getEmoji(0x1F606)); // laughing closed eye face
        emojiList.add(getEmoji(0x1F601)); // laughing face
        put("lol", emojiList);
        put("laugh", emojiList);
        put("laughed", emojiList);
        put("lmao", emojiList);
        put("lmfao", emojiList);
        put("funny", emojiList);
        put("hilarious", emojiList);
        put("hilariously", emojiList);

        put("leaked", emojiList);

        put("yesterday", emojiList);

        put("interest", emojiList);
        put("interesting", emojiList);

        put("stupid", emojiList);
        put("stupidly", emojiList);
        put("idiot", emojiList);
        put("dumb", emojiList);

        put("work", emojiList);

        put("annoying", emojiList);

        put("both", emojiList);

        put("road", emojiList);

        put("closed", emojiList);

        put("marine", emojiList);

        put("detour", emojiList);

        put("stop", emojiList);
        put("dont", emojiList);
        put("don't", emojiList);
        put("no", emojiList);
        put("bad", emojiList);
        put("not", emojiList);
        put("scam", emojiList);
        put("nothing", emojiList);

        put("dirty", emojiList);
        put("gross", emojiList);
        put("disguisting", emojiList);

        put("garbage", emojiList);

        put("phone", emojiList);

        put("car", emojiList);

        put("pretty", emojiList);
        put("beautiful", emojiList);

        put("residential", emojiList);
        put("residence", emojiList);
        put("home", emojiList);
        put("house", emojiList);
        put("place", emojiList);
        put("condo", emojiList);
        put("apartment", emojiList);

        put("prison", emojiList);
        put("jail", emojiList);
        put("penitentiary", emojiList);
        put("dogpound", emojiList);

        put("train", emojiList);

        put("smoke", emojiList);
        put("fire", emojiList);
        put("flame", emojiList);
        put("burn", emojiList);

        put("look", emojiList);
        put("scope", emojiList);
        put("find", emojiList);

        put("money", emojiList);
        put("cash", emojiList);
        put("sell", emojiList);
        put("buy", emojiList);
        put("trade", emojiList);
        put("tax", emojiList);
        put("rich", emojiList);
        put("loaded", emojiList);
        put("free", emojiList);

        put("store", emojiList);

        put("down", emojiList);

        put("laptop", emojiList);
        put("computer", emojiList);

        put("tree", emojiList);
        put("trees", emojiList);
        put("nature", emojiList);
        put("green", emojiList);

        put("light", emojiList);
        put("lights", emojiList);
        put("idea", emojiList);

        put("hit", emojiList);
        put("bang", emojiList);
        put("pow", emojiList);
        put("smack", emojiList);
        put("decked", emojiList);
        put("smacked", emojiList);
        put("crash", emojiList);
        put("crashed", emojiList);

        put("bottle", emojiList);
        put("liq", emojiList);
        put("liquor", emojiList);
        put("drink", emojiList);
        put("juice", emojiList);
        put("water", emojiList);
        put("liquid", emojiList);
        put("alcohol", emojiList);

        put("eat", emojiList);
        put("restaurant", emojiList);
        put("munch", emojiList);
        put("food", emojiList);

        put("message", emojiList);
        put("text", emojiList);

        put("bike", emojiList);
        put("bicycle", emojiList);

        put("hood", emojiList);
        put("trap", emojiList);
    }

    private String getEmoji(int unicode) {
        return new String(Character.toChars(unicode));
    }

    private void clearList() {
        emojiList = new ArrayList<String>();
    }
}
