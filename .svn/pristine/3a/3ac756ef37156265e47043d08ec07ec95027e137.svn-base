package ftp.view.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.EventObject;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.MouseInputListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import ftp.view.event.ChangeDirEvent;
import ftp.view.listener.ChangeDirListener;

public class FileViewTable extends JTable {

	private ChangeDirListener changeDirListener;

	private String parentPath = null;

	private String currentPath = null;

	private FileViewTablePopupMenu popupMenu = new FileViewTablePopupMenu();// 右键菜单

	public FileViewTable(TableModel dm) {
		super(dm);
		this.setBackground(Color.white);
		this.setIntercellSpacing(new Dimension(0, 0));
		this.setShowHorizontalLines(false);
		this.getTableHeader().setBackground(Color.white);
		this.getTableHeader().setBorder(
				BorderFactory.createLineBorder(Color.white, 1));
		this.getColumnModel().getColumn(0)
				.setCellRenderer(new FileViewTableRenderer());
		// ((DefaultTableCellRenderer)(this.getColumnModel().getColumn(1).getCellRenderer())).setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
		initEvent();
	}

	private void initEvent() {
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = e.getY() / FileViewTable.this.getRowHeight();//点击的行
				if (e.getButton() == MouseEvent.BUTTON1) {//左键点击
					//双击
					if (e.getClickCount() == 2) {
						int rowIndex = FileViewTable.this.getSelectedRow();
						RowInfo rowInfo = (RowInfo) FileViewTable.this
								.getModel().getValueAt(rowIndex, 0);
						if (!new File(rowInfo.getPath()).isDirectory()) {// 不是目录
							return;
						}
						String oldPath = currentPath;
						try {
							currentPath = rowInfo.getPath();
							// 改变目录的逻辑由外部完成，因为内部无法知道是本地还是远程
							changeDirListener.changeDir(new ChangeDirEvent(
									FileViewTable.this, rowInfo.getPath()));
							// parentPath = currentPath;
						} catch (Exception e1) {
							currentPath = oldPath;
						}
					}
				} else if(e.getButton() == MouseEvent.BUTTON3){//右键点击
					popupMenu.show(e.getComponent(), e.getX(), e.getY());
					FileViewTable.this.setRowSelectionInterval(row, row);
				}
			}

		});

	}

	// 显示新数据
	public void setData(Object[][] datas) {
		FileViewTableModel model = (FileViewTableModel) getModel();
		model.setDatas(datas);
		updateUI();
	}
	
	//----------

	// ----------inner classes---------------
	public static class FileViewTableModel extends AbstractTableModel {

		private String[] columnNames;

		private Object[][] datas;

		public FileViewTableModel() {
			columnNames = new String[] { "名称", "大小", "修改时间" };
			datas = new Object[][] {};
		}

		public FileViewTableModel(String[] columnNames, Object[][] datas) {
			super();
			this.columnNames = columnNames;
			this.datas = datas;
		}

		@Override
		public int getRowCount() {
			return datas.length;
		}

		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public String getColumnName(int column) {
			// TODO Auto-generated method stub
			return columnNames[column];
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			return datas[rowIndex][columnIndex];
		}

		public Object[][] getDatas() {
			return datas;
		}

		public void setDatas(Object[][] datas) {
			this.datas = datas;
		}
	}

	// 表格渲染器
	public class FileViewTableRenderer implements TableCellRenderer {

		private final Color DEFAULT_BGCOLOR = new Color(51, 153, 255);

		private JPanel panel = new JPanel();

		private JLabel label = new JLabel();

		private JLabel iconLabel = new JLabel();

		public FileViewTableRenderer() {
			FlowLayout l = new FlowLayout();
			l.setAlignment(FlowLayout.LEFT);
			l.setHgap(0);
			l.setVgap(0);
			panel.setLayout(l);
		}

		@Override
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			RowInfo in = (RowInfo) value;
			iconLabel.setIcon(in.getIcon());// 设置图标
			panel.add(iconLabel);

			label.setText(in.getText());// 设置文件名
			panel.add(label);
			// bgcolor
			if (isSelected) {
				panel.setBackground(DEFAULT_BGCOLOR);
			} else {
				panel.setBackground(Color.white);
			}
			return panel;
		}

	}

	
	public static class RowInfo {
		private Icon icon;
		private String text;
		private String path;

		public Icon getIcon() {
			return icon;
		}

		public void setIcon(Icon icon) {
			this.icon = icon;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public RowInfo(Icon icon, String text) {
			super();
			this.icon = icon;
			this.text = text;
		}

		public RowInfo() {
			super();
		}
	}

	// ---------------setter getter-------------

	public ChangeDirListener getChangeDirListener() {
		return changeDirListener;
	}

	public void setChangeDirListener(ChangeDirListener changeDirListener) {
		this.changeDirListener = changeDirListener;
	}

	public String getParentPath() {
		return parentPath;
	}

	public void setParentPath(String parentPath) {
		this.parentPath = parentPath;
	}

	public String getCurrentPath() {
		return currentPath;
	}

	public void setCurrentPath(String currentPath) {
		this.currentPath = currentPath;
	}

	public FileViewTablePopupMenu getPopupMenu() {
		return popupMenu;
	}

	public void setPopupMenu(FileViewTablePopupMenu popupMenu) {
		this.popupMenu = popupMenu;
	}
}
