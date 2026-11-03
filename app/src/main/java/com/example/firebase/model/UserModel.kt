package com.example.firebase.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class UserModel(
    val uid: String = "",
    val name: String = "",
    val email: String = ""
)
