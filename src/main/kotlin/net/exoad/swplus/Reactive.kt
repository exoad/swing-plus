package net.exoad.swplus

import javax.swing.JFrame
import javax.swing.SwingUtilities

class MutableState<T>(initialValue: T) {
    private var _value = initialValue
    private val observers = mutableListOf<(T) -> Unit>()
    var value: T
        get() = _value
        set(newValue) {
            if (_value != newValue) {
                _value = newValue
                observers.forEach { it(newValue) }
            }
        }

    fun observe(observer: (T) -> Unit) {
        observers.add(observer)
    }

    operator fun invoke(): T {
        return value
    }

    operator fun invoke(t: T) {
        value = t
    }
}

fun <T> remember(initialValue: T): MutableState<T> {
    return MutableState(initialValue)
}

fun showFrame(frame: JFrame) {
    SwingUtilities.invokeLater {
        frame.pack()
        frame.isVisible = true
    }
}