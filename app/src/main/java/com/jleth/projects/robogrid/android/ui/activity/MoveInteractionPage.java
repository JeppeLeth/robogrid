package com.jleth.projects.robogrid.android.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
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
import com.jleth.projects.robogrid.android.model.Location;
import com.jleth.projects.robogrid.android.model.Size;
import com.jleth.projects.robogrid.android.ui.animation.AnimationHelper;
import com.jleth.projects.robogrid.android.ui.widget.ItemDecorationAlbumColumns;
import com.jleth.projects.robogrid.android.ui.widget.LocationDisplay;

/**
 * Interaction page
 */
public class MoveInteractionPage extends RelativeLayout {

    private LocationDisplay mDisplay;
    private RecyclerView mRecyclerView;
    private Listener mListener;
    private boolean blockTouch;

    public interface Listener {
        void onTurnLeft();

        void onTurnRight();

        void onForward();
    }

    public MoveInteractionPage(Context context) {
        super(context);
        initialize();
    }

    public MoveInteractionPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public MoveInteractionPage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MoveInteractionPage(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize();
    }

    private void initialize() {
        LayoutInflater.from(getContext()).inflate(R.layout.page_move_interaction, this, true);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mDisplay = (LocationDisplay) findViewById(R.id.locationTxt);
        OnClickListener btnListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                if (mListener != null) {
                    switch (v.getId()) {
                        case R.id.leftBtn:
                            mListener.onTurnLeft();
                            break;
                        case R.id.upBtn:
                            mListener.onForward();
                            break;
                        case R.id.rightBtn:
                            mListener.onTurnRight();
                            break;
                    }
                }
            }
        };
        findViewById(R.id.leftBtn).setOnClickListener(btnListener);
        findViewById(R.id.upBtn).setOnClickListener(btnListener);
        findViewById(R.id.rightBtn).setOnClickListener(btnListener);
    }

    public void setGridSize(Size gridSize) {
        adjustRecyclerBounds(gridSize);

        GridAdapter gridAdapter = new GridAdapter(gridSize);
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

    public void updateLocation(Location location){
        updateDisplay(location);
    }

    private void updateDisplay(Location location) {
        //noinspection ResourceType
        mDisplay.setLocation(location);
        getAdapter().updateData(location);
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


    public MoveInteractionPage setListener(Listener listener) {
        this.mListener = listener;
        return this;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return blockTouch || super.onInterceptTouchEvent(ev);
    }
}
