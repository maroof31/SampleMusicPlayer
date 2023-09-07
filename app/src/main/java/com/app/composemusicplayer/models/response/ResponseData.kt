 package com.app.composemusicplayer.models.response

import com.google.gson.annotations.SerializedName

data class ResponseData(
	@field:SerializedName("data")
	val data: List<DataItem?>? = null
)

data class DataItem(
	@field:SerializedName("date_updated")
	val dateUpdated: String? = null,

	@field:SerializedName("artist")
	val artist: String? = null,

	@field:SerializedName("date_created")
	val dateCreated: String? = null,

	@field:SerializedName("user_created")
	val userCreated: String? = null,

	@field:SerializedName("sort")
	val sort: Any? = null,

	@field:SerializedName("accent")
	val accent: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("cover")
	val cover: String? = null,

	@field:SerializedName("user_updated")
	val userUpdated: String? = null,

	@field:SerializedName("top_track")
	val topTrack: Boolean? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("status")
	val status: String? = null
)
