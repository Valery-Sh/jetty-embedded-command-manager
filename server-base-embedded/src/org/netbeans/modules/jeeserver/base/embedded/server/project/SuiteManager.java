package org.netbeans.modules.jeeserver.base.embedded.server.project;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.deploy.shared.factories.DeploymentFactoryManager;
import javax.enterprise.deploy.spi.exceptions.DeploymentManagerCreationException;
import org.netbeans.api.project.FileOwnerQuery;
import org.netbeans.api.project.Project;
import org.netbeans.modules.j2ee.deployment.devmodules.api.Deployment;
import org.netbeans.modules.j2ee.deployment.plugins.api.InstanceProperties;
import org.netbeans.modules.jeeserver.base.deployment.BaseDeploymentManager;
import org.netbeans.modules.jeeserver.base.deployment.utils.BaseUtils;
import org.netbeans.modules.jeeserver.base.embedded.server.project.nodes.ChildrenNotifier;
import org.netbeans.modules.jeeserver.base.embedded.server.project.nodes.SuiteNotifier;
import org.netbeans.modules.jeeserver.base.embedded.utils.SuiteConstants;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Lookup;

/**
 *
 * @author V. Shyshkin
 */
public class SuiteManager {

    private static final Logger LOG = Logger.getLogger(BaseUtils.class.getName());

    public static BaseDeploymentManager getManager(String uri) {
        BaseDeploymentManager dm = null;
        try {
            dm = (BaseDeploymentManager) DeploymentFactoryManager.getInstance().getDisconnectedDeploymentManager(uri);
        } catch (DeploymentManagerCreationException ex) {
            LOG.log(Level.INFO, ex.getMessage());
        }
        return dm;

    }

    public static BaseDeploymentManager getManager(Project serverInstance) {
        return BaseUtils.managerOf(serverInstance);
    }

    public static List<String> getServerInstanceIds(Project serverSuite) {

        BaseDeploymentManager dm = null;

        if (serverSuite == null || serverSuite.getProjectDirectory() == null) {
            return null;
        }
        Path suitePath = Paths.get(serverSuite.getProjectDirectory().getPath());
        Deployment d = Deployment.getDefault();

        if (d == null || d.getServerInstanceIDs() == null) {
            return null;
        }
        List<String> result = new ArrayList<>();
        for (String uri : d.getServerInstanceIDs()) {
            InstanceProperties ip = InstanceProperties.getInstanceProperties(uri);

            String foundSuiteLocation = ip.getProperty(SuiteConstants.SUITE_PROJECT_LOCATION);
            if (foundSuiteLocation == null || !new File(foundSuiteLocation).exists()) {
                // May be not a native plugin server
                continue;
            }
            Project foundSuite = FileOwnerQuery.getOwner(FileUtil.toFileObject(new File(foundSuiteLocation)));

            if (foundSuite == null) {
                continue;
            }
            Path p = Paths.get(foundSuiteLocation);

            if (suitePath.equals(p)) {
                result.add(uri);
            }
        }
        return result;
    }

/*    public static Lookup getServerInstanceLookup(String uri) {
        SuiteNotifier snm = getServerSuiteProject(uri).getLookup().lookup(SuiteNotifier.class);
        Lookup lk = snm.getServerInstanceLookup(uri);
        if ( lk != null ) {
            return lk;
        }
        return null;
    }
*/    
    public static Project getServerSuiteProject(String uri) {

        InstanceProperties ip = InstanceProperties.getInstanceProperties(uri);
        String suiteLocation;
        if (ip != null) {
            suiteLocation = ip.getProperty(SuiteConstants.SUITE_PROJECT_LOCATION);
        } else {
            // extract from url
            String s = SuiteConstants.SUITE_URL_ID; //":server:suite:project:";
            int i = uri.indexOf(s);
            suiteLocation = uri.substring(i + s.length());
        }

        if (suiteLocation == null || !new File(suiteLocation).exists()) {
            // May be not a native plugin server
            return null;
        }
        return FileOwnerQuery.getOwner(FileUtil.toFileObject(new File(suiteLocation)));

    }
    
    public static void removeInstance(String uri) {

        InstanceProperties.removeInstance(uri);
        
        SuiteManager.getServerSuiteProject(uri)
                .getLookup()
                .lookup(SuiteNotifier.class)
                .instancesChanged();
    }
    public static FileObject getServerInstancesDir(String uri) {

        return SuiteManager.getServerSuiteProject(uri)
                .getProjectDirectory()
                .getFileObject(SuiteConstants.SERVER_INSTANCES_FOLDER);
    }

}