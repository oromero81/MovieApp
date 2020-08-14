package cat.oscarromero.movieapp.core

import android.webkit.URLUtil
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.squareup.picasso.Picasso

fun ImageView.loadImageFromUrl(
    url: String,
    @DrawableRes errorRes: Int? = null,
    @DrawableRes placeholderRes: Int? = null
) {
    if (URLUtil.isValidUrl(url)) {
        Picasso.get()
            .load(url)
            .apply { errorRes?.let { this.error(it) } }
            .apply { placeholderRes?.let { this.placeholder(it) } }
            .into(this)
    } else {
        if (placeholderRes != null) {
            setImageResource(placeholderRes)
        } else if (errorRes != null) {
            setImageResource(errorRes)
        }
    }
}
