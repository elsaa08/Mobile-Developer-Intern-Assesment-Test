package com.capstone.suitmediatestmsib

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var nama : String,
    var image : String,
    var lastname : String,
    var email : String,
): Parcelable