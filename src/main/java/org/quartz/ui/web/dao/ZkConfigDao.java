package org.quartz.ui.web.dao;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class ZkConfigDao {

	private static final Log log = LogFactory.getLog(ZkConfigDao.class);

	private ZooKeeper zk;

	public ZkConfigDao(String connectString, int timeout) {
		try {
			zk = new ZooKeeper(connectString, timeout, null);
		} catch (IOException e) {
			log.error("error init zookeeper connection", e);
		}
	}

	public boolean isConfigExist(String jobName) throws KeeperException, InterruptedException {
		Stat stat = zk.exists("/" + jobName, null);
		return (stat != null);
	}

	public List<String> getAllChildrenName() throws KeeperException, InterruptedException {
		return zk.getChildren("/", false);
	}

	public String getConfig(String jobName) throws KeeperException, InterruptedException, UnsupportedEncodingException {
		byte[] b = zk.getData("/" + jobName, false, null);
		return new String(b,"GBK");
	}

	public void addNewConfig(String jobName, String content) throws KeeperException, InterruptedException, UnsupportedEncodingException {
		zk.create("/" + jobName, content.getBytes("GBK"), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
	}

	public void modifyConfig(String jobName, String content) throws InterruptedException, KeeperException, UnsupportedEncodingException {
		zk.setData("/" + jobName, content.getBytes("GBK"), -1);
	}

	public void deleteConfig(String jobName) throws InterruptedException, KeeperException {
		zk.delete("/" + jobName, -1);
	}

	public static void main(String[] args) throws KeeperException, InterruptedException, IOException {
		ZooKeeper zk = new ZooKeeper("133.0.192.194:29182/com/ai/quartz/job", 10000, null);
		zk.create("/aaa", "aaaaa".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
	}
}
