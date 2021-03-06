package open.dolphin.client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import open.dolphin.delegater.MasudaDelegater;
import open.dolphin.infomodel.ModuleModel;
import open.dolphin.infomodel.RoutineMedModel;

/**
 * 処方歴登録クラス
 * 
 * @author masuda, Masuda Naika
 */
public class RoutineMedDialog {
    
    private String memo;
    private List<StampHolder> shList;
    private JDialog dialog;
    private Frame parent;
    
    public void showDialog(List<StampHolder> list, Frame parent) {
        
        memo = null;
        shList = list;
        this.parent = parent;
        
        initComponents();

        dialog.setVisible(true);
        
        if (memo != null) {
            regist();
        }
        dialog.dispose();
    }

    private void initComponents() {

        dialog = new JDialog(parent);
        dialog.setModal(true);
        String title = ClientContext.getFrameTitle("個人薬歴登録");
        dialog.setTitle(title);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JLabel lbl = new JLabel("メモを入力してください。");
        panel.add(lbl, BorderLayout.NORTH);
        final JTextArea ta = new JTextArea(3,30);
        ta.setLineWrap(true);
        ta.addFocusListener(AutoKanjiListener.getInstance());
        ta.setBorder(BorderFactory.createEtchedBorder());
        panel.add(ta, BorderLayout.CENTER);
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton btnCancel = new JButton("取消");
        btnCancel.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
               dialog.setVisible(false);
            }
        });
        
        btnPanel.add(btnCancel);
        JButton btnOK = new JButton("保存");
        btnOK.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                memo = ta.getText();
                dialog.setVisible(false);
            }
        });
        btnPanel.add(btnOK);
        
        panel.add(btnPanel, BorderLayout.SOUTH);
        dialog.setContentPane(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(parent);
    }
    
    private void regist() {
        
        ModuleModel stamp1 = shList.get(0).getStamp();
        Date d = stamp1.getFirstConfirmed();
        long karteId = stamp1.getKarteBean().getId();
        
        //List<ModuleModel> mmList = new ArrayList<ModuleModel>();
        boolean first = true;
        StringBuilder sb = new StringBuilder();
        for (StampHolder sh : shList) {
            //mmList.add(sh.getStamp());
            if (!first) {
                sb.append(",");
            } else {
                first = false;
            }
            sb.append(String.valueOf(sh.getStamp().getId()));
        }
        String moduleIds = sb.toString();
        
        final RoutineMedModel model = new RoutineMedModel();
        model.setRegistDate(d);
        model.setKarteId(karteId);
        //model.setModuleList(mmList);
        model.setModuleIds(moduleIds);
        model.setMemo(memo);
        
        SwingWorker worker = new SwingWorker<Void, Void>() {

            @Override
            protected Void doInBackground() throws Exception {
                MasudaDelegater del = MasudaDelegater.getInstance();
                del.addRoutineMedModel(model);
                return null;
            }
        };
        worker.execute();
    }
    
}
