package ftp.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.filechooser.FileSystemView;

import ftp.util.CommonUtils;
import ftp.view.components.FileViewTable;
import ftp.view.components.FileViewTable.FileViewTableModel;
import ftp.view.components.FileViewTable.RowInfo;
import ftp.view.components.QuickConnecDialog;
import ftp.view.event.ChangeDirEvent;
import ftp.view.listener.ChangeDirListener;

public class MainFrame extends JFrame implements ChangeDirListener {
	private Dimension screenDimension = Toolkit.getDefaultToolkit()
			.getScreenSize();

	// ---------------view------------------
	private JMenuItem jmiQuickConnect = new JMenuItem("快速连接");// 快速连接
	private JMenuItem jmiMinimize = new JMenuItem("最小化");// 最小化
	private JMenuItem jmiExit = new JMenuItem("退出");// 退出

	private JMenuItem jmiAbout = new JMenuItem("关于");// 关于

	private JPanel panel1 = new JPanel();// 左上panel
	private JPanel panel2 = new JPanel();// 右上panel
	private JPanel panel3 = new JPanel();// 左下
	private JPanel panel4 = new JPanel();// 右下

	private JLabel backLabelLocal = new JLabel();
	private JLabel backLabelRemote = new JLabel();
	
	private JLabel statusLabelLocal = new JLabel();
	private JLabel statusLabelRemote = new JLabel();
	
	JScrollPane scrollPaneLocal;

	// --------------- fields--------------
	private FileViewTable localTable;
	private FileViewTable remoteTable;

	private boolean seeHiddenFile = false;// 是否查看隐藏文件
	private String localParentPath = null;

	// --------------constants---------------
	private static final double DEFAULT_SCALE = 0.85;
	private static final String DEFAULT_TITLE = "FTPClient";
	private static final Icon ICON_BACK = new ImageIcon(
			System.getProperty("user.dir") + "\\res\\image\\Back.png");
	private static final Icon ICON_BACK_HOVER = new ImageIcon(
			System.getProperty("user.dir") + "\\res\\image\\Back_hover.png");
	private static final Icon ICON_FILE = FileSystemView.getFileSystemView()
			.getSystemIcon(
					new File("C:\\Users\\lixiaohui\\Desktop\\mergefile.sh"));
	private static final Color HOVER_COLOR = new Color(229, 243, 255);
	private static final Color STATUS_BG_COLOR = new Color(201, 236, 82);

	public MainFrame() {
		this(DEFAULT_TITLE);
	}

	public MainFrame(String title) {
		super(title);

		// 菜单栏
		JMenuBar mb = new JMenuBar();
		mb.setBackground(Color.white);
		initMenuBar(mb);
		this.setJMenuBar(mb);

		// 4个panel
		initPanel1();
		initPanel2();
		initPanel3();
		initPanle4();

		initEvent();

		// 将4个panel加到splitpane中
		JSplitPane topHorizontalSP = new JSplitPane();
		topHorizontalSP.setDividerSize(2);
		topHorizontalSP.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		topHorizontalSP.setLeftComponent(panel1);
		topHorizontalSP.setRightComponent(panel2);
		JSplitPane bottomHorizontalSP = new JSplitPane();
		bottomHorizontalSP.setDividerSize(2);
		bottomHorizontalSP.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		bottomHorizontalSP.setLeftComponent(panel3);
		bottomHorizontalSP.setRightComponent(panel4);

		JSplitPane verticalSP = new JSplitPane();
		verticalSP.setDividerSize(2);
		verticalSP.setOrientation(JSplitPane.VERTICAL_SPLIT);
		verticalSP.setTopComponent(topHorizontalSP);
		verticalSP.setBottomComponent(bottomHorizontalSP);
		// verticalSP.setDividerLocation();

		this.getContentPane().add(verticalSP);
		this.setSize((int) (screenDimension.getWidth() * DEFAULT_SCALE),
				(int) (screenDimension.getHeight() * DEFAULT_SCALE));
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		verticalSP.setDividerLocation(0.7);
		topHorizontalSP.setDividerLocation(0.5);
		bottomHorizontalSP.setDividerLocation(0.5);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	// 给菜单栏添加事件
	private void initEvent() {
		jmiQuickConnect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				quickConnect();
			}
		});

		jmiMinimize.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				minimize();
			}
		});

		jmiExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});

		jmiAbout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				about();
			}
		});
		backLabelLocal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				backLabelLocal.setIcon(ICON_BACK_HOVER);
				// System.out.println("hover");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				backLabelLocal.setIcon(ICON_BACK);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (localTable.getCurrentPath() == null) {
					localTable.setData(getLocalDirContent(null));
				} else {
					try {
						String path = new File(localTable.getCurrentPath())
								.getParentFile().getPath();// 父目录
						localTable.setData(getLocalDirContent(path));
						localTable.setCurrentPath(path);
					} catch (Exception e1) {
						localTable.setData(getLocalDirContent(null));
						localTable.setCurrentPath(null);
					}
				}
				statusLabelLocal.setText(getStatusLabelString());
			}
		});
		
		scrollPaneLocal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				localTable.clearSelection();
			}
		});
		
		localTable.getPopupMenu().getOpen().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = localTable.getSelectedRow();
				if(selectedRow == -1) return;
				FileViewTableModel m = (FileViewTableModel) localTable.getModel();
				RowInfo rowInfo = (RowInfo) m.getDatas()[selectedRow][0];
				localTable.setData(getLocalDirContent(rowInfo.getPath()));
				localTable.setCurrentPath(rowInfo.getPath());
			}
		});
		
		localTable.getPopupMenu().getDelete().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = localTable.getSelectedRow();
				if(selectedRow == -1) return;
				FileViewTableModel m = (FileViewTableModel) localTable.getModel();
				RowInfo rowInfo = (RowInfo) m.getDatas()[selectedRow][0];
				boolean success = CommonUtils.deleteF(rowInfo.getPath());//删除文件/文件夹
				if(success){
					//System.out.println("deleted");
					Object[][] newDatas = new Object[m.getDatas().length - 1][3];
					for(int i = 0, j = 0; i < m.getDatas().length; i++){
						if(i == selectedRow){
							continue;
						}
						newDatas[j][0] = m.getDatas()[i][0];
						newDatas[j][1] = m.getDatas()[i][1];
						newDatas[j][2] = m.getDatas()[i][2];
						j++;
					}
					localTable.setData(newDatas);
				}
			}
		});
		
		localTable.getPopupMenu().getRename().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = localTable.getSelectedRow();
				if(selectedRow == -1) return;
				FileViewTableModel m = (FileViewTableModel) localTable.getModel();
				RowInfo rowInfo = (RowInfo) m.getDatas()[selectedRow][0];
				String oldName = rowInfo.getText();
				String newName = (String) JOptionPane.showInputDialog(null,
				        "请输入新文件名: ", "重命名", JOptionPane.PLAIN_MESSAGE, null,
				        null, oldName);
				boolean success = CommonUtils.rename(rowInfo.getPath(), newName);
				if(success){
					rowInfo.setText(newName);
					localTable.updateUI();
				}
			}
		});
	}
	
	/*private void intoPathLocal(String path){
		localTable.setData(getLocalDirContent(path));
	}*/
	
	private void deleteRow(){
		
	}
	
	private void initMenuBar(JMenuBar mb) {
		JMenu session = new JMenu("会话(E)");
		session.setBackground(Color.white);
		JMenu help = new JMenu("帮助(H)");
		help.setBackground(Color.white);
		mb.add(session);
		mb.add(help);

		session.setMnemonic(KeyEvent.VK_E);
		session.add(jmiQuickConnect);
		session.add(jmiMinimize);
		session.add(jmiExit);

		help.setMnemonic(KeyEvent.VK_H);
		help.add(jmiAbout);
	}

	// 初始化panel1
	private void initPanle4() {
		panel4.setBackground(Color.white);
	}

	// 初始化panel2
	private void initPanel3() {
		panel3.setBackground(Color.white);
	}

	// 初始化panel3
	private void initPanel2() {
		panel2.setBackground(Color.white);
	}

	// 初始化panel4
	private void initPanel1() {
		panel1.setBackground(Color.white);
		BorderLayout bl = new BorderLayout();
		bl.setHgap(0);
		bl.setVgap(0);
		panel1.setLayout(bl);
		// 工具面板
		JPanel toolPanel = new JPanel();
		toolPanel.setBackground(Color.white);
		FlowLayout lm = new FlowLayout(FlowLayout.LEFT);
		lm.setHgap(5);
		lm.setVgap(5);
		toolPanel.setLayout(lm);
		backLabelLocal.setIcon(ICON_BACK);
		toolPanel.add(backLabelLocal);
		// 表格面板
		GridLayout g = new GridLayout(1, 1);
		JPanel centerPanel = new JPanel(g);
		centerPanel.setBackground(Color.white);
		RowInfo in = new RowInfo(ICON_FILE, "AAA");
		FileViewTableModel m = new FileViewTableModel(new String[] { "名称",
				"大小", "修改时间" }, getLocalDirContent(null));
		localTable = new FileViewTable(m);
		localTable.setChangeDirListener(this);
		scrollPaneLocal = new JScrollPane(localTable);
		scrollPaneLocal.getViewport().setBackground(Color.white);
		centerPanel.add(scrollPaneLocal);
		
		//状态面板
		JPanel statusPanel = new JPanel();
		statusPanel.setBackground(STATUS_BG_COLOR);
		statusPanel.setLayout(new GridLayout(2, 1, 5, 5));
		statusLabelLocal.setText(getStatusLabelString());
		statusLabelLocal.setHorizontalAlignment(JLabel.CENTER);
		statusPanel.add(statusLabelLocal);
		statusPanel.add(new JLabel("本地浏览器",JLabel.CENTER));
		//System.out.println(statusLabelLocal.getText());

		panel1.add(toolPanel, BorderLayout.NORTH);
		panel1.add(centerPanel, BorderLayout.CENTER);
		panel1.add(statusPanel, BorderLayout.SOUTH);
		// panel1.add(new JPanel(), BorderLayout.WEST);
		// panel1.add(new JPanel(), BorderLayout.EAST);
	}

	// --------------------action methods--------
	private void exit() {
		System.exit(0);
	}

	private void quickConnect() {
		QuickConnecDialog dialog = new QuickConnecDialog();
	}

	private void minimize() {

	}

	private void about() {

	}

	// ------------ utility methods --------------
	/*
	 * public static Object[][] getDefaultLocalDirContent() { File[] roots =
	 * File.listRoots(); Object[][] datas = new Object[roots.length][3]; File
	 * tmpFile; for (int i = 0; i < roots.length; i++) { tmpFile = roots[i];
	 * RowInfo in = new RowInfo(); in.setIcon(FileSystemView.getFileSystemView()
	 * .getSystemIcon(tmpFile)); in.setText(tmpFile.getPath()); datas[i][0] =
	 * in; datas[i][1] = tmpFile.isFile() ? tmpFile.getTotalSpace() : "";
	 * datas[i][2] = timeConvertLong2String(tmpFile.lastModified()); } return
	 * datas; }
	 */

	public Object[][] getLocalDirContent(String path) {
		File[] roots;
		if (path == null) {// 获取根目录
			roots = File.listRoots();
		} else {
			System.out.println(path);
			roots = new File(path).listFiles();
		}
		// 去掉隐藏文件
		if (seeHiddenFile) {
			int len = 0;
			for (int i = 0; i < roots.length; i++) {
				if (!roots[i].isHidden()) {
					roots[i] = null;
				} else {
					len++;
				}
			}
			File[] newRoots = new File[len];
			for (int i = 0, j = 0; i < roots.length; i++) {
				if (roots[i] != null) {
					newRoots[j] = roots[i];
					j++;
				}
			}
			roots = newRoots;
		}

		Object[][] datas = new Object[roots.length][3];
		File tmpFile;
		for (int i = 0; i < roots.length; i++) {
			tmpFile = roots[i];
			RowInfo in = new RowInfo();
			in.setIcon(FileSystemView.getFileSystemView()
					.getSystemIcon(tmpFile));
			in.setText(path == null ? tmpFile.getPath() : tmpFile.getName());
			in.setPath(tmpFile.getAbsolutePath());
			datas[i][0] = in;
			datas[i][1] = tmpFile.isFile() ? tmpFile.length() : "";
			datas[i][2] = CommonUtils.timeConvertLong2String(tmpFile
					.lastModified());
		}
		return datas;
	}
	
	private String getStatusLabelString(){
		return "当前路径: " + (localTable.getCurrentPath() == null ? "根目录" : localTable.getCurrentPath()) + ", 共" + localTable.getModel().getRowCount() + "个文件";
	}

	// ----------- implemented methods-----------

	@Override
	public void changeDir(ChangeDirEvent e) {
		FileViewTable table = (FileViewTable) e.getSource();
		table.setData(getLocalDirContent(e.getTargetPath()));
		statusLabelLocal.setText(getStatusLabelString());
	}

}
