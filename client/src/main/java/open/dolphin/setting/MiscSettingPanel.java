package open.dolphin.setting;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import open.dolphin.client.AutoRomanListener;
import open.dolphin.client.ClientContext;
import open.dolphin.client.FontManager;
import open.dolphin.client.GUIFactory;
import open.dolphin.client.RegexConstrainedDocument;
import open.dolphin.delegater.MasudaDelegater;
import open.dolphin.helper.GridBagBuilder;
import open.dolphin.infomodel.UserPropertyModel;
import open.dolphin.project.Project;

/**
 * 増田内科 追加機能の設定パネル
 *
 * Masuda Naika Clinic, Wakayama City
 * @author masuda, Masuda Naika
 */
public class MiscSettingPanel extends AbstractSettingPanel {
    
    private static final Color DEFAULT_EVEN_COLOR = ClientContext.getColor("color.even");

    private static final String ID = "miscSetting";
    private static final String TITLE = "その他";
    private static final String ICON = "icon_misc_settings_small";

    // preference名
    public static final String LBLPRT_ADDRESS = "lblPrtAddress";
    public static final String LBLPRT_PORT = "lblPrtPort";
    public static final String FEV_SHAREPATH = "fevSharePath";
    public static final String USE_FEV = "useFev";
    public static final String FEV40_PATH = "fev40Path";
    public static final String USE_WINE = "useWine";
    public static final String WINE_PATH = "winePath";
    public static final String SEND_PATIENT_INFO = "sendPatientInfo";
    //public static final String PVT_ON_SERVER = "pvtOnServer";
    public static final String FEV_ON_SERVER = "fevOnServer";

    public static final String FOLLOW_MEDICOM = "followMedicom";
    public static final String SANTEI_CHECK = "santeiCheck";

    public static final String STAMP_HOLDER_CELLPADDING = "stampHolderCellPadding";
    public static final String USE_RSB = "useRSB";
    public static final String RSB_URL = "rsbURL";
    public static final String RSB_DRS_PATH = "rsbDrsPath";
    public static final String RSB_LINK_PATH = "rsbLinkPath";
    public static final String RSB_RSN_PATH = "rsbRsnPath";
    public static final String RSB_BROWSER_PATH = "rsbBrowserPath";
    public static final String USE_PACS = "usePacs";
    public static final String PACS_REMOTE_HOST = "pacsServerIp";
    public static final String PACS_REMOTE_PORT = "pacsServerPort";
    public static final String PACS_REMOTE_AE = "pacsServerAE";
    public static final String PACS_LOCAL_HOST = "pacsClientIp";
    public static final String PACS_LOCAL_PORT = "pacsClientPort";
    public static final String PACS_LOCAL_AE = "pacsClientAE";
    public static final String PACS_USE_SUFFIXSEARCH = "pacsUseSuffixSearch";
    public static final String PACS_WEASIS_ADDRESS = "weasisAddress";
    public static final String PACS_OSIRIX_ADDRESS = "osirixAddress";
    
    //public static final String USE_JMS = "useJms";
    public static final String RP_OUT = "rp.out";
    public static final String KARTE_SCROLL_TYPE = "karteScrollType";
    public static final String USE_VERTICAL_LAYOUT = "verticalLayout";

    public static final String PREFER_WAREKI = "preferWareki";
    public static final String ENABLE_DENSHI = "enableDenshi";
    public static final String USE_HIBERNATE_SEARCH = "useHibernateSearch";
    public static final String PACS_SHOW_IMAGEINFO = "pacsShowImageInfo";
    public static final String PACS_VIEWER_GAMMA = "pacsViewerGamma";
    
    public static final String ZEBRA_COLOR = "zebraColor";
    public static final String HL7_FORMAT = "hl7format";
    public static final String USE_JERSEY = "useJersey";
    public static final String USE_WEBSOCKET = "useWebsocket";
    
    public static final String UI_FONT_SIZE = "uiFontSize";
    public static final String UI_FONT_NAME = "uiFontName";
    public static final String UI_FONT_STYLE = "uiFontStyle";
    public static final String STAMP_FONT_SIZE = "stampFontSize";
    public static final String STAMP_FONT_NAME = "stampFontName";
    public static final String STAMP_FONT_STYLE = "stampFontStyle";
    
    public static final String ORCA_MED_USE_API = "orcaMedUseApi";

    // preferencesのdefault
    public static final String DEFAULT_LBLPRT_ADDRESS = null;
    public static final int DEFAULT_LBLPRT_PORT = 9100;
    public static final boolean DEFAULT_USEFEV = false;
    public static final String DEFAULT_FEV40_PATH = "C:\\Program Files\\Fukuda Denshi\\ECG Viewer FEV-40\\FEV-40.EXE";
    public static final String DEFAULT_WINE_PATH = "/opt/local/bin/wine";
    public static final boolean DEFAULT_USE_WINE = false;
    public static final boolean DEFAULT_SENDPATIENTINFO = false;
    public static final String DEFAULT_SHAREPATH = null;
    public static final boolean DEFAULT_FOLLOW_MEDICOM = true;
    public static final boolean DEFAULT_SANTEI_CHECK = true;
    public static final boolean DEFAULT_EX_MED = false;
    
    public static final boolean DEFAULT_PVT_ON_SERVER = false;
    public static final boolean DEFAULT_FEV_ON_SERVER = false;

   // public static final int DEFAULT_STAMP_HOLDER_CELLPADDING = 3;

    public static final boolean DEFAULT_USE_RSB = false;
    public static final String DEFAULT_RSB_URL = "http://localhost/~rsn";
    public static final String DEFAULT_RSB_DRS_PATH = "C:\\DRS\\";
    public static final String DEFAULT_RSB_LINK_PATH = "C:\\RSB_LINK\\";
    public static final String DEFAULT_RSB_RSN_PATH = "C:\\User\\rsn\\public_html\\";
    public static final String DEFAULT_RSB_BROWSER_PATH = "";

    public static final boolean DEFAULT_USE_PACS = false;
    public static final boolean DEFAULT_PACS_SUFFIX_SEARCH = false;
    public static final String DEFAULT_PACS_REMOTE_HOST = "localhost";
    public static final String DEFAULT_PACS_REMOTE_AE = "PACSSERVER";
    public static final int DEFAULT_PACS_REMOTE_PORT = 104;
    public static final String DEFAULT_PACS_LOCAL_HOST = "localhost";
    public static final String DEFAULT_PACS_LOCAL_AE = "Dolphin1";
    public static final int DEFAULT_PACS_LOCAL_PORT = 8104;
    public static final String DEFAULT_PACS_WEASIS_ADDRESS = ""; // "http://localhost:8080"
    public static final String DEFAULT_PACS_OSIRIX_ADDRESS = "";
    public static final boolean DEFAULT_USE_JMS = false;

    public static final int DEFAULT_KARTE_SCROLL = 0;
    public static final int SKIP_KARTE_SCROLL = 1;
    public static final int PAGE_KARTE_SCROLL = 2;
    public static final boolean DEFAULT_USE_VERTICAL_LAYOUT = false;
    public static final boolean DEFAULT_PREFER_WAREKI = false;
    public static final boolean DEFAULT_HIBERNATE_SEARCH = false;
    public static final double DEFAULT_PACS_GAMMA = 1;
    public static final boolean DEFAULT_PACS_SHOW_IMAGEINFO = true;
    
    public static final String DEFAULT_HL7_FORMAT = "wakayama";
    public static final boolean DEFAULT_USE_JERSEY = true;
    public static final boolean DEFAULT_USE_WEBSOCKET = false;
    
    public static final int DEFAULT_UI_FONT_SIZE = 13;
    public static final String DEFAULT_UI_FONT_NAME = Font.SANS_SERIF;
    public static final int DEFAULT_UI_FONT_STYLE = Font.PLAIN;
    public static final int DEFAULT_STAMP_FONT_SIZE = 12;
    public static final String DEFAULT_STAMP_FONT_NAME = Font.SANS_SERIF;
    public static final int DEFAULT_STAMP_FONT_STYLE = Font.PLAIN;
    
    public static final boolean DEFAULT_ORCA_MED_USE_API = false;

    // GUI staff
    private JTextField tf_lblPrtAddress;
    private JTextField tf_lblPrtPort;
    private JCheckBox cb_UseFev;
    private JCheckBox cb_UseWine;
    private JCheckBox cb_SendPatientInfo;
    private JCheckBox cb_Yakujo;
    private JCheckBox cb_Santei;
    private JButton btn_openFEV;
    private JButton btn_openWine;
    private JTextField tf_fevSharePath;
    private JTextField tf_fev40Path;
    private JTextField tf_winePath;
    private JLabel lbl_useWine;
    private JLabel lbl_winePath;
    private JLabel lbl_fev40Path;
    private JRadioButton rb_inMed;
    private JRadioButton rb_exMed;
    //private JComboBox cmb_cellPadding;
    //private JCheckBox cb_useJms;
    private JCheckBox cb_verticalLayout;
    private JComboBox cmb_karteScroll;

    private JLabel lbl_fev70;
    private JLabel lbl_fevShareFolder;
    
    //private JCheckBox cb_PvtOnServer;
    private JCheckBox cb_FevOnServer;
    
    private JButton btn_discardSize;
    private JButton btn_openBase;
    private JButton btn_saveProp;
    private JButton btn_loadProp;
    
/*    
    private JLabel lbl_rsbURL;
    private JLabel lbl_rsbDrsPath;
    private JLabel lbl_rsbLinkPath;
    private JLabel lbl_rsbRsnPath;
    private JLabel lbl_rsbBrowser;

    private JCheckBox cb_useRsb;
    private JButton btn_openRSB;
    private JTextField tf_rsbBrowserPath;
    private JTextField tf_rsbURL;
    private JTextField tf_rsbRsnPath;
    private JTextField tf_rsbDrsPath;
    private JTextField tf_rsbLinkPath;
*/
    private JCheckBox cb_UsePacs;
    private JTextField tf_pacsRemoteHost;
    private JTextField tf_pacsRemotePort;
    private JTextField tf_pacsRemoteAE;
    private JTextField tf_pacsLocalHost;
    private JTextField tf_pacsLocalPort;
    private JTextField tf_pacsLocalAE;
    private JCheckBox cb_SuffixSearch;
    private JTextField tf_weasis;
    private JLabel lbl_remoteHost;
    private JLabel lbl_remotePort;
    private JLabel lbl_remoteAE;
    private JLabel lbl_localHost;
    private JLabel lbl_localPort;
    private JLabel lbl_localAE;
    private JTextField tf_osirix;
    
    private JTextField tf_zebra;
    private JLabel lbl_color;
    
    private JCheckBox cb_orcaMedUseApi;
    private JRadioButton rb_falcoHl7;
    private JRadioButton rb_wakayamaHl7;
    private JCheckBox cb_sendLaboTest;
    private JTextField tf_falcoFacilityId;
    private JTextField tf_falcoOutputPath;
    
    private JButton btn_hsInit;
    
    //private JRadioButton rb_jersey;
    //private JRadioButton rb_resteasy;
    private JRadioButton rb_comet;
    private JRadioButton rb_websocket;

    
    private JComboBox cmb_UiFontName;
    private JComboBox cmb_UiFontSize;
    private JComboBox cmb_UiFontStyle;
    private JComboBox cmb_StampFontName;
    private JComboBox cmb_StampFontSize;
    private JComboBox cmb_StampFontStyle;
    private JButton btn_UiDefault;

    /** 画面モデル */
    private MiscModel model;
    private StateMgr stateMgr;

    public MiscSettingPanel() {
        this.setId(ID);
        this.setTitle(TITLE);
        this.setIcon(ICON);
    }

    /**
     * GUI 及び State を生成する。
     */
    @Override
    public void start() {

        // モデルを生成し初期化する
        model = new MiscModel();
        model.populate();

        // GUIを構築する
        initComponents();

        // bind する
        bindModelToView();
    }

    /**
     * 設定値を保存する。
     */
    @Override
    public void save() {
        bindViewToModel();
        model.restore();
        
        FontManager.updateFonts();
    }

    /**
     * GUIを構築する
     */
    private void initComponents() {

        String programFolder = ClientContext.isWin()
                ? System.getenv("PROGRAMFILES")
                : "~/.wine/drive_c/Program Files/";
        String userHome = System.getProperty("user.home");

        // ラベルプリンタ、FEV-40
        GridBagBuilder gbl = new GridBagBuilder("ラベルプリンタQL-580N設定");
        tf_lblPrtAddress = GUIFactory.createTextField(10, null, null, null);
        tf_lblPrtPort = GUIFactory.createTextField(5, null, null, null);

        int row = 0;
        JLabel label = new JLabel("IPアドレス:");
        gbl.add(label, 0, row, GridBagConstraints.EAST);
        gbl.add(tf_lblPrtAddress, 1, row, GridBagConstraints.WEST);

        row++;
        label = new JLabel("ポート番号:");
        gbl.add(label, 0, row, GridBagConstraints.EAST);
        gbl.add(tf_lblPrtPort, 1, row, GridBagConstraints.WEST);
        JPanel port = gbl.getProduct();

        row = 0;
        gbl = new GridBagBuilder("フクダ電子心電図ファイリング設定");
        label = new JLabel("この端末でFEV-40を使用する");
        cb_UseFev = new JCheckBox();
        cb_UseFev.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                controlFEV();
            }
        });

        gbl.add(cb_UseFev, 0, row, GridBagConstraints.EAST);
        gbl.add(label, 1, row, GridBagConstraints.WEST);

        row++;
        lbl_fev40Path = new JLabel("FEV-40のパス");
        tf_fev40Path = GUIFactory.createTextField(20, null, null, null);
        btn_openFEV = new JButton("開く");
        MyBtnActionListener listener = new MyBtnActionListener(programFolder, tf_fev40Path, JFileChooser.FILES_ONLY);
        btn_openFEV.addActionListener(listener);
        gbl.add(lbl_fev40Path, 0, row, GridBagConstraints.EAST);
        gbl.add(tf_fev40Path, 1, row, GridBagConstraints.CENTER);
        gbl.add(btn_openFEV, 2, row, GridBagConstraints.WEST);
        
        row++;
        lbl_useWine = new JLabel("Wineを使う");
        cb_UseWine = new JCheckBox();
        gbl.add(cb_UseWine, 0, row, GridBagConstraints.EAST);
        gbl.add(lbl_useWine, 1, row, GridBagConstraints.WEST);
        
        row++;
        lbl_winePath = new JLabel("Wineのパス");
        tf_winePath = GUIFactory.createTextField(20, null, null, null);
        btn_openWine = new JButton("開く");
        listener = new MyBtnActionListener(userHome, tf_winePath, JFileChooser.FILES_ONLY);
        btn_openWine.addActionListener(listener);
        gbl.add(lbl_winePath, 0, row, GridBagConstraints.EAST);
        gbl.add(tf_winePath, 1, row, GridBagConstraints.CENTER);
        gbl.add(btn_openWine, 2, row, GridBagConstraints.WEST);
        
        row++;
        lbl_fev70 = new JLabel("PVT受信時、FEV-70に患者情報自動登録を行う");
        cb_SendPatientInfo = new JCheckBox();
        gbl.add(cb_SendPatientInfo, 0, row, GridBagConstraints.EAST);
        gbl.add(lbl_fev70, 1, row, GridBagConstraints.WEST);

        row++;
        lbl_fevShareFolder = new JLabel("共有フォルダ");
        gbl.add(lbl_fevShareFolder, 0, row, GridBagConstraints.EAST);
        tf_fevSharePath = GUIFactory.createTextField(20, null, null, null);
        gbl.add(tf_fevSharePath, 1, row, GridBagConstraints.WEST);
        JPanel fev = gbl.getProduct();

        cb_SendPatientInfo.setEnabled(false);
        tf_fevSharePath.setEnabled(false);
        lbl_fev70.setEnabled(false);
        lbl_fevShareFolder.setEnabled(false);

        row++;
        gbl = new GridBagBuilder("算定チェック");
        label = new JLabel("同一月に同一処方があれば薬剤情報提供料算定不可とする");
        cb_Yakujo = new JCheckBox();
        gbl.add(cb_Yakujo, 0, row, GridBagConstraints.EAST);
        gbl.add(label, 1, row, GridBagConstraints.WEST);

        row++;
        label = new JLabel("カルテ保存時内科向け算定チェックする");
        cb_Santei = new JCheckBox();
        gbl.add(cb_Santei, 0, row, GridBagConstraints.EAST);
        gbl.add(label, 1, row, GridBagConstraints.WEST);

        JPanel yakujo = gbl.getProduct();

        // 全体レイアウト
        gbl = new GridBagBuilder();
        gbl.add(port, 0, 0, GridBagConstraints.HORIZONTAL, 1.0, 0.0);
        gbl.add(fev, 0, 1, GridBagConstraints.HORIZONTAL, 1.0, 0.0);
        gbl.add(yakujo, 0, 2, GridBagConstraints.HORIZONTAL, 1.0, 0.0);

        JPanel setting = gbl.getProduct();
        setting.setLayout(new BoxLayout(setting, BoxLayout.Y_AXIS));

        // 設定２
        JPanel setting2 = new JPanel();
        setting2.setLayout(new BoxLayout(setting2, BoxLayout.Y_AXIS));
        gbl = new GridBagBuilder("処方デフォルト設定");
        rb_inMed = new JRadioButton("院内処方");
        rb_exMed = new JRadioButton("院外処方");
        ButtonGroup bg = new ButtonGroup();
        bg.add(rb_inMed);
        bg.add(rb_exMed);
        JPanel medP = GUIFactory.createRadioPanel(new JRadioButton[]{rb_inMed, rb_exMed});
        gbl.add(medP, 0, 0, GridBagConstraints.CENTER);
        JPanel medPanel = gbl.getProduct();
/*
        gbl = new GridBagBuilder("チャート状態を同期する");
        cb_useJms = new JCheckBox("サーバーと同期");
        gbl.add(cb_useJms, 0, 0, GridBagConstraints.CENTER);
        JPanel chartSyncPanel = gbl.getProduct();

        gbl = new GridBagBuilder("スタンプホルダ");
        cmb_cellPadding = new JComboBox();
        for (int i = 0; i < 4; ++i) {
            cmb_cellPadding.addItem(i);
        }

        gbl.add(new JLabel("スタンプ行間隔"), 0, 0, GridBagConstraints.EAST);
        gbl.add(cmb_cellPadding, 1, 0, GridBagConstraints.WEST);
        JPanel stamp = gbl.getProduct();
*/        
        
        gbl = new GridBagBuilder("設定");
        btn_discardSize = new JButton("インスペクタサイズ初期化");
        btn_discardSize.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                discardInspectorSize();
            }
        });

        btn_openBase = new JButton("ベースフォルダを開く");
        btn_openBase.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                File file = new File(ClientContext.getBaseDirectory());
                try {
                    Desktop.getDesktop().open(file);
                } catch (IOException ex) {
                }
            }
        });
        
        btn_saveProp = new JButton("設定をサーバーに保存");
        btn_saveProp.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    saveProperties();
                } catch (Exception ex) {
                }
            }
        });
        btn_loadProp = new JButton("設定をサーバーから読込");
        btn_loadProp.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    loadProperties();
                } catch (Exception ex) {
                }
            }
        });
        gbl.add(btn_openBase, 0, 0, GridBagConstraints.CENTER);
        gbl.add(btn_discardSize, 1, 0, GridBagConstraints.CENTER);
        gbl.add(btn_saveProp, 0, 1, GridBagConstraints.CENTER);
        gbl.add(btn_loadProp, 1, 1, GridBagConstraints.CENTER);
        JPanel inspector = gbl.getProduct();
        
        gbl = new GridBagBuilder("サーバー設定");
        //cb_PvtOnServer = new JCheckBox("PVT受信登録処理をサーバーで行う");
        cb_FevOnServer = new JCheckBox("FEV患者登録処理をサーバーで行う");
        cb_FevOnServer.setToolTipText("出力先フォルダは設定１で入力してください");
        //gbl.add(cb_PvtOnServer, 0, 0, GridBagConstraints.CENTER);
        gbl.add(cb_FevOnServer, 0, 0, GridBagConstraints.CENTER);
        JPanel pvt = gbl.getProduct();
        
        gbl = new GridBagBuilder("カルテスクロール");
        //cb_skipScroll = new JCheckBox("スキップスクロール有効");
        //gbl.add(cb_skipScroll, 0, 0, GridBagConstraints.CENTER);
        String[] items = new String[]{"ノーマル", "スキップ", "ページ"};
        cmb_karteScroll = new JComboBox(items);
        gbl.add(cmb_karteScroll, 0, 0, GridBagConstraints.CENTER);
        cb_verticalLayout = new JCheckBox("水平配置カルテでは所見と処置を縦に並べる");
        gbl.add(cb_verticalLayout, 0, 1, GridBagConstraints.CENTER);
        JPanel karteScroll = gbl.getProduct();

        gbl = new GridBagBuilder("電子点数表");
        JButton btn_etensu = new JButton("電子点数表登録");
        btn_etensu.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                RegistETensuData reg = new RegistETensuData();
                reg.startRegist(MiscSettingPanel.this.getContext());
            }
        });
        JButton btn_santei = new JButton("算定歴初期化");
        btn_santei.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                RegistETensuData reg = new RegistETensuData();
                reg.startInitSantei(MiscSettingPanel.this.getContext());
            }
        });

        gbl.add(btn_etensu, 0, 0, GridBagConstraints.CENTER);
        gbl.add(btn_santei, 1, 0, GridBagConstraints.CENTER);
        JPanel etensu = gbl.getProduct();
        if (!isLoginState()) {
            btn_etensu.setEnabled(false);
            btn_santei.setEnabled(false);
        }

        // 全体レイアウト
        gbl = new GridBagBuilder();
        gbl.add(medPanel, 0, 0, GridBagConstraints.HORIZONTAL, 1.0, 0.0);
        //gbl.add(stamp, 0, 1, GridBagConstraints.HORIZONTAL, 1.0, 0.0);
        gbl.add(inspector, 0, 2, GridBagConstraints.HORIZONTAL, 1.0, 0.0);
        gbl.add(pvt, 0, 3, GridBagConstraints.HORIZONTAL, 1.0, 0.0);
        //gbl.add(chartSyncPanel, 0, 3, GridBagConstraints.HORIZONTAL, 1.0, 0.0);
        gbl.add(karteScroll, 0, 4, GridBagConstraints.HORIZONTAL, 1.0, 0.0);
        gbl.add(etensu, 0, 5, GridBagConstraints.HORIZONTAL, 1.0, 0.0);
        setting2.add(gbl.getProduct());
/*
        // RSB設定
        row = 0;
        JPanel settingRSB = new JPanel();
        settingRSB.setLayout(new BoxLayout(settingRSB, BoxLayout.Y_AXIS));
        gbl = new GridBagBuilder("RS_Base設定");
        label = new JLabel("RS_Baseを使用");
        cb_useRsb = new JCheckBox();
        cb_useRsb.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                controlRSB();
            }
        });

        gbl.add(cb_useRsb, 0, row, GridBagConstraints.EAST);
        gbl.add(label, 1, row, GridBagConstraints.WEST);
        row++;
        tf_rsbBrowserPath = GUIFactory.createTextField(20, null, null, null);
        lbl_rsbBrowser = new JLabel("ブラウザ指定:");
        btn_openRSB = new JButton("開く");
        listener = new MyBtnActionListener(programFolder, tf_rsbBrowserPath);
        btn_openRSB.addActionListener(listener);
        gbl.add(lbl_rsbBrowser, 0, row, GridBagConstraints.EAST);
        gbl.add(tf_rsbBrowserPath, 1, row, GridBagConstraints.CENTER);
        gbl.add(btn_openRSB, 2, row, GridBagConstraints.WEST);
        row++;
        tf_rsbURL = GUIFactory.createTextField(20, null, null, null);
        lbl_rsbURL = new JLabel("URL:");
        gbl.add(lbl_rsbURL, 0, row, GridBagConstraints.EAST);
        gbl.add(tf_rsbURL, 1, row, GridBagConstraints.WEST);
        row++;
        tf_rsbRsnPath = GUIFactory.createTextField(20, null, null, null);
        lbl_rsbRsnPath = new JLabel("rsn Path:");
        gbl.add(lbl_rsbRsnPath, 0, row, GridBagConstraints.EAST);
        gbl.add(tf_rsbRsnPath, 1, row, GridBagConstraints.WEST);
        row++;
        tf_rsbDrsPath = GUIFactory.createTextField(20, null, null, null);
        lbl_rsbDrsPath = new JLabel("DRS Path:");
        gbl.add(lbl_rsbDrsPath, 0, row, GridBagConstraints.EAST);
        gbl.add(tf_rsbDrsPath, 1, row, GridBagConstraints.WEST);
        row++;
        tf_rsbLinkPath = GUIFactory.createTextField(20, null, null, null);
        lbl_rsbLinkPath = new JLabel("Link Path:");
        gbl.add(lbl_rsbLinkPath, 0, row, GridBagConstraints.EAST);
        gbl.add(tf_rsbLinkPath, 1, row, GridBagConstraints.WEST);
        JPanel rsb = gbl.getProduct();

        // 全体レイアウト
        gbl = new GridBagBuilder();
        gbl.add(rsb, 0, 0, GridBagConstraints.HORIZONTAL, 1.0, 0.0);
        settingRSB.add(gbl.getProduct());
*/
        // PACS
        gbl = new GridBagBuilder("PACSサーバー設定");
        tf_pacsRemoteHost = GUIFactory.createTextField(15, null, null, null);
        tf_pacsRemotePort = GUIFactory.createTextField(5, null, null, null);
        tf_pacsRemoteAE = GUIFactory.createTextField(15, null, null, null);
        tf_pacsLocalHost = GUIFactory.createTextField(15, null, null, null);
        tf_pacsLocalPort = GUIFactory.createTextField(5, null, null, null);
        tf_pacsLocalAE = GUIFactory.createTextField(15, null, null, null);
        tf_weasis = GUIFactory.createTextField(15, null, null, null);
        tf_osirix = GUIFactory.createTextField(15, null, null, null);
        // tooltip
        tf_weasis.setToolTipText("http://{weasisのIP]:{port}");
        tf_osirix.setToolTipText("http://{osirixのIP]:{port}");

        row = 0;
        label = new JLabel("PACS接続機能を利用する");
        cb_UsePacs = new JCheckBox();
        cb_UsePacs.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                controlPacs();
            }
        });
        gbl.add(cb_UsePacs, 0, row, GridBagConstraints.EAST);
        gbl.add(label, 1, row, GridBagConstraints.WEST);

        row++;
        lbl_remoteHost = new JLabel("IPアドレス:");
        gbl.add(lbl_remoteHost, 0, row, GridBagConstraints.EAST);
        gbl.add(tf_pacsRemoteHost, 1, row, GridBagConstraints.WEST);

        row++;
        lbl_remotePort = new JLabel("ポート番号:");
        gbl.add(lbl_remotePort, 0, row, GridBagConstraints.EAST);
        gbl.add(tf_pacsRemotePort, 1, row, GridBagConstraints.WEST);
        row++;
        lbl_remoteAE = new JLabel("AEタイトル:");
        gbl.add(lbl_remoteAE, 0, row, GridBagConstraints.EAST);
        gbl.add(tf_pacsRemoteAE, 1, row, GridBagConstraints.WEST);
        JPanel server = gbl.getProduct();

        gbl = new GridBagBuilder("クライアント設定");
        row = 0;
        lbl_localHost = new JLabel("IPアドレス:");
        gbl.add(lbl_localHost, 0, row, GridBagConstraints.EAST);
        gbl.add(tf_pacsLocalHost, 1, row, GridBagConstraints.WEST);

        row++;
        lbl_localPort = new JLabel("ポート番号:");
        gbl.add(lbl_localPort, 0, row, GridBagConstraints.EAST);
        gbl.add(tf_pacsLocalPort, 1, row, GridBagConstraints.WEST);
        row++;
        lbl_localAE = new JLabel("AEタイトル:");
        gbl.add(lbl_localAE, 0, row, GridBagConstraints.EAST);
        gbl.add(tf_pacsLocalAE, 1, row, GridBagConstraints.WEST);
        JPanel client = gbl.getProduct();

        gbl = new GridBagBuilder("患者ID検索");
        row = 0;
        cb_SuffixSearch = new JCheckBox("後方一致検索する");
        gbl.add(cb_SuffixSearch, 0, row, GridBagConstraints.EAST);
        JPanel search = gbl.getProduct();
        
        gbl = new GridBagBuilder("Pacs viewer設定");
        JLabel lbl_weasis = new JLabel("Weasisアドレス:");
        gbl.add(lbl_weasis, 0, 0, GridBagConstraints.EAST);
        gbl.add(tf_weasis, 1, 0, GridBagConstraints.WEST);
        JLabel lbl_osirix = new JLabel("Osirix XML-RPC URL:");
        gbl.add(lbl_osirix, 0, 1, GridBagConstraints.EAST);
        gbl.add(tf_osirix, 1, 1, GridBagConstraints.WEST);
        JPanel pacsViewer = gbl.getProduct();
    
        // 全体レイアウト
        gbl = new GridBagBuilder();
        gbl.add(server, 0, 0, GridBagConstraints.HORIZONTAL, 1.0, 0.0);
        gbl.add(client, 0, 1, GridBagConstraints.HORIZONTAL, 1.0, 0.0);
        gbl.add(search, 0, 2, GridBagConstraints.HORIZONTAL, 1.0, 0.0);
        gbl.add(pacsViewer, 0, 3, GridBagConstraints.HORIZONTAL, 1.0, 0.0);
        JPanel pacsSetting = gbl.getProduct();

        // 設定３
        // orcaMed
        gbl = new GridBagBuilder("ORCA処方参照");
        cb_orcaMedUseApi = new JCheckBox("ORCA APIを利用する");
        gbl.add(cb_orcaMedUseApi, 0, 0, GridBagConstraints.CENTER);
        JPanel orcaMed = gbl.getProduct();
        // Labo
        gbl = new GridBagBuilder("ラボ");
        row = 0;

        rb_falcoHl7 = new JRadioButton("FALCO");
        rb_wakayamaHl7 = new JRadioButton("和歌山市医師会");
        JPanel pnlHl7 = new JPanel();
        pnlHl7.setLayout(new FlowLayout());
        pnlHl7.add(rb_falcoHl7);
        pnlHl7.add(rb_wakayamaHl7);
        ButtonGroup jbg = new ButtonGroup();
        jbg.add(rb_falcoHl7);
        jbg.add(rb_wakayamaHl7);
        JLabel lbl_hl7 = new JLabel("HL7形式");
        gbl.add(lbl_hl7, 0, row, GridBagConstraints.EAST);
        gbl.add(pnlHl7, 1, row, GridBagConstraints.CENTER);
        
        row++;
        JLabel lbl_sendFalco = new JLabel("FALCOラボオーダー出力する。");
        cb_sendLaboTest = new JCheckBox();
        gbl.add(cb_sendLaboTest, 0, row, GridBagConstraints.EAST);
        gbl.add(lbl_sendFalco, 1, row, GridBagConstraints.WEST);
        
        row++;
        JLabel lbl_falcoFacilityId = new JLabel("FALCO施設ID");
        tf_falcoFacilityId = GUIFactory.createTextField(20, null, null, null);
        gbl.add(lbl_falcoFacilityId, 0, row, GridBagConstraints.EAST);
        gbl.add(tf_falcoFacilityId, 1, row, GridBagConstraints.WEST);
        
        row++;
        JLabel lbl_falcoOutputPath = new JLabel("FALCO出力パス");
        tf_falcoOutputPath = GUIFactory.createTextField(20, null, null, null);
        JButton btn_openFalcoPath = new JButton("開く");
        listener = new MyBtnActionListener(userHome, tf_falcoOutputPath, JFileChooser.DIRECTORIES_ONLY);
        btn_openFalcoPath.addActionListener(listener);

        gbl.add(lbl_falcoOutputPath, 0, row, GridBagConstraints.EAST);
        gbl.add(tf_falcoOutputPath, 1, row, GridBagConstraints.WEST);
        gbl.add(btn_openFalcoPath, 2, row, GridBagConstraints.WEST);
        JPanel labo = gbl.getProduct();
        
        // HibernateSearch
        gbl = new GridBagBuilder("HibernateSearch");
        btn_hsInit = new JButton("初期インデックス作成");
        btn_hsInit.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                InitHibernateSearchIndex ihsi = new InitHibernateSearchIndex();
                ihsi.start(getContext());
            }
        });
        gbl.add(btn_hsInit, 0, 0, GridBagConstraints.CENTER);
        JPanel hs = gbl.getProduct();
/*
        // client REST
        gbl = new GridBagBuilder("クライアントJAX-RS");
        rb_jersey = new JRadioButton("Jersey");
        rb_resteasy = new JRadioButton("RESTEasy");
        ButtonGroup bgRest = new ButtonGroup();
        bgRest.add(rb_jersey);
        bgRest.add(rb_resteasy);

        JPanel pnlRest = new JPanel();
        pnlRest.setLayout(new FlowLayout());
        pnlRest.add(rb_jersey);
        pnlRest.add(rb_resteasy);

        gbl.add(pnlRest, 0, row, GridBagConstraints.CENTER);
        JPanel rest = gbl.getProduct();
*/
//        // comet / websocket
//        gbl = new GridBagBuilder("チャート状態同期");
//        rb_comet = new JRadioButton("Comet");
//        rb_websocket = new JRadioButton("WebSocket");
//        ButtonGroup bgSync = new ButtonGroup();
//        bgSync.add(rb_comet);
//        bgSync.add(rb_websocket);
//
//        JPanel pnlSync = new JPanel();
//        pnlSync.setLayout(new FlowLayout());
//        pnlSync.add(rb_comet);
//        pnlSync.add(rb_websocket);
//
//        gbl.add(pnlSync, 0, row, GridBagConstraints.CENTER);
//        JPanel sync = gbl.getProduct();
        
        // 全体レイアウト
        gbl = new GridBagBuilder();
        gbl.add(orcaMed, 0, 0, GridBagConstraints.HORIZONTAL, 1.0, 0.0);
        gbl.add(labo, 0, 1, GridBagConstraints.HORIZONTAL, 1.0, 0.0);
        gbl.add(hs, 0, 2, GridBagConstraints.HORIZONTAL, 1.0, 0.0);
        //gbl.add(rest, 0, 3, GridBagConstraints.HORIZONTAL, 1.0, 0.0);
        //gbl.add(sync, 0, 3, GridBagConstraints.HORIZONTAL, 1.0, 0.0);
        JPanel setting3 = gbl.getProduct();
        
        // UI設定
        // 色
        gbl = new GridBagBuilder("表ストライプの色");
        tf_zebra = GUIFactory.createTextField(15, null, null, null);
        //tf_zebra.setEditable(false);
        JButton zebraBtn = new JButton("色選択");
        zebraBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Color color = stringToColor(tf_zebra.getText().trim());
                color = JColorChooser.showDialog(getContext().getDialog(), "色選択", color);
                if (color == null) {
                    color = DEFAULT_EVEN_COLOR;
                }
                tf_zebra.setText(colorToString(color));
                lbl_color.setBackground(color);
            }
        });
        JButton defaultColorBtn = new JButton("デフォルト");
        defaultColorBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Color color = DEFAULT_EVEN_COLOR;
                tf_zebra.setText(colorToString(color));
                lbl_color.setBackground(color);
            }
        });
        
        lbl_color = new JLabel("            ");
        lbl_color.setOpaque(true);
        lbl_color.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        gbl.add(lbl_color, 0, 0, GridBagConstraints.CENTER);
        gbl.add(tf_zebra, 1, 0, GridBagConstraints.CENTER);
        gbl.add(zebraBtn, 0, 1, GridBagConstraints.CENTER);
        gbl.add(defaultColorBtn, 1, 1, GridBagConstraints.CENTER);
        JPanel color = gbl.getProduct();
        
        // フォント
        gbl = new GridBagBuilder("ＵＩフォント設定");
        String[] fontNames = getFontNames();
        cmb_UiFontName = new JComboBox();
        cmb_StampFontName = new JComboBox();
        for (String fontName : fontNames) {
            cmb_UiFontName.addItem(fontName);
            cmb_StampFontName.addItem(fontName);
        }
        cmb_UiFontSize = new JComboBox();
        cmb_StampFontSize = new JComboBox();
        final int[] fontSizes = FontManager.getFontSizes();
        for (int fontSize : fontSizes) {
            cmb_UiFontSize.addItem(fontSize);
            cmb_StampFontSize.addItem(fontSize);
        }
        final String[] fontStyles = {"PLAIN", "BOLD", "ITALIC"};
        cmb_UiFontStyle = new JComboBox();
        cmb_StampFontStyle = new JComboBox();
        for (String style : fontStyles) {
            cmb_UiFontStyle.addItem(style);
            cmb_StampFontStyle.addItem(style);
        }
        // style変更はやめとく
        cmb_UiFontStyle.setVisible(false);
        cmb_StampFontStyle.setVisible(false);
         
        btn_UiDefault = new JButton("デフォルト");
        btn_UiDefault.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                cmb_UiFontName.setSelectedItem(DEFAULT_UI_FONT_NAME);
                cmb_UiFontSize.setSelectedItem(DEFAULT_UI_FONT_SIZE);
                cmb_UiFontStyle.setSelectedIndex(0);
                cmb_StampFontName.setSelectedItem(DEFAULT_STAMP_FONT_NAME);
                cmb_StampFontSize.setSelectedItem(DEFAULT_STAMP_FONT_SIZE);
                cmb_StampFontStyle.setSelectedIndex(0);
                
            }
        });
        
        gbl.add(new JLabel("カルテ"), 0, 0, GridBagConstraints.CENTER);
        gbl.add(cmb_UiFontName, 1, 0, GridBagConstraints.CENTER);
        gbl.add(cmb_UiFontSize, 2, 0, GridBagConstraints.CENTER);
        gbl.add(cmb_UiFontStyle, 3, 0, GridBagConstraints.CENTER);
        gbl.add(new JLabel("スタンプ"), 0, 1, GridBagConstraints.CENTER);
        gbl.add(cmb_StampFontName, 1, 1, GridBagConstraints.CENTER);
        gbl.add(cmb_StampFontSize, 2, 1, GridBagConstraints.CENTER);
        gbl.add(cmb_StampFontStyle, 3, 1, GridBagConstraints.CENTER);
        gbl.add(btn_UiDefault, 1, 2, GridBagConstraints.CENTER);
        JPanel font = gbl.getProduct();

        // 全体レイアウト
        gbl = new GridBagBuilder();
        gbl.add(color, 0, 0, GridBagConstraints.HORIZONTAL, 1.0, 0.0);
        gbl.add(font, 0, 1, GridBagConstraints.HORIZONTAL, 1.0, 0.0);
        JPanel uiSetting = gbl.getProduct();
        
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("設定１", setting);
        tabbedPane.addTab("設定２", setting2);
        tabbedPane.addTab("設定３", setting3);
        //tabbedPane.addTab("RS_Base", settingRSB);
        tabbedPane.addTab("ＵＩ", uiSetting);
        tabbedPane.addTab("PACS", pacsSetting);

        getUI().setLayout(new BorderLayout());
        getUI().add(tabbedPane);

        controlFEV();
        //controlRSB();
        controlPacs();

        connect();
    }

    private static class MyBtnActionListener implements ActionListener {

        private final JTextField tf;
        private final String currentDirectory;
        private final int mode;

        private MyBtnActionListener(String currentDirectory, JTextField tf, int mode) {
            this.tf = tf;
            this.currentDirectory = currentDirectory;
            this.mode = mode;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser(currentDirectory);
            chooser.setFileSelectionMode(mode);
            int returnVal = chooser.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                String path = chooser.getSelectedFile().getPath();
                tf.setText(path);
            }
        }
    }
/*
    private void controlRSB() {
        boolean b = cb_useRsb.isSelected();
        tf_rsbURL.setEnabled(b);
        tf_rsbDrsPath.setEnabled(b);
        tf_rsbLinkPath.setEnabled(b);
        tf_rsbRsnPath.setEnabled(b);
        lbl_rsbURL.setEnabled(b);
        lbl_rsbDrsPath.setEnabled(b);
        lbl_rsbLinkPath.setEnabled(b);
        lbl_rsbRsnPath.setEnabled(b);
        lbl_rsbBrowser.setEnabled(b);
        btn_openRSB.setEnabled(b);
        tf_rsbBrowserPath.setEnabled(b);
    }
*/
    private void controlFEV() {
        boolean b = cb_UseFev.isSelected();
        cb_SendPatientInfo.setEnabled(b);
        tf_fevSharePath.setEnabled(b);
        lbl_fev70.setEnabled(b);
        lbl_fevShareFolder.setEnabled(b);
        lbl_fev40Path.setEnabled(b);
        tf_fev40Path.setEnabled(b);
        btn_openFEV.setEnabled(b);
        cb_UseWine.setEnabled(b);
        lbl_winePath.setEnabled(b);
        tf_winePath.setEnabled(b);
        btn_openWine.setEnabled(b);
        lbl_useWine.setEnabled(b);
    }

    private void controlPacs() {
        boolean b = cb_UsePacs.isSelected();
        tf_pacsRemoteHost.setEnabled(b);
        tf_pacsRemotePort.setEnabled(b);
        tf_pacsRemoteAE.setEnabled(b);
        tf_pacsLocalHost.setEnabled(b);
        tf_pacsLocalPort.setEnabled(b);
        tf_pacsLocalAE.setEnabled(b);
        lbl_remoteHost.setEnabled(b);
        lbl_remotePort.setEnabled(b);
        lbl_remoteAE.setEnabled(b);
        lbl_localHost.setEnabled(b);
        lbl_localPort.setEnabled(b);
        lbl_localAE.setEnabled(b);
        cb_SuffixSearch.setEnabled(b);
    }

    /**
     * リスナを接続する。
     */
    private void connect() {

        stateMgr = new StateMgr();

        // DocumentListener
        DocumentListener dl = new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                stateMgr.checkState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                stateMgr.checkState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                stateMgr.checkState();
            }
        };

        String portPattern = "[0-9]*";
        RegexConstrainedDocument portDoc = new RegexConstrainedDocument(portPattern);
        tf_lblPrtPort.setDocument(portDoc);
        tf_lblPrtPort.getDocument().addDocumentListener(dl);
        tf_lblPrtPort.addFocusListener(AutoRomanListener.getInstance());

        String ipPattern = "[A-Za-z0-9.]*";
        RegexConstrainedDocument ipDoc = new RegexConstrainedDocument(ipPattern);
        tf_lblPrtAddress.setDocument(ipDoc);
        tf_lblPrtAddress.getDocument().addDocumentListener(dl);
        tf_lblPrtAddress.addFocusListener(AutoRomanListener.getInstance());
        
        // ログインしていないと利用不可
        if (!isLoginState()) {
            btn_discardSize.setEnabled(false);
            btn_openBase.setEnabled(false);
            btn_loadProp.setEnabled(false);
            btn_saveProp.setEnabled(false);
            btn_hsInit.setEnabled(false);
        }
    }

    /**
     * ModelToView
     */
    private void bindModelToView() {

        // ラベルプリンタのIPアドレスを設定する
        String val = model.lblPrtAddress;
        val = val != null ? val : "";
        tf_lblPrtAddress.setText(val);

        // ラベルプリンタのポート番号を設定する
        val = String.valueOf(model.lblPrtPort);
        val = val != null ? val : "";
        tf_lblPrtPort.setText(val);

        // FEV40.EXE, 共有フォルダを設定する
        val = model.fevSharePath;
        val = val != null ? val : "";
        tf_fevSharePath.setText(val);
        val = model.fev40Path;
        val = val != null ? val : "";
        tf_fev40Path.setText(val);
        
        // Wine
        val = model.winePath;
        val = val != null ? val : "";
        tf_winePath.setText(val);
        cb_UseWine.setSelected(model.useWine);

        // Use Fev
        cb_UseFev.setSelected(model.useFEV);
        // SendPatient Info
        cb_SendPatientInfo.setSelected(model.sendPatientInfo);

        // 薬剤情報
        cb_Yakujo.setSelected(model.followMedicom);
        // 算定
        cb_Santei.setSelected(model.santeiCheck);

        // 処方デフォルト
        if (model.defaultExMed) {
            rb_exMed.setSelected(true);
        } else {
            rb_inMed.setSelected(true);
        }
        // Chart stateをサーバーと同期
        //cb_useJms.setSelected(model.useJms);
        // CellPadding
        //cmb_cellPadding.setSelectedItem(model.stampHolderCellPadding);
        // Skip Scroll
        //cb_skipScroll.setSelected(model.skipScroll);
        cmb_karteScroll.setSelectedIndex(model.karteScrollType);
        // vertical layout
        cb_verticalLayout.setSelected(model.verticalLayout);
/*
        // RS_Base
        cb_useRsb.setSelected(model.useRsb);
        val = model.rsbURL;
        val = val != null ? val : "";
        tf_rsbURL.setText(val);
        val = model.rsbDrsPath;
        val = val != null ? val : "";
        tf_rsbDrsPath.setText(val);
        val = model.rsbLinkPath;
        val = val != null ? val : "";
        tf_rsbLinkPath.setText(val);
        val = model.rsbRsnPath;
        val = val != null ? val : "";
        tf_rsbRsnPath.setText(val);
        val = model.rsbBrowserPath;
        val = val != null ? val : "";
        tf_rsbBrowserPath.setText(val);
*/
        // PVT
        //cb_PvtOnServer.setSelected(model.pvtOnServer);
        cb_FevOnServer.setSelected(model.fevOnServer);
        // Pacs
        cb_UsePacs.setSelected(model.usePacs);
        cb_SuffixSearch.setSelected(model.useSuffixSearch);
        val = model.remoteHost;
        val = val != null ? val : "";
        tf_pacsRemoteHost.setText(val);
        val = model.remoteAE;
        val = val != null ? val : "";
        tf_pacsRemoteAE.setText(val);
        val = String.valueOf(model.remotePort);
        val = val != null ? val : "";
        tf_pacsRemotePort.setText(val);
        val = model.localHost;
        val = val != null ? val : "";
        tf_pacsLocalHost.setText(val);
        val = model.localAE;
        val = val != null ? val : "";
        tf_pacsLocalAE.setText(val);
        val = String.valueOf(model.localPort);
        val = val != null ? val : "";
        tf_pacsLocalPort.setText(val);
        val = String.valueOf(model.weasisAddress);
        val = val != null ? val : "";
        tf_weasis.setText(val);
        val = String.valueOf(model.osirixAddress);
        val = val != null ? val : "";
        tf_osirix.setText(val);

        // 色
        val = String.valueOf(model.zebraColor);
        val = val != null ? val : "";
        tf_zebra.setText(val);
        Color c = stringToColor(val);
        lbl_color.setBackground(c);
        
        // labo
        val = model.hl7Format;
        val = val != null ? val : "";
        if ("wakayama".equals(val)) {
            rb_wakayamaHl7.setSelected(true);
        } else {
            rb_falcoHl7.setSelected(true);
        }
        cb_sendLaboTest.setSelected(model.sendLaboTest);
        val = model.falcoFacilityId;
        val = val != null ? val : "";
        tf_falcoFacilityId.setText(val);
        val = model.falcoOutputPath;
        val = val != null ? val : "";
        tf_falcoOutputPath.setText(val);
        
        // orcaMed
        cb_orcaMedUseApi.setSelected(model.orcaMedUseApi);
/*
        // rest
        if (model.useJersey) {
            rb_jersey.setSelected(true);
        } else {
            rb_resteasy.setSelected(true);
        }
*/
        // UI
        cmb_UiFontName.setSelectedItem(model.uiFontName);
        cmb_UiFontSize.setSelectedItem(model.uiFontSize);
        cmb_UiFontStyle.setSelectedIndex(model.uiFontStyle);
        cmb_StampFontName.setSelectedItem(model.stampFontName);
        cmb_StampFontSize.setSelectedItem(model.stampFontSize);
        cmb_StampFontStyle.setSelectedIndex(model.stampFontStyle);
        
        // Chart sync
        if (model.useWebsocket) {
            rb_websocket.setSelected(true);
        } else {
            rb_comet.setSelected(true);
        }
    }

    /**
     * ViewToModel
     */
    private void bindViewToModel() {

        // IPアドレスを保存する
        model.lblPrtAddress = tf_lblPrtAddress.getText().trim();

        // ポート番号を保存する
        try {
            int port = Integer.parseInt(tf_lblPrtPort.getText().trim());
            model.lblPrtPort = port;

        } catch (NumberFormatException e) {
            model.lblPrtPort = 9100;
        }

        // FEV
        model.useFEV = cb_UseFev.isSelected();
        model.fev40Path = tf_fev40Path.getText().trim();
        model.fevSharePath = tf_fevSharePath.getText().trim();
        model.sendPatientInfo = cb_SendPatientInfo.isSelected();
        model.useWine = cb_UseWine.isSelected();
        model.winePath = tf_winePath.getText().trim();
        // 薬剤情報
        model.followMedicom = cb_Yakujo.isSelected();
        // 算定
        model.santeiCheck = cb_Santei.isSelected();
        // 院内・院外処方デフォルト
        model.defaultExMed = rb_exMed.isSelected();
        // cell padding
        //int i = (Integer) cmb_cellPadding.getSelectedItem();
        //model.stampHolderCellPadding = i;
        //StampRenderingHints.getInstance().setCellPadding(i);
        // Skip scroll
        //model.skipScroll = cb_skipScroll.isSelected();
        model.karteScrollType = cmb_karteScroll.getSelectedIndex();
        // vertical layout
        model.verticalLayout = cb_verticalLayout.isSelected();
/*
        // RS_Base
        model.useRsb = cb_useRsb.isSelected();
        model.rsbURL = tf_rsbURL.getText().trim();
        model.rsbDrsPath = tf_rsbDrsPath.getText().trim();
        model.rsbLinkPath = tf_rsbLinkPath.getText().trim();
        model.rsbRsnPath = tf_rsbRsnPath.getText().trim();
        model.rsbBrowserPath = tf_rsbBrowserPath.getText().trim();
*/
        // PVT
        //model.pvtOnServer = cb_PvtOnServer.isSelected();
        model.fevOnServer = cb_FevOnServer.isSelected();
        
        // Pacs
        model.usePacs = cb_UsePacs.isSelected();
        model.useSuffixSearch = cb_SuffixSearch.isSelected();
        model.remoteHost = tf_pacsRemoteHost.getText().trim();
        model.remoteAE = tf_pacsRemoteAE.getText().trim();
        model.remotePort = Integer.valueOf(tf_pacsRemotePort.getText());
        model.localHost = tf_pacsLocalHost.getText().trim();
        model.localAE = tf_pacsLocalAE.getText().trim();
        model.localPort = Integer.valueOf(tf_pacsLocalPort.getText());
        model.weasisAddress = tf_weasis.getText().trim();
        model.osirixAddress = tf_osirix.getText().trim();

        // Chart stateをサーバーと同期
        //model.useJms = cb_useJms.isSelected();
        
        // 色
        model.zebraColor = tf_zebra.getText().trim();
        if (rb_wakayamaHl7.isSelected()) {
            model.hl7Format = "wakayama";
        } else {
            model.hl7Format = "falco";
        }
        model.sendLaboTest = cb_sendLaboTest.isSelected();
        model.falcoFacilityId = tf_falcoFacilityId.getText().trim();
        model.falcoOutputPath = tf_falcoOutputPath.getText().trim();
        
        // orcaMed
        model.orcaMedUseApi = cb_orcaMedUseApi.isSelected();
        
        // rest
        //model.useJersey = rb_jersey.isSelected();
        
        // UI
        model.uiFontName = (String) cmb_UiFontName.getSelectedItem();
        model.uiFontSize = (int) cmb_UiFontSize.getSelectedItem();
        model.uiFontStyle = cmb_UiFontStyle.getSelectedIndex();
        model.stampFontName = (String) cmb_StampFontName.getSelectedItem();
        model.stampFontSize = (int) cmb_StampFontSize.getSelectedItem();
        model.stampFontStyle = cmb_StampFontStyle.getSelectedIndex();
        
        // Chart sync
        model.useWebsocket = rb_websocket.isSelected();
    }

    /**
     * 画面モデルクラス。
     */
    private class MiscModel {

        private String lblPrtAddress;
        private int lblPrtPort;
        private boolean useFEV;
        private boolean sendPatientInfo;
        private String fevSharePath;
        private String fev40Path;
        private boolean useWine;
        private String winePath;
        private boolean followMedicom;
        private boolean santeiCheck;
        private boolean defaultExMed;
        //private int stampHolderCellPadding;
        private boolean verticalLayout;
        private int karteScrollType;
/*
        private boolean useRsb;
        private String rsbURL;
        private String rsbDrsPath;
        private String rsbLinkPath;
        private String rsbRsnPath;
        private String rsbBrowserPath;
        private boolean useJms;
*/
        private boolean pvtOnServer;
        private boolean fevOnServer;
        
        private boolean usePacs;
        private boolean useSuffixSearch;
        private String remoteHost;
        private int remotePort;
        private String remoteAE;
        private String localHost;
        private int localPort;
        private String localAE;
        private String weasisAddress;
        private String osirixAddress;
        
        private String zebraColor;
        
        private boolean sendLaboTest;
        private String hl7Format;
        private String falcoOutputPath;
        private String falcoFacilityId;
        private boolean orcaMedUseApi;
        
        private boolean useJersey;
        private boolean useWebsocket;
        
        private String uiFontName;
        private int uiFontSize;
        private int uiFontStyle;
        private String stampFontName;
        private int stampFontSize;
        private int stampFontStyle;

        public void populate() {

            // ラベルプリンタのIPアドレス
            lblPrtAddress = Project.getString(LBLPRT_ADDRESS, DEFAULT_LBLPRT_ADDRESS);
            // ラベルプリンタのポート番号
            lblPrtPort = Project.getInt(LBLPRT_PORT, DEFAULT_LBLPRT_PORT);
            // FEV
            useFEV = Project.getBoolean(USE_FEV, DEFAULT_USEFEV);
            sendPatientInfo = Project.getBoolean(SEND_PATIENT_INFO, DEFAULT_SENDPATIENTINFO);
            fevSharePath = Project.getString(FEV_SHAREPATH, DEFAULT_SHAREPATH);
            fev40Path = Project.getString(FEV40_PATH, DEFAULT_FEV40_PATH);
            useWine = Project.getBoolean(USE_WINE, DEFAULT_USE_WINE);
            winePath = Project.getString(WINE_PATH, DEFAULT_WINE_PATH);
            // 薬剤情報
            followMedicom = Project.getBoolean(FOLLOW_MEDICOM, DEFAULT_FOLLOW_MEDICOM);
            // 算定
            santeiCheck = Project.getBoolean(SANTEI_CHECK, DEFAULT_SANTEI_CHECK);
            // 院内院外
            defaultExMed = Project.getBoolean(RP_OUT, DEFAULT_EX_MED);
            // cellPadding
            //stampHolderCellPadding = Project.getInt(STAMP_HOLDER_CELLPADDING, DEFAULT_STAMP_HOLDER_CELLPADDING);
            // Skip scroll
            karteScrollType = Project.getInt(KARTE_SCROLL_TYPE, DEFAULT_KARTE_SCROLL);
            // vertical layout
            verticalLayout = Project.getBoolean(USE_VERTICAL_LAYOUT, DEFAULT_USE_VERTICAL_LAYOUT);
/*
            // RS_Base
            useRsb = Project.getBoolean(USE_RSB, DEFAULT_USE_RSB);
            rsbURL = Project.getString(RSB_URL, DEFAULT_RSB_URL);
            rsbDrsPath = Project.getString(RSB_DRS_PATH, DEFAULT_RSB_DRS_PATH);
            rsbLinkPath = Project.getString(RSB_LINK_PATH, DEFAULT_RSB_LINK_PATH);
            rsbRsnPath = Project.getString(RSB_RSN_PATH, DEFAULT_RSB_RSN_PATH);
            rsbBrowserPath = Project.getString(RSB_BROWSER_PATH, DEFAULT_RSB_BROWSER_PATH);
*/
            // PVT
            //pvtOnServer = Project.getBoolean(PVT_ON_SERVER, DEFAULT_PVT_ON_SERVER);
            fevOnServer = Project.getBoolean(FEV_ON_SERVER, DEFAULT_FEV_ON_SERVER);
            
            // Pacs
            usePacs = Project.getBoolean(USE_PACS, DEFAULT_USE_PACS);
            useSuffixSearch = Project.getBoolean(PACS_USE_SUFFIXSEARCH, DEFAULT_PACS_SUFFIX_SEARCH);
            remoteHost = Project.getString(PACS_REMOTE_HOST, DEFAULT_PACS_REMOTE_HOST);
            remotePort = Project.getInt(PACS_REMOTE_PORT, DEFAULT_PACS_REMOTE_PORT);
            remoteAE = Project.getString(PACS_REMOTE_AE, DEFAULT_PACS_REMOTE_AE);
            localHost = Project.getString(PACS_LOCAL_HOST, DEFAULT_PACS_LOCAL_HOST);
            localPort = Project.getInt(PACS_LOCAL_PORT, DEFAULT_PACS_LOCAL_PORT);
            localAE = Project.getString(PACS_LOCAL_AE, DEFAULT_PACS_LOCAL_AE);
            weasisAddress = Project.getString(PACS_WEASIS_ADDRESS, DEFAULT_PACS_WEASIS_ADDRESS);
            osirixAddress = Project.getString(PACS_OSIRIX_ADDRESS, DEFAULT_PACS_OSIRIX_ADDRESS);

            // Chart stateをサーバーと同期
            //useJms = Project.getBoolean(RP_OUT, DEFAULT_USE_JMS);
            
            // 色
            zebraColor = Project.getString(ZEBRA_COLOR, ClientContext.getString("color.even"));
            
            // labo
            sendLaboTest = Project.getBoolean(Project.SEND_LABTEST, false);
            hl7Format = Project.getString(HL7_FORMAT, DEFAULT_HL7_FORMAT);
            falcoFacilityId = Project.getString(Project.SEND_LABTEST_FACILITY_ID, "");
            falcoOutputPath = Project.getString(Project.SEND_LABTEST_PATH, "");
            
            // orca med
            orcaMedUseApi = Project.getBoolean(ORCA_MED_USE_API, DEFAULT_ORCA_MED_USE_API);
            // rest
            useJersey = Project.getBoolean(USE_JERSEY, DEFAULT_USE_JERSEY);
            
            // UI
            uiFontName = Project.getString(UI_FONT_NAME, DEFAULT_UI_FONT_NAME);
            uiFontSize = Project.getInt(UI_FONT_SIZE, DEFAULT_UI_FONT_SIZE);
            uiFontStyle = Project.getInt(UI_FONT_STYLE, DEFAULT_UI_FONT_STYLE);
            stampFontName = Project.getString(STAMP_FONT_NAME, DEFAULT_STAMP_FONT_NAME);
            stampFontSize = Project.getInt(STAMP_FONT_SIZE, DEFAULT_STAMP_FONT_SIZE);
            stampFontStyle = Project.getInt(STAMP_FONT_STYLE, DEFAULT_STAMP_FONT_STYLE);
            
            // Chart sync
            useWebsocket = Project.getBoolean(USE_WEBSOCKET, DEFAULT_USE_WEBSOCKET);
        }

        public void restore() {

            // ラベルプリンタのIPアドレス
            Project.setString(LBLPRT_ADDRESS, lblPrtAddress);
            // ラベルプリンタのポート番号
            Project.setInt(LBLPRT_PORT, lblPrtPort);

            Project.setBoolean(USE_FEV, useFEV);
            Project.setBoolean(SEND_PATIENT_INFO, sendPatientInfo);
            Project.setString(FEV_SHAREPATH, fevSharePath);
            Project.setString(FEV40_PATH, fev40Path);
            Project.setBoolean(USE_WINE, useWine);
            Project.setString(WINE_PATH, winePath);
            Project.setBoolean(FOLLOW_MEDICOM, followMedicom);
            Project.setBoolean(SANTEI_CHECK, santeiCheck);
            Project.setBoolean(RP_OUT, defaultExMed);
            //Project.setInt(STAMP_HOLDER_CELLPADDING, stampHolderCellPadding);
            Project.setInt(KARTE_SCROLL_TYPE, karteScrollType);
            Project.setBoolean(USE_VERTICAL_LAYOUT, verticalLayout);
/*
            // RS_Base
            Project.setBoolean(USE_RSB, useRsb);
            Project.setString(RSB_URL, rsbURL);
            Project.setString(RSB_DRS_PATH, rsbDrsPath);
            Project.setString(RSB_LINK_PATH, rsbLinkPath);
            Project.setString(RSB_RSN_PATH, rsbRsnPath);
            Project.setString(RSB_BROWSER_PATH, rsbBrowserPath);
*/
            // PVT
            //Project.setBoolean(PVT_ON_SERVER, pvtOnServer);
            Project.setBoolean(FEV_ON_SERVER, fevOnServer);
            
            // Pacs
            Project.setString(PACS_REMOTE_HOST, remoteHost);
            Project.setInt(PACS_REMOTE_PORT, remotePort);
            Project.setString(PACS_REMOTE_AE, remoteAE);
            Project.setString(PACS_LOCAL_HOST, localHost);
            Project.setInt(PACS_LOCAL_PORT, localPort);
            Project.setString(PACS_LOCAL_AE, localAE);
            Project.setBoolean(USE_PACS ,usePacs);
            Project.setBoolean(PACS_USE_SUFFIXSEARCH, useSuffixSearch);
            Project.setString(PACS_WEASIS_ADDRESS, weasisAddress);
            Project.setString(PACS_OSIRIX_ADDRESS, osirixAddress);

            // Chart stateをサーバーと同期
            //Project.setBoolean(USE_JMS, useJms);
            
            // 色
            Project.setString(ZEBRA_COLOR, zebraColor);
            
            // labo
            Project.setString(HL7_FORMAT, hl7Format);
            Project.setBoolean(Project.SEND_LABTEST, sendLaboTest);
            Project.setString(Project.SEND_LABTEST_FACILITY_ID, falcoFacilityId);
            Project.setString(Project.SEND_LABTEST_PATH, falcoOutputPath);
            
            // orcaMed
            Project.setBoolean(ORCA_MED_USE_API, orcaMedUseApi);
            
            // rest
            Project.setBoolean(USE_JERSEY, useJersey);
            
            // UI
            Project.setString(UI_FONT_NAME, uiFontName);
            Project.setInt(UI_FONT_SIZE, uiFontSize);
            Project.setInt(UI_FONT_STYLE, uiFontStyle);
            Project.setString(STAMP_FONT_NAME, stampFontName);
            Project.setInt(STAMP_FONT_SIZE, stampFontSize);
            Project.setInt(STAMP_FONT_STYLE, stampFontStyle);
            
            // Chart sync
            Project.setBoolean(USE_WEBSOCKET, useWebsocket);
        }
    }


    class StateMgr {

        public void checkState() {

            AbstractSettingPanel.State newState = isValid()
                    ? AbstractSettingPanel.State.VALID_STATE
                    : AbstractSettingPanel.State.INVALID_STATE;
            if (newState != state) {
                setState(newState);
            }
        }

        private boolean isValid() {

            // ラベルプリンタのip addressが設定されていない場合は、ラベルプリンタ不使用として
            // valid stateとする masuda
            if ("".equals(tf_lblPrtAddress.getText())) {
                return true;
            }

            boolean lblPrtAddrOk = !tf_lblPrtAddress.getText().trim().isEmpty();
            boolean lblPrtPortOk = !tf_lblPrtPort.getText().trim().isEmpty();

            return (lblPrtAddrOk && lblPrtPortOk);
        }
    }

    private boolean isValidIp(String val) {
        if (val == null) {
            return false;
        }
        if ("localhost".equals(val.toLowerCase())) {
            return true;
        }
        if (val.matches("[0-9][0-9.]+$")) {
            return true;
        }
        return false;
    }

    private boolean isValidPort(String val) {
        if (val == null) {
            return false;
        }
        if (val.matches("^[0-9]+$")) {
            return true;
        }
        return false;
    }

    private void discardInspectorSize() {
        Project.getUserDefaults().remove("chartPanelLeftSize");
        Project.getUserDefaults().remove("chartInspectorsSize");
        Project.getUserDefaults().remove("chartPanelRightSize");
    }
    
    private void loadProperties() throws Exception {
        
        Properties prop = Project.getUserDefaults();
        String userId = Project.getUserModel().getUserId();
        List<UserPropertyModel> list = MasudaDelegater.getInstance().getUserProperties(userId);
        if (list == null || list.isEmpty()) {
            return;
        }
        for (UserPropertyModel propModel : list) {
            prop.put(propModel.getKey(), propModel.getValue());
        }
    }
    
    private void saveProperties() throws Exception {
        
        // サーバーに保存する前にPropertiesを更新する
        getContext().saveOnly();
        
        List<UserPropertyModel> list = new ArrayList<>();
        Properties prop = Project.getUserDefaults();
        String idAsLocal = Project.getUserModel().idAsLocal();
        String userId = Project.getUserModel().getUserId();
        String facilityId = Project.getFacilityId();
        
        for (Map.Entry entry : prop.entrySet()) {
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            UserPropertyModel propModel = new UserPropertyModel();
            propModel.setKey(key);
            propModel.setValue(value);
            propModel.setFacilityId(facilityId);

            if (!propModel.isFacilityCommon(key)) {
                // ユーザー固有ならばuserId(=idAsLocal)を設定する
                propModel.setUserId(idAsLocal);
            } else {
                // 施設共通ならfacilityIdを設定する
                propModel.setUserId(facilityId);
            }
            list.add(propModel);
        }
        
        MasudaDelegater.getInstance().postUserProperties(userId, list);
    }
    
    private String colorToString(Color c) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(c.getRed())).append(",");
        sb.append(String.valueOf(c.getGreen())).append(",");
        sb.append(String.valueOf(c.getBlue())).append(",");
        sb.append(String.valueOf(c.getAlpha()));
        return sb.toString();
    }
    
    private Color stringToColor(String str) {
        Color c = DEFAULT_EVEN_COLOR;
        try {
            String[] values = str.split(",");
            int[] intValues = new int[values.length];
            for (int i = 0; i < values.length; ++i) {
                intValues[i] = Integer.valueOf(values[i]);
            }

            if (values.length == 3) {
                c = new Color(intValues[0], intValues[1], intValues[2]);
            } else if (values.length == 4) {
                c = new Color(intValues[0], intValues[1], intValues[2], intValues[3]);
            }
        } catch (NullPointerException | NumberFormatException e) {
        }
        return c;
    }
    
    private String[] getFontNames() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontNames =  ge.getAvailableFontFamilyNames();
        return fontNames;
    }
}
