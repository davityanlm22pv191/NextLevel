package com.example.nextlevel.ui.common.lazyitems

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import com.example.nextlevel.domain.model.DataInfo

fun <T> LazyListScope.itemsWithSkeletons(
	dataInfo: DataInfo<List<T>>,
	skeletonItemsCount: Int,
	content: @Composable (item: T, index: Int) -> Unit,
	skeletonItem: @Composable (index: Int) -> Unit,
	emptyStateContent: @Composable () -> Unit,
	errorStateContent: @Composable (String) -> Unit
) {
	when (dataInfo) {
		is DataInfo.Error -> {
			item(key = "errorState") {
				errorStateContent(dataInfo.throwable.message!!)
			}
		}
		is DataInfo.Loading -> {
			items(
				count = skeletonItemsCount,
				key = { index -> "skeleton_$index" },
				itemContent = { index -> skeletonItem(index) }
			)
		}
		is DataInfo.Success<List<T>> -> {
			if (dataInfo.isEmptyState()) {
				item(
					key = "emptyStateContent",
					content = { emptyStateContent() }
				)
			} else {
				items(
					count = dataInfo.data.size,
					key = { index -> dataInfo.data[index].hashCode() },
					itemContent = { index -> content(dataInfo.data[index], index) }
				)
			}
		}
	}
}