package com.blackmanba.nio;

import static org.junit.Assert.*;

import org.junit.Test;

public class ByteBufferTest {
	@Test
	public void testByteBufferRW() throws Exception {
		ByteBufferDemo.readFile("file/1.txt");
		
	}
}
