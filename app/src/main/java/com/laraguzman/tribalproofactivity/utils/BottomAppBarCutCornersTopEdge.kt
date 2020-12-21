package com.laraguzman.tribalproofactivity.utils

import android.annotation.SuppressLint
import com.google.android.material.bottomappbar.BottomAppBarTopEdgeTreatment
import com.google.android.material.shape.ShapePath

class BottomAppBarCutCornersTopEdge(
    fabMargin: Float?,
    roundedCornerRadius: Float?,
    cradleVerticalOffset: Float?
) : BottomAppBarTopEdgeTreatment(fabMargin!!, roundedCornerRadius!!, cradleVerticalOffset!!) {

    var fabMargin : Float = 0F;
    var cradleVerticalOffset : Float = 0F;

    @SuppressLint("RestrictedApi")
    @SuppressWarnings("RestrictTo")
    override fun getEdgePath(length: Float, center: Float, interpolation: Float, shapePath: ShapePath) {
        var fabDiameter = getFabDiameter()
        if(fabDiameter == 0F){
            shapePath.lineTo(length, 0F)
            return
        }

        var diamondSize = fabDiameter / 2F
        var middle = center + getHorizontalOffset()

        var verticalOffsetRatio = cradleVerticalOffset / diamondSize

        if(verticalOffsetRatio >= 1.0F){
            shapePath.lineTo(length, 0F)
            return
        }

        shapePath.lineTo(middle - (fabMargin + diamondSize - cradleVerticalOffset), 1F)
        shapePath.lineTo(middle, (diamondSize - cradleVerticalOffset + fabMargin) * interpolation)
        shapePath.lineTo(middle + (fabMargin + diamondSize - cradleVerticalOffset), 1F)
        shapePath.lineTo(length, 0F)

        super.getEdgePath(length, center, interpolation, shapePath)
    }

}