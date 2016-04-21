package com.caoyt;

import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooKeeper.States;
import org.apache.zookeeper.data.ACL;

/**
 * @文件名:SyncCreateNode.java <br/>
 * @版权:Copyright 2016.Cao Ying tong(caoyingtong@aliyun.com) All Rights Reserved.<br/>
 * @描述:同步创建节点 <br/>
 * @修改人:caoyingtong <br/>
 * @修改时间:2016-4-19 下午10:02:51 <br/>
 */
public class SyncCreateNode implements Watcher{

	private static ZooKeeper zooKeeper;
	
	public static void main(String[] args) throws Exception {
		/** 实例化的过程也是客户端连接的过程 */
		String serverIpAndPort = "192.168.1.105:2181";
		Integer timeout = 5000;
		//第三个参数是一个事件监听器,通过这个事件监听器来接收zookeeper的一些事件通知
		zooKeeper = new ZooKeeper(serverIpAndPort, timeout, new SyncCreateNode());
		
		//获取zookeeper的状态
		States state = zooKeeper.getState();
		
		System.out.println("state---->" + state);
		
		
		Thread.sleep(Integer.MAX_VALUE);
	}
	
	/**
	 * CreateMode.PERSISTENT 持久节点
	 * CreateMode.PERSISTENT_SEQUENTIAL 持久节点,并且是一个顺序节点
	 * CreateMode.EPHEMERAL 临时节点
	 * CreateMode.EPHEMERAL_SEQUENTIAL 临时节点,并且是一个顺序节点
	 */
	@Override
	public void process(WatchedEvent event) {
		String nodePath = "/node_1";
		byte[] nodeDate = "123".getBytes();
		List<ACL> nodeAcl = Ids.OPEN_ACL_UNSAFE; //权限信息,当前的权限信息表示,该节点创建以后,任何人可以对这个节点做任何操作
		CreateMode nodeMode = CreateMode.PERSISTENT; //节点模式--持久节点, 例如:临时节点,持久节点
		try {
			String returnNodePath = zooKeeper.create(nodePath, nodeDate, nodeAcl, nodeMode);
			System.out.println("nodePath---->" + returnNodePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
