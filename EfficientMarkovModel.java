import java.util.*;

public class EfficientMarkovModel extends AbstractMarkovModel {
    
    private int nOrder;
    private HashMap<String, ArrayList<String>> followUps;
    public EfficientMarkovModel() {
        myRandom = new Random();
        followUps = new HashMap<String , ArrayList<String>>();
    }
    public EfficientMarkovModel(int n){
        myRandom = new Random();
        nOrder = n;
        followUps = new HashMap<String , ArrayList<String>>();
    }
    public ArrayList<String> getArrayList(String key){
        return followUps.get(key);
    }
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    
    public void buildMap(){
        for(int i=0; i<myText.length() - (nOrder-1); i++){
            String key = myText.substring(i, i+nOrder);
            String follow = "";

            if(!(i+nOrder+1 > myText.length())){
                follow = myText.substring(i+nOrder, i+nOrder+1);
            }
            if(followUps.containsKey(key)){
                followUps.get(key).add(follow);
            }else{
                followUps.put(key, new ArrayList<>(Arrays.asList(follow)));
            }
        }
    }

    public String getRandomText(int numChars) {
        if (myText == null) { return ""; }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-nOrder);
        String key = myText.substring(index, index+nOrder);
        sb.append(key);

        for(int k=0; k < numChars-nOrder; k++){
            ArrayList<String> follows = getFollows(key);
            if(follows.size()==0){ break; }
            String next = follows.get(myRandom.nextInt(follows.size()));
            sb.append(next);
            key = key.substring(1) + next;
        }return sb.toString();
    }

    @Override
    public ArrayList<String> getFollows(String key) { 
        return followUps.get(key); 
    }

    public void printHashMapInfo(){ 
        System.out.println(followUps); 
    }

    public String toString(){
        buildMap();
        int maxSize = 0;
        for(String e: followUps.keySet()) {
            if (maxSize < followUps.get(e).size()) {
                maxSize = followUps.get(e).size();
            }
        }
        System.out.println("Number of keys: " + followUps.size() + "\nMax Num of ArrayList: " + maxSize);
        return "EfficientMarkovModel of order " + nOrder + ".";
    }
}


