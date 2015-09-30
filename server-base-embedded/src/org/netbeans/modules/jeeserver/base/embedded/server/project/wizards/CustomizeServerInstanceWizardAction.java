/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.modules.jeeserver.base.embedded.server.project.wizards;

import java.io.File;
import java.util.Properties;
import org.netbeans.modules.j2ee.deployment.plugins.api.InstanceProperties;
import org.netbeans.modules.jeeserver.base.deployment.ServerInstanceProperties;
import org.netbeans.modules.jeeserver.base.deployment.specifics.InstanceBuilder;
import org.netbeans.modules.jeeserver.base.deployment.specifics.ServerSpecifics;
import org.netbeans.modules.jeeserver.base.deployment.utils.BaseConstants;
import org.netbeans.modules.jeeserver.base.embedded.server.project.ServerSuiteManager;
import org.netbeans.modules.jeeserver.base.embedded.utils.SuiteConstants;
import org.openide.WizardDescriptor;
import org.openide.filesystems.FileObject;
import org.openide.util.Lookup;

/**
 *
 * @author Valery
 */
public class CustomizeServerInstanceWizardAction extends ExistingServerInstanceWizardAction{

    public CustomizeServerInstanceWizardAction(Lookup context, File instanceProjectDir) {
        super(context, instanceProjectDir);
    }
    @Override
    protected InstanceBuilder getBuilder(ServerSpecifics specifics, Properties props) {
        return (EmbeddedInstanceBuilder) specifics.getInstanceBuilder(props, InstanceBuilder.Options.CUSTOMIZER);
    }
    @Override
    protected FileObject gerServerInstancesDir(Lookup context) {
        String uri = context.lookup(ServerInstanceProperties.class).getUri();
        return ServerSuiteManager.getServerInstancesDir(uri);
    }
    
    @Override
    protected void fillWizardDescriptor(WizardDescriptor wiz) {

//        wiz.setButtonListener(new ButtonListener(this));

        String uri = context.lookup(ServerInstanceProperties.class).getUri();
        InstanceProperties ip = InstanceProperties.getInstanceProperties(uri);
        wiz.putProperty(BaseConstants.URL_PROP, uri);        
        wiz.putProperty(BaseConstants.DISPLAY_NAME_PROP, ip.getProperty(BaseConstants.DISPLAY_NAME_PROP));
        wiz.putProperty(BaseConstants.HOME_DIR_PROP, ip.getProperty(BaseConstants.HOME_DIR_PROP));
        wiz.putProperty(BaseConstants.HOST_PROP, ip.getProperty(BaseConstants.HOST_PROP));
        wiz.putProperty(BaseConstants.HTTP_PORT_PROP, ip.getProperty(BaseConstants.HTTP_PORT_PROP));
        wiz.putProperty(BaseConstants.DEBUG_PORT_PROP, ip.getProperty(BaseConstants.DEBUG_PORT_PROP));
        wiz.putProperty(BaseConstants.SHUTDOWN_PORT_PROP, ip.getProperty(BaseConstants.SHUTDOWN_PORT_PROP));
        wiz.putProperty(BaseConstants.SERVER_ID_PROP, ip.getProperty(BaseConstants.SERVER_ID_PROP));
        wiz.putProperty("projdir", new File(ip.getProperty(BaseConstants.SERVER_LOCATION_PROP)));
        wiz.putProperty(SuiteConstants.SUITE_PROJECT_LOCATION, new File(ip.getProperty(SuiteConstants.SUITE_PROJECT_LOCATION)));

    }
    
    
}
