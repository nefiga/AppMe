package fastpace.com.appme.edit;

import android.view.DragEvent;
import android.view.View;

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
                break;
        }

        return true;
    }
}
