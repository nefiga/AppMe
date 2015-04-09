package fastpace.com.appme;

import fastpace.com.appme.model.AppMeScreen;

public interface LoadAppFinishedListener {
    public void loadMainScreenFinished(AppMeScreen mainScreen);
    public void loadScreenFinished(AppMeScreen screen);
}
