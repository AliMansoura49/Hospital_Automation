package com.example.hospital_automation.core.components.text

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import com.example.hospital_automation.core.ext.clickableTextRange
import com.example.hospital_automation.ui.helper.DarkAndLightModePreview
import com.example.hospital_automation.ui.theme.Hospital_AutomationTheme

@Composable
fun ClickableText(
    text:String,
    clickableTextRange: IntRange,
    onClick:()->Unit,
    modifier: Modifier = Modifier,
    clickableTextColor:Color=MaterialTheme.colorScheme.onBackground,
    normalTextColor: Color=MaterialTheme.colorScheme.onBackground,
    maxLines:Int=Int.MAX_VALUE,
) {
    val tag = "tag"
    val firstPart=text.substring(0..<clickableTextRange.first)
    val clickablePart=text.substring(clickableTextRange.first..clickableTextRange.last)
    val secondPart=text.substring(clickableTextRange.last+1..text.lastIndex)

    val annotatedText = buildAnnotatedString {
        pushStringAnnotation(tag = tag, annotation = "annotation")
        withStyle(style = SpanStyle(
            color = normalTextColor,
        )) {
            append(firstPart)
        }
        withStyle(style = SpanStyle(
            color = clickableTextColor,
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline
        )) {
            append(clickablePart)
        }
        withStyle(style = SpanStyle(
            color = normalTextColor,
        )) {
            append(secondPart)
        }
        pop()
    }

    ClickableText(
        modifier = modifier,
        text = annotatedText,
        style = MaterialTheme.typography.bodyMedium,
        maxLines = maxLines,
        onClick = { offset ->
            annotatedText.getStringAnnotations(tag = tag, start = offset, end = offset)
                .firstOrNull()?.let {
                    onClick()
                }
        }
    )
}

@DarkAndLightModePreview
@Composable
fun DetailsItemWithClickableDescriptionPreview(){
    Hospital_AutomationTheme{
        Surface{
            ClickableText(
                text = "Uploaded by Zaid Ahmad",
                clickableTextRange = "Uploaded by Zaid Ahmad".clickableTextRange("Zaid Ahmad"),
                onClick = {}
            )
        }
    }
}