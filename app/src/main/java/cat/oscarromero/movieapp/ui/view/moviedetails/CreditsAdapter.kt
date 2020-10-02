package cat.oscarromero.movieapp.ui.view.moviedetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cat.oscarromero.movieapp.R
import cat.oscarromero.movieapp.ui.model.CreditModel

class CreditsAdapter(private val itemSelected: (CreditModel) -> Unit) :
    RecyclerView.Adapter<CreditViewHolder>() {

    private val items: MutableList<CreditModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreditViewHolder =
        CreditViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cell_credit, parent, false)
        )

    override fun getItemCount(): Int =
        items.size

    override fun onBindViewHolder(holder: CreditViewHolder, position: Int) {
        holder.bind(items[position], itemSelected)
    }

    fun loadItems(items: List<CreditModel>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
}
