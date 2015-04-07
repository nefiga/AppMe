package fastpace.com.appme.edit;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import fastpace.com.appme.AppMeFragment;
import fastpace.com.appme.R;
import fastpace.com.appme.ViewSpinner;
import fastpace.com.appme.database.AppDataLoader;
import fastpace.com.appme.utils.AppState;
import fastpace.com.appme.utils.Utils;

public class EditFragment extends AppMeFragment {
    private final int MENU_TIMER = 5000;

    private boolean mBottomMenuOpen;
    private BottomActionBar mBottomActionBar;

    private View mRootView;
    private Spinner mViewSpinner;
    private Button mAddView;
    private Button mUndo;

    private LinearLayout mAppContainer;

    private Drawable[] mViewImages;

    private static EditFragmentData mEditFragmentData;
    ViewMaker mViewMaker;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadData();

        mViewImages = new Drawable[]{getDrawable(R.drawable.button), getDrawable(R.drawable.text_view), getDrawable(R.drawable.edit_text),
                getDrawable(R.drawable.image_view), getDrawable(R.drawable.spinner), getDrawable(R.drawable.list_view)};
        mViewMaker = new ViewMaker(getActivity());
    }

    private void loadData() {
        Intent intent = new Intent(getActivity(), AppDataLoader.class);
        intent.setAction(AppDataLoader.LOAD_EDIT_DATA);
        intent.putExtra(AppDataLoader.APP_DATA, AppState.getPrivateUuid());

        getActivity().startService(intent);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        mRootView = inflater.inflate(R.layout.edit_layout, container, false);

        mViewSpinner = (Spinner) mRootView.findViewById(R.id.view_spinner);
        mAddView = (Button) mRootView.findViewById(R.id.add_view);
        mUndo = (Button) mRootView.findViewById(R.id.undo);

        mAppContainer = (LinearLayout) mRootView.findViewById(R.id.app_container);
        mAppContainer.setId(Utils.VIEW_HOLDER_ID);
        mAppContainer.setOnDragListener(new ViewDragListener());

        mViewSpinner.setAdapter(new ViewSpinner(getActivity(), R.layout.view_spinner, mViewImages));

        setListeners();

        return mRootView;
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    public void setListeners() {
        mAddView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAppContainer.addView(mViewMaker.createView(mViewSpinner.getSelectedItemPosition()));
            }
        });

        mUndo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditFragmentData.undo();
            }
        });

        mAppContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleMenus();
            }
        });
    }

    public void toggleMenus() {
        if (!mBottomMenuOpen) {
            mBottomActionBar = new BottomActionBar();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.animator.slide_in_bottom, R.animator.slide_out_bottom);
            transaction.add(android.R.id.content, mBottomActionBar).commit();
            mBottomMenuOpen = true;
            CloseMenuDelay();
        } else {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.animator.slide_in_bottom, R.animator.slide_out_bottom);
            transaction.remove(mBottomActionBar).commit();
            mBottomMenuOpen = false;
        }
    }

    public void CloseMenuDelay() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mBottomMenuOpen) {
                    toggleMenus();
                }
            }
        }, MENU_TIMER);
    }

    private Drawable getDrawable(int id) {
        return getActivity().getResources().getDrawable(id);
    }

    public static void setData(EditFragmentData editFragmentData) {
        mEditFragmentData = editFragmentData;
    }

    @Override
    public void addButton(Button button) {
        mAppContainer.addView(button);
    }
}
