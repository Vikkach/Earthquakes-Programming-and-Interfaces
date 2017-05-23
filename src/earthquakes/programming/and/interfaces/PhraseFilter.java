/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package earthquakes.programming.and.interfaces;

/**
 *
 * @author Виктория
 */
public class PhraseFilter implements Filter{
    
    private String wherePhrase;
    private String phrase;
    
    public PhraseFilter(String where, String phr){
        wherePhrase = where;
        phrase = phr;
    }
    
    @Override
     public boolean satisfies(QuakeEntry qe) { 
         String title = qe.getInfo();
         boolean containsState = false;
            if (wherePhrase.equals("start") && title.substring(0, phrase.length()).equals(phrase)) {
                containsState = true;
            }
            if (wherePhrase.equals("end") && title.substring(title.length() - phrase.length(), title.length()).equals(phrase)) {
                containsState = true;
            }
            if (wherePhrase.equals("any") && title.contains(phrase)) {
                containsState = true;
            }
        return containsState;
    }
    
    public String getName(){
        return "Phrase";
    }
    
}
