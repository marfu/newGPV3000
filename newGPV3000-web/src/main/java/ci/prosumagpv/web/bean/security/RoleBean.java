package ci.prosumagpv.web.bean.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.model.DualListModel;

import ci.prosuma.prosumagpv.entity.security.Permission;
import ci.prosuma.prosumagpv.entity.security.Role;
import ci.prosuma.prosumagpv.metier.service.ISecurityService;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.TreeNode;
import org.primefaces.sentinel.Document;

@Named("roleBean")
@ViewScoped
public class RoleBean implements Serializable {
    
    private static final long serialVersionUID = -4010334200813729925L;
    
    @EJB
    ISecurityService securityService;
    
    @SuppressWarnings("unused")
    private List<Role> roles;
    
    private Role selectedRole;
    
    private DualListModel<Permission> perms;
    private List<Permission> source;
    private List<Permission> target;
    
    private TreeNode[] selectedNodes1;
    
    private TreeNode root1;
    private TreeNode selectedNode;
    
    public RoleBean() {
        selectedRole = new Role();
        source = new ArrayList<>();
        target = new ArrayList<>();
        perms = new DualListModel<>(source, target);
        
    }

    //@PostConstruct
//    public void init() {
//
//        TreeNode root = new CheckboxTreeNode(new Document("Files", "-", "Folder"), null);
//
//        List<Permission> listParentsPermission = securityService.getAllParentsPermissions();
//
//        for (Permission tt : listParentsPermission) {
//            System.out.println(tt.getPermissionId());
//           // TreeNode documents = new CheckboxTreeNode(tt, root);
//      }
//
////       
////        TreeNode pictures = new CheckboxTreeNode(new Document("Pictures", "-", "Folder"), root);
////        TreeNode movies = new CheckboxTreeNode(new Document("Movies", "-", "Folder"), root);
////
////        TreeNode work = new CheckboxTreeNode(new Document("Work", "-", "Folder"), documents);
////        TreeNode primefaces = new CheckboxTreeNode(new Document("PrimeFaces", "-", "Folder"), documents);
////
////        //Documents
////        TreeNode expenses = new CheckboxTreeNode("document", new Document("Expenses.doc", "30 KB", "Word Document"), work);
////        TreeNode resume = new CheckboxTreeNode("document", new Document("Resume.doc", "10 KB", "Word Document"), work);
////        TreeNode refdoc = new CheckboxTreeNode("document", new Document("RefDoc.pages", "40 KB", "Pages Document"), primefaces);
////
////        //Pictures
////        TreeNode barca = new CheckboxTreeNode("picture", new Document("barcelona.jpg", "30 KB", "JPEG Image"), pictures);
////        TreeNode primelogo = new CheckboxTreeNode("picture", new Document("logo.jpg", "45 KB", "JPEG Image"), pictures);
////        TreeNode optimus = new CheckboxTreeNode("picture", new Document("optimusprime.png", "96 KB", "PNG Image"), pictures);
////
////        //Movies
////        TreeNode pacino = new CheckboxTreeNode(new Document("Al Pacino", "-", "Folder"), movies);
////        TreeNode deniro = new CheckboxTreeNode(new Document("Robert De Niro", "-", "Folder"), movies);
////
////        
//        root1 = root;
//        // selectedNodes1[0]=root2;
//    }
    public void newRole(ActionEvent actionEvent) {
        clearEntity();
        
    }
    
    public String detailRole() {
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("roleId");
        if (id != null && !id.trim().equals("")) {
            System.out.println("ROLE ID : " + id);
            selectedRole = securityService.getRoleById(id);
            System.out.println("SELECTED ROLE : " + selectedRole);
            source.clear();
            target.clear();
            
            source = securityService.getAllPermissions();
            
            if (selectedRole.getPermission() != null) {
                source.removeAll(selectedRole.getPermission());
                target.addAll(selectedRole.getPermission());
            }
            
            perms = new DualListModel<>(source, target);
            return "";
        } else {
            return "";
        }
    }
    
    private void clearEntity() {
        selectedRole = new Role();
        target = new ArrayList<>();
        source = securityService.getAllPermissions();
        perms = new DualListModel<>(source, target);
    }
    
    public void saveRole(TreeNode[] nodes) {
         List<Permission> listPerm=new ArrayList<Permission>();
        if(nodes != null && nodes.length > 0) {
            StringBuilder builder = new StringBuilder();
           
 
            for(TreeNode node : nodes) {
                 Permission permis =(Permission)( node.getData());
                //builder.append(node.getData().getClass());
                listPerm.add(permis);
                 System.out.println(permis.getPermissionId( ));
                // builder.append("<br />");
            }
 
         
        }
        
      
        selectedRole.setPermission(listPerm);
        selectedRole = securityService.createRole(selectedRole);
        roles = securityService.getAllRoles();
        clearEntity();
        
    }
    
    public List<Role> getRoles() {
        return securityService.getAllRoles();
    }
    
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    
    public Role getSelectedRole() {
        return selectedRole;
    }
    
    public void setSelectedRole(Role selectedRole) {
        this.selectedRole = selectedRole;
    }
    
    public DualListModel<Permission> getPerms() {
        return perms;
    }
    
    public void setPerms(DualListModel<Permission> perms) {
        this.perms = perms;
    }
    
    public TreeNode[] getSelectedNodes1() {
        return selectedNodes1;
    }
    
    public void setSelectedNodes1(TreeNode[] selectedNodes1) {
        this.selectedNodes1 = selectedNodes1;
    }
    
    public TreeNode getRoot1() {
        
        TreeNode root = new CheckboxTreeNode(new Document("Files", "-", "Folder"), null);
        
        List<Permission> listParentsPermission = securityService.getAllParentsPermissions();
        if (listParentsPermission.isEmpty()) {
            System.out.println("Vide");
            
        } else {
            for (Permission tt : listParentsPermission) {
                //System.out.println("xxxxxxxxxxxxx");
                TreeNode parent = new CheckboxTreeNode(tt, root);
                
                List<Permission> listChildPermission = securityService.getAllChildsPermissions(tt.getPermissionId());
                if (listChildPermission.isEmpty()) {
                    
                } else {
                    for (Permission ttChilds : listChildPermission) {
                        
                        TreeNode child = new CheckboxTreeNode(ttChilds, parent);
                        
                        List<Permission> listChild1Permission = securityService.getAllChildsPermissions(ttChilds.getPermissionId());
                        if (listChild1Permission.isEmpty()) {
                            
                        } else {
                            for (Permission ttChilds1 : listChild1Permission) {
                                TreeNode child1 = new CheckboxTreeNode(ttChilds1, child);
                                
                                List<Permission> listChild2Permission = securityService.getAllChildsPermissions(ttChilds1.getPermissionId());
                                if (listChild2Permission.isEmpty()) {
                                    //System.out.println("Vide 2 fois");
                                } else {
                                    for (Permission ttChilds2 : listChild2Permission) {
                                        //System.out.println("xxxxxxxxxxxxx");
                                        TreeNode child2 = new CheckboxTreeNode(ttChilds2, child1);
                                        
                                    }
                                }
                                
                            }
                        }
                        
                    }
                }
            }
        }
        
        root1 = root;
        return root1;
    }
    
    public void setRoot1(TreeNode root1) {
        this.root1 = root1;
    }

    //node tree select
    public void onNodeExpand(NodeExpandEvent event) {
        System.out.println("xxxxxxxxxxx");
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Expanded", event.getTreeNode().toString());
//        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void onNodeCollapse(NodeCollapseEvent event) {
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Collapsed", event.getTreeNode().toString());
//        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void onNodeSelect(NodeSelectEvent event) {
        System.out.println(selectedNodes1.length);
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", event.getTreeNode().toString());
//        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void onNodeUnselect(NodeUnselectEvent event) {
        System.out.println("xxxxxxxxxxx");
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Unselected", event.getTreeNode().toString());
//        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
}
