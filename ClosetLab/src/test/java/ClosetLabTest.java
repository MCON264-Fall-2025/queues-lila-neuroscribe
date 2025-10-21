import org.junit.jupiter.api.*;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 scaffold for the ClosetLab.
 * Assumes the lab code is in default package and the following are public:
 * - ClosetLab.ClothingItem
 * - ClosetLab.Closet
 * - ClosetLab.ColorIndex
 * - ClosetLab.makeRandomCloset(int)
 */
public class ClosetLabTest {

    static ClosetLab.Closet smallCloset;
    static ClosetLab.Closet mediumCloset;
    static ClosetLab.Closet largeCloset;

    @BeforeAll
    static void setup() {
        smallCloset = new ClosetLab.Closet();
        ClosetLab.makeRandomCloset(500).forEach(smallCloset::add);

        mediumCloset = new ClosetLab.Closet();
        ClosetLab.makeRandomCloset(5_000).forEach(mediumCloset::add);

        largeCloset = new ClosetLab.Closet();
        ClosetLab.makeRandomCloset(20_000).forEach(largeCloset::add);
    }

    // ---------- Helpers ----------
    private static long timeMillis(Runnable r) {
        long t0 = System.nanoTime();
        r.run();
        long t1 = System.nanoTime();
        return (t1 - t0) / 1_000_000;
    }

    private static long countCategory(Collection<ClosetLab.ClothingItem> items, String category) {
        return items.stream().filter(ci -> category.equals(ci.category)).count();
    }

    private static List<ClosetLab.ClothingItem> linearGetByColor(List<ClosetLab.ClothingItem> items, String color) {
        List<ClosetLab.ClothingItem> out = new ArrayList<>();
        for (ClosetLab.ClothingItem ci : items) {
            if (color.equals(ci.color)) out.add(ci);
        }
        return out;
    }

    // ---------- Functional Correctness ----------

    @Test
    @DisplayName("grabFirst(): returns null on empty, else first item (sanity)")
    void testGrabFirstSanity() {
        ClosetLab.Closet empty = new ClosetLab.Closet();
        assertNull(empty.grabFirst(), "Empty closet should return null from grabFirst()");

        ClosetLab.Closet one = new ClosetLab.Closet();
        ClosetLab.ClothingItem item = new ClosetLab.ClothingItem("TOP", "Brand-1", "black", "M");
        one.add(item);
        assertSame(item, one.grabFirst(), "grabFirst() should return the first inserted item");
    }

    @Test
    @DisplayName("findFirstByColor(): finds a color if present or returns null")
    void testFindFirstByColor() {
        ClosetLab.ClothingItem found = mediumCloset.findFirstByColor("black");
        // Non-deterministic content, but 'black' is common: we just assert no exception; if null, it's still valid.
        // Strengthen by verifying that, if any black items exist, the result is black.
        boolean anyBlack = mediumCloset.items.stream().anyMatch(ci -> "black".equals(ci.color));
        if (anyBlack) {
            assertNotNull(found, "Should find an item with color=black when one exists");
            assertEquals("black", found.color);
        } else {
            assertNull(found, "Should return null if no item with color=black exists");
        }
    }

    @Test
    @DisplayName("sortByLabel() then binarySearchByLabel(): finds an existing label")
    void testSortThenBinarySearchExisting() {
        ClosetLab.Closet copy = new ClosetLab.Closet();
        copy.items.addAll(mediumCloset.items);

        copy.sortByLabel();
        assertFalse(copy.items.isEmpty());

        String existing = copy.items.get(copy.items.size() / 2).label;
        ClosetLab.ClothingItem res = copy.binarySearchByLabel(existing);
        assertNotNull(res, "binarySearch should find an existing label");
        assertEquals(existing, res.label);
    }

    @Test
    @DisplayName("binarySearchByLabel(): returns null on missing label")
    void testBinarySearchMissing() {
        ClosetLab.Closet copy = new ClosetLab.Closet();
        copy.items.addAll(smallCloset.items);
        copy.sortByLabel();

        ClosetLab.ClothingItem res = copy.binarySearchByLabel("ZZZ-NOT-A-LABEL-9999999");
        assertNull(res, "binarySearch should return null for a label not present");
    }

    @Test
    @DisplayName("countAllOutfits(): equals (#TOP * #BOTTOM)")
    void testCountAllOutfitsMath() {
        ClosetLab.Closet copy = new ClosetLab.Closet();
        copy.items.addAll(smallCloset.items);

        long tops = countCategory(copy.items, "TOP");
        long bottoms = countCategory(copy.items, "BOTTOM");
        int expected = Math.toIntExact(tops * bottoms);

        int actual = copy.countAllOutfits();
        assertEquals(expected, actual, "Outfit count must be TOPS × BOTTOMS");
    }

    @Test
    @DisplayName("ColorIndex: lookup equals linear filter (same items)")
    void testColorIndexMatchesLinear() {
        ClosetLab.Closet copy = new ClosetLab.Closet();
        copy.items.addAll(mediumCloset.items);

        ClosetLab.ColorIndex idx = new ClosetLab.ColorIndex(copy.items);
        String color = "black";

        List<ClosetLab.ClothingItem> viaIndex = idx.getByColor(color);
        List<ClosetLab.ClothingItem> viaLinear = linearGetByColor(copy.items, color);

        // compare by identity or label tuple
        var setIndex = viaIndex.stream().map(ci -> ci.label).collect(Collectors.toSet());
        var setLinear = viaLinear.stream().map(ci -> ci.label).collect(Collectors.toSet());

        assertEquals(setLinear, setIndex, "Index-based lookup should return the same set as linear filter");
    }

    // ---------- (Optional) Performance Smoke Tests ----------
    // NOTE: Disabled by default to keep CI stable across machines.
    @Test
    @Disabled("Enable locally for exploration; performance can vary by environment.")
    @DisplayName("Performance smoke: index lookup typically faster than linear search")
    void testIndexBeatsLinearSearch() {
        ClosetLab.Closet copy = new ClosetLab.Closet();
        ClosetLab.makeRandomCloset(100000).forEach(copy::add);

        String color = "black";
        ClosetLab.ColorIndex idx = new ClosetLab.ColorIndex(copy.items);

        // warmups
        linearGetByColor(copy.items, color);
        idx.getByColor(color);

        long tLinear = timeMillis(() -> linearGetByColor(copy.items, color));
        long tIndex  = timeMillis(() -> idx.getByColor(color));

        // Allow a generous factor since data and hardware vary:
        assertTrue(tIndex * 3 < tLinear,
                "Expected index lookup to be at least ~3x faster than linear filter. tLinear=" + tLinear + "ms, tIndex=" + tIndex + "ms");
    }
}
