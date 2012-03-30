package fr.univ.orleans.ter.lec.persistence.sql.relation.roles;

/**
 * ChildRole - Persistence layer
 * 
 * Simulates the role of an child in an one-to-many relation.
 * 
 * The child is the table with a FK in it, which should correspond
 * with the PK of an Parent table.
 * 
 * @author AdrianSeredinschi
 *
 */
public abstract class ChildRole implements SQLRole {

	public abstract void addParent(String relName);

	public abstract ParentRole getParent(String relName);

	public abstract Long getParentIdentity(String relName);

	public Integer getRole() {
		return SQLRole.ROLE_CHILD;
	}

}
