package com.caoyt;

import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooKeeper.States;

public class CreateSession {
	private static ZooKeeper zooKeeper;
	
	public static void main(String[] args) throws Exception {
		/** 实例化的过程也是客户端连接的过程 */
		String serverIpAndPort = "192.168.1.105:2181";
		Integer timeout = 5000;
		//第三个参数是一个事件监听器,通过这个事件监听器来接收zookeeper的一些事件通知
		zooKeeper = new ZooKeeper(serverIpAndPort, timeout, new CustomWatcher());
		
		//获取zookeeper的状态
		States state = zooKeeper.getState();
		
		System.out.println("state---->" + state);
		
		
		Thread.sleep(Integer.MAX_VALUE);
		
	}
}
