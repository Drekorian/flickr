package cz.drekorian.android.flickr.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection

operator fun PaddingValues.plus(other: PaddingValues): PaddingValues =
    CompoundPaddingValues(this, other)

private class CompoundPaddingValues(
    private val first: PaddingValues,
    private val second: PaddingValues,
) : PaddingValues {
    override fun calculateBottomPadding(): Dp {
        return first.calculateBottomPadding() + second.calculateBottomPadding()
    }

    override fun calculateLeftPadding(layoutDirection: LayoutDirection): Dp {
        return first.calculateLeftPadding(layoutDirection) + second.calculateLeftPadding(layoutDirection)
    }

    override fun calculateRightPadding(layoutDirection: LayoutDirection): Dp {
        return first.calculateRightPadding(layoutDirection) + second.calculateRightPadding(layoutDirection)
    }

    override fun calculateTopPadding(): Dp {
        return first.calculateTopPadding() + second.calculateTopPadding()
    }
}
