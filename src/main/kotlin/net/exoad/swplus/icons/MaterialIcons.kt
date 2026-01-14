package net.exoad.swplus.icons

import net.exoad.swplus.Dim
import net.exoad.swplus.icons.MaterialIcons.I.getIcon
import net.exoad.swplus.svg
import java.util.concurrent.ConcurrentHashMap
import javax.swing.Icon

object MaterialIcons {
    private val iconCache = ConcurrentHashMap<String, Icon>()
    private const val BASE_PATH = "MaterialIcons"

    private data class IconDefinition(
        private val rawFileName: String,
        val defaultSize: Dim = Dim(24, 24),
    ) {
        val path get() = "$BASE_PATH/$rawFileName.svg"
    }

    private fun i(name: String): Pair<String, IconDefinition> {
        return name to IconDefinition(name)
    }

    private val iconDefinitions = arrayOf(
        "search", "add", "delete", "edit", "download", "upload", "play_arrow",
        "pause", "star", "error", "warning", "do_not_disturb", "link", "security",
        "chevron_right", "chevron_left", "code", "minimize", "lightbulb", "menu",
        "favorite", "functions", "location_ping", "folder", "replay", "stop", "sync",
        "arrow_right", "arrow_left", "arrow_down", "sun", "moon", "settings", "accessibility",
        "cleaning_device", "notifications", "person"
    ).associate { i(it) }

    // == icons functions ==
    fun search(size: Dim? = null): Icon = getIcon("search", size)
    fun add(size: Dim? = null): Icon = getIcon("add", size)
    fun delete(size: Dim? = null): Icon = getIcon("delete", size)
    fun edit(size: Dim? = null): Icon = getIcon("edit", size)
    fun download(size: Dim? = null): Icon = getIcon("download", size)
    fun upload(size: Dim? = null): Icon = getIcon("upload", size)
    fun playArrow(size: Dim? = null): Icon = getIcon("play_arrow", size)
    fun pause(size: Dim? = null): Icon = getIcon("pause", size)
    fun star(size: Dim? = null): Icon = getIcon("star", size)
    fun error(size: Dim? = null): Icon = getIcon("error", size)
    fun warning(size: Dim? = null): Icon = getIcon("warning", size)
    fun doNotDisturb(size: Dim? = null): Icon = getIcon("do_not_disturb", size)
    fun priorityHigh(size: Dim? = null): Icon = getIcon("priority_high", size)
    fun link(size: Dim? = null): Icon = getIcon("link", size)
    fun security(size: Dim? = null): Icon = getIcon("security", size)
    fun chevronRight(size: Dim? = null): Icon = getIcon("chevron_right", size)
    fun chevronLeft(size: Dim? = null): Icon = getIcon("chevron_left", size)
    fun code(size: Dim? = null): Icon = getIcon("code", size)
    fun minimize(size: Dim? = null): Icon = getIcon("minimize", size)
    fun lightbulb(size: Dim? = null): Icon = getIcon("lightbulb", size)
    fun menu(size: Dim? = null): Icon = getIcon("menu", size)
    fun favorite(size: Dim? = null): Icon = getIcon("favorite", size)
    fun functions(size: Dim? = null): Icon = getIcon("functions", size)
    fun locationPin(size: Dim? = null): Icon = getIcon("location_pin", size)
    fun folder(size: Dim? = null): Icon = getIcon("folder", size)
    fun replay(size: Dim? = null): Icon = getIcon("replay", size)
    fun stop(size: Dim? = null): Icon = getIcon("stop", size)
    fun sync(size: Dim? = null): Icon = getIcon("sync", size)
    fun arrowRight(size: Dim? = null): Icon = getIcon("arrow_right", size)
    fun arrowLeft(size: Dim? = null): Icon = getIcon("arrow_left", size)
    fun arrowDown(size: Dim? = null): Icon = getIcon("arrow_down", size)
    fun sun(size: Dim? = null): Icon = getIcon("sun", size)
    fun moon(size: Dim? = null): Icon = getIcon("moon", size)
    fun settings(size: Dim? = null): Icon = getIcon("settings", size)
    fun accessibility(size: Dim? = null): Icon = getIcon("accessibility", size)
    fun cleaningDevice(size: Dim? = null): Icon = getIcon("cleaning_device", size)
    fun notifications(size: Dim? = null): Icon = getIcon("notifications", size)
    fun person(size: Dim? = null): Icon = getIcon("person", size)

    // == utility functions ==
    object I {
        fun getIcon(name: String, size: Dim? = null): Icon {
            requireNotNull(iconDefinitions[name])
            val iconDef = iconDefinitions[name]!!
            val actualSize = size ?: iconDef.defaultSize
            val cacheKey = "${name}_${actualSize.width}x${actualSize.height}"

            return iconCache.computeIfAbsent(cacheKey) {
                svg(iconDef.path, actualSize.width, actualSize.height)
            }
        }

        val getAvailableIcons get(): Set<String> = iconDefinitions.keys

        fun clearCache() {
            iconCache.clear()
        }
    }
}