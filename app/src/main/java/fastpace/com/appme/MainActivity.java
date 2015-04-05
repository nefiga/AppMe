package fastpace.com.appme;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import fastpace.com.appme.database.ButtonTable;
import fastpace.com.appme.database.Provider;
import fastpace.com.appme.edit.EditFragment;
import fastpace.com.appme.model.AppMeButton;


public class MainActivity extends ActionBarActivity {

    Button mEditScreen;
    Button mViewApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mEditScreen = (Button) findViewById(R.id.edit_app);
        mViewApp = (Button) findViewById(R.id.view_app);

        setListeners();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setListeners() {
        mEditScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                EditFragment fragment = new EditFragment();
                fragment.addButtons(getButtons());
                transaction.replace(android.R.id.content, fragment);
                transaction.commit();
            }
        });
    }

    private ArrayList<AppMeButton> getButtons() {
        ArrayList<AppMeButton> buttons = new ArrayList<>();
        Cursor cursor = getContentResolver().query(Provider.BUTTON_CONTENT_URI, null, null, null, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int parent = cursor.getInt(cursor.getColumnIndex(ButtonTable.PARENT));
                int viewId = cursor.getInt(cursor.getColumnIndex(ButtonTable.VIEW_ID));
                String position = cursor.getString(cursor.getColumnIndex(ButtonTable.POSITION));
                String text = cursor.getString(cursor.getColumnIndex(ButtonTable.TEXT));
                AppMeButton button = new AppMeButton(parent, viewId, position);
                button.setText(text);
                buttons.add(button);
                cursor.moveToNext();
            }

        }
        cursor.close();
        return buttons;
    }
}
