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
package de.dimensionv.java.libraries.common.utilities.file.filefilters;

import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author vseifert
 */
public class FileFileFilterTest extends AbstractFileFilterTest {

  public FileFileFilterTest() {
  }

  @Override
  @Before
  public void setUp() throws IOException, InterruptedException {
    instance = new FileFileFilter();
    super.setUp();
  }

  @Override
  @After
  public void tearDown() {
    super.tearDown();
  }

  @Test
  public void testAcceptWithoutHiddenFile() {
    checkAcceptWithoutHiddenFile(true);
  }

  @Test
  public void testAcceptWithoutHiddenHiddenFile() {
    checkAcceptWithoutHiddenHiddenFile(false);
  }

  @Test
  public void testAcceptWithoutHiddenDirectory() {
    checkAcceptWithoutHiddenDirectory(false);
  }

  @Test
  public void testAcceptWithoutHiddenHiddenDirectory() {
    checkAcceptWithoutHiddenHiddenDirectory(false);
  }

  @Test
  public void testAcceptWithHiddenFile() {
    checkAcceptWithHiddenHiddenFile(true);
  }

  @Test
  public void testAcceptWithHiddenHiddenFile() {
    checkAcceptWithoutHiddenFile(true);
  }

  @Test
  public void testAcceptWithHiddenDirectory() {
    checkAcceptWithHiddenDirectory(false);
  }

  @Test
  public void testAcceptWithHiddenHiddenDirectory() {
    checkAcceptWithHiddenHiddenDirectory(false);
  }
}
