package shopping;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FittingRoomTest {

    @Test
    @DisplayName("Trying on clothes: Red Dress then Blue Jacket")
    void tryOnClothes() {
        // TODO: create fitting room, try on "Red Dress", then "Blue Jacket"
        // assert currentOutfit() is correct
    }

    @Test
    @DisplayName("Taking off clothes: Sneakers, Jeans, T-shirt")
    void takeOffClothes() {
        // TODO: push three items
        // takeOff should return "T-shirt"
        // currentOutfit should be "Jeans"
    }

    @Test
    @DisplayName("Overflow test: exceeding capacity throws exception")
    void overflowTest() {
        // TODO: create fitting room with capacity = 3
        // add 4 items and expect StackOverflowException
    }

    @Test
    @DisplayName("Underflow test: removing from empty room throws exception")
    void underflowTest() {
        // TODO: create empty fitting room
        // call takeOff and expect StackUnderflowException
    }

    @Test
    @DisplayName("Empty fitting room check: isEmpty true initially and after removing all")
    void emptyRoomCheck() {
        // TODO: check isEmpty before and after removing all items
    }
}

