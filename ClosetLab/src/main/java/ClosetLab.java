import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class ClosetLab {

    // ---------- Data Model ----------
    public static class ClothingItem {
        String category;   // "TOP", "BOTTOM", "DRESS", "SHOES"
        String label;      // brand/label (used for sorting/search)
        String color;      // e.g., "black", "blue"
        String size;       // e.g., "S","M","L"

        ClothingItem(String category, String label, String color, String size) {
            this.category = category;
            this.label = label;
            this.color = color;
            this.size = size;
        }
    }

    // ---------- Closet Utilities ----------
    public static class Closet {
        List<ClothingItem> items = new ArrayList<>();

        void add(ClothingItem item) { items.add(item); }

        // O(1): grabFirst() — grabbing the first item is constant-time.
        ClothingItem grabFirst() {
            // Defensive check to avoid IndexOutOfBounds
            return items.isEmpty() ? null : items.get(0);
        }

        // O(n): linear search — find first item by color
        ClothingItem findFirstByColor(String color) {
            for (ClothingItem ci : items) {
                if (ci.color.equals(color)) return ci;
            }
            return null;
        }

        // O(n log n): sort by label (brand) using Comparator
        void sortByLabel() {
            items.sort(Comparator.comparing(ci -> ci.label));
        }

        // O(log n): binary search by label (requires sorted by label)
        ClothingItem binarySearchByLabel(String label) {
            int lo = 0, hi = items.size() - 1;
            while (lo <= hi) {
                int mid = (lo + hi) >>> 1;
                int cmp = items.get(mid).label.compareTo(label);
                if (cmp == 0) return items.get(mid);
                if (cmp < 0) lo = mid + 1;
                else hi = mid - 1;
            }
            return null;
        }

        // O(n^2): generate all outfits (TOP × BOTTOM)
        // Return count only to avoid huge memory allocations.
        int countAllOutfits() {
            List<ClothingItem> tops = new ArrayList<>();
            List<ClothingItem> bottoms = new ArrayList<>();
            for (ClothingItem ci : items) {
                if ("TOP".equals(ci.category)) tops.add(ci);
                if ("BOTTOM".equals(ci.category)) bottoms.add(ci);
            }
            int count = 0;
            for (ClothingItem t : tops) {
                for (ClothingItem b : bottoms) {
                    // pairing t with b -> one outfit
                    count++;
                }
            }
            return count;
        }
    }

    // ---------- Optional Index (Time vs Space) ----------
    // Build a color index -> O(n) build time, extra memory, O(1) average lookup.
    public static class ColorIndex {
        private final Map<String, List<ClothingItem>> byColor = new HashMap<>();

        ColorIndex(List<ClothingItem> items) {
            for (ClothingItem ci : items) {
                byColor.computeIfAbsent(ci.color, k -> new ArrayList<>()).add(ci);
            }
        }

        List<ClothingItem> getByColor(String color) {
            return byColor.getOrDefault(color, Collections.emptyList());
        }
    }

    // ---------- Data Generation ----------
    static final String[] CATEGORIES = {"TOP", "BOTTOM", "DRESS", "SHOES"};
    static final String[] COLORS = {"black","white","blue","red","green","beige","pink","purple","gray"};
    static final String[] SIZES = {"XS","S","M","L","XL"};
    static final String[] LABELS = {
            "Aerie", "Zara", "H&M", "Uniqlo", "Nike", "Adidas", "Levi's", "Reformation",
            "Madewell", "Gucci", "Prada", "Coach", "Patagonia", "Everlane", "OutdoorVoices",
            "Chanel", "Dior", "Fabletics", "Alo", "Skims", "Gap", "BananaRepublic"
    };

    public static List<ClothingItem> makeRandomCloset(int n) {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        List<ClothingItem> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            String category = CATEGORIES[r.nextInt(CATEGORIES.length)];
            String color = COLORS[r.nextInt(COLORS.length)];
            String size = SIZES[r.nextInt(SIZES.length)];
            String label = LABELS[r.nextInt(LABELS.length)] + "-" + r.nextInt(1_000_000);
            list.add(new ClothingItem(category, label, color, size));
        }
        return list;
    }

    // ---------- Timing Helpers ----------
    static long timeMillis(Runnable r) {
        long t0 = System.nanoTime();
        r.run();
        long t1 = System.nanoTime();
        return (t1 - t0) /1000; // to millions of a second
    }

    // ---------- Main: Run Experiments ----------
    public static void main(String[] args) {
        // Try different sizes to observe growth
        int[] sizes = {200, 1000, 2000}; // adjust if your machine is slow/fast

        for (int n : sizes) {
            System.out.println("\n=== Closet size n = " + n + " ===");
            Closet closet = new Closet();
            for (ClothingItem ci : makeRandomCloset(n)) closet.add(ci);

            // O(1): Grab first
            long tGrab = timeMillis(() -> {
                ClothingItem first = closet.grabFirst();
                // no-op with 'first' to avoid JIT elimination
                if (first != null && first.size.equals("NOPE")) System.out.print("");
            });
            System.out.println("O(1) grabFirst(): " + tGrab + " ms");

            // O(n): linear search by color
            String targetColor = "black";
            long tLinear = timeMillis(() -> {
                ClothingItem found = closet.findFirstByColor(targetColor);
                if (found != null && found.size.equals("NOPE")) System.out.print("");
            });
            System.out.println("O(n) findFirstByColor(\"" + targetColor + "\"): " + tLinear + " ms");

            // O(n log n): sort by label
            long tSort = timeMillis(closet::sortByLabel);
            System.out.println("O(n log n) sortByLabel(): " + tSort + " ms");

            // O(log n): binary search by label (use an existing label from the closet)
            String existingLabel = closet.items.get(closet.items.size() / 2).getClass() != null
                    ? closet.items.get(closet.items.size() / 2).label
                    : "Zara-0";
            long tBinSearch = timeMillis(() -> {
                ClothingItem f = closet.binarySearchByLabel(existingLabel);
                if (f != null && f.size.equals("NOPE")) System.out.print("");
            });
            System.out.println("O(log n) binarySearchByLabel(existing): " + tBinSearch + " ms");

            // O(n^2): count all outfits (TOP × BOTTOM)
            long tOutfits = timeMillis(() -> {
                int count = closet.countAllOutfits();
                if (count == -1) System.out.print("");
            });
            System.out.println("O(n^2) countAllOutfits(): " + tOutfits + " ms");

            // Time vs. Space: Build color index (O(n) build; O(1) average lookup)
            long tIndexBuild = timeMillis(() -> {
                ColorIndex idx = new ColorIndex(closet.items);
                if (idx.getByColor("magenta").isEmpty() && n == -1) System.out.print("");
            });
            System.out.println("Index build (HashMap) ~O(n): " + tIndexBuild + " ms");

            // O(1) average: color lookup from index
            ColorIndex idx = new ColorIndex(closet.items);
            long tIndexLookup = timeMillis(() -> {
                List<ClothingItem> blacks = idx.getByColor("black");
                if (blacks.size() == -1) System.out.print("");
            });
            System.out.println("Index lookup by color ~O(1): " + tIndexLookup + " ms");
        }
    }
}
