package view.components;

import java.io.File;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileSystemView;

import ftp.view.components.FileViewTable;
import ftp.view.components.FileViewTable.FileViewTableModel;
import ftp.view.components.FileViewTable.RowInfo;

public class TestFileViewTable extends JFrame{
	private Icon icon = FileSystemView.getFileSystemView().getSystemIcon(
			new File("C:\\Users\\lixiaohui\\Desktop\\mergefile.sh"));
	
	public TestFileViewTable() {
		String[] titles = new String[]{"名称", "大小", "修改时间"};
		RowInfo in = new RowInfo(icon, "AAA");
		Object[][] datas = new Object[][]{{in, "32", "dasda"},{in, "32", "dasda"}};
		FileViewTableModel m = new FileViewTableModel(titles, datas);
		this.getContentPane().add(new JScrollPane(new FileViewTable(m)));
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) throws Exception {
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		new TestFileViewTable();
	}

}
