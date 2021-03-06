package fastpace.com.appme;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import fastpace.com.appme.edit.EditFragmentController;


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
                loadEditFragment();
            }
        });
    }

    private void loadEditFragment() {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        EditFragmentController fragController = new EditFragmentController(this);
        transaction.replace(android.R.id.content, fragController.getEditFragment());
        transaction.commit();
    }
}
