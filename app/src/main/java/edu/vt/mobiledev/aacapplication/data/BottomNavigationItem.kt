package edu.vt.mobiledev.aacapplication.data

import edu.vt.mobiledev.aacapplication.R

data class BottomNavigationItem(
    val route: String,
    val label: String,
    val icon: Int
)

val bottomNavItems = listOf(
    BottomNavigationItem("Home", "홈", R.drawable.aac_bottomn_navigation_home),
    BottomNavigationItem("Profile", "프로필", R.drawable.aac_profile)
)
