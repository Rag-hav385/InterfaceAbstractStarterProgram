
/**
 * Write a description of interface IMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public interface IMarkovModel {
    public void setTraining(String text);
    
    public void setRandom(int n);
    
    public String getRandomText(int numChars);
    
}
