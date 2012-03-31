package fr.univ.orleans.ter.lec.persistence.sql.relation.roles;

/**
 * IntermediateRole - Persistence layer
 * 
 * An class extending IntermediateRole has the purpose of linking two
 * PartnerRole tables. It acts as the mapping table in a many-to-many
 * relationship.
 * 
 * @author AdrianSeredinschi
 * 
 */
public interface IntermediateRole extends SQLRole {
	
	public final Integer role = SQLRole.ROLE_INTERMEDIATE;

	public abstract Long getLeftPartnerIdentity(String relName);

	public abstract Long getRightPartnerIdentity(String relName);

}
