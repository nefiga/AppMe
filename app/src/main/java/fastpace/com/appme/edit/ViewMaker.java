package fastpace.com.appme.edit;

import android.content.ClipData;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import fastpace.com.appme.model.AppMeView;
import fastpace.com.appme.utils.Utils;

public class ViewMaker {

    private Context mContext;

    public ViewMaker (Context context) {
        mContext = context;
    }

    public View createView(int type) {
        switch (type) {
            case AppMeView.BUTTON:
                return createButton();
            case AppMeView.TEXT_VIEW:
                return createTextView();
            case AppMeView.EDIT_TEXT:
                return createEditText();
            // Do we want to have an image view?
            case AppMeView.SPINNER:
                return createSpinner();
            case AppMeView.LIST_VIEW:
                return createListView();
            default:
                return createButton();
        }
    }

    private Button createButton() {
        Button button = new Button(mContext);
        button.setLayoutParams(new LinearLayout.LayoutParams(150, 75));
        button.setText("Button");
        button.setId(Utils.getNextId());
        setOnLongClickListener(button);

        return button;
    }

    private TextView createTextView(){
        TextView textView = new TextView(mContext);
        textView.setLayoutParams(new LinearLayout.LayoutParams(150, 75));
        textView.setText("Text View");
        textView.setTextSize(30);
        textView.setId(Utils.getNextId());
        setOnLongClickListener(textView);

        return textView;
    }

    private EditText createEditText() {
        EditText editText = new EditText(mContext);
        editText.setLayoutParams(new LinearLayout.LayoutParams(150, 75));
        editText.setText("Edit Text");
        editText.setTextSize(30);
        editText.setId(Utils.getNextId());
        setOnLongClickListener(editText);

        return editText;
    }

    private Spinner createSpinner() {
        Spinner spinner = new Spinner(mContext);
        spinner.setLayoutParams(new LinearLayout.LayoutParams(150, 75));
        spinner.setId(Utils.getNextId());
        setOnLongClickListener(spinner);

        return spinner;
    }

    private ListView createListView() {
        ListView listView = new ListView(mContext);
        listView.setLayoutParams(new ViewGroup.LayoutParams(150, 250));
        listView.setId(Utils.getNextId());
        setOnLongClickListener(listView);

        return listView;
    }

    private void setOnLongClickListener(final View view) {
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadow = new View.DragShadowBuilder(v);
                v.startDrag(data, shadow, v, 0);

                return true;
            }
        });
    }
}
