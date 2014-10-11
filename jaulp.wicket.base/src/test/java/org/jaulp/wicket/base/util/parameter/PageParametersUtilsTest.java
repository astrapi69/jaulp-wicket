package org.jaulp.wicket.base.util.parameter;

import org.apache.wicket.util.string.StringValue;
import org.jaulp.wicket.base.util.parameter.PageParametersUtils;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class PageParametersUtilsTest {

	@Test
	public void testIsNotNullOrEmpty() {
		boolean expected = false;
		boolean actual = PageParametersUtils.isNotNullOrEmpty(null);
		AssertJUnit.assertTrue("Should be false.", expected == actual);
		StringValue sv = StringValue.valueOf("");
		actual = PageParametersUtils.isNotNullOrEmpty(sv);
		AssertJUnit.assertTrue("Should be false.", expected == actual);
		String s = null;
		sv = StringValue.valueOf(s);
		actual = PageParametersUtils.isNotNullOrEmpty(sv);
		AssertJUnit.assertTrue("Should be false.", expected == actual);
	}
	@Test
	public void testIsNullOrEmpty() {
		boolean expected = true;
		boolean actual = PageParametersUtils.isNullOrEmpty(null);
		AssertJUnit.assertTrue("Should be true.", expected == actual);
		StringValue sv = StringValue.valueOf("");
		actual = PageParametersUtils.isNullOrEmpty(sv);
		AssertJUnit.assertTrue("Should be true.", expected == actual);
		String s = null;
		sv = StringValue.valueOf(s);
		actual = PageParametersUtils.isNullOrEmpty(sv);
		AssertJUnit.assertTrue("Should be true.", expected == actual);
	}

}
