import java.util.*;

public class EfficientMarkov extends BaseMarkov {
    private Map<String, ArrayList<String>> myMap;

    public EfficientMarkov() {
        this(3);
    }


    public EfficientMarkov(int order) {
        super(order);
        myMap = new HashMap<>();

    }

    @Override
    public void setTraining(String text) {
        myText = text;
        myMap.clear();
        for (int x = 0; x <= myText.length() - myOrder; x++) {
            String y = myText.substring(x, x + myOrder);
            if (myMap.containsKey(y)) {
                if (x == myText.length() - myOrder)
                    myMap.get(y).add(PSEUDO_EOS);
                else
                    myMap.get(y).add(myText.substring(x + myOrder, x + myOrder + 1));

            } else {
                ArrayList<String> z = new ArrayList<>();
                if (x == myText.length() - myOrder)
                    z.add(PSEUDO_EOS);
                else
                    z.add(myText.substring(x + myOrder, x + myOrder + 1));
                myMap.put(y, z);


            }


        }


    }

    @Override
    public ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        if (myMap.containsKey(key)) {
            follows = myMap.get(key); //constant time operation
        } else {
            throw new NoSuchElementException(key + "not in map"); // throws an exception if the key is not present in the map
        }
        return follows;
    }


}
