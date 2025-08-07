package com.pe.mascotapp.vistas.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pe.mascotapp.R
import com.pe.mascotapp.modelos.Review

class ReviewAdapter(private val reviewList: List<Review>) :
        RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {

override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
    val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_review, parent, false)
    return ReviewViewHolder(view)
}

override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
    val review = reviewList[position]
    holder.bind(review)
}

override fun getItemCount(): Int = reviewList.size

class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val reviewText: TextView = itemView.findViewById(R.id.tvReviewText)

    fun bind(review: Review) {
        reviewText.text = review.text
    }
}
}
