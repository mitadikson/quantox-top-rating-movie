package com.develop.mita.ddimitrijevic.topratedmovie_quantox.RecyclerViewAdapters.RecyclerViewModel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.view.View;

import com.develop.mita.ddimitrijevic.topratedmovie_quantox.R;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.Utility.MyRecyclerViewViewHolder;
import com.facebook.shimmer.ShimmerFrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoadingMovieViewHolder extends MyRecyclerViewViewHolder {

    @BindView(R.id.item_card_loading_feed_card_shimmerFrameLayout_root)
    ShimmerFrameLayout container;

    public LoadingMovieViewHolder(View view, Activity activity) {
        super(view);
        ButterKnife.bind(this, view);

      /*  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            container.setTranslationZ(10f);
            container.setBackgroundColor(activity.getResources().getColor(R.color.white));
        } else {
            container.setBackground(activity.getResources().getDrawable(R.drawable.default_shedow_api_down));
        }*/
        container.startShimmerAnimation();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void setData(int position) {
        super.setData(position);
    }
}