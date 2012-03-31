package fr.univ.orleans.ter.lec.persistence.sql.relation.roles;

import fr.univ.orleans.ter.lec.persistence.sql.relation.ManyToMany;
import fr.univ.orleans.ter.lec.persistence.sql.relation.OneToMany;

/**
 * SQLRole - Persistence layer
 * 
 * Raw base class used to simulate a class that is part of an SQLRelation, be it
 *  {@link OneToMany} or {@link ManyToMany}
 * 
 * The roles have the purpose to help us reconstruct the relationship types
 * between tables.
 * 
 * @author AdrianSeredinschi
 *
 */
public interface SQLRole {
	
	/*
	 * Role identity definitions for One To Many modeling:
	 * Child is the table with FK in it.
	 * Parent is the referenced table.
	 */
	public static final Integer ROLE_PARENT = 1;
	public static final Integer ROLE_CHILD = 2;
	
	/*
	 * Role identities for Many To Many modeling:
	 * Partners are the main tables, containing the mapped rows.
	 * Intermediate is the many-to-many table, containing FK for the two partners.
	 *   - No actual distinction needs to be made at this level between the two partners.
	 */
	public static final Integer ROLE_PARTNER = 3;
	public static final Integer ROLE_INTERMEDIATE = 4;
}
