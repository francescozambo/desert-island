package main;
public class NPC extends Character {
    private String mapPiece;
    private boolean interact=false;				//variabile booleana per memorizzare se è già stata eseguita o no un'interazione con l'NPC
    private boolean hide;
   
    NPC(String id, int mH, int d, String mapPiece) {		//costruttore
        super(id, mH, d);
        this.mapPiece = mapPiece;
        hide=false;
    }

    public String interact() {
        return mapPiece;
    }
    public boolean getInteract() {
    	return interact;
    }
    public void setInteract() {
    	interact=true;
    }
    public boolean getHide() {
    	return hide;
    }
    public void setHide() {
    	hide=!hide;
    }
}
