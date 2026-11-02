# Parallel Boggle

[![Build](https://github.com/sayedihashimi/boggle/actions/workflows/build.yml/badge.svg)](https://github.com/sayedihashimi/boggle/actions/workflows/build.yml)

A multithreaded Boggle solver, originally written in 2007 as a parallel programming
course project (see `docs/`). Given an N×N board and a dictionary, it finds every
dictionary word that can be formed on the board. It compares several search
strategies and the speedup from running them across many threads.

## Prerequisites

- **JDK 25** (any distribution; [Eclipse Temurin](https://adoptium.net/) recommended).
  On Windows: `winget install EclipseAdoptium.Temurin.25.JDK`
- You don't need to install Gradle. The included Gradle wrapper (`gradlew` /
  `gradlew.bat`) downloads the right version the first time you run it.

Check your JDK with:

```sh
java -version   # should report 25
```

## Build

```sh
./gradlew build        # macOS / Linux / Git Bash
gradlew.bat build      # Windows cmd / PowerShell
```

This compiles the code and runs the tests. Compiled output goes to `build/`.

## Run the tests

```sh
./gradlew test
```

The HTML test report is written to `build/reports/tests/test/index.html`. The slow
large-board (32×32, 100×100) and performance tests are marked `@Ignore`. To run one,
remove its `@Ignore` annotation, or run it from IntelliJ.

## Run the app

The main entry point is `hw4.boggle.ParallelBoggleEvaluator`. It times the
search with a shared synchronized board and with one copy of the board per thread.

```sh
./gradlew run --args="<boardSize> <numThreads> [dictionaryFile]"
```

Examples:

```sh
./gradlew run --args="16 4"                        # 16x16 board, 4 threads
./gradlew run --args="32 8"                        # 32x32 board, 8 threads
./gradlew run --args="8 2 ./files/word.1000.list"  # custom dictionary
```

- `boardSize` selects `files/board<N>x<N>.txt`. Boards included: 4, 5, 8, 16, 25, 32, 100.
- `dictionaryFile` defaults to `files/word.list` (about 264k words).
- Running with **no arguments** benchmarks `files/board64x64.txt` with 16 threads.
  That board isn't in the repo; `./gradlew test` generates a random one. Run the
  tests once first.
- Paths are relative to the project root, and results are written to `output/`.

## Open in IntelliJ IDEA

1. **File > Open**, select `build.gradle.kts`, and choose **Open as Project**.
2. If IntelliJ asks for a JDK, pick JDK 25 (**File > Project Structure > SDKs**, or
   **Download JDK** there).
3. Wait for the Gradle import to finish, then:
   - **Run the app:** open `src/hw4/boggle/ParallelBoggleEvaluator.java` and click
     the green arrow next to `main`. To pass arguments, edit the run configuration
     (**Run > Edit Configurations > Program arguments**, e.g. `16 4`) and set the
     **Working directory** to the project root so `./files` resolves.
   - **Run tests:** right-click the `test` folder, then **Run 'All Tests'**, or click
     the arrow next to any test class or method.
   - **Gradle tasks:** use the Gradle tool window (elephant icon on the right).

## Project layout

```
src/                        application code (source root)
  hw4/boggle/               final version: boards, dictionary, players, evaluator
  com/sedodream/boggle/     earlier experiments and alternative implementations
  com/sedodream/swtTest/    small SWT GUI demos (Windows x64 only)
test/                       JUnit 4 tests (test source root)
files/                      boards (board<N>x<N>.txt), dictionaries (*.list), dice
docs/                       original assignment and presentation
build.gradle.kts            Gradle build (Java 25 toolchain, dependencies, run/test setup)
.github/workflows/build.yml CI: builds and tests on every push to main and every PR
```

## Dependencies

Gradle downloads these from Maven Central:

- `commons-collections:commons-collections:3.2.2`
- `org.eclipse.platform:org.eclipse.swt.win32.win32.x86_64` (only for the SWT demos)
- `junit:junit:4.13.2` (tests)

## License

See [LICENSE](LICENSE).
