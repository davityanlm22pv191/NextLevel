package com.example.tutorplace.navigation.bottomnavbar

import androidx.navigation3.runtime.NavKey
import com.example.tutorplace.R
import com.example.tutorplace.navigation.destinations.Destinations

enum class BottomNavigationBarItems(
	val destination: NavKey,
	val navBarItem: NavBarItem
) {
	CATALOG(
		Destinations.Catalog,
		NavBarItem(R.drawable.ic_catalog, R.string.tab_catalog_title)
	),
	MY_COURSES(
		Destinations.MyCourses,
		NavBarItem(R.drawable.ic_play, R.string.tab_my_courses_title)
	),
	HOME(
		Destinations.Home,
		NavBarItem(R.drawable.ic_home, R.string.tab_home_title)
	),
	TASKS(
		Destinations.Tasks,
		NavBarItem(R.drawable.ic_star, R.string.tab_tasks_title)
	);
}