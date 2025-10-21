package shopping;

public class FittingRoom {
    /**
     * Represents a fitting room in a clothing store.
     * Each fitting room has a unique identifier and can be occupied or unoccupied.
     * Create a class FittingRoom that manages the clothing items being tried on
     * Use ArrayBoundedStack to manage the clothing items in the fitting room.
     * remove the return statements and implement the methods properly
     */
    public static final int DEFAULT_CAPACITY = 5;
    private final int id;
    public int capacity;

    public FittingRoom(int id, int capacity) {
        /*
         * Initializes a fitting room with the given identifier.
         */
        this.id = id;
        this.capacity = capacity;
        // add field for stack of clothing items
    }
    public FittingRoom(int id) {
        /*
         * Initializes a fitting room with the given identifier and default capacity.
         */
        this(id, DEFAULT_CAPACITY);
    }
    // push item onto the stack. Throw exception if full
    public void tryOn(String item) {
        /**
         * Adds a clothing item to the fitting room stack.
         * If this item makes the room to exceed capacity, throw an exception.
         */
    }
    // pop item. Throw exception if empty
    public String takeOff()  {
        /**
         * Removes and returns the top clothing item from the fitting room stack.
         */
        return "";
    }
    // peek at top item
    public String currentOutfit() {
        /**
         * Returns the top clothing item from the fitting room stack without removing it.
         */
        return "";
    }     // peek at top item
    public boolean isEmpty(){
        /**
         * Returns true if the fitting room stack is empty, otherwise returns false.
         */
        return true;
    }
}
