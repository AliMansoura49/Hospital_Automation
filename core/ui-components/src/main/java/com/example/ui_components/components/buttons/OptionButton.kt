package com.example.ui_components.components.buttons

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.constants.icons.AppIcons
import com.example.ui_components.R
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.spacing

@Composable
fun OptionButton(
    @DrawableRes icon: Int,
    @StringRes text: Int,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val transition = updateTransition(targetState = isSelected, label = "isSelectedTransition")

    val color by transition.animateColor(
        label = "color",
        transitionSpec = {
            tween(durationMillis = 400)
        }
    ) { isSelected ->
        if (isSelected) MaterialTheme.colorScheme.primaryContainer
        else MaterialTheme.colorScheme.background
    }

    val borderColor by transition.animateColor(
        label = "borderColor",
        transitionSpec = {
            tween(durationMillis = 400)
        }
    ) { isSelected ->
        if (isSelected) MaterialTheme.colorScheme.primary
        else Color.Transparent
    }

    val borderWidth by transition.animateDp(
        label = "borderWidth",
        transitionSpec = {
            tween(durationMillis = 300)
        }
    ) { isSelected ->
        if (isSelected) 1.dp
        else 0.dp
    }

    val baseModifier = modifier
        .border(width = borderWidth, borderColor, shape = MaterialTheme.shapes.small)
        .clip(MaterialTheme.shapes.small)

    val optionModifier = if (enabled) {
        baseModifier
            .clickable { onClick() }
    } else {
        baseModifier
            .alpha(0.4f)
    }

    Surface(
        modifier =optionModifier,
        color = color,
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = MaterialTheme.spacing.small8,
                vertical = MaterialTheme.spacing.small12
            ),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall4),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(painter = painterResource(icon), null)
            Text(
                text = stringResource(text),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun OptionPreview() {
    Hospital_AutomationTheme {
        var isSelected by remember {
            mutableStateOf(false)
        }
        OptionButton(
            icon = AppIcons.Outlined.man,
            text = R.string.male,
            isSelected = isSelected,
            modifier = Modifier.width(182.dp),
            onClick = {
                isSelected = !isSelected
            }

        )
    }
}








