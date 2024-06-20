package com.dicoding.utaya.data.response.produk

import com.google.gson.annotations.SerializedName

data class ResponseProduk(

	@field:SerializedName("ResponseProduk")
	val responseProduk: List<ResponseProdukItem>
)