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

        myText=text;
        myMap.clear();

        for(int i=0;i<myText.length()-myOrder+1;i++) {
            String next = myText.substring(i, i+myOrder); //takes the letters from i(included) and i+myOrder(not included)
            if(i==myText.length()-myOrder) { // checks if it is the last letter in the string
                if(myMap.containsKey(next)) { // checks if the key is present in the map or not
                    ArrayList<String> follows= new ArrayList<String>();

                    follows= myMap.get(next);
                    follows.add(PSEUDO_EOS);
                }
                else {
                    ArrayList<String> follows= new ArrayList<String>();

                    follows.add(PSEUDO_EOS);
                    myMap.put(next, follows);
                }

            }
            else {
                if(myMap.containsKey(next)) {
                    ArrayList<String> follows= new ArrayList<String>();
                    String letter= myText.substring(i+myOrder,i+myOrder+1);
                    follows= myMap.get(next);
                    follows.add(letter);
                }
                else {
                    ArrayList<String> follows= new ArrayList<String>();
                    String letter= myText.substring(i+myOrder,i+myOrder+1);
                    follows.add(letter);
                    myMap.put(next, follows); //adds the updated list and the key to the map
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
