package fastpace.com.appme.model;

public class Position {

    private final int POSITION_X = 0, POSITION_Y = 1, POSITION_WIDTH = 2, POSITION_HEIGHT = 3;

    private float mX;
    private float mY;
    private int mWidth;
    private int mHeight;

    public Position(String position) {
        String[] positionData = position.split(",");
        mX = Float.valueOf(positionData[POSITION_X]);
        mY = Float.valueOf(positionData[POSITION_Y]);
        mWidth = Integer.valueOf(positionData[POSITION_WIDTH]);
        mHeight = Integer.valueOf(positionData[POSITION_HEIGHT]);
    }
    
    public float getX() {
        return mX;
    }

    public float getY() {
        return mY;
    }

    public int getWidth() {
        return mWidth;
    }

    public int getHeight() {
        return mHeight;
    }
}
