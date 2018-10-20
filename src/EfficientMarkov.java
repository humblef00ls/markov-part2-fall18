import java.util.*;

public class EfficientMarkov extends BaseMarkov {
  private   Map<String, ArrayList<String>> myMap ;

    public EfficientMarkov() {
        this(3);
    }


    public EfficientMarkov(int order) {
        super(order);
        myMap= new HashMap<>();

    }
@Override
   public  void setTraining(String text)
{
myText = text;
myMap.clear();
    for(int x=0;x<myText.length()-myOrder+1;x++) {
        String tensei = myText.substring(x, x+myOrder);

            if(myMap.containsKey(tensei)) {
                if (x == myText.length() - myOrder)
                    myMap.get(tensei).add(PSEUDO_EOS);
                    myMap.get(tensei).add(myText.substring(x, x + 1));
            }
            else {

                ArrayList<String> z = new ArrayList<>();
                if (x == myText.length() - myOrder)
                z.add(PSEUDO_EOS);
                else
                    z.add(myText.substring(x,x+1));

                myMap.put(tensei,z);
            }



        }


}
@Override
    public ArrayList<String> getFollows(String key)
{
    ArrayList<String> ajax = new ArrayList<>();
    if (myMap.containsKey(key))
        return myMap.get(key);
    else
    throw new NoSuchElementException(key+" not in map");
}




}
