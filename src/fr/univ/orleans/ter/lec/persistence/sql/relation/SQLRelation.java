package fr.univ.orleans.ter.lec.persistence.sql.relation;

public interface SQLRelation {

	public static final Integer RELTYPE_ONE_TO_MANY = 1;
	public static final Integer RELTYPE_MANY_TO_MANY = 2;

	/*
	 * The relation names defined inside table_definitions.xml are also kept
	 * here to help Model classes have access to them from a central point.
	 * 
	 * Note: Has to be keept in sync with the relation-name attribute from the
	 * table_definitions.xml .
	 */
	public static final String RELNAME_LANGUAGES_LCOMPONENTS = "languages_lcomponents";
	public static final String RELNAME_LANGUAGES_TAGS = "languages_tags";
	public static final String RELNAME_LANGUAGES_METHODS = "languages_methods";

	public static final String RELNAME_LEVELS_LANGUAGE = "levels_language";

	public static final String RELNAME_LEVELS_METHOD = "levels_method";

	public static final String RELNAME_EXERCISES_LEVEL = "exercises_level";
	public static final String RELNAME_CHOICES_EXERCISE = "choices_exercise";

	public Integer getRelationType();

	public String getRelationName();
}
