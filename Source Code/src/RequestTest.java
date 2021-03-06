import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

class RequestTest {
	//private Request Request;

		@Test
		public void testgetStartFloor() {
			Request r = new Request(LocalTime.NOON,2,"UP",4);
			assertEquals(2, r.getStartFloor());
			//assertFalse(3, r.getStartFloor());
			
		}
		
		@Test
		public void testgetTime() {
			Request r1 = new Request(LocalTime.MIDNIGHT, 3,"down",5);
			assertEquals(LocalTime.MIDNIGHT, r1.getTime());
			
		}
		@Test
		public void testgetDirection() {
			Request r5 = new Request(LocalTime.now(), 3,"down",2);
			assertEquals("down", r5.getDirection());
			String direction =  "down";
			assertTrue(r5.getDirection().equals(direction));
			
			}
			
		
		@Test
		public void testgetDestFloor() {
			Request r3 = new Request(LocalTime.MIDNIGHT, 3,"down",2);
			assertFalse(r3.getDestFloor()==6);//should assert false since destination actual is 5
			
		}
		
		@Test
		public void testToString() {
			Request r4 = new Request(LocalTime.parse("00:00:01.100"), 3,"UP",5);
			String result = "00:00:01.100 3 UP 5";
			assertEquals(r4.toString(), result);
			
		}
	

}
