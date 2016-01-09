package com.l000phone.themovietime.firstpage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;;
import android.view.View;

/**
 * 自定义RecyclerView
 *
 * 
 */
public class MyRecyclerView extends RecyclerView{

    private View mCurrentView;
    private ItemScrollCallback mItemCallback;

    public MyRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public void setItemScrollCallback(ItemScrollCallback itemScrollCallback){

        mItemCallback = itemScrollCallback;

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        mCurrentView = getChildAt(0);

        if (mItemCallback != null){
            mItemCallback.onChange(mCurrentView, getChildLayoutPosition(mCurrentView));
        }

        super.onLayout(changed, l, t, r, b);
    }

    @Override
    public void onScrolled(int dx, int dy) {
        View view = getChildAt(0);

        if (view!=null && view!=mCurrentView){

            mCurrentView = view;
            mItemCallback.onChange(mCurrentView, getChildLayoutPosition(mCurrentView));
        }

        super.onScrolled(dx, dy);
    }

    @Override
    public void addOnScrollListener(OnScrollListener listener) {
        super.addOnScrollListener(listener);
    }

    public interface ItemScrollCallback{

        public void onChange(View view, int postion);

    }

}
