package com.karis.videoozone.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class YoutubeObject(

	@field:SerializedName("kind")
	val kind: String? = null,

	@field:SerializedName("nextPageToken")
	val nextPageToken: String? = null,

	@field:SerializedName("prevPageToken")
	val prevPageToken: String? = null,

	@field:SerializedName("pageInfo")
	val pageInfo: PageInfo? = null,

	@field:SerializedName("etag")
	val etag: String? = null,

	@field:SerializedName("items")
	val items: List<Videoitem>

) : Parcelable

@Parcelize
data class Thumbnails(

	@field:SerializedName("standard")
	val standard: Standard? = null,

	@field:SerializedName("default")
	val jsonMemberDefault: JsonMemberDefault? = null,

	@field:SerializedName("high")
	val high: High? = null,

	@field:SerializedName("maxres")
	val maxres: Maxres? = null,

	@field:SerializedName("medium")
	val medium: Medium? = null
) : Parcelable

@Parcelize
data class High(

	@field:SerializedName("width")
	val width: Int? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("height")
	val height: Int? = null
) : Parcelable

@Parcelize
data class Standard(

	@field:SerializedName("width")
	val width: Int? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("height")
	val height: Int? = null
) : Parcelable

@Parcelize
data class Maxres(

	@field:SerializedName("width")
	val width: Int? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("height")
	val height: Int? = null
) : Parcelable

@Parcelize
data class Statistics(

	@field:SerializedName("dislikeCount")
	val dislikeCount: String? = null,

	@field:SerializedName("likeCount")
	val likeCount: String? = null,

	@field:SerializedName("viewCount")
	val viewCount: String? = null,

	@field:SerializedName("favoriteCount")
	val favoriteCount: String? = null,

	@field:SerializedName("commentCount")
	val commentCount: String? = null
) : Parcelable

@Parcelize
data class Videoitem(

	@field:SerializedName("snippet")
	val snippet: Snippet? = null,

	@field:SerializedName("kind")
	val kind: String? = null,

	@field:SerializedName("etag")
	val etag: String? = null,

	@field:SerializedName("id")
	val id: String ,

	@field:SerializedName("statistics")
	val statistics: Statistics? = null
) : Parcelable

@Parcelize
data class JsonMemberDefault(

	@field:SerializedName("width")
	val width: Int? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("height")
	val height: Int? = null
) : Parcelable

@Parcelize
data class PageInfo(

	@field:SerializedName("totalResults")
	val totalResults: Int? = null,

	@field:SerializedName("resultsPerPage")
	val resultsPerPage: Int? = null
) : Parcelable

@Parcelize
data class Snippet(

	@field:SerializedName("defaultLanguage")
	val defaultLanguage: String? = null,

	@field:SerializedName("publishedAt")
	val publishedAt: String? = null,

	@field:SerializedName("defaultAudioLanguage")
	val defaultAudioLanguage: String? = null,

	@field:SerializedName("localized")
	val localized: Localized? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("thumbnails")
	val thumbnails: Thumbnails? = null,

	@field:SerializedName("channelId")
	val channelId: String? = null,

	@field:SerializedName("categoryId")
	val categoryId: String? = null,

	@field:SerializedName("channelTitle")
	val channelTitle: String? = null,

	@field:SerializedName("tags")
	val tags: List<String?>? = null,

	@field:SerializedName("liveBroadcastContent")
	val liveBroadcastContent: String? = null
) : Parcelable

@Parcelize
data class Localized(

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("title")
	val title: String? = null
) : Parcelable

@Parcelize
data class Medium(

	@field:SerializedName("width")
	val width: Int? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("height")
	val height: Int? = null
) : Parcelable
