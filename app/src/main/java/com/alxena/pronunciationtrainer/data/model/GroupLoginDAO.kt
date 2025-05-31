package com.alxena.pronunciationtrainer.data.model

class GroupLoginDAO(
    val name: String,
    val login_token: String,
    val group_token: String,
    val accepted: Boolean,
    val declined: Boolean
) {
}