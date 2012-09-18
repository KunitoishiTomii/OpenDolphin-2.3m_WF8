package open.dolphin.impl.tempkarte;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import open.dolphin.client.ChartEventListener;
import open.dolphin.client.ClientContext;
import open.dolphin.client.Dolphin;
import open.dolphin.client.IChartEventListener;
import open.dolphin.delegater.MasudaDelegater;
import open.dolphin.helper.ComponentMemory;
import open.dolphin.infomodel.ChartEventModel;
import open.dolphin.infomodel.PatientModel;
import open.dolphin.infomodel.PatientVisitModel;
import open.dolphin.infomodel.UserModel;
import open.dolphin.project.Project;
import open.dolphin.table.ColumnSpecHelper;
import open.dolphin.table.ListTableModel;
import open.dolphin.table.ListTableSorter;
import open.dolphin.table.StripeTableCellRenderer;

/**
 * 現時点で過去日で仮保存のままのカルテを警告する
 * @author masuda, Masuda Naika
 */
public class TempKarteChecker extends JFrame implements IChartEventListener {
    
    private JPanel panel;
    private JTable table;
    private ListTableModel<PatientModel> tableModel;
    private ListTableSorter sorter;
    
    private JButton closeBtn;
    private JButton searchBtn;  
    
    private static final String[] COLUMN_NAMES 
            = {"ID", "氏名", "カナ", "性別", "生年月日", "受診日", "状態"};
    private final String[] PROPERTY_NAMES 
            = {"patientId", "fullName", "kanaName", "genderDesc", "ageBirthday", "pvtDateTrimTime", "isOpened"};
    private static final Class[] COLUMN_CLASSES = {
        String.class, String.class, String.class, String.class, String.class, 
        String.class, String.class};
    private final int[] COLUMN_WIDTH = {50, 100, 120, 30, 100, 80, 20};
    private final int START_NUM_ROWS = 1;
    
    // カラム仕様名
    private static final String COLUMN_SPEC_NAME = "tempKarteChecker.column.spec";
    // カラム仕様ヘルパー
    private ColumnSpecHelper columnHelper;
    
    private int stateColumn;
    
    // 選択されている行を保存
    private int selectedRow;
    
    private String clientUUID;
    
    private ChartEventListener cel;
    
    
    private static final TempKarteChecker instance;
    
    static {
        instance = new TempKarteChecker();
    }
    
    private TempKarteChecker() {
        setup();
        initComponents();
        connect();
    }
    
    public static TempKarteChecker getInstance() {
        return instance;
    }
    
    private void setup() {
        
        cel = ChartEventListener.getInstance();
        clientUUID = cel.getClientUUID();
        
        // ColumnSpecHelperを準備する
        columnHelper = new ColumnSpecHelper(COLUMN_SPEC_NAME,
                COLUMN_NAMES, PROPERTY_NAMES, COLUMN_CLASSES, COLUMN_WIDTH);
        columnHelper.loadProperty();
        // Scan して state カラムを設定する
        stateColumn = columnHelper.getColumnPosition("isOpened");
    }
    
    private void initComponents() {
        
        ClientContext.setDolphinIcon(this);
        String title = ClientContext.getFrameTitle("仮保存カルテチェック");
        setTitle(title);
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        setContentPane(panel);

        table = new JTable();

        // ColumnSpecHelperにテーブルを設定する
        columnHelper.setTable(table);
        //------------------------------------------
        // View のテーブルモデルを置き換える
        //------------------------------------------
        String[] columnNames = columnHelper.getTableModelColumnNames();
        String[] methods = columnHelper.getTableModelColumnMethods();
        Class[] cls = columnHelper.getTableModelColumnClasses();
        
        tableModel = new ListTableModel<PatientModel>(columnNames, 1, methods, cls) {

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
        // sorter組み込み
        sorter = new ListTableSorter(tableModel);
        table.setModel(sorter);
        sorter.setTableHeader(table.getTableHeader());
        
        // 選択モード
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // 連ドラ、梅ちゃん先生
        PatientListTableRenderer renderer = new PatientListTableRenderer();
        renderer.setTable(table);
        renderer.setDefaultRenderer();

        // カラム幅更新
        columnHelper.updateColumnWidth();
        
        searchBtn = new JButton("検索");
        closeBtn = new JButton("閉じる");
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        btnPanel.add(searchBtn);
        btnPanel.add(closeBtn);
        
        panel.add(table, BorderLayout.CENTER);
        panel.add(btnPanel, BorderLayout.SOUTH);
        
        pack();
        ComponentMemory cm = new ComponentMemory(this, new Point(100, 100), this.getPreferredSize(), TempKarteChecker.this);
        cm.setToPreferenceBounds();
    }
    
    private void connect() {
        
        // 来院リストテーブル 選択
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() == false) {
                    selectedRow = table.getSelectedRow();
                }
            }
        });
        
        // 来院リストテーブル ダブルクリック
        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    openKarte();
                }
            }
        });

        closeBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        searchBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                renewList();
            }
        });
    }
    
    private void renewList() {
        
        SwingWorker worker = new SwingWorker<List<PatientModel>, Void>(){

            @Override
            protected List<PatientModel> doInBackground() throws Exception {
                Date d = new Date();
                UserModel user = Project.getUserModel();
                long userPk = user.getId();
                MasudaDelegater del = MasudaDelegater.getInstance();
                List<PatientModel> list = del.getTempDocumentPatients(d, userPk);
                return list;
            }

            @Override
            protected void done() {
                try {
                    List<PatientModel> list = get();
                    tableModel.setDataProvider(list);
                } catch (InterruptedException ex) {
                } catch (ExecutionException ex) {
                }
            }
        };
        worker.execute();
    }
    
    @Override
    public void setVisible(boolean b) {
        if (b == isVisible()) {
            return;
        }
        if (b) {
            cel.addListener(instance);
            renewList();
        } else {
            cel.removeListener(instance);
        }
        super.setVisible(b);
    }
    
    public boolean isTempKarteExists() {
        return tableModel.getObjectCount() > 0;
    }
    
    private void openKarte() {
        
        PatientModel patient = getSelectedPatient();
        PatientVisitModel pvt = ChartEventListener.getInstance().createFakePvt(patient);
        
        // カルテコンテナを生成する
        Dolphin.getInstance().openKarte(pvt);
    }
    
    private PatientModel getSelectedPatient() {
        selectedRow = table.getSelectedRow();
        return (PatientModel) sorter.getObject(selectedRow);
    }

    @Override
    public void onEvent(ChartEventModel evt) {
        
        int sRow = -1;
        long ptPk = evt.getPtPk();
        List<PatientModel> list = tableModel.getDataProvider();
        ChartEventModel.EVENT eventType = evt.getEventType();
        
        switch (eventType) {
            case PVT_STATE:
                for (int row = 0; row < list.size(); ++row) {
                    PatientModel pm = list.get(row);
                    if (ptPk == pm.getId()) {
                        sRow = row;
                        pm.setOwnerUUID(evt.getOwnerUUID());
                        break;
                    }
                }
                break;
            case PM_MERGE:
                for (int row = 0; row < list.size(); ++row) {
                    PatientModel pm = list.get(row);
                    if (ptPk == pm.getId()) {
                        sRow = row;
                        //pm = msg.getPatientModel();
                        list.set(row, evt.getPatientModel());
                        break;
                    }
                }
                break;            
            case PVT_MERGE:
                for (int row = 0; row < list.size(); ++row) {
                    PatientModel pm = list.get(row);
                    if (ptPk == pm.getId()) {
                        sRow = row;
                        //pm = msg.getPatientVisitModel().getPatientModel();
                        list.set(row, evt.getPatientVisitModel().getPatientModel());
                        break;
                    }
                }
                break;
            default:
                break;
        }
        
        if (sRow != -1) {
            tableModel.fireTableRowsUpdated(sRow, sRow);
        }
    }
    
    private class PatientListTableRenderer extends StripeTableCellRenderer {

        public PatientListTableRenderer() {
            super();
        }

        @Override
        public Component getTableCellRendererComponent(JTable table,
                Object value,
                boolean isSelected,
                boolean isFocused,
                int row, int col) {

            super.getTableCellRendererComponent(table, value, isSelected, isFocused, row, col);
            this.setHorizontalAlignment(JLabel.LEFT);
            PatientModel pm = (PatientModel) sorter.getObject(row);
            
            if (pm != null && col == stateColumn) {
                setHorizontalAlignment(JLabel.CENTER);
                if (pm.isOpened()) {
                    if (clientUUID.equals(pm.getOwnerUUID())) {
                        setIcon(OPEN_ICON);
                    } else {
                        setIcon(NETWORK_ICON);
                    }
                } else {
                    setIcon(null);
                }
                setText("");
            } else {
                setIcon(null);
                setText(value == null ? "" : value.toString());
            }

            return this;
        }
    }
}
