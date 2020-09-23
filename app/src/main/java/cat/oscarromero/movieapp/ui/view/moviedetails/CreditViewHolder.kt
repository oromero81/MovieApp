package cat.oscarromero.movieapp.ui.view.moviedetails

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import cat.oscarromero.movieapp.R
import cat.oscarromero.movieapp.core.loadImageFromUrl
import cat.oscarromero.movieapp.ui.model.CreditModel
import kotlinx.android.synthetic.main.cell_credit.view.*

class CreditViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(model: CreditModel, itemSelected: (CreditModel) -> Unit) {
        with(itemView) {
            photoImageView.loadImageFromUrl(
                model.thumbnail,
                R.drawable.ph_credits,
                R.drawable.ph_credits
            )
            nameTextView.text = model.name
            jobTextView.text = model.job

            setOnClickListener { itemSelected.invoke(model) }
        }
    }
}
