package ci.prosumagpv.web.bean.security;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import ci.prosuma.prosumagpv.entity.security.Permission;
import ci.prosuma.prosumagpv.metier.service.ISecurityService;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.primefaces.sentinel.Document;

@Named("permissionBean")
@ViewScoped
public class PermissionBean implements Serializable {

    private static final long serialVersionUID = -4010334200813729925L;

    @EJB
    ISecurityService securityService;

    @SuppressWarnings("unused")
    private List<Permission> perms;

    private Permission selectedPerm;
    private TreeNode perms2;
    private String codeProfile;

    public String getCodeProfile() {
        return codeProfile;
    }

    public void setCodeProfile(String codeProfile) {
        this.codeProfile = codeProfile;
    }

    public TreeNode getPerms2() {
        
        
        return perms2;
    }

    public void setPerms2(TreeNode perms2) {
        this.perms2 = perms2;
    }

    public PermissionBean() {
        selectedPerm = new Permission();
    }

    public String detailPerm() {
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("permId");
        if (id != null && !id.trim().equals("")) {
            System.out.println("PERM ID : " + id);
            selectedPerm = securityService.getPermissionsById(Long.parseLong(id));
            return "";
        } else {
            return "";
        }
    }

    public void newPerm(ActionEvent actionEvent) {
        selectedPerm = new Permission();
    }

    public void savePerm() {

        selectedPerm = securityService.createPermission(selectedPerm);
        perms = securityService.getAllPermissions();
        selectedPerm = new Permission();

    }

    public List<Permission> getPerms() {
        // System.out.println("xxxxxxxxx");
        return securityService.getAllPermissions();

    }

    public void setPerms(List<Permission> perms) {
        this.perms = perms;
    }

    public Permission getSelectedPerm() {
        return selectedPerm;
    }

    public void setSelectedPerm(Permission selectedPerm) {
        this.selectedPerm = selectedPerm;
    }

    //node tree select
    public void onNodeExpand(NodeExpandEvent event) {
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Expanded", event.getTreeNode().toString());
//        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void onNodeCollapse(NodeCollapseEvent event) {
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Collapsed", event.getTreeNode().toString());
//        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void onNodeSelect(NodeSelectEvent event) {
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", event.getTreeNode().toString());
//        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void onNodeUnselect(NodeUnselectEvent event) {
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Unselected", event.getTreeNode().toString());
//        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    
     private TreeNode rootNode;

     @PostConstruct
     public void init() {
         Permission root = new Permission(); // instead get root object from database 
         rootNode = newNodeWithChildren(root, null);
     }
    
    public TreeNode newNodeWithChildren(Permission ttParent, TreeNode parent){
          TreeNode newNode= new DefaultTreeNode(ttParent, parent);
//          for (Permission tt : ttParent.getChildren()){
//               TreeNode newNode2= newNodeWithChildren(tt, newNode);
//          }
          return newNode;
     }

     public TreeNode getRootNode() {
         return rootNode;
     }

     public void setRootNode(TreeNode node) {
         rootNode = node;
     }

}
