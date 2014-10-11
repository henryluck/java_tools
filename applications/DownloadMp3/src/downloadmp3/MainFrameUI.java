/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadmp3;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author oneleaf
 */
public class MainFrameUI {

    public static void setTaskTable(JTable jTable) {
        jTable.setModel(new TaskTableModel());
        jTable.getColumnModel().getColumn(0).setPreferredWidth(10);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(10);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(80);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(200);
    }

    public static void setDowningTable(JTable jTable) {
        jTable.setModel(new DowningTableModel());
        jTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(50);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(50);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(50);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(50);
        jTable.getColumnModel().getColumn(5).setPreferredWidth(150);
    }
}

class TaskTableModel extends AbstractTableModel {

    public int getRowCount() {
        return DownTask.getInstance().getTaskList().size();
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        if (columnIndex == 0) {
            return Boolean.class;
        } else if (columnIndex == 1) {
            return Integer.class;
        } else if (columnIndex == 2) {
            return String.class;
        } else if (columnIndex == 3) {
            return String.class;
        } else if (columnIndex == 4) {
            return String.class;
        }
        return Object.class;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return (column == 0);
    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {
        if (column == 0) {
            boolean value = ((Boolean) aValue).booleanValue();
            DownTask.getInstance().getTaskList().get(row).isEnabled = value;
        }
    }

    @Override
    public String getColumnName(int column) {
        if (column == 0) {
            return "下载";
        } else if (column == 1) {
            return "序号";
        } else if (column == 2) {
            return "歌曲名";
        } else if (column == 3) {
            return "演唱/专辑";
        } else if (column == 4) {
            return "链接";
        }
        return "";
    }

    public int getColumnCount() {
        return 5;
    }

    public Object getValueAt(int row, int column) {
        if (row >= DownTask.getInstance().getTaskList().size()) {
            return null;
        }
        if (column == 0) {
            return DownTask.getInstance().getTaskList().get(row).isEnabled;
        } else if (column == 1) {
            return DownTask.getInstance().getTaskList().get(row).no;
        } else if (column == 2) {
            return DownTask.getInstance().getTaskList().get(row).title;
        } else if (column == 3) {
            return DownTask.getInstance().getTaskList().get(row).artist;
        } else if (column == 4) {
            return DownTask.getInstance().getTaskList().get(row).url;
        }
        return "";
    }
}

class DowningTableModel extends AbstractTableModel {

    public int getRowCount() {
        return DownTask.getInstance().getDownloadList().size();
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        if (columnIndex == 0) {
            return String.class;
        } else if (columnIndex == 1) {
            return String.class;
        } else if (columnIndex == 2) {
            return String.class;
        } else if (columnIndex == 3) {
            return Integer.class;
        } else if (columnIndex == 4) {
            return Integer.class;
        } else if (columnIndex == 5) {
            return String.class;
        }
        return Object.class;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    @Override
    public String getColumnName(int column) {
        if (column == 0) {
            return "歌曲名";
        } else if (column == 1) {
            return "演唱";
        } else if (column == 2) {
            return "专辑";
        } else if (column == 3) {
            return "长度";
        } else if (column == 4) {
            return "已下载";
        } else if (column == 5) {
            return "地址";
        }
        return "";
    }

    public int getColumnCount() {
        return 6;
    }

    public Object getValueAt(int row, int column) {
        if (row >= DownTask.getInstance().getDownloadList().size()) {
            return null;
        }
        if (column == 0) {
            return DownTask.getInstance().getDownloadList().get(row).title;
        } else if (column == 1) {
            return DownTask.getInstance().getDownloadList().get(row).artist;
        } else if (column == 2) {
            return DownTask.getInstance().getDownloadList().get(row).album;
        } else if (column == 3) {
            return DownTask.getInstance().getDownloadList().get(row).length;
        } else if (column == 4) {
            return DownTask.getInstance().getDownloadList().get(row).pos;
        } else if (column == 5) {
            return DownTask.getInstance().getDownloadList().get(row).currDownUrl;
        }
        return "";
    }
}
