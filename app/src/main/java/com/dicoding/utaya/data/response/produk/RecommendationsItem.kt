package com.dicoding.utaya.data.response.produk

import com.google.gson.annotations.SerializedName

data class RecommendationsItem(

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("urlArticle")
	val urlArticle: String,

	@field:SerializedName("productName")
	val productName: String,

	@field:SerializedName("urlImage")
	val urlImage: String,

	@field:SerializedName("urlProduct")
	val urlProduct: String
)