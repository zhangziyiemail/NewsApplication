package com.example.github.newsapplication.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 *   Created by zhangziyi on 9/23/20 02:23
 */
@Parcelize
@Entity(
    tableName = "user"
)
data class User(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "accout") var accout: String,
    @ColumnInfo(name = "password")var password :String,
    @ColumnInfo(name = "image")var image : String
): Parcelable
