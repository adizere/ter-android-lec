package fr.univ.orleans.ter.lec.persistence.sql.relation.roles;

/**
 * IntermediateRole - Persistence layer
 * 
 * An class extending IntermediateRole has the purpose of
 * linking two PartnerRole tables. It acts as the mapping
 * table in a many-to-many relationship.
 * 
 * @author AdrianSeredinschi
 *
 */
public abstract class IntermediateRole implements SQLRole {

	public abstract Long getLeftPartnerIdentity(String relName);

	public abstract Long getRightPartnerIdentity(String relName);

	public Integer getRole() {
		return SQLRole.ROLE_INTERMEDIATE;
	}

}
