package ci.prosuma.prosumagpv.entity.security;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ROLE")
public class Role implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@Column(name = "ROLE_PK")
	private String designation;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "param_secu_liaison_role_permission", joinColumns = @JoinColumn(name = "ROLE_FK", referencedColumnName = "ROLE_PK"), inverseJoinColumns = @JoinColumn(name = "permissionId", referencedColumnName = "permissionId"))
	private List<Permission> permission;


	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Role other = (Role) obj;
		if (other == null || other.getDesignation() == null
				|| getDesignation() == null)
			return false;

		if (!getDesignation().equals(other.getDesignation())) {
			return false;
		}
		return true;
	}

	
	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public List<Permission> getPermission() {
		return permission;
	}

	public void setPermission(List<Permission> permission) {
		this.permission = permission;
	}


}
