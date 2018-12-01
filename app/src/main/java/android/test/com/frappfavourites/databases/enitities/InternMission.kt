package android.test.com.frappfavourites.databases.enitities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
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
)