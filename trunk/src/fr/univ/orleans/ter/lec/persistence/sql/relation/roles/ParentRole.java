package fr.univ.orleans.ter.lec.persistence.sql.relation.roles;

import java.util.List;

/**
 * ParentRole - Persistence layer
 * 
 * Simulates the role of a parent from an one-to-many SQL relationship.
 * 
 * The parent is the table that has the PK in it. It has many ChildRole.
 * 
 * @author AdrianSeredinschi
 * 
 */
public interface ParentRole extends SQLRole {

	public final Integer role = SQLRole.ROLE_PARENT;

	public abstract void addChild(String relationName, ChildRole child);

	public abstract List<ChildRole> getChilds(String relationName);

}
