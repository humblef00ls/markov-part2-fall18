/**
 * EfficientMarkov helps in increasing the speed of the program
 *
 * @author Himanshu Jain
 *
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 *
 * inherits the class BaseMarkov
 * instance variable : myMap
 */

public class EfficientMarkov extends BaseMarkov {
    private HashMap<String,ArrayList<String>> myMap;

    /**
     * Constructor EfficientMarkov() calls the Constructor with a parameter
     */

    public EfficientMarkov() {
        this(3);
    }

    /**
     * Constructor initializes the HashMap variable with a new HashMap
     * @param order helps in making a parent class with the given integer
     */
    public EfficientMarkov(int order) {
        super(order); //initializes the myRandom instance variable
        myMap= new HashMap<String,ArrayList<String>>();
    }

    /**
     * overrides method setTraining in the BaseMarkov
     * clears the variable myMap
     * iterates through the text and gets keys of order myOrder
     * adds characters to the ArrayList and to the map with the associated ArrayList as a value
     * creates a new key if they it is not present and updates the value if the key is present
     * puts PSEUDO_EOS in the end of the ArrayList as it helps in knowing the end
     * @param text initializes the variable myText and is the input text which the loop has to iterate through
     */
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
    /**
     * overrides the method in BaseMarkov
     * iterates through the map and checks if the key is present
     * @param key is the string which is used as a key in the map
     * @return ArrayList associated with the given key
     */
    @Override

    public ArrayList<String> getFollows(String key){
        ArrayList<String> follows= new ArrayList<String>();
        if(myMap.containsKey(key)) {
            follows=myMap.get(key); //constant time operation
        }
        else {
            throw new NoSuchElementException(key+"not in map"); // throws an exception if the key is not present in the map
        }
        return follows;
    }
}
