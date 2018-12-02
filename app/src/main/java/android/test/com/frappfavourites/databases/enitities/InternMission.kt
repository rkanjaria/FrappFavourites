package android.test.com.frappfavourites.databases.enitities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import android.test.com.frappfavourites.classes.*
import com.google.gson.annotations.SerializedName

@Entity(tableName = INTERN_MISSION_TABLE)
data class InternMission(

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = ID) var imId: Int = 0,
        @SerializedName(value = "name", alternate = ["title"])
        @ColumnInfo(name = TITLE) var title: String? = null,
        @SerializedName(value = "description")
        @ColumnInfo(name = DESCRIPTION) var description: String? = null,
        @SerializedName(value = "logo")
        @ColumnInfo(name = LOGO) var logo: String? = null,
        @SerializedName(value = "views")
        @ColumnInfo(name = VIEWS) var views: Int = -1,
        @ColumnInfo(name = TYPE) var imType: String? = null,
        @ColumnInfo(name = IS_FAVOURITE) var isFavourite: Boolean = false,
        @Ignore var prop: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readByte() != 0.toByte(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(imId)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(logo)
        parcel.writeInt(views)
        parcel.writeString(imType)
        parcel.writeByte(if (isFavourite) 1 else 0)
        parcel.writeString(prop)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<InternMission> {
        override fun createFromParcel(parcel: Parcel): InternMission {
            return InternMission(parcel)
        }

        override fun newArray(size: Int): Array<InternMission?> {
            return arrayOfNulls(size)
        }
    }
}