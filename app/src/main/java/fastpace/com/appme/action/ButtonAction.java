package fastpace.com.appme.action;

import android.view.ViewGroup;
import android.widget.Button;

import fastpace.com.appme.model.Position;

public class ButtonAction extends Action {

    private Button mButton;

    public ButtonAction(Button button) {
        mButton = button;
    }

    @Override
    public void undo() {
        super.undo();
        switch (mActionType) {
            case CREATE:
                ((ViewGroup) mButton.getParent()).removeView(mButton);
                break;
            case CHANGE_TEXT:
                mButton.setText(mOldText);
                break;
            case CHANGE_POSITION:
                Position position = new Position(mOldPosition);
                mButton.setX(position.getX());
                mButton.setY(position.getY());
                mButton.setWidth(position.getWidth());
                mButton.setHeight(position.getHeight());
                break;
            case CHANGE_DRAWABLE:
                //TODO make a class that can retrieve a drawable based on a ID
                break;
        }
    }
}
