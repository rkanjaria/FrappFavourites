package android.test.com.frappfavourites.databases.enitities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.test.com.frappfavourites.classes.*
import com.google.gson.annotations.SerializedName

@Entity(tableName = INTERN_MISSION_TABLE)
data class InternMission(

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = ID) val imId: Int,
        @SerializedName(value = "name", alternate = ["title"])
        @ColumnInfo(name = TITLE) val title: String?,
        @SerializedName(value = "description")
        @ColumnInfo(name = DESCRIPTION) val description: String?,
        @SerializedName(value = "logo")
        @ColumnInfo(name = LOGO) val logo: String?,
        @SerializedName(value = "views")
        @ColumnInfo(name = VIEWS) val views: Int,
        @ColumnInfo(name = TYPE) var imType: String?,
        @ColumnInfo(name = IS_FAVOURITE) var isFavourite: Boolean = false
)