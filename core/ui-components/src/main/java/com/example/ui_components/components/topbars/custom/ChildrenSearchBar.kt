package com.example.ui_components.components.topbars.custom

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.ui_components.R
import com.example.ui_components.components.topbars.HospitalAutomationTopBarWithSearchBar

@Composable
fun ChildrenSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onTrailingIconClick: () -> Unit,
    onSearch: (String) -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    HospitalAutomationTopBarWithSearchBar(
        query = query,
        onQueryChange = onQueryChange,
        onTrailingIconClick = onTrailingIconClick,
        placeholderText = R.string.search_for_children,
        onNavigationIconCLick = onNavigateUp,
        modifier = modifier,
    )
}