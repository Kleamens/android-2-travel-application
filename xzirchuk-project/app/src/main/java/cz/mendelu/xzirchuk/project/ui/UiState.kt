package cz.mendelu.xzirchuk.project.ui

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

open class UiState<T, E>
    (
    var loading: Boolean = true,
    var data: T? = null,
    var errors: E? = null) : Serializable,Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readByte() != 0.toByte(),
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
        p0.writeInt(p1)
    }

    companion object CREATOR : Parcelable.Creator<String> {
        override fun createFromParcel(parcel: Parcel): String {
            return parcel.toString()
        }

        override fun newArray(size: Int): Array<String?> {
            return arrayOfNulls(size)
        }
    }


}