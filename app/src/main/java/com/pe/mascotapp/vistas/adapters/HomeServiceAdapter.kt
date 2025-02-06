package com.pe.mascotapp.vistas.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.pe.mascotapp.R
import com.pe.mascotapp.databinding.ServiceHomeHolderBinding

data class ServiceCategory(
    val id: Int,
    val name: String,
    val isSelected: Boolean,
    // Change it with urls if it's necessary
    @DrawableRes val selectedImage: Int,
    @DrawableRes val unselectedImage: Int
)

class HomeServiceAdapter(
    private val onSelectCategory: (Int) -> Unit,
) :
    ListAdapter<ServiceCategory, HomeServiceAdapter.HomeServiceAdapterHolder>(DiffCallBack) {

    class HomeServiceAdapterHolder(private val binding: ServiceHomeHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            element: ServiceCategory,
            onSelectCategory: (Int) -> Unit
        ) {
            binding.root.setOnClickListener {
                Log.d("petadapter", "Clickando nuevo adapter")
                if (!element.isSelected) {
                    onSelectCategory(element.id)
                }
            }
            binding.ivIcon.load(
                if (element.isSelected) element.selectedImage else element.unselectedImage
            ) {
                size(400, 400)
                scale(Scale.FIT)
            }
            binding.tvTitle.text = element.name

            binding.cardView.setCardBackgroundColor(
                if (element.isSelected) itemView.context.getColor(R.color.backgroundCard)
                else itemView.context.getColor(R.color.plomoq)
            )
            binding.tvTitle.setTextColor(
                if (element.isSelected) itemView.context.getColor(R.color.primaryColor)
                else itemView.context.getColor(R.color.plomoq)
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeServiceAdapterHolder {
        return HomeServiceAdapterHolder(
            ServiceHomeHolderBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeServiceAdapterHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current, onSelectCategory = onSelectCategory)
    }

    companion object {
        private val DiffCallBack = object : DiffUtil.ItemCallback<ServiceCategory>() {
            override fun areItemsTheSame(
                oldItem: ServiceCategory,
                newItem: ServiceCategory
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ServiceCategory,
                newItem: ServiceCategory
            ): Boolean {
                return oldItem.isSelected == newItem.isSelected
            }

        }
    }

}