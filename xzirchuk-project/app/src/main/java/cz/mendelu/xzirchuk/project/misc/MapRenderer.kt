package cz.mendelu.xzirchuk.project.misc

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import cz.mendelu.xzirchuk.project.R
import cz.mendelu.xzirchuk.project.database.Location


class CustomClusterRenderer constructor(
    var context: Context,
    map: GoogleMap,
    clusterManager: ClusterManager<Location>
) : DefaultClusterRenderer<Location>(context, map, clusterManager) {


    override fun shouldRenderAsCluster(cluster: Cluster<Location>): Boolean =
        cluster.size > 2 // Define when the manager should regroup nearby markers into a cluster

    override fun onBeforeClusterItemRendered(
        item: Location,
        markerOptions: MarkerOptions,
    ) {
        markerOptions.icon(iconget())

    }

    override fun setOnClusterItemClickListener(listener: ClusterManager.OnClusterItemClickListener<Location>?) {
        super.setOnClusterItemClickListener(listener)
        Log.d("bruh","sdlkj")
    }

    fun iconget(): BitmapDescriptor? {
        return bitmapDescriptorFromVector(context, R.drawable.favorites_fill)
    }

    override fun onClusterItemUpdated(item: Location, marker: Marker) {
        super.onClusterItemUpdated(item, marker)
    }

    override fun onBeforeClusterRendered(cluster: Cluster<Location>, markerOptions: MarkerOptions) {
        super.onBeforeClusterRendered(cluster, markerOptions)
    }

    override fun onClusterUpdated(cluster: Cluster<Location>, marker: Marker) {
        super.onClusterUpdated(cluster, marker)
    }




}



fun bitmapDescriptorFromVector(
    context: Context,
    vectorResId: Int
): BitmapDescriptor? {

    // retrieve the actual drawable
    val drawable = ContextCompat.getDrawable(context, vectorResId) ?: return null
    drawable.setBounds(0, 0, 60, 60)
    val bm = Bitmap.createBitmap(
        60,
        60,
        Bitmap.Config.ARGB_8888
    )

    // draw it onto the bitmap
    val canvas = android.graphics.Canvas(bm)
    drawable.draw(canvas)
    return BitmapDescriptorFactory.fromBitmap(bm)
}
