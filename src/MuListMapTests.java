import org.junit.Test;
import structures.MapI;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MuListMapTests {

	@Test
	public void itInitializes() {
		new MuListMap();
	}

	@Test
	public void itSets() {
		MuListMap<String, String> map = new MuListMap<String, String>();
		map.set("A", "B");
	}

	@Test
	public void itSetsAndGets() {
		MuListMap<String, String> map = new MuListMap<String, String>();
		map.set("A", "B");
		assertEquals(map.get("A"), "B");
	}

	@Test
	public void itCorrectlyReportsOnSet() {
		MuListMap<String, String> map = new MuListMap<String, String>();
		assertTrue(map.set("A", "B"));
		assertFalse(map.set("A", "C"));
	}

	@Test
	public void itGetsSize() {
		MuListMap<String, String> map = new MuListMap<String, String>();
		map.set("A", "B");
		map.set("A", "C");
		map.set("B", "B");
		map.set("C", "B");
		assertEquals(map.size(), 3);
	}

	@Test
	public void itContains() {
		MuListMap<String, String> map = new MuListMap<String, String>();
		map.set("A", "B");
		assertTrue(map.contains("A"));
		map.remove("A");
		assertFalse(map.contains("A"));
	}

	@Test
	public void itRemoves() {
		MuListMap<String, String> map = new MuListMap<String, String>();
		map.set("A", "B");
		map.remove("A");
		assertEquals(map.size(), 0);
	}

	@Test
	public void itConvertsToArray() {
		MuListMap<String, String> map = new MuListMap<String, String>();
		map.set("A", "B");
		map.set("A", "C");
		map.set("B", "B");
		map.set("C", "B");
		assertArrayEquals(map.toArray(), new MuListMap.ListEntry[] {
			new MuListMap.ListEntry<String, String>("A", "C"), new MuListMap.ListEntry<String, String>("B", "B"), new MuListMap.ListEntry<String, String>("C", "B")
		});
	}

	@Test
	public void itIterates() {
		MuListMap<String, String> map = new MuListMap<String, String>();
		map.set("A", "B");
		map.set("A", "C");
		map.set("B", "B");
		map.set("C", "B");
		ArrayList<MapI.Entry<String, String>> list = new ArrayList<MapI.Entry<String, String>>();
		for (MapI.Entry<String, String> entry: map) {
			list.add(entry);
		}
		assertArrayEquals(list.toArray(), map.toArray());
	}

}
