package com.example.tutorplace.ui.common.sectiontitle

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tutorplace.R
import com.example.tutorplace.data.common.Sort
import com.example.tutorplace.data.common.SortOrder.ASC
import com.example.tutorplace.data.common.SortOrder.DESC
import com.example.tutorplace.data.common.SortType
import com.example.tutorplace.data.common.SortType.DATE_ADDED
import com.example.tutorplace.ui.common.sectiontitle.model.SectionSortInfo
import com.example.tutorplace.ui.common.sectiontitle.model.SectionTitle
import com.example.tutorplace.ui.theme.Black16
import com.example.tutorplace.ui.theme.PurpleCC
import com.example.tutorplace.ui.theme.Typography

@Composable
fun SectionTitle(
	modifier: Modifier = Modifier,
	sectionTitle: SectionTitle,
) {
	SectionTitleWithSort(modifier = modifier, sectionTitle = sectionTitle, sort = null)
}

@Composable
fun SectionTitleWithSort(
	modifier: Modifier = Modifier,
	sectionTitle: SectionTitle,
	sort: SectionSortInfo?,
) {
	Row(
		modifier = modifier,
		verticalAlignment = Alignment.CenterVertically
	) {
		SectionTitle(sectionTitle)
		Spacer(Modifier.weight(1f))
		sort?.let { Sorts(it) }
	}
}

@Composable
private fun SectionTitle(sectionTitle: SectionTitle) {
	val isTapDetected = remember { mutableStateOf(false) }
	val iconOffsetX = animateDpAsState(if (isTapDetected.value) (-6).dp else 0.dp)
	Row(
		modifier = Modifier.pointerInput(Unit) {
			if (sectionTitle is SectionTitle.Clickable) {
				detectTapGestures(
					onLongPress = {
						isTapDetected.value = true
						isTapDetected.value = false
					},
					onPress = {
						isTapDetected.value = true
						isTapDetected.value = true
					},
					onTap = {
						isTapDetected.value = true
						isTapDetected.value = false
						sectionTitle.onClick()
					},
				)
			}
		},
		verticalAlignment = Alignment.CenterVertically
	) {
		Text(
			text = sectionTitle.text,
			style = Typography.headlineMedium.copy(
				color = Black16,
				fontWeight = FontWeight.SemiBold
			),
		)
		if (sectionTitle is SectionTitle.Clickable) {
			Icon(
				modifier = Modifier
					.rotate(180f)
					.offset(x = iconOffsetX.value),
				painter = painterResource(id = R.drawable.ic_arrow_left_black_16),
				contentDescription = null,
				tint = Color.Unspecified
			)
		}
	}

}

@Composable
private fun Sorts(sectionSortInfo: SectionSortInfo) {
	val arrowRotation = animateFloatAsState(
		if (sectionSortInfo.selectedSort.order == DESC) 0f else 180f
	)
	Text(
		text = stringResource(sectionSortInfo.selectedSort.type.titleResId),
		style = Typography.labelMedium.copy(color = PurpleCC)
	)
	Icon(
		modifier = Modifier.rotate(arrowRotation.value),
		painter = painterResource(id = R.drawable.ic_arrow_down_black_16),
		contentDescription = null,
		tint = PurpleCC
	)
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun SectionTitleWithSortPreview() {
	Column(
		modifier = Modifier.padding(16.dp),
		verticalArrangement = Arrangement.spacedBy(8.dp)
	) {
		SectionTitle(sectionTitle = SectionTitle.NonClickable("Задания"))
		SectionTitleWithSort(
			sectionTitle = SectionTitle.Clickable("Мое обучение", onClick = {}),
			sort = SectionSortInfo(
				selectedSort = Sort(type = DATE_ADDED, order = ASC),
				sorts = emptyList(),
				onClick = {}
			)
		)
		SectionTitleWithSort(
			sectionTitle = SectionTitle.Clickable("Мое обучение", onClick = {}),
			sort = SectionSortInfo(
				selectedSort = Sort(type = SortType.PROGRESS, order = DESC),
				sorts = emptyList(),
				onClick = {}
			)
		)
	}
}