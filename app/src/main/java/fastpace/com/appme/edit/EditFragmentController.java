package fastpace.com.appme.edit;


import android.content.Context;
import android.content.Intent;
import android.view.View;

import java.util.ArrayList;

import fastpace.com.appme.LoadAppFinishedListener;
import fastpace.com.appme.database.AppDataLoader;
import fastpace.com.appme.model.AppMeButton;
import fastpace.com.appme.model.AppMeScreen;
import fastpace.com.appme.model.AppMeView;
import fastpace.com.appme.model.Position;
import fastpace.com.appme.utils.AppState;

public class EditFragmentController implements LoadAppFinishedListener {

    private static EditFragment mFragment;
    private static EditFragmentData mData;

    ViewMaker mViewMaker;

    private Context mContext;

    public EditFragmentController(Context context) {
        mContext = context;
        mViewMaker = new ViewMaker(context);
        mFragment = new EditFragment();
        mFragment.setFragController(this);
        mData = new EditFragmentData();

        loadData();
    }

    public void save() {
        ArrayList<AppMeScreen> screens = mData.getScreens();

        for (AppMeScreen screen : screens) {
           if (screen.hasChanged())
                IntentLauncher.saveScreen(mContext, screen);
        }
    }

    private void loadData() {
        AppMeScreen screen = new AppMeScreen("021jasfda00952", true);
        mData.addScreen(screen);
        mData.setCurrentScreen(screen.getUuid());

        if (AppDataLoader.getLock()) {
            Intent intent = new Intent(mContext, AppDataLoader.class);
            intent.setAction(AppDataLoader.LOAD_EDIT_DATA);
            intent.putExtra(AppDataLoader.APP_DATA, AppState.getPrivateUuid());

            AppDataLoader.setLoadListener(this);
            mContext.startService(intent);
        }
        else
            throw new RuntimeException("EditFragmentController tried to get AppDataLoader lock but it was already locked");
    }

    public void undo() {
        mData.undo();}

    public void loadMainScreen(AppMeScreen mainScreen) {
        mData.addScreen(mainScreen);
        mData.setCurrentScreen(mainScreen.getUuid());
        loadButtons(mData.getCurrentScreen().getButtons());
    }

    public void addScreen(AppMeScreen screen) {
        mData.addScreen(screen);
    }

    private void loadButtons(ArrayList<AppMeButton> buttons) {
        for (AppMeButton button : buttons) {
            mFragment.addButton(button.getAndroidButton(mContext));
        }
    }

    public View addView(int type) {
        mData.addView(new Position(200, 200, 200, 200), AppMeView.BUTTON);
        return mViewMaker.createView(type);
    }

    public EditFragment getEditFragment() {
        return mFragment;
    }

    @Override
    public void loadMainScreenFinished(AppMeScreen mainScreen) {
        loadMainScreen(mainScreen);
    }

    @Override
    public void loadScreenFinished(AppMeScreen screen) {
        addScreen(screen);
    }
}
