package com.example.tutorplace.ui.common.coursecard.cardpager

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tutorplace.ui.common.SkeletonShimmer
import com.example.tutorplace.ui.common.coursecard.card.CardSkeleton
import com.example.tutorplace.ui.common.coursecard.card.CourseCardShapeType
import com.example.tutorplace.ui.common.coursecard.card.CourseCardShapeType.LARGE
import com.example.tutorplace.ui.common.coursecard.card.CourseCardShapeType.SQUARE
import com.example.tutorplace.ui.theme.Black16
import com.example.tutorplace.ui.theme.Black16Alpha30
import com.example.tutorplace.ui.theme.PurpleDE
import com.example.tutorplace.ui.theme.ScreenColor
import com.example.tutorplace.ui.theme.White

private const val SKELETON_CARD_COUNT = 3

@Composable
fun CardPagerWithTitleAndSortSkeleton(
	modifier: Modifier = Modifier,
	shape: CourseCardShapeType,
	withSort: Boolean
) {
	val thresholdOffset = remember { if (shape == SQUARE) 100 else 200 }
	val lazyListState = rememberLazyListState()
	val currentIndex = remember {
		derivedStateOf {
			val firstVisibleItemIndex = lazyListState.firstVisibleItemIndex
			val offset = lazyListState.firstVisibleItemScrollOffset
			val lastVisibleItem = lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()
			val isLastFullyVisible = lastVisibleItem?.let {
				it.index == SKELETON_CARD_COUNT - 1 &&
						it.offset + it.size <= lazyListState.layoutInfo.viewportEndOffset
			} ?: false
			val current = when {
				isLastFullyVisible -> SKELETON_CARD_COUNT - 1
				offset > thresholdOffset -> firstVisibleItemIndex + 1
				else -> firstVisibleItemIndex
			}
			current.coerceIn(0, SKELETON_CARD_COUNT - 1)
		}
	}
	SkeletonShimmer(modifier = Modifier.background(color = ScreenColor)) {
		Column(
			modifier = modifier
				.fillMaxWidth()
				.background(color = White, shape = RoundedCornerShape(20.dp))
				.padding(vertical = 16.dp),
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.spacedBy(24.dp)
		) {
			Row(
				modifier = Modifier
					.fillMaxWidth()
					.padding(horizontal = 16.dp),
				verticalAlignment = Alignment.CenterVertically
			) {
				Box(
					modifier = Modifier
						.height(26.dp)
						.fillMaxWidth(0.4f)
						.background(Black16Alpha30, RoundedCornerShape(20.dp)),
				)
				if (withSort) {
					Spacer(modifier = Modifier.weight(1f))
					Box(
						modifier = Modifier
							.height(21.dp)
							.fillMaxWidth(0.4f)
							.background(PurpleDE, RoundedCornerShape(20.dp)),
					)
				}
			}
			LazyRow(
				modifier = Modifier.fillMaxWidth(),
				state = lazyListState,
				horizontalArrangement = Arrangement.spacedBy(8.dp),
				contentPadding = PaddingValues(horizontal = 16.dp),
				overscrollEffect = null
			) {
				(1..SKELETON_CARD_COUNT).forEach { index ->
					item(key = index) { CardSkeleton(shape = shape) }
				}
			}
			DotsIndicator(
				totalDots = SKELETON_CARD_COUNT,
				selectedIndex = currentIndex.value
			)
		}
	}
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun CardPagerSquareWithTitleAndSortSkeletonPreview() {
	Column(
		modifier = Modifier.background(color = Black16),
		verticalArrangement = Arrangement.spacedBy(20.dp)
	) {
		CardPagerWithTitleAndSortSkeleton(shape = SQUARE, withSort = true)
		CardPagerWithTitleAndSortSkeleton(shape = SQUARE, withSort = false)
	}
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun CardPagerLargeWithTitleAndSortSkeletonPreview() {
	Column(
		modifier = Modifier.background(color = Black16),
		verticalArrangement = Arrangement.spacedBy(20.dp)
	) {
		CardPagerWithTitleAndSortSkeleton(shape = LARGE, withSort = true)
		CardPagerWithTitleAndSortSkeleton(shape = LARGE, withSort = false)
	}
}