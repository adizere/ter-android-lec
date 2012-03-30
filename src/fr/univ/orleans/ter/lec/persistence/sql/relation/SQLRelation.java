package fr.univ.orleans.ter.lec.persistence.sql.relation;

public interface SQLRelation {

	public static final Integer RELATION_ONE_TO_MANY = 1;
	public static final Integer RELATION_MANY_TO_MANY = 2;
	
	public Integer getRelationType();
	public String getRelationName();
}
