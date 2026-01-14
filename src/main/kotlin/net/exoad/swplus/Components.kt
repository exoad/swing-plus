package net.exoad.swplus

import com.formdev.flatlaf.extras.FlatSVGIcon
import java.awt.*
import java.awt.event.ActionListener
import java.io.File
import javax.swing.*
import javax.swing.WindowConstants.*
import javax.swing.filechooser.FileFilter

fun svg(path: String, width: Int = 16, height: Int = 16): Icon {
    return FlatSVGIcon(path, width, height)
}

enum class WindowCloseOperation(val componentValue: Int) {
    EXIT(EXIT_ON_CLOSE),
    MINIMIZE(HIDE_ON_CLOSE),
    IGNORE(DO_NOTHING_ON_CLOSE),
    DISPOSE(DISPOSE_ON_CLOSE)
}

fun frame(
    title: String,
    closeOperation: WindowCloseOperation,
    icon: Image? = null,
    modifier: Modifier? = null,
    content: SingleChildScope.() -> Unit,
): JFrame {
    val scope = SingleChildScope()
    scope.content()
    return JFrame(title).apply {
        if (icon != null) {
            iconImage = icon
        }
        contentPane = scope.child as Container
        defaultCloseOperation = closeOperation.componentValue
        applyModifier(modifier)
    }
}

fun button(
    text: String,
    modifier: Modifier? = null,
    outlined: Boolean = false,
    icon: Icon? = null,
    onClick: ActionListener? = null,
): JButton {
    return JButton(text).apply {
        if (outlined) {
            background = color(0, 0, 0, 0)
        }
        if (icon != null) {
            this.icon = icon
        }
        onClick?.let { addActionListener(it) }
        applyModifier(modifier)
    }
}

fun text(
    text: String,
    hAlignment: Alignment? = null,
    modifier: Modifier? = null,
    fontSize: Float = 16F,
    bolded: Boolean = false,
): JLabel {
    return JLabel(text).apply {
        font = font.deriveFont(fontSize)
        if (bolded) {
            font = font.deriveFont(Font.BOLD)
        }
        if (hAlignment != null) {
            horizontalAlignment = when (hAlignment) {
                Alignment.LEFT -> SwingConstants.LEFT
                Alignment.RIGHT -> SwingConstants.RIGHT
                Alignment.CENTER -> SwingConstants.CENTER
                else -> throw UIBuildException(
                    "Horizontal Alignment Value '$hAlignment' is not allowed to be supplied as 'horizontalAlignment' for a label."
                )
            }
        }
        applyModifier(modifier)
    }
}

fun customPainter(
    modifier: Modifier? = null,
    painter: (Graphics2D, Dim) -> Unit,
): JComponent {
    return object : JComponent() {
        override fun paintComponent(g: Graphics?) {
            super.paintComponent(g)
            val g2d = g as Graphics2D
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
            painter(g2d, dim(width, height))
        }
    }.apply {
        applyModifier(modifier)
    }
}

fun iconButton(
    icon: Icon,
    modifier: Modifier? = null,
    onClick: ActionListener? = null,
): JButton {
    return JButton(icon).apply {
        onClick?.let { addActionListener(it) }
        applyModifier(modifier)
    }
}

fun textArea(
    initialText: String = "",
    rows: Int = 5,
    columns: Int = 20,
    modifier: Modifier? = null,
): JTextArea {
    return JTextArea(initialText, rows, columns).apply {
        applyModifier(modifier)
    }
}

fun progressBar(
    value: Int = 0,
    min: Int = 0,
    max: Int = 100,
    modifier: Modifier? = null,
): JProgressBar {
    return JProgressBar(min, max).apply {
        this.value = value
        applyModifier(modifier)
    }
}

fun textField(
    initialText: String = "",
    modifier: Modifier? = null,
    onChange: ((String) -> Unit)? = null,
): JTextField {
    return JTextField().apply {
        text = initialText
        if (onChange != null) {
            addCaretListener {
                onChange(text)
            }
        }
        applyModifier(modifier)
    }
}

fun hDivider(
    color: Color = UIManager.getColor("Separator.foreground"),
    thickness: Float = 2F,
): JComponent {
    return customPainter(modifier = Modifier().apply {
        size = dim(Int.MAX_VALUE, 6 + thickness.toInt())
        maxSize = size
        minSize = size
    }) { g, dim ->
        g.color = color
        g.stroke = BasicStroke(thickness)
        g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE)
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF)
        g.drawLine(0, dim.height / 2, dim.width, dim.height / 2)
        g.dispose()
    }
}

fun viewport(
    modifier: Modifier? = null,
    content: SingleChildScope.() -> Unit,
): JViewport {
    val scope = SingleChildScope()
    scope.content()
    return JViewport().apply {
        scope.child?.let { this.view = it }
        applyModifier(modifier)
    }
}

enum class FilePickerMode {
    FILES,
    DIRECTORIES,
    BOTH
}

fun filePicker(
    allowMultiple: Boolean = false,
    initialStart: File? = null,
    mode: FilePickerMode = FilePickerMode.FILES,
    modifier: Modifier? = null,
    fileFilter: FileFilter? = null,
): JFileChooser {
    return JFileChooser().apply {
        if (initialStart != null) {
            currentDirectory = initialStart
        }
        isMultiSelectionEnabled = allowMultiple
        this.fileFilter = fileFilter
        fileSelectionMode = when (mode) {
            FilePickerMode.FILES -> JFileChooser.FILES_ONLY
            FilePickerMode.DIRECTORIES -> JFileChooser.DIRECTORIES_ONLY
            FilePickerMode.BOTH -> JFileChooser.FILES_AND_DIRECTORIES
        }
        applyModifier(modifier)
    }
}

fun <T> comboBox(
    modifier: Modifier? = null,
    initialValue: T,
    values: Array<T>,
    onChange: ((T) -> Unit)? = null,
): JComboBox<T> {
    return JComboBox(values).apply {
        if (onChange != null) {
            addItemListener {
                @Suppress("UNCHECKED_CAST") (selectedItem as T)
            }
        }
        selectedItem = initialValue
        applyModifier(modifier)
    }
}

fun spinner(
    modifier: Modifier? = null,
    initialValue: Long,
    allowNegative: Boolean,
    stepSize: Long = 1,
    onChange: ((Long) -> Unit)? = null,
): JSpinner {
    return JSpinner(
        SpinnerNumberModel(
            initialValue,
            if (allowNegative) Long.MIN_VALUE else 0,
            Long.MAX_VALUE,
            stepSize
        )
    ).apply {
        value = initialValue
        if (onChange != null) {
            addChangeListener {
                onChange(value as Long)
            }
        }
        applyModifier(modifier)
    }
}

fun spinner(
    modifier: Modifier? = null,
    initialValue: Double,
    discreteValues: Array<Double>,
    mantissaLength: Int = 2,
    onChange: ((Double) -> Unit)? = null,
): JSpinner {
    return JSpinner(SpinnerListModel(discreteValues)).apply {
        value = initialValue
        if (onChange != null) {
            addChangeListener {
                onChange(value as Double)
            }
        }
        setEditor(JSpinner.NumberEditor(this, "#.${"#".repeat(mantissaLength)}"))
        applyModifier(modifier)
    }
}

fun spinner(
    modifier: Modifier? = null,
    initialValue: Long,
    discreteValues: Array<Long>,
    onChange: ((Long) -> Unit)? = null,
): JSpinner {
    return JSpinner(SpinnerListModel(discreteValues)).apply {
        value = initialValue
        if (onChange != null) {
            addChangeListener {
                onChange(value as Long)
            }
        }

        applyModifier(modifier)
    }
}

fun spinner(
    modifier: Modifier? = null,
    initialValue: Double,
    allowNegative: Boolean,
    stepSize: Double = 1.0,
    mantissaLength: Int = 2,
    onChange: ((Double) -> Unit)? = null,
): JSpinner {
    return JSpinner(
        SpinnerNumberModel(
            initialValue,
            if (allowNegative) Double.MIN_VALUE else .0,
            Double.MAX_VALUE,
            stepSize
        )
    ).apply {
        value = initialValue
        if (onChange != null) {
            addChangeListener {
                onChange(value as Double)
            }
        }
        setEditor(JSpinner.NumberEditor(this, "#.${"#".repeat(mantissaLength)}"))
        applyModifier(modifier)
    }
}

fun checkbox(
    selected: Boolean = false,
    modifier: Modifier? = null,
    onChange: ((Boolean) -> Unit)? = null,
): JCheckBox {
    return JCheckBox().apply {
        isSelected = selected
        if (onChange != null) {
            addItemListener {
                onChange(isSelected)
            }
        }
        applyModifier(modifier)
    }
}

fun scrollPane(
    modifier: Modifier? = null,
    content: SingleChildScope.() -> Unit,
): JScrollPane {
    val scope = SingleChildScope()
    scope.content()
    return JScrollPane(scope.child).apply {
        border = BorderFactory.createEmptyBorder()
        applyModifier(modifier)
    }
}
