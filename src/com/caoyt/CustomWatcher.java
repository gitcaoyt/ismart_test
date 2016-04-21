package com.caoyt;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;


/**
 * @文件名:CustomWatcher.java <br/>
 * @版权:Copyright 2016.Cao Ying tong(caoyingtong@aliyun.com) All Rights Reserved.<br/>
 * @描述:事件监听器 <br/>
 * @修改人:caoyingtong <br/>
 * @修改时间:2016-4-19 下午9:43:47 <br/>
 */
public class CustomWatcher implements Watcher{

	@Override
	public void process(WatchedEvent event) {
		System.out.println("收到的事件----->" + event);
		
		//从事件是获取到当前客户端的状态,判断是否等于系统预设的一个状态“已连接”
		if (event.getState() == KeeperState.SyncConnected) {
			doSomething();
		}
		
	}

	private void doSomething() {
		System.out.println("======doSomething=====");
	}

}
