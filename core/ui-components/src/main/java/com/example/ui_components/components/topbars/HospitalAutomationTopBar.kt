package com.example.ui_components.components.topbars

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.ui_components.R
import com.example.ui_components.icons.HospitalAutomationIcons
import com.example.ui.theme.Hospital_AutomationTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HospitalAutomationTopBar(
    @StringRes title: Int,
    @DrawableRes navigationIcon: Int,
    onNavigationIconClick: () -> Unit,
    modifier: Modifier = Modifier,
    imageUrl: String?,
) {
    TopAppBar(
        modifier = modifier,
        title = {
            if(imageUrl.isNullOrBlank()){
                Text(
                    text = stringResource(title),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }else{
                Row(
                    modifier = Modifier.fillMaxWidth(1f),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.onSecondaryContainer),
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(imageUrl)
                            .crossfade(true)
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )
                    Text(
                        text = stringResource(title),
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
            }
        },
        navigationIcon = {
            IconButton(
                onClick = onNavigationIconClick
            ) {
                Icon(
                    painter = painterResource(navigationIcon),
                    contentDescription = null
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun HospitalAutomationTopBarPreview() {
    Hospital_AutomationTheme {
        HospitalAutomationTopBar(
            title = R.string.mail,
            navigationIcon = HospitalAutomationIcons.menu,
            onNavigationIconClick = {},
            imageUrl = null
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HospitalAutomationTopBarWithImagePreview() {
    Hospital_AutomationTheme {
        HospitalAutomationTopBar(
            title = R.string.mail,
            navigationIcon = HospitalAutomationIcons.menu,
            onNavigationIconClick = {},
            imageUrl ="example"
        )
    }
}
