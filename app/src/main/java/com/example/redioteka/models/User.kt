package com.example.redioteka.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("is_sub")
    val subscriber: Boolean
) {
    constructor() : this(1, "name", "email", "avatar", false)
}

data class UserAuth(
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("confirm_password")
    val passwordConfirm: String,
) {
    constructor() : this("name", "email", "pass", "confirm")
}

data class UserAvatar(
    @SerializedName("user_avatar")
    val avatar: String
)
