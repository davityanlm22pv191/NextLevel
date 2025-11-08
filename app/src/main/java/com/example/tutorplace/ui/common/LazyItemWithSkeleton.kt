package com.example.tutorplace.ui.common

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.tutorplace.domain.model.DataInfo

fun <T> LazyListScope.itemWithSkeleton(
	key: String,
	contentType: Any = key,
	dataInfo: DataInfo<T>,
	paddingValues: PaddingValues = PaddingValues(),
	content: @Composable () -> Unit,
	skeletonContent: @Composable () -> Unit,
	emptyStateContent: @Composable () -> Unit = {}
) {
	item(
		key = key,
		contentType = contentType,
	) {
		AnimatedContent(
			modifier = Modifier.padding(paddingValues),
			label = "${key}_itemWithSkeleton",
			targetState = dataInfo.isLoaded,
			transitionSpec = { fadeIn(tween(500)) togetherWith fadeOut(tween(500)) }
		) { isLoaded ->
			when {
				dataInfo.throwable != null -> {}
				!isLoaded -> skeletonContent()
				dataInfo.isEmptyState -> emptyStateContent()
				else -> content()
			}
		}
	}
}