package cz.mendelu.xzirchuk.project.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem


@Entity(tableName = "locations")
data class Location(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name="id") var uid:Long? = null,
    @ColumnInfo(name = "name") var name:String,
    @ColumnInfo(name = "geocoded_name") var geocoded_name:String?,
    @ColumnInfo(name = "latitude") var latitude: Double,
    @ColumnInfo(name = "longitude") var longitude: Double,
    @ColumnInfo(name = "description") var description:String?
):ClusterItem{
    override fun getPosition(): LatLng {
        return LatLng(latitude,longitude)
    }

    override fun getTitle(): String? {
        return name
    }

    override fun getSnippet(): String? = ""

    override fun getZIndex(): Float? {
        return 0f
    }

}


