package com.example.tutorplace.ui.common

import androidx.compose.foundation.background
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import com.example.tutorplace.navigation.bottomnavbar.BottomNavigationBarItems
import com.example.tutorplace.ui.theme.Grey82
import com.example.tutorplace.ui.theme.PurpleCC
import com.example.tutorplace.ui.theme.Transparent
import com.example.tutorplace.ui.theme.Typography
import com.example.tutorplace.ui.theme.White

const val BOTTOM_NAVIGATION_BAR_HEIGHT = 94

@Composable
fun BottomNavigationBar(
	currentTopLevelRoute: NavKey,
	onClick: (NavKey) -> Unit
) {
	NavigationBar(
		modifier = Modifier
			.background(Transparent)
			.shadow(
				elevation = 8.dp,
				shape = RoundedTopCornerShape(20.dp),
			),
		containerColor = White
	) {
		BottomNavigationBarItems.entries.forEach { topLevelRoute ->
			val isSelected = topLevelRoute.destination == currentTopLevelRoute
			NavigationBarItem(
				selected = isSelected,
				label = {
					Text(
						text = stringResource(topLevelRoute.navBarItem.label),
						style = Typography.bodySmall
					)
				},
				icon = {
					Icon(
						painter = painterResource(topLevelRoute.navBarItem.icon),
						contentDescription = null
					)
				},
				onClick = { onClick(topLevelRoute.destination) },
				colors = NavigationBarItemDefaults.colors(
					selectedIconColor = PurpleCC,
					unselectedIconColor = Grey82,
					selectedTextColor = PurpleCC,
					unselectedTextColor = Grey82,
					indicatorColor = Transparent
				)
			)
		}
	}
}