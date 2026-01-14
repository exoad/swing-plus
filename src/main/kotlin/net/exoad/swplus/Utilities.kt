package net.exoad.swplus

import java.awt.Component
import javax.swing.BorderFactory
import javax.swing.Box.*
import javax.swing.BoxLayout
import javax.swing.JComponent
import javax.swing.border.EmptyBorder

fun hStrut(space: Int): Component {
    return createHorizontalStrut(space)
}

fun vStrut(space: Int): Component {
    return createVerticalStrut(space)
}

fun hSpacer(): Component {
    return createHorizontalGlue()
}

fun vSpacer(): Component {
    return createVerticalGlue()
}

fun padNone(): EmptyBorder {
    return BorderFactory.createEmptyBorder() as EmptyBorder
}

fun padSym(h: Int = 0, v: Int = 0): EmptyBorder {
    return BorderFactory.createEmptyBorder(v, h, v, h) as EmptyBorder
}

fun padAll(value: Int): EmptyBorder {
    return BorderFactory.createEmptyBorder(value, value, value, value) as EmptyBorder
}

fun padOnly(top: Int = 0, bottom: Int = 0, left: Int = 0, right: Int = 0): EmptyBorder {
    return BorderFactory.createEmptyBorder(top, left, bottom, right) as EmptyBorder
}

fun rowLayout(target: JComponent): BoxLayout {
    return BoxLayout(target, BoxLayout.X_AXIS)
}

fun colLayout(target: JComponent): BoxLayout {
    return BoxLayout(target, BoxLayout.Y_AXIS)
}
