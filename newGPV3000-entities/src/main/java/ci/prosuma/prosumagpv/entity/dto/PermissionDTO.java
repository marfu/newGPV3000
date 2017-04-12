/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.prosuma.prosumagpv.entity.dto;

import ci.prosuma.prosumagpv.entity.security.Permission;
import java.util.List;

/**
 *
 * @author marfu
 */
public class PermissionDTO {
  
    private long permissionId;

    private String name;

    private List<Permission> menuChildren;

    public long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(long permissionId) {
        this.permissionId = permissionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Permission> getMenuChildren() {
        return menuChildren;
    }

    public void setMenuChildren(List<Permission> menuChildren) {
        this.menuChildren = menuChildren;
    }
    
    
    
    
}
