package fastpace.com.appme.edit;

import android.view.DragEvent;
import android.view.View;
import android.widget.Button;

import fastpace.com.appme.action.Action;
import fastpace.com.appme.action.ButtonAction;
import fastpace.com.appme.model.Position;

public class ViewDragListener implements View.OnDragListener{

    @Override
    public boolean onDrag(View v, DragEvent event) {

        switch (event.getAction()) {
            case DragEvent.ACTION_DROP:
                View droppedView = (View) event.getLocalState();
                droppedView.setX(event.getX() - droppedView.getWidth() /2);
                droppedView.setY(event.getY() - droppedView.getHeight() / 2);
                break;
            case DragEvent.ACTION_DRAG_ENDED:

                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                break;
            case DragEvent.ACTION_DRAG_EXITED:

                break;
            case DragEvent.ACTION_DRAG_LOCATION:

                break;
            case DragEvent.ACTION_DRAG_STARTED:
                View view = (View) event.getLocalState();
                if (view instanceof Button) {
                    Action buttonAction = new ButtonAction((Button) view);
                    buttonAction.setActionChangePosition(Position.getPositionString(view.getX(), view.getY(), view.getWidth(), view.getHeight()));
                    EditFragmentData.addAction(buttonAction);
                }
                break;
        }

        return true;
    }
}
