package th.skyousuke.braveland;


public enum ViewDirection {
    LEFT,
    RIGHT;

    public ViewDirection getOpposite() {
        return this == LEFT ? RIGHT : LEFT;
    }
}
