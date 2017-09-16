package com.jiechu.jiechupro.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.facebook.binaryresource.FileBinaryResource;
import com.facebook.cache.common.SimpleCacheKey;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.jiechu.jiechupro.view.photodraweeview.PhotoDraweeView;

import java.io.File;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * author:gaojingwei
 * date:2016/12/27
 * des: Fresco图片加载工具类
 */

public class FrescoUtils {

    /**
     * 设置通用图片显示
     *
     * @param url
     * @param draweeView
     */
    public static void setCommonPic(String url, SimpleDraweeView draweeView) {
        if (!TextUtils.isEmpty(url)) {
            try {
                Uri uri = Uri.parse(url);
                if (uri != null) {
                    draweeView.setImageURI(uri);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            draweeView.setImageURI("");
        }
    }


    /**
     * 获取加载图片的Bitmap
     *
     * @param url
     * @return
     */
    public static Bitmap returnBitmap(String url) {
        Bitmap bitmap = null;
        try {
            Uri uri = Uri.parse(url);
            FileBinaryResource resource = (FileBinaryResource) Fresco.getImagePipelineFactory()
                    .getMainFileCache().getResource(new SimpleCacheKey(uri.toString()));
            if (resource != null) {
                File file = resource.getFile();
                bitmap = BitmapFactory.decodeFile(file.getPath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;

    }

    /**
     * 首页底部导航菜单加载图片
     *
     * @param url
     * @param callback
     */
    public static void loadBitmap(String url, final FrescoBitmapCallback<Bitmap> callback) {
        if (!TextUtils.isEmpty(url)) {
            try {
                final Uri uri = Uri.parse(url);
                if (uri != null) {
                    ImageRequestBuilder requestBuilder = ImageRequestBuilder.newBuilderWithSource(uri);
                    ImageRequest imageRequest = requestBuilder.build();
                    DataSource<CloseableReference<CloseableImage>> dataSource = ImagePipelineFactory.getInstance().getImagePipeline().fetchDecodedImage(imageRequest, null);
                    dataSource.subscribe(new BaseBitmapDataSubscriber() {
                        @Override
                        protected void onNewResultImpl(final Bitmap bitmap) {
                            if (callback == null)
                                return;
                            if (bitmap != null && !bitmap.isRecycled()) {
                                handlerBackgroundTask(new Callable<Bitmap>() {
                                    @Override
                                    public Bitmap call() throws Exception {
                                        final Bitmap resultBitmap = bitmap.copy(bitmap.getConfig(), bitmap.isMutable());
                                        if (resultBitmap != null && !resultBitmap.isRecycled())
                                            postResult(resultBitmap, uri, callback);
                                        return resultBitmap;
                                    }
                                });
                            }
                        }

                        @Override
                        protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                            if (callback == null)
                                return;
                            Throwable throwable = null;
                            if (dataSource != null) {
                                throwable = dataSource.getFailureCause();
                            }
                            callback.onFailure(uri, throwable);
                        }

                        @Override
                        public void onCancellation(DataSource<CloseableReference<CloseableImage>> dataSource) {
                            super.onCancellation(dataSource);
                            if (callback == null)
                                return;
                            callback.onCancel(uri);
                        }
                    }, UiThreadImmediateExecutorService.getInstance());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * @param callable Callable
     * @param <T>      T
     * @return Future
     */
    private static ExecutorService executeBackgroundTask = Executors.newSingleThreadExecutor();

    private static <T> Future<T> handlerBackgroundTask(Callable<T> callable) {
        return executeBackgroundTask.submit(callable);
    }

    /**
     * 回调UI线程中去
     *
     * @param result   result
     * @param uri      uri
     * @param callback FrescoBitmapCallback
     * @param <T>      T
     */
    private static <T> void postResult(final T result, final Uri uri, final FrescoBitmapCallback<T> callback) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(uri, result);
            }
        });
    }


    public static void setBigPic(final Activity ctx, final String url, final PhotoDraweeView draweeView) {
        if (!TextUtils.isEmpty(url)) {
            draweeView.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.CENTER_INSIDE);
            try {
                PipelineDraweeControllerBuilder controller = Fresco.newDraweeControllerBuilder();
                controller.setUri(Uri.parse(url));
                controller.setOldController(draweeView.getController());
                controller.setControllerListener(new BaseControllerListener<ImageInfo>() {
                    @Override
                    public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
                        super.onFinalImageSet(id, imageInfo, animatable);
                        if (imageInfo == null || draweeView == null) {
                            return;
                        }
                        draweeView.update(DisplayUtil.getScreenWidth(ctx), imageInfo.getHeight());
                    }
                });
                draweeView.setController(controller.build());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
