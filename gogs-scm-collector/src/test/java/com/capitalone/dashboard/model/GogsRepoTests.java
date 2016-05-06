package com.capitalone.dashboard.model;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class GogsRepoTests {


	private GogsRepo gogsRepo1;
	private GogsRepo gogsRepo2;
	private GogsRepo gogsRepo3;



	@Before
    public void init() {
		gogsRepo1 = new GogsRepo();
		gogsRepo1.setRepoUrl("https://github.com/capitalone/Hygiea.git");
		gogsRepo1.setBranch("master");
		gogsRepo2 = new GogsRepo();
		gogsRepo2.setRepoUrl("https://github.com/capitalone/Hygiea.git");
        gogsRepo2.setBranch("master");
        gogsRepo3=new GogsRepo();
        gogsRepo3.setRepoUrl("https://github.com/capitalone/Hygieas.git");
        gogsRepo3.setBranch("master");
        }



	@Test
	public void testEquals() throws Exception {
		boolean x=gogsRepo1.equals(gogsRepo2);
		assertTrue(x);
	}

	@Test
	public void testHashCode() throws Exception {
		int hashcode1=gogsRepo1.hashCode();
		int hashcode2=gogsRepo2.hashCode();

		assertEquals(hashcode1, hashcode2);
	}

	@Test
	public void testEqualsNegative() throws Exception {
			boolean y=gogsRepo3.equals(gogsRepo1);
			assertTrue(!y);
		}

	@Test
	public void testHashCodeNegative() throws Exception {
		int hashcode1=gogsRepo1.hashCode();
		int hashcode3=gogsRepo3.hashCode();
		assertTrue(hashcode1!=hashcode3);
	}




}
