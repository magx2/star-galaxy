package star.galaxy.engine.metainformations

import kotlin.annotation.AnnotationTarget.*

@Target(CLASS, ANNOTATION_CLASS, TYPE_PARAMETER,
        PROPERTY, FIELD, LOCAL_VARIABLE,
        VALUE_PARAMETER, CONSTRUCTOR, FUNCTION,
        PROPERTY_GETTER, PROPERTY_SETTER, TYPE,
        EXPRESSION, FILE, TYPEALIAS)
@Retention(AnnotationRetention.SOURCE)
@MustBeDocumented
annotation class Meter

@Target(CLASS, ANNOTATION_CLASS, TYPE_PARAMETER,
        PROPERTY, FIELD, LOCAL_VARIABLE,
        VALUE_PARAMETER, CONSTRUCTOR, FUNCTION,
        PROPERTY_GETTER, PROPERTY_SETTER, TYPE,
        EXPRESSION, FILE, TYPEALIAS)
@Retention(AnnotationRetention.SOURCE)
@MustBeDocumented
annotation class Gram

@Target(CLASS, ANNOTATION_CLASS, TYPE_PARAMETER,
        PROPERTY, FIELD, LOCAL_VARIABLE,
        VALUE_PARAMETER, CONSTRUCTOR, FUNCTION,
        PROPERTY_GETTER, PROPERTY_SETTER, TYPE,
        EXPRESSION, FILE, TYPEALIAS)
@Retention(AnnotationRetention.SOURCE)
@MustBeDocumented
/**
 * 1 / g
 */
annotation class GramInverted

@Target(CLASS, ANNOTATION_CLASS, TYPE_PARAMETER,
        PROPERTY, FIELD, LOCAL_VARIABLE,
        VALUE_PARAMETER, CONSTRUCTOR, FUNCTION,
        PROPERTY_GETTER, PROPERTY_SETTER, TYPE,
        EXPRESSION, FILE, TYPEALIAS)
@Retention(AnnotationRetention.SOURCE)
@MustBeDocumented
annotation class Newton


@Target(CLASS, ANNOTATION_CLASS, TYPE_PARAMETER,
        PROPERTY, FIELD, LOCAL_VARIABLE,
        VALUE_PARAMETER, CONSTRUCTOR, FUNCTION,
        PROPERTY_GETTER, PROPERTY_SETTER, TYPE,
        EXPRESSION, FILE, TYPEALIAS)
@Retention(AnnotationRetention.SOURCE)
@MustBeDocumented
annotation class Second