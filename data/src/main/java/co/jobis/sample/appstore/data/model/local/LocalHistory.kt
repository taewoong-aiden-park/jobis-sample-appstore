package co.jobis.sample.appstore.data.model.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import co.jobis.sample.appstore.domain.entity.History
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "history")
class LocalHistory(
    @PrimaryKey
    val terms: String,
    val timestamp: Long
) : Parcelable {
    fun toHistory() = History(
        terms = terms,
        timestamp = timestamp
    )
}