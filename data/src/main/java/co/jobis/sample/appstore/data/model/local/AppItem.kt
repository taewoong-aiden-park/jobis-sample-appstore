package co.jobis.sample.appstore.data.model.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "apps")
data class AppItem(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val name: String,
) : Parcelable