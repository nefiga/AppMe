package fastpace.com.appme.edit;


import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

import fastpace.com.appme.LoadAppFinishedListener;
import fastpace.com.appme.database.AppDataLoader;
import fastpace.com.appme.model.AppMeButton;
import fastpace.com.appme.model.AppMeScreen;
import fastpace.com.appme.utils.AppState;

public class EditFragmentController implements LoadAppFinishedListener {

    private static EditFragment mEditFragment;
    private static EditFragmentData mEditFragmentData;

    private Context mContext;

    public EditFragmentController(Context context) {
        mContext = context;
        mEditFragment = new EditFragment();
        mEditFragment.setFragController(this);
        mEditFragmentData = new EditFragmentData();

        loadData();
    }

    public void saveButtons() {
        IntentLauncher.saveButtons(mContext, mEditFragmentData.getCurrentScreen().getButtons());
    }

    private void loadData() {
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

    public void undo() {mEditFragmentData.undo();}

    public void loadMainScreen(AppMeScreen mainScreen) {
        mEditFragmentData.setMainScreen(mainScreen);
        loadButtons(mEditFragmentData.getCurrentScreen().getButtons());
    }

    public void addScreen(AppMeScreen screen) {
        mEditFragmentData.addScreen(screen);
    }

    private void loadButtons(ArrayList<AppMeButton> buttons) {
        for (AppMeButton button : buttons) {
            mEditFragment.addButton(button.getAndroidButton(mContext));
        }
    }

    public EditFragment getEditFragment() {
        return mEditFragment;
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
