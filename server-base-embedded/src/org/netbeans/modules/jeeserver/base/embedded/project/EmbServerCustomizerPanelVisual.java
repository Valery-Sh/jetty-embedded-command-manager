/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.modules.jeeserver.base.embedded.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.netbeans.api.project.Project;
import org.netbeans.modules.jeeserver.base.deployment.BaseDeploymentManager;
import org.netbeans.modules.jeeserver.base.deployment.ServerInstanceProperties;
import org.netbeans.modules.jeeserver.base.deployment.specifics.ServerSpecifics;
import org.netbeans.modules.jeeserver.base.embedded.utils.SuiteConstants;
import org.netbeans.modules.jeeserver.base.embedded.utils.SuiteUtil;
import org.netbeans.modules.jeeserver.base.deployment.utils.BaseUtils;
import org.netbeans.spi.project.ui.support.ProjectCustomizer.Category;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;

public class EmbServerCustomizerPanelVisual extends JPanel implements ActionListener {

    public static final String PROP_PROJECT_NAME = "projectName";
    //private final Project project;
    private final Lookup context;
    
    private final Properties settings;
    private final Category category;

    public EmbServerCustomizerPanelVisual(Lookup context, Category category) {
        initComponents();
        //this.project = BaseUtils.managerOf(context).getServerProject();
        this.context = context;
        this.category = category;
        this.settings = context.lookup(ServerInstanceProperties.class).getProperties();
        read();
        checkServerRunning();
        addListeners();
    }

    private void checkServerRunning() {
        BaseDeploymentManager manager = BaseUtils.managerOf(context);

        if (manager.isServerRunning()) {
            serverPort_Spinner.setEnabled(false);
            serverDebugPort_Spinner.setEnabled(false);
            shutdownPort_Spinner.setEnabled(false);
            incremental_Deployment_CheckBox.setEnabled(false);
            category.setErrorMessage(NbBundle.getMessage(EmbServerCustomizerPanelVisual.class, "MSG_NEED_STOP_SERVER"));
        }
    }

    private void addListeners() {
        JSpinner.NumberEditor me = (JSpinner.NumberEditor) serverPort_Spinner.getEditor();
        JFormattedTextField tf = me.getTextField();
        tf.getDocument().addDocumentListener(new PortHandler(serverPort_Spinner));

        me = (JSpinner.NumberEditor) serverDebugPort_Spinner.getEditor();
        tf = me.getTextField();
        tf.getDocument().addDocumentListener(new PortHandler(serverDebugPort_Spinner));

        if (needsShutdownPort()) {
            me = (JSpinner.NumberEditor) shutdownPort_Spinner.getEditor();
            tf = me.getTextField();
            tf.getDocument().addDocumentListener(new PortHandler(shutdownPort_Spinner));
        }
        incremental_Deployment_CheckBox.addActionListener(this);
    }

    public String getProjectName() {
        return this.projectNameTextField.getText();
    }

    ServerSpecifics getSpecifics() {
        String id = serverID_TextField.getText();
        BaseUtils.out("actualId=" + id);
        
        BaseUtils.out("aserverId=" + BaseUtils.getServerIdByAcualId(id));
        
        return BaseUtils.getServerSpecifics(BaseUtils.getServerIdByAcualId(id));
    }

    boolean needsShutdownPort() {
        return getSpecifics().needsShutdownPort();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        autoManualButtonGroup = new javax.swing.ButtonGroup();
        projectNameLabel = new javax.swing.JLabel();
        projectNameTextField = new javax.swing.JTextField();
        projectLocationLabel = new javax.swing.JLabel();
        projectLocationTextField = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        hostLabel = new javax.swing.JLabel();
        hostTextField = new javax.swing.JTextField();
        serverPortLabel = new javax.swing.JLabel();
        serverPort_Spinner = new javax.swing.JSpinner();
        serverDebugPortLabel = new javax.swing.JLabel();
        serverDebugPort_Spinner = new javax.swing.JSpinner();
        shutdownPort_Label = new javax.swing.JLabel();
        shutdownPort_Spinner = new javax.swing.JSpinner();
        incremental_Deployment_CheckBox = new javax.swing.JCheckBox();
        serverID_Label = new javax.swing.JLabel();
        serverID_TextField = new javax.swing.JTextField();

        org.openide.awt.Mnemonics.setLocalizedText(projectNameLabel, "Project Name:");

        projectNameTextField.setEditable(false);

        projectLocationLabel.setLabelFor(projectLocationTextField);
        org.openide.awt.Mnemonics.setLocalizedText(projectLocationLabel, "Project Location:");

        projectLocationTextField.setEditable(false);

        org.openide.awt.Mnemonics.setLocalizedText(hostLabel, "Host:");

        hostTextField.setEditable(false);
        hostTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hostTextFieldActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(serverPortLabel, "Server Port:");

        serverPort_Spinner.setEditor(new javax.swing.JSpinner.NumberEditor(serverPort_Spinner, "#"));

        org.openide.awt.Mnemonics.setLocalizedText(serverDebugPortLabel, "Debug Port:");

        serverDebugPort_Spinner.setEditor(new javax.swing.JSpinner.NumberEditor(serverDebugPort_Spinner, "#"));

        org.openide.awt.Mnemonics.setLocalizedText(shutdownPort_Label, "Shutdown Port:"); // NOI18N

        shutdownPort_Spinner.setEditor(new javax.swing.JSpinner.NumberEditor(shutdownPort_Spinner, "#"));

        incremental_Deployment_CheckBox.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(incremental_Deployment_CheckBox, "Supports Incremental Deployment"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(hostLabel)
                            .addComponent(serverPortLabel)
                            .addComponent(serverDebugPortLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(shutdownPort_Label))
                        .addGap(47, 47, 47)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(serverPort_Spinner, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hostTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(shutdownPort_Spinner, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(serverDebugPort_Spinner, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(incremental_Deployment_CheckBox)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hostTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hostLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(serverPort_Spinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(serverPortLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(serverDebugPort_Spinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(serverDebugPortLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(shutdownPort_Label)
                    .addComponent(shutdownPort_Spinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(incremental_Deployment_CheckBox)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Connection", jPanel1);

        org.openide.awt.Mnemonics.setLocalizedText(serverID_Label, "Server ID:"); // NOI18N

        serverID_TextField.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTabbedPane1)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(projectLocationLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                                .addComponent(projectNameLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(serverID_Label))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(projectLocationTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(projectNameTextField)
                            .addComponent(serverID_TextField))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(projectNameLabel)
                    .addComponent(projectNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(projectLocationLabel)
                    .addComponent(projectLocationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(serverID_Label)
                    .addComponent(serverID_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void hostTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hostTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hostTextFieldActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup autoManualButtonGroup;
    private javax.swing.JLabel hostLabel;
    private javax.swing.JTextField hostTextField;
    private javax.swing.JCheckBox incremental_Deployment_CheckBox;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel projectLocationLabel;
    private javax.swing.JTextField projectLocationTextField;
    private javax.swing.JLabel projectNameLabel;
    private javax.swing.JTextField projectNameTextField;
    private javax.swing.JLabel serverDebugPortLabel;
    private javax.swing.JSpinner serverDebugPort_Spinner;
    private javax.swing.JLabel serverID_Label;
    private javax.swing.JTextField serverID_TextField;
    private javax.swing.JLabel serverPortLabel;
    private javax.swing.JSpinner serverPort_Spinner;
    private javax.swing.JLabel shutdownPort_Label;
    private javax.swing.JSpinner shutdownPort_Spinner;
    // End of variables declaration//GEN-END:variables

    void store() {
        settings.setProperty(SuiteConstants.HTTP_PORT_PROP, getPort());
        settings.setProperty(SuiteConstants.DEBUG_PORT_PROP, getDebugPort());
        settings.setProperty(SuiteConstants.SHUTDOWN_PORT_PROP, getShutdownPort());
        settings.setProperty(SuiteConstants.SERVER_ID_PROP, getServerId());
        settings.setProperty(SuiteConstants.SERVER_ACTUAL_ID_PROP, getActualServerId());
        settings.setProperty(SuiteConstants.INCREMENTAL_DEPLOYMENT, isIncrementalDeployment());        
    }

    Properties getSettings() {
        return settings;
    }
    String isIncrementalDeployment() {
        return incremental_Deployment_CheckBox.isSelected() ?
                "true" : "false";
    }

    String getServerId() {
        String s = getActualServerId();
        return String.valueOf(BaseUtils.getServerIdByAcualId(s));
    }
    String getActualServerId() {
        return String.valueOf(serverID_TextField.getText());
    }
    String getPort() {
        return String.valueOf(serverPort_Spinner.getValue());
    }

    String getShutdownPort() {
        return String.valueOf(shutdownPort_Spinner.getValue());
    }

    String getDebugPort() {
        return String.valueOf(serverDebugPort_Spinner.getValue());
    }

    String getHost() {
        return this.hostTextField.getText().trim();
    }

    private void read() {
        Project project = BaseUtils.managerOf(context).getServerProject();
        
        projectLocationTextField.setText(project.getProjectDirectory().getPath());

        projectNameTextField.setText(project.getProjectDirectory().getName());

        hostTextField.setText(settings.getProperty(SuiteConstants.HOST_PROP));

        //OLDString serverId = settings.getProperty(SuiteConstants.SERVER_ID_PROP);
        String actualServerId = settings.getProperty(SuiteConstants.SERVER_ACTUAL_ID_PROP);        
        serverID_TextField.setText(actualServerId);

        String port = settings.getProperty(SuiteConstants.HTTP_PORT_PROP);
        serverPort_Spinner.setValue(Integer.parseInt(port));

        port = settings.getProperty(SuiteConstants.SHUTDOWN_PORT_PROP);
        if (port == null) {
            port = "-1";
        }
        shutdownPort_Spinner.setValue(Integer.parseInt(port));
        if (!needsShutdownPort()) {
            shutdownPort_Spinner.setVisible(false);
            shutdownPort_Label.setVisible(false);
        } else {
            shutdownPort_Spinner.setVisible(true);
            shutdownPort_Label.setVisible(true);
        }
        String incrDepl = settings.getProperty("incrementalDeployment");
        if ( incrDepl == null ) {
            incrDepl = "true";
        }
        if ( incrDepl.equals("true")) {
            incremental_Deployment_CheckBox.setSelected(true);
        } else {
            incremental_Deployment_CheckBox.setSelected(false);
        }

        port = settings.getProperty(SuiteConstants.DEBUG_PORT_PROP);
        serverDebugPort_Spinner.setValue(Integer.parseInt(port));
        initialValidatePorts();

    }

    void initialValidatePorts() {
        category.setValid(true);
        category.setErrorMessage(null);
        if (SuiteUtil.isHttpPortBusy_OLD(Integer.parseInt(getPort()), context)) {
            // Warning message do not use setValid
            category.setErrorMessage(NbBundle.getMessage(EmbServerCustomizerPanelVisual.class, "MSG_HTTP_PORT_IN_USE", getPort()));
        } else if (needsShutdownPort() && SuiteUtil.isShutdownPortBusy_OLD(Integer.parseInt(getShutdownPort()), context)) {
            category.setErrorMessage(NbBundle.getMessage(EmbServerCustomizerPanelVisual.class, "MSG_SHUTDOWN_PORT_IN_USE", getShutdownPort()));
        }
    }

    boolean isNumber(String value, String msg) {
        boolean result = true;
        try {
            Integer.parseInt(value);
        } catch (Exception e) {
            category.setValid(false);
            category.setErrorMessage(msg);
            result = false;
        }
        return result;

    }

    boolean isPositive(String value, String msg) {
        boolean result = true;
        if (Integer.parseInt(value) < 0) {
            category.setValid(false);
            category.setErrorMessage(msg);
            result = false;
        }
        return result;

    }

    void validatePorts(String port, String debugPort, String shutdownPort, JComponent c) {
        category.setValid(true);
        category.setErrorMessage(null);
        String msg = NbBundle.getMessage(EmbServerCustomizerPanelVisual.class, "MSG_HTTP_PORT_NOT_NUMBER", port);
        if (!isNumber(port, msg)) {
            return;
        }
        msg = NbBundle.getMessage(EmbServerCustomizerPanelVisual.class, "MSG_INVALID_PORT");
        if (!isPositive(port, msg)) {
            return;
        }
        
        msg = NbBundle.getMessage(EmbServerCustomizerPanelVisual.class, "MSG_DEBUG_PORT_NOT_NUMBER", debugPort);
        if (!isNumber(debugPort, msg)) {
            return;
        }
        msg = NbBundle.getMessage(EmbServerCustomizerPanelVisual.class, "MSG_INVALID_DEBUG_PORT");
        if (!isPositive(debugPort, msg)) {
            return;
        }
        msg = NbBundle.getMessage(EmbServerCustomizerPanelVisual.class, "MSG_SHUTDOWN_PORT_NOT_NUMBER", shutdownPort);
        if (needsShutdownPort()) {
            if (!isNumber(shutdownPort, msg)) {
                return;
            }
            msg = NbBundle.getMessage(EmbServerCustomizerPanelVisual.class, "MSG_INVALID_SHUTDOWN_PORT");
            if (!isPositive(shutdownPort, msg)) {
                return;
            }
        }
        
        if (SuiteUtil.isHttpPortBusy_OLD(Integer.parseInt(port), context)) {
            // Warning message do not use setValid
            category.setErrorMessage(NbBundle.getMessage(EmbServerCustomizerPanelVisual.class, "MSG_HTTP_PORT_IN_USE", port));
            return;
        }

        msg = NbBundle.getMessage(EmbServerCustomizerPanelVisual.class, "MSG_SHUTDOWN_PORT_IN_USE", shutdownPort);
        if (needsShutdownPort()) {
            if (SuiteUtil.isShutdownPortBusy_OLD(Integer.parseInt(shutdownPort), context)) {
                category.setErrorMessage(msg);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == incremental_Deployment_CheckBox) {
            //panel.fireChangeEvent();
            //readDefaultPortSettings(wiz);
        }

    }

    protected class PortHandler implements DocumentListener {

        private JSpinner spinner;

        public PortHandler(JSpinner spinner) {
            this.spinner = spinner;
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            doValidate();

        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            doValidate();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            doValidate();
        }
        
        private void doValidate() {
            JSpinner.NumberEditor me = (JSpinner.NumberEditor) spinner.getEditor();
            JFormattedTextField tf = me.getTextField();
            
            if (tf.hasFocus()) {
                if (spinner == serverPort_Spinner) {
                    validatePorts(tf.getText().trim(), getDebugPort(), getShutdownPort(), spinner);
                } else if (spinner == serverDebugPort_Spinner) {
                    validatePorts(getPort(), tf.getText().trim(), getShutdownPort(), spinner);
                } else if (spinner == shutdownPort_Spinner) {
                    validatePorts(getPort(), getDebugPort(), tf.getText().trim(), spinner);
                }
            } else {
                validatePorts(getPort(), getDebugPort(), getShutdownPort(), spinner);
            }
        }
        
    }
}//class
