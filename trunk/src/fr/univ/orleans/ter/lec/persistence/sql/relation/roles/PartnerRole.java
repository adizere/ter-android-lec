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
public interface PartnerRole extends SQLRole {

	public final Integer role = SQLRole.ROLE_PARTNER;

	public abstract void addPartner(String relName, PartnerRole pr);

	public abstract List<PartnerRole> getPartners(String relname);
}
