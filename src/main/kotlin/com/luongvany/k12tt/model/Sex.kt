package com.luongvany.k12tt.model
enum class Sex(val symbol: Char){
    NAM('\u2642'),
    NU('\u2640'),
    GIOITINHTHUBA('\u2600');

    override fun toString(): String {
        return "$symbol ${when(name){
            "NAM" -> "Nam"
            "NU" -> "Nữ"
            else -> "Không xác định"
        }
        }"
    }
}