package com.jleth.projects.robogrid.android.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.jleth.projects.robogrid.android.R;
import com.jleth.projects.robogrid.android.data.adapter.GridAdapter;
import com.jleth.projects.robogrid.android.data.storage.SavedState;
import com.jleth.projects.robogrid.android.model.Direction;
import com.jleth.projects.robogrid.android.model.Location;
import com.jleth.projects.robogrid.android.model.Size;
import com.jleth.projects.robogrid.android.ui.animation.AnimationHelper;
import com.jleth.projects.robogrid.android.ui.widget.ItemDecorationAlbumColumns;
import com.jleth.projects.robogrid.android.ui.widget.LocationDisplay;

/**
 * Interaction page
 */
public class StartLocationPage extends RelativeLayout {

    private int mX = -1;
    private int mY = -1;
    private int mDirection = Direction.UNKNOWN;
    private LocationDisplay mDisplay;
    private RecyclerView mRecyclerView;
    private Listener mListener;
    private boolean blockTouch;

    public interface Listener {
        void onLocationChosen(Location location);
    }

    public StartLocationPage(Context context) {
        super(context);
        initialize();
    }

    public StartLocationPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public StartLocationPage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public StartLocationPage(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize();
    }

    private void initialize() {
        LayoutInflater.from(getContext()).inflate(R.layout.page_start_location, this, true);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mDisplay = (LocationDisplay) findViewById(R.id.locationTxt);
        OnClickListener btnListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.leftBtn:
                        mDirection = Direction.WEST;
                        break;
                    case R.id.upBtn:
                        mDirection = Direction.NORTH;
                        break;
                    case R.id.downBtn:
                        mDirection = Direction.SOUTH;
                        break;
                    case R.id.rightBtn:
                        mDirection = Direction.EAST;
                        break;
                }
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                updateDisplay();
            }
        };
        findViewById(R.id.leftBtn).setOnClickListener(btnListener);
        findViewById(R.id.upBtn).setOnClickListener(btnListener);
        findViewById(R.id.downBtn).setOnClickListener(btnListener);
        findViewById(R.id.rightBtn).setOnClickListener(btnListener);

        findViewById(R.id.checkBtn).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                    mListener.onLocationChosen(new Location(mX, mY, mDirection));
                }
            }
        });
    }

    public void setGridSize(Size gridSize) {
        adjustRecyclerBounds(gridSize);

        GridAdapter gridAdapter = new GridAdapter(gridSize);
        gridAdapter.setListener(new GridAdapter.Listener() {
            @Override
            public void onGridClick(int x, int y) {
                mX = x;
                mY = y;
                updateDisplay();
            }
        });
        mRecyclerView.setAdapter(gridAdapter);

        mRecyclerView.setHasFixedSize(true);
        // Setting the layoutManager
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), gridSize.getWidth()));
        mRecyclerView.addItemDecoration(
                new ItemDecorationAlbumColumns((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1.5f, getResources().getDisplayMetrics()), gridSize.getWidth()));
    }

    private GridAdapter getAdapter() {
        return (GridAdapter) mRecyclerView.getAdapter();
    }

    private void adjustRecyclerBounds(Size gridSize) {
        if (gridSize.getHeight() > gridSize.getWidth()) {
            ViewGroup.LayoutParams params = mRecyclerView.getLayoutParams();
            params.width = (int) (((double) params.height) * 0.96 * ((double) gridSize.getWidth() / (double) gridSize.getHeight()));
            mRecyclerView.setLayoutParams(params);
        }
    }

    private void updateDisplay() {
        //noinspection ResourceType
        Location location = new Location(mX, mY, mDirection);
        mDisplay.setLocation(location);
        getAdapter().updateData(location);
        if (Location.isLocationValid(location)) {
            findViewById(R.id.checkBtn).setVisibility(VISIBLE);
        } else {
            findViewById(R.id.checkBtn).setVisibility(GONE);
        }
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);

        Bundle b = new Bundle();
        if (mX >= 0 || mY >=0 || mDirection > Direction.UNKNOWN) {
            b.putParcelable("ARGS_TEMP_LOCATION", new Location(mX, mY, mDirection));
        }
        ss.setBundle(b);
        return ss;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

        Location loc = ss.getBundle().getParcelable("ARGS_TEMP_LOCATION");
        if (loc != null) {
            mX = loc.getX();
            mY = loc.getY();
            mDirection = loc.getDirection();
            updateDisplay();
        }
    }


    public void animateRevealContent(final Runnable endListener) {
        AnimationHelper.startAnimateRevealGridContent(this, new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                blockTouch = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                blockTouch = false;
                if (endListener != null) {
                    endListener.run();
                }
            }
        });
    }

    public void animateDisappearContent(@Nullable final Runnable endListener) {
        AnimationHelper.startAnimateDisappearGridContent(this, new AnimatorListenerAdapter() {

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                blockTouch = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                blockTouch = false;
                if (endListener != null) {
                    endListener.run();
                }
            }
        });
    }

    public StartLocationPage setListener(Listener listener) {
        this.mListener = listener;
        return this;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return blockTouch || super.onInterceptTouchEvent(ev);
    }

}
