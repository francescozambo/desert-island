public class NPC extends Character {
    private String mapPiece;

    NPC(String id, int mH, int d, String mapPiece) {
        super(id, mH, d);
        this.mapPiece = mapPiece;
    }

    public String interact() {
        return mapPiece;
    }
}
