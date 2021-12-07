package br.com.app5m.pluralofertas.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.util.RecyclerItemClickListener
import br.com.app5m.pluralofertas.model.Category
class CategoriesSearchAdapter (private val context: Context, private val listCartegories: List<Category>,
                               private val clickOnListener: RecyclerItemClickListener
)
    : RecyclerView.Adapter<CategoriesSearchAdapter.CartegoriesViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartegoriesViewHolder {
        val listItem: View = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.adapter_categories_search,
                parent,
                false
            ) // vai conectar com os ids abaixo
        return CartegoriesViewHolder(listItem)


    }

    override fun onBindViewHolder(holder: CartegoriesViewHolder, position: Int) {
        val categori = listCartegories[position]

        holder.nameCategoriTv.text = Category().getNameCategory()


        holder.itemView.setOnClickListener { clickOnListener.onClickListenerCategoriesSearch(categori) }

    }

    override fun getItemCount(): Int {
        return listCartegories.size
    }

    class CartegoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameCategoriTv: TextView


        init {
            nameCategoriTv = itemView.findViewById(R.id.nameCategoriTv)



        }
    }
}