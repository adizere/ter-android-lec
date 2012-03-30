package fr.univ.orleans.ter.lec.persistence.sql.relation.roles;

import java.util.List;

/**
 * ParentRole - Persistence layer
 * 
 * Simulates the role of a parent from an one-to-many SQL relationship.
 * 
 * The parent is the table that has the PK in it.
 * It has many ChildRole.
 * 
 * @author AdrianSeredinschi
 *
 */
public abstract class ParentRole implements SQLRole {

	public abstract void addChild(String relationName);

	public abstract List<ChildRole> getChilds(String relationName);

	public Integer getRole() {
		return SQLRole.ROLE_PARENT;
	}

}
