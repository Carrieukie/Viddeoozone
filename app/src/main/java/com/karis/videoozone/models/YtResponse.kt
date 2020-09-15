package com.karis.videoozone.models

data class YtResponse(
	val kind: String? = null,
	val nextPageToken: String? = null,
	val pageInfo: PageInfo? = null,
	val etag: String? = null,
	val items: List<ItemsItem?>? = null
)

data class JsonMemberDefault(
	val width: Int? = null,
	val url: String? = null,
	val height: Int? = null
)

data class Snippet(
	val publishedAt: String? = null,
	val localized: Localized? = null,
	val description: String? = null,
	val title: String? = null,
	val thumbnails: Thumbnails? = null,
	val channelId: String? = null,
	val categoryId: String? = null,
	val channelTitle: String? = null,
	val tags: List<String?>? = null,
	val liveBroadcastContent: String? = null,
	val defaultAudioLanguage: String? = null,
	val defaultLanguage: String? = null
)

data class PageInfo(
	val totalResults: Int? = null,
	val resultsPerPage: Int? = null
)

data class Medium(
	val width: Int? = null,
	val url: String? = null,
	val height: Int? = null
)

data class Thumbnails(
	val standard: Standard? = null,
	val jsonMemberDefault: JsonMemberDefault? = null,
	val high: High? = null,
	val maxres: Maxres? = null,
	val medium: Medium? = null
)

data class High(
	val width: Int? = null,
	val url: String? = null,
	val height: Int? = null
)

data class Standard(
	val width: Int? = null,
	val url: String? = null,
	val height: Int? = null
)

data class Maxres(
	val width: Int? = null,
	val url: String? = null,
	val height: Int? = null
)

data class Localized(
	val description: String? = null,
	val title: String? = null
)

data class ItemsItem(
	val snippet: Snippet? = null,
	val kind: String? = null,
	val etag: String? = null,
	val id: String? = null
)

