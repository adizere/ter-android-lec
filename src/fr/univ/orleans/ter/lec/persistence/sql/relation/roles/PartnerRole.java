package fr.univ.orleans.ter.lec.persistence.sql.relation.roles;

import java.util.List;

/**
 * PartnerRole - Persistence layer
 * 
 * Has the purpose of simulating a row from a table that has a many-to-many
 * relation with another table (throgh an intermediary one).
 * 
 * @author AdrianSeredinschi
 *
 */
public abstract class PartnerRole implements SQLRole {

	public abstract void addPartner(String relName, PartnerRole pr);

	public abstract List<PartnerRole> getPartners(String relname);

	public Integer getRole() {
		return SQLRole.ROLE_PARTNER;
	}

}
