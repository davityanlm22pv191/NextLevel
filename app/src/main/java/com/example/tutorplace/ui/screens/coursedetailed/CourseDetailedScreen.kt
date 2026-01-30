package com.example.tutorplace.ui.screens.coursedetailed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.tutorplace.data.courses.course.CourseDetailed
import com.example.tutorplace.domain.model.DataInfo
import com.example.tutorplace.navigation.Navigator
import com.example.tutorplace.ui.common.itemWithSkeleton
import com.example.tutorplace.ui.common.toolbar.TOOLBAR_HEADER_HEIGHT
import com.example.tutorplace.ui.screens.coursedetailed.model.CourseDetailedParams
import com.example.tutorplace.ui.screens.coursedetailed.presentation.CourseDetailedEvent
import com.example.tutorplace.ui.screens.coursedetailed.presentation.CourseDetailedViewModel
import com.example.tutorplace.ui.screens.coursedetailed.ui.CourseDetailedShortInfo
import com.example.tutorplace.ui.theme.Black
import com.example.tutorplace.ui.theme.Black34
import com.example.tutorplace.ui.theme.ScreenColor

@Composable
fun CourseDetailedScreen(navigator: Navigator, params: CourseDetailedParams) {
	val viewModel = hiltViewModel<CourseDetailedViewModel>()
	LaunchedEffect(params.courseId) {
		viewModel.onEvent(CourseDetailedEvent.AttachParams(params))
	}
	val state by viewModel.state.collectAsStateWithLifecycle()

	CourseDetailedContent(state.courseDetailed)
}

@Composable
private fun CourseDetailedContent(
	courseDetailed: DataInfo<CourseDetailed>,
) {
	Scaffold(containerColor = ScreenColor) { paddingValues ->
		LazyColumn(
			modifier = Modifier
				.fillMaxSize()
				.padding(paddingValues)
		) {
			itemWithSkeleton(
				key = "courseInfo",
				dataInfo = courseDetailed,
				content = {
					CourseDetailedShortInfo(
						modifier = Modifier
							.background(
								brush = Brush.verticalGradient(colors = listOf(Black, Black34)),
								shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
							)
							.padding(top = TOOLBAR_HEADER_HEIGHT.dp),
						course = it,
						onStartLessonClicked = {},
						onMaterialsClicked = {}
					)
				},
				skeletonContent = {},
			)
		}
	}
}

@Preview
@Composable
private fun CourseDetailedContentPreview() {
	CourseDetailedContent(
		courseDetailed = DataInfo.Success(CourseDetailed.MOCK),
	)
}