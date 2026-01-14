package net.exoad.swplus

import java.awt.BorderLayout
import java.awt.Component.*
import java.awt.GridLayout
import java.awt.LayoutManager
import javax.swing.Box.createHorizontalStrut
import javax.swing.Box.createVerticalStrut
import javax.swing.JPanel

enum class Alignment(val componentValue: Float) {
    TOP(TOP_ALIGNMENT),
    BOTTOM(BOTTOM_ALIGNMENT),
    CENTER(CENTER_ALIGNMENT),
    LEFT(LEFT_ALIGNMENT),
    RIGHT(RIGHT_ALIGNMENT)
}

fun grid(
    rows: Int,
    columns: Int,
    horizontalGap: Int = 4,
    verticalGap: Int = 4,
    modifier: Modifier? = null,
    content: MultiChildrenScope.() -> Unit,
): JPanel {
    val scope = MultiChildrenScope()
    scope.content()
    return JPanel().apply {
        layout = GridLayout(rows, columns, horizontalGap, verticalGap)
        scope.children.forEach { add(it) }
        applyModifier(modifier)
    }
}

fun scaffold(
    modifier: Modifier? = null,
    north: (SingleChildScope.() -> Unit)? = null,
    center: (SingleChildScope.() -> Unit)? = null,
    south: (SingleChildScope.() -> Unit)? = null,
    west: (SingleChildScope.() -> Unit)? = null,
    east: (SingleChildScope.() -> Unit)? = null,
): JPanel {
    return JPanel(BorderLayout()).apply {
        north?.let {
            val scope = SingleChildScope()
            scope.it()
            scope.child?.let { component -> add(component, BorderLayout.NORTH) }
        }
        center?.let {
            val scope = SingleChildScope()
            scope.it()
            scope.child?.let { component -> add(component, BorderLayout.CENTER) }
        }
        south?.let {
            val scope = SingleChildScope()
            scope.it()
            scope.child?.let { component -> add(component, BorderLayout.SOUTH) }
        }
        west?.let {
            val scope = SingleChildScope()
            scope.it()
            scope.child?.let { component -> add(component, BorderLayout.WEST) }
        }
        east?.let {
            val scope = SingleChildScope()
            scope.it()
            scope.child?.let { component -> add(component, BorderLayout.EAST) }
        }
        applyModifier(modifier)
    }
}

fun column(
    modifier: Modifier? = null,
    spacing: Int = 0,
    content: MultiChildrenScope.() -> Unit,
): JPanel {
    val scope = MultiChildrenScope()
    scope.content()
    return JPanel().apply {
        this.layout = colLayout(this)
        if (spacing > 0) {
            scope.children.forEachIndexed { index, component ->
                add(component)
                if (index < scope.children.size - 1) {
                    add(createVerticalStrut(spacing))
                }
            }
        } else {
            scope.children.forEach { add(it) }
        }
        applyModifier(modifier)
    }
}

fun row(
    modifier: Modifier? = null,
    spacing: Int = 0,
    content: MultiChildrenScope.() -> Unit,
): JPanel {
    val scope = MultiChildrenScope()
    scope.content()
    return JPanel().apply {
        this.layout = rowLayout(this)
        if (spacing > 0) {
            scope.children.forEachIndexed { index, component ->
                add(component)
                if (index < scope.children.size - 1) {
                    add(createHorizontalStrut(spacing))
                }
            }
        } else {
            scope.children.forEach { add(it) }
        }
        applyModifier(modifier)
    }
}

fun panel(
    modifier: Modifier? = null,
    layout: LayoutManager? = null,
    content: SingleChildScope.() -> Unit = {},
): JPanel {
    val scope = SingleChildScope()
    scope.content()
    return JPanel().apply {
        if (layout != null) {
            this.layout = layout
        }
        scope.child?.let { this.add(it) }
        applyModifier(modifier)
    }
}