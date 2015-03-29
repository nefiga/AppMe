package fastpace.com.appme.edit;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import fastpace.com.appme.R;
import fastpace.com.appme.ViewSpinner;

public class EditFragment extends Fragment {
    private final int MENU_TIMER = 5000;

    private boolean mBottomMenuOpen;
    private BottomActionBar mBottomActionBar;

    private View mRootView;
    private Spinner mViewSpinner;
    private Button mAddView;
    private Button mUndo;

    private LinearLayout mAppContainer;

    private Drawable[] mViewImages;

    ViewMaker mViewMaker;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewImages = new Drawable[]{getDrawable(R.drawable.button), getDrawable(R.drawable.text_view), getDrawable(R.drawable.edit_text),
                getDrawable(R.drawable.image_view), getDrawable(R.drawable.spinner), getDrawable(R.drawable.list_view)};
        mViewMaker = new ViewMaker(getActivity());
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
        mAppContainer.setOnDragListener(new ViewDragListener());

        mViewSpinner.setAdapter(new ViewSpinner(getActivity(), R.layout.view_spinner, mViewImages));

        setListeners();

        return mRootView;
    }

    public void setListeners() {
        mAddView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAppContainer.addView(mViewMaker.createView(mViewSpinner.getSelectedItemPosition()));
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
}
