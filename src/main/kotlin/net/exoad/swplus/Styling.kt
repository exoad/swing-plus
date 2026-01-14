package net.exoad.swplus

import java.awt.Color
import java.awt.Component
import javax.swing.JComponent
import javax.swing.border.Border

fun color(r: Int, g: Int, b: Int, a: Int = 255): Color {
    return Color(r, g, b, a)
}

fun color(r: Float, g: Float, b: Float, a: Float = 1.0F): Color {
    return Color(r, g, b, a)
}

fun color(rgb: Int): Color {
    return Color(rgb)
}

class Modifier {
    var padding: Border? = null
    var size: Dim? = null
    var tooltip: String? = null
    var maxSize: Dim? = null
    var minSize: Dim? = null
    var background: Color? = null
    var foreground: Color? = null
    var border: Border? = null
    var visible: Boolean? = null
    var enabled: Boolean? = null
    var opaque: Boolean? = null
    var alignmentX: Alignment? = null
    var alignmentY: Alignment? = null
}

fun Component.applyModifier(modifier: Modifier?) {
    if (this is JComponent && modifier != null) {
        modifier.padding?.let { border = it }
        modifier.size?.let {
            size = it
            preferredSize = it
        }
        modifier.tooltip?.let { toolTipText = it }
        modifier.maxSize?.let { maximumSize = it }
        modifier.minSize?.let { minimumSize = it }
        modifier.background?.let { background = it }
        modifier.foreground?.let { foreground = it }
        modifier.border?.let { border = it }
        modifier.visible?.let { isVisible = it }
        modifier.enabled?.let { isEnabled = it }
        modifier.opaque?.let { isOpaque = it }
        modifier.alignmentX?.let { alignmentX = it.componentValue }
        modifier.alignmentY?.let { alignmentX = it.componentValue }
    }
}
