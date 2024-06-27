public class NPC extends Character {
    private String mapPiece;
    private boolean hide=true;
    private boolean interact=false;
    NPC(String id, int mH, int d, String mapPiece) {
        super(id, mH, d);
        this.mapPiece = mapPiece;
    }

    public String interact() {
        return mapPiece;
    }
    public boolean getHide() {
    	return hide;
    }
    public boolean getInteract() {
    	return interact;
    }
    public void setHide(){
    	hide=!hide;
    }
    public void setInteract() {
    	interact=!interact;
    }
}
