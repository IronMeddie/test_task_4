package com.example.remote.data.dto

import com.example.models.FlashSale
import com.google.gson.annotations.SerializedName

data class ListSale(@SerializedName("flash_sale") val sales: List<FlashSale>)
