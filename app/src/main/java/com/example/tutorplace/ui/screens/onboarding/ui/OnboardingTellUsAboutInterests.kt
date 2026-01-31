package com.example.tutorplace.ui.screens.onboarding.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tutorplace.data.onboarding.model.Interest
import com.example.tutorplace.data.onboarding.model.OnboardingInfo
import com.example.tutorplace.data.onboarding.model.toTag
import com.example.tutorplace.domain.model.DataInfo
import com.example.tutorplace.ui.common.tagselector.TagSelector
import com.example.tutorplace.ui.common.tagselector.model.Tag
import com.example.tutorplace.ui.screens.onboarding.presentation.OnboardingState

@Composable
fun OnboardingTellUsAboutInterests(
	state: OnboardingState,
	columnScope: ColumnScope,
	onTagClicked: (Tag) -> Unit,
) = with(columnScope) {
	val scrollState = rememberScrollState()
	Column(
		modifier = Modifier
			.heightIn(max = 262.dp)
			.verticalScroll(scrollState)
	) {
		TagSelector(
			tags = (state.onboardingInfo as DataInfo.Success).data.interests.map { interest ->
				interest.toTag(isSelected = interest.id in state.selectedInterestsIds)
			},
			onTagSelected = { tag -> onTagClicked(tag) }
		)
	}
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun OnboardingTellUsAboutInterestsPreview() {
	Column {
		OnboardingTellUsAboutInterests(
			columnScope = this,
			state = OnboardingState(
				step = OnboardingState.Step.TELL_US_ABOUT_INTERESTS,
				onboardingInfo = DataInfo.Success(
					data = OnboardingInfo.empty().copy(
						interests = listOf(
							Interest(1, "Бизнес"),
							Interest(2, "Наука"),
							Interest(3, "Астрология"),
							Interest(4, "Политика"),
							Interest(5, "Отношения"),
							Interest(6, "Экономика"),
							Interest(7, "Здоровье"),
							Interest(8, "Нумерология"),
						)
					)
				),
				selectedInterestsIds = listOf(1, 4, 5, 8)
			),
			onTagClicked = {}
		)
	}
}