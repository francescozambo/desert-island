public class NPC extends Character {
    private String mapPiece;
    private boolean interact=false;
    NPC(String id, int mH, int d, String mapPiece) {
        super(id, mH, d);
        this.mapPiece = mapPiece;
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
}
