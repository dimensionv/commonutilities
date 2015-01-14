/*
 * Copyright (c) 2015, vseifert
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package de.dimensionv.java.libraries.common.utilities.strings;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author vseifert
 */
public class NaturalSortComparatorTest {

  @Test
  public void testCompareNormalString1() {
    System.out.println("Test NaturalSortComparator.compare() with simple string: less-than");
    String s1 = "Bla";
    String s2 = "Blu";
    NaturalSortComparator instance = new NaturalSortComparator();
    int expResult = -1;
    int result = instance.compare(s1, s2);
    Assert.assertEquals(expResult, result);
  }

  @Test
  public void testCompareNormalString2() {
    System.out.println("Test NaturalSortComparator.compare() with simple string: greater-than");
    String s1 = "Blu";
    String s2 = "Bla";
    NaturalSortComparator instance = new NaturalSortComparator();
    int expResult = 1;
    int result = instance.compare(s1, s2);
    Assert.assertEquals(expResult, result);
  }

  @Test
  public void testCompareNormalString3() {
    System.out.println("Test NaturalSortComparator.compare() with simple string: equals");
    String s1 = "Bla";
    String s2 = "Bla";
    NaturalSortComparator instance = new NaturalSortComparator();
    int expResult = 0;
    int result = instance.compare(s1, s2);
    Assert.assertEquals(expResult, result);
  }

  @Test
  public void testCompareNumberedString1() {
    System.out.println("Test NaturalSortComparator.compare() with numbers: less-than");
    String s1 = "Bla9";
    String s2 = "Bla10";
    NaturalSortComparator instance = new NaturalSortComparator();
    int expResult = -1;
    int result = instance.compare(s1, s2);
    Assert.assertEquals(expResult, result);
  }

  @Test
  public void testCompareNumberedString2() {
    System.out.println("Test NaturalSortComparator.compare() with numbers: greater-than");
    String s1 = "Bla10";
    String s2 = "Bla9";
    NaturalSortComparator instance = new NaturalSortComparator();
    int expResult = 1;
    int result = instance.compare(s1, s2);
    Assert.assertEquals(expResult, result);
  }

  @Test
  public void testCompareNumberedString3() {
    System.out.println("Test NaturalSortComparator.compare() with numbers: equals");
    String s1 = "Bla10";
    String s2 = "Bla10";
    NaturalSortComparator instance = new NaturalSortComparator();
    int expResult = 0;
    int result = instance.compare(s1, s2);
    Assert.assertEquals(expResult, result);
  }

  @Test
  public void testCompareNumberedWithLZString1() {
    System.out.println("Test NaturalSortComparator.compare() with leading zeroed numbers: less-than");
    String s1 = "Bla000109";
    String s2 = "Bla000110";
    int expResult = -1;
    int result = NaturalSortComparator.getInstance().compare(s1, s2);
    Assert.assertEquals(expResult, result);
  }

  @Test
  public void testCompareNumberedWithLZString2() {
    System.out.println("Test NaturalSortComparator.compare() with leading zeroed numbers: greater-than");
    String s1 = "Bla000110";
    String s2 = "Bla000109";
    int expResult = 1;
    int result = NaturalSortComparator.getInstance().compare(s1, s2);
    Assert.assertEquals(expResult, result);
  }

  @Test
  public void testCompareNumberedWithLZString3() {
    System.out.println("Test NaturalSortComparator.compare() with leading zeroed numbers: equals");
    String s1 = "Bla000110";
    String s2 = "Bla000110";
    int expResult = 0;
    int result = NaturalSortComparator.getInstance().compare(s1, s2);
    Assert.assertEquals(expResult, result);
  }

  @Test
  public void testCompareNumberedWithLZAndTextString1() {
    System.out.println("Test NaturalSortComparator.compare() with leading zeroed numbers and text: less-than");
    String s1 = "Bla000110 hm!";
    String s2 = "Bla000110 oh!";
    NaturalSortComparator instance = new NaturalSortComparator();
    int expResult = -1;
    int result = instance.compare(s1, s2);
    Assert.assertEquals(expResult, result);
  }

  @Test
  public void testCompareNumberedWithLZAndTextString2() {
    System.out.println("Test NaturalSortComparator.compare() with leading zeroed numbers and text: greater-than");
    String s1 = "Bla000110 oh!";
    String s2 = "Bla000110 hm!";
    NaturalSortComparator instance = new NaturalSortComparator();
    int expResult = 1;
    int result = instance.compare(s1, s2);
    Assert.assertEquals(expResult, result);
  }

  @Test
  public void testCompareNumberedWithLZAndTextString3() {
    System.out.println("Test NaturalSortComparator.compare() with leading zeroed numbers and text: equal");
    String s1 = "Bla000110 oh!";
    String s2 = "Bla000110 oh!";
    NaturalSortComparator instance = new NaturalSortComparator();
    int expResult = 0;
    int result = instance.compare(s1, s2);
    Assert.assertEquals(expResult, result);
  }

  @Test
  public void testComplexArray() {
    System.out.println("Test complex array");

    // original data, manually sorted in the order we expect the
    // comparator to sort the list...that way , it can be used for
    // comparison in the assert.
    String[] originalData = new String[]{
      "1-04", "1-4", "1-40", "10-40", "Alice", "Bob", "Charly",
      "a6-b6", "h2-i7", "item01", "item02", "item02a", "item2", "item3",
      "item00004", "item00004", "item00004", "item00004", "item00004",
      "item4", "item 4 else", "item05", "item 5", "item 5",
      "item 5 something", "item 6", "item    8", "item128", "item128a",
      "item255", "item256", "item04096", "item04096 test 1",
      "item04096 test 2", "item04096 test 2a", "item04096 test 2b",
      "item04096 test 3", "item04096 test 3a", "item04096 test 3b",
      "x2-y08", "z3-f6"
    };

    // shuffle() is only supported for Collections, not for Arrays.
    // Therefore, we need to transform the array above into a list.
    List shuffledData = Arrays.asList(originalData.clone());
    // now disrupt the order of the original data by shuffling it around :)
    Collections.shuffle(shuffledData);
    // and sort the shuffled array using the NaturalSortComparator...
    Collections.sort(shuffledData, NaturalSortComparator.getInstance());
    // transform list back into an array for the assert...
    String[] result = (String[]) shuffledData.toArray(new String[shuffledData.size()]);

    // now check if the result as as we expect it to be...
    Assert.assertArrayEquals(originalData, result);
  }
}
