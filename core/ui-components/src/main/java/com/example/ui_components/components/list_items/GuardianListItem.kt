package com.example.ui_components.components.list_items

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ext.shimmerEffect
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.components.items.FailedImage
import com.example.ui_components.components.network_image.NetworkImage

@Composable
fun GuardianListItem(
    imageUrl: String?,
    name: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(MaterialTheme.sizing.small8),
    containerColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = MaterialTheme.colorScheme.onBackground,
) {
    Card(
        modifier = modifier
            .clickable {
                onClick()
            },
        shape = shape,
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            contentColor = contentColor
        )
    ) {
        Row(
            modifier = Modifier.padding(MaterialTheme.spacing.medium16),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium16),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            NetworkImage(
                model = imageUrl,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(MaterialTheme.sizing.medium40)
                    .clip(CircleShape),
                loading = {
                    Box(
                        modifier = Modifier
                            .size(MaterialTheme.sizing.medium40)
                            .clip(CircleShape)
                            .shimmerEffect()
                    )
                },
                errorCompose = {
                    FailedImage()
                },
            )
            Text(
                text = name,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GuardianListItemPreview() {
    Hospital_AutomationTheme {
        GuardianListItem(
            modifier = Modifier.width(380.dp),
            imageUrl = "",
            name = "Mariam Saoud",
            onClick = {}
        )
    }
}