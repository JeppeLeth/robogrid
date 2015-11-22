package com.jleth.projects.robogrid.android.data.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.jleth.projects.robogrid.android.model.Location;
import com.jleth.projects.robogrid.android.model.Size;
import com.jleth.projects.robogrid.android.ui.widget.SquareView;

public class GridAdapter extends RecyclerView.Adapter {

    private final Size mSize;
    private Listener mListener;
    private Location mLocation;

    public interface Listener {
        void onGridClick(int x, int y);
    }

    // Adapter constructor
    public GridAdapter(Size size) {
        this.mSize = size;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View layoutView = new SquareView(viewGroup.getContext());
        return new GridViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        final Location dataItem = mLocation;
        final GridViewHolder vh = (GridViewHolder) viewHolder;
        vh.setSelected(getPos(dataItem) == position, dataItem);
        vh.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    int clickPos = vh.getAdapterPosition();
                    mListener.onGridClick((clickPos) % mSize.getWidth(), clickPos / mSize.getWidth());
                }
            }
        });
    }

    private int getPos(Location loc) {
        int pos;
        if (loc == null) {
            pos = -1;
        } else {
            pos = (mSize.getWidth()) * loc.getY() + loc.getX();
        }
        return pos;
    }

    @Override
    public int getItemCount() {
        return (mSize.getHeight()) * (mSize.getWidth());
    }

    public void updateData(Location location) {
        if (location != null && (location.getY() < 0 || location.getX() < 0)) {
            location = null;
        }

        Location oldLocation = mLocation;
        mLocation = location;
        if (oldLocation == null && mLocation != null) {
            notifyItemChanged(getPos(mLocation));
        } else if (oldLocation != null && mLocation != null && !mLocation.equals(oldLocation)) {
            if (oldLocation.getY() == mLocation.getY() && oldLocation.getX() == mLocation.getX()) {
                notifyItemChanged(getPos(mLocation));
            } else {
                notifyItemMoved(getPos(oldLocation), getPos(mLocation));
            }
        } else if (oldLocation != null && mLocation == null) {
            notifyItemChanged(getPos(oldLocation));
        }
    }

    public GridAdapter setListener(Listener mListener) {
        this.mListener = mListener;
        return this;
    }

}