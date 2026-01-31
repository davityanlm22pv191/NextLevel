package com.example.tutorplace.ui.screens.mail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection.Rtl
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.tutorplace.R
import com.example.tutorplace.domain.model.DataInfo
import com.example.tutorplace.navigation.Navigator
import com.example.tutorplace.ui.common.BOTTOM_NAVIGATION_BAR_HEIGHT
import com.example.tutorplace.ui.common.lazyitems.itemsWithSkeletons
import com.example.tutorplace.ui.common.toolbar.TOOLBAR_HEADER_HEIGHT
import com.example.tutorplace.ui.screens.mail.presentation.MailEffect
import com.example.tutorplace.ui.screens.mail.presentation.MailState
import com.example.tutorplace.ui.screens.mail.presentation.MailViewModel
import com.example.tutorplace.ui.screens.mail.ui.Mail
import com.example.tutorplace.ui.screens.mail.ui.MailEmptyState
import com.example.tutorplace.ui.screens.mail.ui.MailSkeleton
import com.example.tutorplace.ui.theme.Black16
import com.example.tutorplace.ui.theme.ScreenColor
import com.example.tutorplace.ui.theme.Typography
import com.example.tutorplace.ui.theme.White
import kotlinx.coroutines.flow.Flow

private const val MAIL_SKELETON_ITEM_COUNT = 3

@Composable
fun MailScreen(navigator: Navigator) {
	val viewModel = hiltViewModel<MailViewModel>()
	val state by viewModel.state.collectAsState()
	CollectEffects(viewModel.effect, navigator)
	MailContent(state)
}

@Composable
private fun MailContent(state: MailState) {
	Scaffold(containerColor = ScreenColor) { paddingValues ->
		LazyColumn(
			modifier = Modifier
				.padding(vertical = 8.dp)
				.fillMaxSize()
				.padding(
					top = paddingValues.calculateTopPadding() + TOOLBAR_HEADER_HEIGHT.dp,
					start = paddingValues.calculateStartPadding(Rtl),
					end = paddingValues.calculateEndPadding(Rtl),
					bottom = paddingValues.calculateBottomPadding()
				)
				.background(White, RoundedCornerShape(20.dp))
				.padding(horizontal = 16.dp)
		) {
			item(key = "title") {
				Text(
					modifier = Modifier.padding(top = 16.dp),
					text = stringResource(R.string.tutor_mail_incoming),
					style = Typography.headlineLarge.copy(color = Black16)
				)
			}
			itemsWithSkeletons(
				dataInfo = state.mails,
				skeletonItemsCount = MAIL_SKELETON_ITEM_COUNT,
				content = { mail, index ->
					Mail(
						modifier = Modifier.padding(top = if (index == 0) 16.dp else 8.dp),
						mail = mail
					)
				},
				skeletonItem = { index ->
					MailSkeleton(
						modifier = Modifier.padding(top = if (index == 0) 16.dp else 8.dp)
					)
				},
				emptyStateContent = { MailEmptyState(Modifier.padding(top = 16.dp)) },
				errorStateContent = {}
			)
			item(key = "bottomBarSeparator") {
				Spacer(Modifier.height(BOTTOM_NAVIGATION_BAR_HEIGHT.dp))
			}
		}
	}
}

@Composable
private fun CollectEffects(effect: Flow<MailEffect>, navigator: Navigator) {
	LaunchedEffect(Unit) {
		effect.collect { mailEffect ->
			when (mailEffect) {
				else -> {}
			}
		}
	}
}

@Preview
@Composable
private fun MailPreview() {
	MailContent(MailState(skeletonItemsCount = DataInfo.Loading))
}