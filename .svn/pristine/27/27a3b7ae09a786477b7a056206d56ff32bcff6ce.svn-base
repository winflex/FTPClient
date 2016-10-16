package ftp.view.components;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class FileViewTablePopupMenu extends JPopupMenu {

	private JMenuItem transfer = new JMenuItem("传输");
	
	private JMenuItem advancedTransfer = new JMenuItem("高级传输");
	
	private JMenuItem queue = new JMenuItem("加入传输队列");
	
	private JMenuItem open = new JMenuItem("打开");
	
	private JMenuItem delete = new JMenuItem("删除");
	
	private JMenuItem rename = new JMenuItem("重命名");
	
	private JMenuItem fresh = new JMenuItem("刷新");
	
	private JMenuItem sortByName = new JMenuItem("名称");
	
	private JMenuItem sortByDate = new JMenuItem("日期");
	
	private JMenuItem newFile = new JMenuItem("新建文件");
	
	private JMenuItem newDirectory = new JMenuItem("新建文件夹");
	
	public FileViewTablePopupMenu() {
		
		this.add(transfer);
		this.add(advancedTransfer);
		this.add(queue);
		this.add(open);
		this.add(delete);
		this.add(rename);
		
		JMenu sort = new JMenu("排序按");
		sort.add(sortByName);
		sort.add(sortByDate);
		this.add(sort);
		this.add(advancedTransfer);
		
		this.add(fresh);
	}

	public JMenuItem getTransfer() {
		return transfer;
	}

	public void setTransfer(JMenuItem transfer) {
		this.transfer = transfer;
	}

	public JMenuItem getAdvancedTransfer() {
		return advancedTransfer;
	}

	public void setAdvancedTransfer(JMenuItem advancedTransfer) {
		this.advancedTransfer = advancedTransfer;
	}

	public JMenuItem getQueue() {
		return queue;
	}

	public void setQueue(JMenuItem queue) {
		this.queue = queue;
	}

	public JMenuItem getOpen() {
		return open;
	}

	public void setOpen(JMenuItem open) {
		this.open = open;
	}

	public JMenuItem getDelete() {
		return delete;
	}

	public void setDelete(JMenuItem delete) {
		this.delete = delete;
	}

	public JMenuItem getRename() {
		return rename;
	}

	public void setRename(JMenuItem rename) {
		this.rename = rename;
	}

	public JMenuItem getFresh() {
		return fresh;
	}

	public void setFresh(JMenuItem fresh) {
		this.fresh = fresh;
	}

	public JMenuItem getSortByName() {
		return sortByName;
	}

	public void setSortByName(JMenuItem sortByName) {
		this.sortByName = sortByName;
	}

	public JMenuItem getSortByDate() {
		return sortByDate;
	}

	public void setSortByDate(JMenuItem sortByDate) {
		this.sortByDate = sortByDate;
	}

	public JMenuItem getNewFile() {
		return newFile;
	}

	public void setNewFile(JMenuItem newFile) {
		this.newFile = newFile;
	}

	public JMenuItem getNewDirectory() {
		return newDirectory;
	}

	public void setNewDirectory(JMenuItem newDirectory) {
		this.newDirectory = newDirectory;
	}

}
