package com.lixlop.example.glide

import android.app.Application
import android.content.Context
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.lixlop.example.R
import java.util.*

/**
 * 图片加载
 **/
object GlideLoader {

    private const val QUALITY = 75
    private const val NETWORK_IMAGE_SIZE_FORMAT_1 = "%s?imageView2/2/w/%d/h/%d/format/webp/q/%d"
    private const val NETWORK_IMAGE_SIZE_FORMAT_2 = "%s?imageMogr2/format/webp/quality/%d"

    @JvmOverloads
    fun displayAsBitmap(context: Context,
                        uri: String?,
                        imageView: ImageView?,
                        width: Int = 0,
                        height: Int = 0,
                        radius: Int = 0,
                        placeholder: Int = R.mipmap.goods_image_loading,
                        circle : Boolean = false,
                        callback: LoaderCallback? = null) {
        displayWithGlide(context, uri, imageView, width = width, height = height, radius = radius, placeholder = placeholder, circle = circle, callback = callback)
    }

    @JvmOverloads
    fun displayAsGif(context: Context, uri: String?, imageView: ImageView?, width: Int = 0, height: Int = 0, radius: Int = 0, placeholder: Int = R.mipmap.goods_image_loading) {
        displayWithGlide(context, uri, imageView, width = width, height = height, radius = radius, placeholder = placeholder)
    }

    @JvmOverloads
    fun displayWithGlide(context: Context,
                         uri: String?,
                         imageView: ImageView?,
                         width: Int = 0,
                         height: Int = 0,
                         radius: Int = 0,
                         placeholder: Int = R.mipmap.goods_image_loading,
                         circle : Boolean = false,
                         callback: LoaderCallback? = null) {
        imageView?.let {
            val url = formatImage(uri, width, height)
            val applicationContext = if (context is Application) context else context.applicationContext

            var glide = Glide.with(applicationContext)
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .placeholder(placeholder)
                    .error(placeholder)
                    .transition(DrawableTransitionOptions.withCrossFade())

            if (width > 0 && height > 0) {
                glide = glide.override(width, height)
            }

            if (radius > 0) {
                glide = glide.transform(RoundedCorners(radius))
            }

            if (circle) {
                glide = glide.transform(CircleCrop())
            }

            if (callback != null) {
                glide = glide.addListener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        callback.onLoadFailed(e)
                        return false
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        callback.onResourceReady(resource)
                        return false
                    }
                })
            }

            glide.into(imageView)
        }
    }

    private fun formatImage(url: String?, width: Int = 0, height: Int = 0): String? {
        if (TextUtils.isEmpty(url)) {
            return "file://error"
        }

        if (url?.startsWith("drawable://") == true
                || url?.startsWith("file://") == true) {
            return url
        }

        //微信用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像）
        if (url?.startsWith("http://wx.qlogo.cn/") == true) {
            return url
        }

        // url 已经处理过
        if (url?.contains("/format/webp/") == true) {
            return url
        }

        if (width > 0) {
            return String.format(Locale.CHINA, NETWORK_IMAGE_SIZE_FORMAT_2, url, QUALITY)
        }

        return String.format(Locale.CHINA, NETWORK_IMAGE_SIZE_FORMAT_1, url, width, height, QUALITY)
    }
}

interface LoaderCallback {
    fun onLoadFailed(e: Exception?)

    fun onResourceReady(resource: Drawable?)
}

