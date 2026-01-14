# Swing+

A small Kotlin library that offers composable helpers for Swing to reduce repetitive code and enable a lightweight declarative style.

This is a hobby project and not production software. It aims to make common Swing UI patterns shorter and easier to read when using Kotlin.

Key points
- Composable wrappers for common Swing components (buttons, labels, panels).
- Simple layout helpers (rows, columns, scaffold-like containers).
- Minimal, focused scope — intended as a small utility library.

Quick example
```kotlin
col {
    +button("Click") { println("clicked") }
    +label("Static text")
}
```

Counter example (concise)
```kotlin
val counter = remember(0)
val label = label("Count: ${counter()}")
counter.observe { label.text = "Count: ${counter()}" }
+scaffold(
    center = { +label },
    south = { +button("Inc") { counter(counter() + 1) } }
)
```

Build and run
- Requires JDK 17 (set in Gradle toolchain).
- Build with Gradle: `./gradlew build` (on Windows use `./gradlew.bat build`).

Contributing
- Issues and pull requests are welcome. Keep changes small and focused.

License
See `LICENSE.txt` in the repository.
