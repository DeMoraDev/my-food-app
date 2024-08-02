package com.example.soulapi.util

class Utils {
    companion object {
        fun formatPrice(price: Double): String {
            return if (price % 1.0 == 0.0) {
                price.toInt().toString() + "€"
            } else {
                String.format("%.2f", price) + "€"
            }
        }
    }
}