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
            String tensei = myText.substring(x, x + myOrder);

            if (x == myText.length() - myOrder) {
                if (myMap.containsKey(tensei)) {
                    ArrayList<String> z = new ArrayList<>();
                    z = myMap.get(tensei);
                    z.add(PSEUDO_EOS);
                }
                else {
                    ArrayList<String> z = new ArrayList<>();
                    z.add(PSEUDO_EOS);
                    myMap.put(tensei,z);
                }
            } else {
                ArrayList<String> z = new ArrayList<>();
                if (myMap.containsKey(tensei)) {
                    z  = myMap.get(tensei);
                    z.add(myText.substring(x, x + 1));
                }
                else {
                    z.add(myText.substring(x, x + 1));
                    myMap.put(tensei, z);
                }

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
