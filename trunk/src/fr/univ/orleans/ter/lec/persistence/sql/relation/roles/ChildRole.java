package fr.univ.orleans.ter.lec.persistence.sql.relation.roles;

/**
 * ChildRole - Persistence layer
 * 
 * Simulates the role of an child in an one-to-many relation.
 * 
 * The child is the table with a FK in it, which should correspond with the PK
 * of an Parent table.
 * 
 * @author AdrianSeredinschi
 * 
 */
public interface ChildRole extends SQLRole {

	public final Integer role = SQLRole.ROLE_CHILD;

	public abstract void setParent(String relName, ParentRole pr);

	public abstract ParentRole getParent(String relName);

	public abstract Long getParentIdentity(String relName);
}
