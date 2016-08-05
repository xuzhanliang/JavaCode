package com.blackmanba.nio;

import javax.management.RuntimeErrorException;

import org.junit.Test;

import com.blackmanba.netty.DiscardClient;


public class NettyClientTest {
	@Test
	public void test01() throws Exception {
		for(int i=0;i<1000;i++){
			long start = System.currentTimeMillis();
			Object obj = DiscardClient.startClient(i);
			if(obj==null){
				throw new RuntimeException("返回数据为空");
			}
			System.out.println(obj);
			long end=System.currentTimeMillis();
			System.out.println("第"+i+"次循环耗时"+(end-start)+"ms");
		}
		
		
	}
}
