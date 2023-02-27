
package com.example.remote.data.dto

import com.example.models.Latest
import com.google.gson.annotations.SerializedName



data class ListLatest(@SerializedName("latest") val latests: List<Latest>)
