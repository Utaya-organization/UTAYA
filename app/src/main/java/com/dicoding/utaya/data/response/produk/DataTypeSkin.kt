package com.dicoding.utaya.data.response.produk

import com.google.gson.annotations.SerializedName

data class DataTypeSkin(

	@field:SerializedName("skinType")
	val skinType: String,

	@field:SerializedName("recommendations")
	val recommendations: List<RecommendationsItem>
)