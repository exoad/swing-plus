package net.exoad.swplus

import java.awt.Dimension
import java.awt.Point
import java.awt.Rectangle
import java.awt.Toolkit
import kotlin.math.absoluteValue

typealias Rect = Rectangle
typealias Dim = Dimension

fun Dimension.toRect(): Rect {
    return Rect(0, 0, width, height)
}

fun dim(w: Int = 0, h: Int = 0): Dim {
    return Dimension(w, h)
}

fun pt(x: Int, y: Int): Point {
    return Point(x, y)
}

fun screenSize(): Dim {
    return Toolkit.getDefaultToolkit().screenSize
}

fun centerScreenRect(rect: Rect): Point {
    val screenSize = screenSize()
    return pt(
        ((screenSize.width - rect.width) / 2).absoluteValue, ((screenSize.height - rect.width) / 2)
            .absoluteValue
    )
}