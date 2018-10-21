import java.util.*;

public class EfficientWordMarkov extends BaseWordMarkov {

    private Map<WordGram, ArrayList<String>> myMap;

    public EfficientWordMarkov() {
        this(3);
    }

    public EfficientWordMarkov(int order) {
        super(order);
        myMap = new HashMap<>();
    }

    @Override
    public void setTraining(String text) {
        myMap.clear();
        myWords = text.split("\\s+");

        for (int x = 0; x <= myWords.length - myOrder; x++) {
            WordGram y = new WordGram(myWords, x, myOrder);
            if (myMap.containsKey(y)) {
                if (x+myOrder==myWords.length)
                    myMap.get(y).add(PSEUDO_EOS);
                else
                    myMap.get(y).add(myWords[x+myOrder]);
            }
            else {

                ArrayList<String> z = new ArrayList<>();
                if (x+myOrder==myWords.length)
                    z.add(PSEUDO_EOS);
                else
                    z.add(myWords[x+myOrder]);
                myMap.put(y, z);
            }
        }
    }

    @Override
    public ArrayList<String> getFollows(WordGram key) {

        if (myMap.containsKey(key))
            return myMap.get(key);
        else
            throw new NoSuchElementException(key + "not in map");
    }


}




