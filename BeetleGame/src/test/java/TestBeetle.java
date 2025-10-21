import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestBeetle {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testBeetle() {
		fail("Not yet implemented");
	}

	@Test
	void testAddHead() {
		Beetle beetle = new Beetle();
		assertFalse(beetle.addHead());
		assertTrue(beetle.addBody());
		assertTrue(beetle.addHead());
		System.out.println("Beetle with Head and Body \n" + beetle.toString());
				
	}

	@Test
	void testAddBody() {
		Beetle beetle = new Beetle();
		assertTrue (beetle.addBody());
		assertFalse(beetle.addBody());
		System.out.println("Beetle with Body \n" + beetle.toString());
	}

	@Test
	void testAddEye() {
		fail("Not yet implemented");
	}

	@Test
	void testAddFeeler() {
		Beetle beetle = new Beetle();
		assertFalse (beetle.addFeeler());
		assertTrue(beetle.addBody());
		assertFalse(beetle.addFeeler());
		assertTrue(beetle.addHead());
		assertTrue(beetle.addFeeler());
		System.out.println("Beetle with one Feeler \n" + beetle.toString());
		assertTrue(beetle.addFeeler());
		System.out.println("Beetle with two feelers \n" + beetle.toString());
		assertFalse(beetle.addFeeler());
	}

	@Test
	void testAddLeg() {
		fail("Not yet implemented");
	}

	@Test
	void testAddTail() {
		fail("Not yet implemented");
	}

	@Test
	void testIsComplete() {
		fail("Not yet implemented");
	}

	@Test
	void testToString() {
		fail("Not yet implemented");
	}

}
