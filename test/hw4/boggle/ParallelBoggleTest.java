package hw4.boggle;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 * End-to-end check that both ParallelBoggle implementations find exactly the
 * words listed in files/board&lt;N&gt;x&lt;N&gt;.results, whether the dictionary
 * uses LF or CRLF line endings.
 */
public class ParallelBoggleTest {
	private static final Path DICTIONARY = Paths.get("files", "word.list");
	private static final int NUM_THREADS = 4;

	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();

	@Test
	public void synchedBoardFindsExpectedWordsWithLfDictionary() throws Exception {
		assertFindsExpectedWords(false, writeDictionary("\n"), 4);
		assertFindsExpectedWords(false, writeDictionary("\n"), 8);
	}

	@Test
	public void synchedBoardFindsExpectedWordsWithCrlfDictionary() throws Exception {
		assertFindsExpectedWords(false, writeDictionary("\r\n"), 4);
		assertFindsExpectedWords(false, writeDictionary("\r\n"), 8);
	}

	@Test
	public void dupedBoardFindsExpectedWordsWithLfDictionary() throws Exception {
		assertFindsExpectedWords(true, writeDictionary("\n"), 4);
		assertFindsExpectedWords(true, writeDictionary("\n"), 8);
	}

	@Test
	public void dupedBoardFindsExpectedWordsWithCrlfDictionary() throws Exception {
		assertFindsExpectedWords(true, writeDictionary("\r\n"), 4);
		assertFindsExpectedWords(true, writeDictionary("\r\n"), 8);
	}

	private void assertFindsExpectedWords(boolean duped, Path dictionary, int n) throws IOException {
		String[] board = ParallelBoggleEvaluator.readBoardFromFile(
				String.format("./files/board%dx%d.txt", n, n));
		ParallelBoggle boggle = duped
				? new DupedBoardParallelBoggle(dictionary.toString(), n, NUM_THREADS)
				: new SynchedBoardParallelBoggle(dictionary.toString(), n, NUM_THREADS);

		Set<String> actual = new TreeSet<>(Arrays.asList(boggle.getWords(board)));
		Set<String> expected = readWords(Paths.get("files", String.format("board%dx%d.results", n, n)));

		assertEquals(String.format("%s on %dx%d board", boggle.getClass().getSimpleName(), n, n),
				expected, actual);
	}

	/** Copies files/word.list into a temp file using the given line separator. */
	private Path writeDictionary(String lineSeparator) throws IOException {
		List<String> words = Files.readAllLines(DICTIONARY, StandardCharsets.UTF_8).stream()
				.map(String::strip)
				.filter(w -> !w.isEmpty())
				.collect(Collectors.toList());
		Path file = tempFolder.newFile().toPath();
		Files.writeString(file, String.join(lineSeparator, words) + lineSeparator, StandardCharsets.UTF_8);
		return file;
	}

	private static Set<String> readWords(Path file) throws IOException {
		return Files.readAllLines(file, StandardCharsets.UTF_8).stream()
				.map(String::strip)
				.filter(w -> !w.isEmpty())
				.collect(Collectors.toCollection(TreeSet::new));
	}
}
