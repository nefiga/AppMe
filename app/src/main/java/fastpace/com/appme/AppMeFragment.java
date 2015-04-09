package fastpace.com.appme;

import android.app.Fragment;
import android.view.View;
import android.widget.Button;

import fastpace.com.appme.model.AppMeScreen;

public abstract class AppMeFragment extends Fragment {

    public abstract void addButton(Button button);

    public abstract void loadScreen(AppMeScreen screen);
}
