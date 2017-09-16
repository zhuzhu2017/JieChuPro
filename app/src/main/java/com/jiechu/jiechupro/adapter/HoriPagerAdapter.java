package com.jiechu.jiechupro.adapter;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jiechu.jiechupro.R;
import com.jiechu.jiechupro.model.XCPicBean;
import com.jiechu.jiechupro.ui.PicActivity;
import com.jiechu.jiechupro.utils.DisplayUtil;
import com.jiechu.jiechupro.utils.FrescoUtils;

import java.util.List;

/**
 * Created by allen on 2017/9/16.
 */

public class HoriPagerAdapter extends PagerAdapter {

    private Activity ctx;
    private List<XCPicBean.Pictures> picturesList;

    public HoriPagerAdapter(Activity ctx, List<XCPicBean.Pictures> picturesList) {
        this.ctx = ctx;
        this.picturesList = picturesList;
    }

    @Override
    public int getCount() {
        return picturesList == null ? 0 : picturesList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        SimpleDraweeView imageView = new SimpleDraweeView(ctx);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(DisplayUtil.dip2px(ctx, 500), ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(layoutParams);
        RoundingParams roundingParams = RoundingParams.fromCornersRadius(5f);
        roundingParams.setRoundAsCircle(false);
        roundingParams.setCornersRadii(5f, 5f, 5f, 5f);
        GenericDraweeHierarchyBuilder builder =
                new GenericDraweeHierarchyBuilder(ctx.getResources());
        GenericDraweeHierarchy hierarchy = builder
                .setFadeDuration(300)
                .setRoundingParams(roundingParams)
                .setPlaceholderImage(R.drawable.deafult_image)
                .setActualImageScaleType(ScalingUtils.ScaleType.CENTER_CROP)
                .setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_XY)
                .setFailureImage(R.drawable.deafult_image)
                .setFailureImageScaleType(ScalingUtils.ScaleType.FIT_XY)
                .build();
        imageView.setHierarchy(hierarchy);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到大图页面
                Intent picIntent = new Intent(ctx, PicActivity.class);
                picIntent.putExtra("url", picturesList.get(position).getUrl_CZXL());
                ctx.startActivity(picIntent);
            }
        });
        XCPicBean.Pictures pictures = picturesList.get(position);
        //设置图片显示
        FrescoUtils.setCommonPic(pictures.getUrl_CZXL(), imageView);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
