package com.karis.videoozone.models

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class YtResponse(
	val kind: String? = null,
	val nextPageToken: String? = null,
	val pageInfo: PageInfo? = null,
	val etag: String? = null,
	val items: List<ItemsItem?>? = null
) : Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readString(),
		parcel.readParcelable(PageInfo::class.java.classLoader),
		parcel.readString(),
		parcel.createTypedArrayList(ItemsItem)
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(kind)
		parcel.writeString(nextPageToken)
		parcel.writeParcelable(pageInfo, flags)
		parcel.writeString(etag)
		parcel.writeTypedList(items)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<YtResponse> {
		override fun createFromParcel(parcel: Parcel): YtResponse {
			return YtResponse(parcel)
		}

		override fun newArray(size: Int): Array<YtResponse?> {
			return arrayOfNulls(size)
		}
	}
}

data class JsonMemberDefault(
	val width: Int? = null,
	val url: String? = null,
	val height: Int? = null
) : Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readString(),
		parcel.readValue(Int::class.java.classLoader) as? Int
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeValue(width)
		parcel.writeString(url)
		parcel.writeValue(height)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<JsonMemberDefault> {
		override fun createFromParcel(parcel: Parcel): JsonMemberDefault {
			return JsonMemberDefault(parcel)
		}

		override fun newArray(size: Int): Array<JsonMemberDefault?> {
			return arrayOfNulls(size)
		}
	}
}

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
) : Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readParcelable(Localized::class.java.classLoader),
		parcel.readString(),
		parcel.readString(),
		parcel.readParcelable(Thumbnails::class.java.classLoader),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.createStringArrayList(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString()
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(publishedAt)
		parcel.writeParcelable(localized, flags)
		parcel.writeString(description)
		parcel.writeString(title)
		parcel.writeParcelable(thumbnails, flags)
		parcel.writeString(channelId)
		parcel.writeString(categoryId)
		parcel.writeString(channelTitle)
		parcel.writeStringList(tags)
		parcel.writeString(liveBroadcastContent)
		parcel.writeString(defaultAudioLanguage)
		parcel.writeString(defaultLanguage)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Snippet> {
		override fun createFromParcel(parcel: Parcel): Snippet {
			return Snippet(parcel)
		}

		override fun newArray(size: Int): Array<Snippet?> {
			return arrayOfNulls(size)
		}
	}
}

data class PageInfo(
	val totalResults: Int? = null,
	val resultsPerPage: Int? = null
) : Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readValue(Int::class.java.classLoader) as? Int
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeValue(totalResults)
		parcel.writeValue(resultsPerPage)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<PageInfo> {
		override fun createFromParcel(parcel: Parcel): PageInfo {
			return PageInfo(parcel)
		}

		override fun newArray(size: Int): Array<PageInfo?> {
			return arrayOfNulls(size)
		}
	}
}

data class Medium(
	val width: Int? = null,
	val url: String? = null,
	val height: Int? = null
) : Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readString(),
		parcel.readValue(Int::class.java.classLoader) as? Int
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeValue(width)
		parcel.writeString(url)
		parcel.writeValue(height)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Medium> {
		override fun createFromParcel(parcel: Parcel): Medium {
			return Medium(parcel)
		}

		override fun newArray(size: Int): Array<Medium?> {
			return arrayOfNulls(size)
		}
	}
}

data class Thumbnails(
	val standard: Standard? = null,
	val jsonMemberDefault: JsonMemberDefault? = null,
	val high: High? = null,
	val maxres: Maxres? = null,
	val medium: Medium? = null
) : Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readParcelable(Standard::class.java.classLoader),
		parcel.readParcelable(JsonMemberDefault::class.java.classLoader),
		parcel.readParcelable(High::class.java.classLoader),
		parcel.readParcelable(Maxres::class.java.classLoader),
		parcel.readParcelable(Medium::class.java.classLoader)
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeParcelable(standard, flags)
		parcel.writeParcelable(jsonMemberDefault, flags)
		parcel.writeParcelable(high, flags)
		parcel.writeParcelable(maxres, flags)
		parcel.writeParcelable(medium, flags)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Thumbnails> {
		override fun createFromParcel(parcel: Parcel): Thumbnails {
			return Thumbnails(parcel)
		}

		override fun newArray(size: Int): Array<Thumbnails?> {
			return arrayOfNulls(size)
		}
	}
}

data class High(
	val width: Int? = null,
	val url: String? = null,
	val height: Int? = null
) : Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readString(),
		parcel.readValue(Int::class.java.classLoader) as? Int
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeValue(width)
		parcel.writeString(url)
		parcel.writeValue(height)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<High> {
		override fun createFromParcel(parcel: Parcel): High {
			return High(parcel)
		}

		override fun newArray(size: Int): Array<High?> {
			return arrayOfNulls(size)
		}
	}
}

data class Standard(
	val width: Int? = null,
	val url: String? = null,
	val height: Int? = null
) : Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readString(),
		parcel.readValue(Int::class.java.classLoader) as? Int
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeValue(width)
		parcel.writeString(url)
		parcel.writeValue(height)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Standard> {
		override fun createFromParcel(parcel: Parcel): Standard {
			return Standard(parcel)
		}

		override fun newArray(size: Int): Array<Standard?> {
			return arrayOfNulls(size)
		}
	}
}

data class Maxres(
	val width: Int? = null,
	val url: String? = null,
	val height: Int? = null
) : Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readString(),
		parcel.readValue(Int::class.java.classLoader) as? Int
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeValue(width)
		parcel.writeString(url)
		parcel.writeValue(height)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Maxres> {
		override fun createFromParcel(parcel: Parcel): Maxres {
			return Maxres(parcel)
		}

		override fun newArray(size: Int): Array<Maxres?> {
			return arrayOfNulls(size)
		}
	}
}

data class Localized(
	val description: String? = null,
	val title: String? = null
) : Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readString()
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(description)
		parcel.writeString(title)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Localized> {
		override fun createFromParcel(parcel: Parcel): Localized {
			return Localized(parcel)
		}

		override fun newArray(size: Int): Array<Localized?> {
			return arrayOfNulls(size)
		}
	}
}

data class ItemsItem(
	val snippet: Snippet? = null,
	val kind: String? = null,
	val etag: String? = null,
	val id: String? = null,
	val statistics: Statistics?
) : Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readParcelable(Snippet::class.java.classLoader),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readParcelable(Statistics::class.java.classLoader)
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeParcelable(snippet, flags)
		parcel.writeString(kind)
		parcel.writeString(etag)
		parcel.writeString(id)
		parcel.writeParcelable(statistics, flags)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<ItemsItem> {
		override fun createFromParcel(parcel: Parcel): ItemsItem {
			return ItemsItem(parcel)
		}

		override fun newArray(size: Int): Array<ItemsItem?> {
			return arrayOfNulls(size)
		}
	}
}

@Parcelize
data class Statistics(
	val statistics: Statistics? = null,
	val dislikeCount: String? = null,
	val likeCount: String? = null,
	val viewCount: String? = null,
	val favoriteCount: String? = null,
	val commentCount: String? = null
) : Parcelable

