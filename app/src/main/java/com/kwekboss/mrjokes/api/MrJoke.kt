package com.kwekboss.mrjokes.api

data class MrJoke(
    val category: String,
    val delivery: String,
    val flags: Flags,
    val id: Int,
    val lang: String,
    val setup: String,
    val type: String
)