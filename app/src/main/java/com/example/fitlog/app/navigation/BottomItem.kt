package com.example.fitlog.app.navigation

import com.example.fitlog.R

sealed class BottomItem (
    val title: String,
    val iconId: Int,
    val route: String
){
    data object Screen1: BottomItem("Screen1", R.mipmap.ic_dumbell, "screen_1")
    data object Screen2: BottomItem("Screen2", R.mipmap.ic_list, "screen_2")
    data object Screen3: BottomItem("Screen3", R.mipmap.ic_settings, "screen_3")
}