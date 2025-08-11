package com.pe.mascotapp.vistas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pe.mascotapp.R
import com.pe.mascotapp.modelos.Review
import com.pe.mascotapp.vistas.adapters.ReviewAdapter

class ReviewActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var reviewAdapter: ReviewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        recyclerView = findViewById(R.id.rcvReviews)

        val reviews = obtenerReviews()

        reviewAdapter = ReviewAdapter(reviews)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = reviewAdapter
    }

    private fun obtenerReviews(): List<Review> {
        val reviews = mutableListOf<Review>()
        reviews.add(Review(0, "Excelente servicio, mi perro estuvo feliz", 4.5f, "2023-08-01"))
        reviews.add(Review(1, "Muy buena atención, pero el precio es algo elevado", 4.0f, "2023-08-02"))
        reviews.add(Review(2, "El lugar es limpio y seguro, pero podría mejorar en el servicio", 3.5f, "2023-08-03"))
        return reviews
    }

}
