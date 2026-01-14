package net.exoad.swplus

import java.awt.Component

class SingleChildScope {
    private var _child: Component? = null
    val child: Component? get() = _child

    operator fun Component.unaryPlus() {
        _child = this
    }

    fun setChild(component: Component) {
        _child = component
    }
}

class MultiChildrenScope {
    val children = mutableListOf<Component>()

    operator fun Component.unaryPlus() {
        children.add(this)
    }

    operator fun List<Component>.unaryPlus() {
        children.addAll(this)
    }
}