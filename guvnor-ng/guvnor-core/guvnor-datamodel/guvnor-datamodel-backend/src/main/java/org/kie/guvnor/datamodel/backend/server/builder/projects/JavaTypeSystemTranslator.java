package org.kie.guvnor.datamodel.backend.server.builder.projects;

import org.drools.guvnor.models.commons.shared.oracle.DataType;
import org.kie.guvnor.datamodel.model.ClassToGenericClassConverter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;

/**
 * Translates Java's Type System to Guvnor's Type System
 */
public class JavaTypeSystemTranslator implements ClassToGenericClassConverter {

    //Convert Java's Type system into a the portable Type system used by Guvnor (that is GWT friendly)
    @Override
    public String translateClassToGenericType( final Class<?> type ) {
        String fieldType = null; // if null, will use standard operators
        if ( type != null ) {
            if ( type.isPrimitive() ) {
                if ( type == byte.class ) {
                    fieldType = DataType.TYPE_NUMERIC_BYTE;
                } else if ( type == double.class ) {
                    fieldType = DataType.TYPE_NUMERIC_DOUBLE;
                } else if ( type == float.class ) {
                    fieldType = DataType.TYPE_NUMERIC_FLOAT;
                } else if ( type == int.class ) {
                    fieldType = DataType.TYPE_NUMERIC_INTEGER;
                } else if ( type == long.class ) {
                    fieldType = DataType.TYPE_NUMERIC_LONG;
                } else if ( type == short.class ) {
                    fieldType = DataType.TYPE_NUMERIC_SHORT;
                } else if ( type == boolean.class ) {
                    fieldType = DataType.TYPE_BOOLEAN;
                } else if(type==char.class) {
                    fieldType = DataType.TYPE_STRING;
                }
            } else if ( BigDecimal.class.isAssignableFrom( type ) ) {
                fieldType = DataType.TYPE_NUMERIC_BIGDECIMAL;
            } else if ( BigInteger.class.isAssignableFrom( type ) ) {
                fieldType = DataType.TYPE_NUMERIC_BIGINTEGER;
            } else if ( Byte.class.isAssignableFrom( type ) ) {
                fieldType = DataType.TYPE_NUMERIC_BYTE;
            } else if ( Double.class.isAssignableFrom( type ) ) {
                fieldType = DataType.TYPE_NUMERIC_DOUBLE;
            } else if ( Float.class.isAssignableFrom( type ) ) {
                fieldType = DataType.TYPE_NUMERIC_FLOAT;
            } else if ( Integer.class.isAssignableFrom( type ) ) {
                fieldType = DataType.TYPE_NUMERIC_INTEGER;
            } else if ( Long.class.isAssignableFrom( type ) ) {
                fieldType = DataType.TYPE_NUMERIC_LONG;
            } else if ( Short.class.isAssignableFrom( type ) ) {
                fieldType = DataType.TYPE_NUMERIC_SHORT;
            } else if ( Boolean.class.isAssignableFrom( type ) ) {
                fieldType = DataType.TYPE_BOOLEAN;
            } else if ( String.class.isAssignableFrom( type ) ) {
                fieldType = DataType.TYPE_STRING;
            } else if ( Collection.class.isAssignableFrom( type ) ) {
                fieldType = DataType.TYPE_COLLECTION;
            } else if ( Date.class.isAssignableFrom( type ) ) {
                fieldType = DataType.TYPE_DATE;
            } else if ( Comparable.class.isAssignableFrom( type ) ) {
                fieldType = DataType.TYPE_COMPARABLE;
            } else {
                fieldType = type.getName();
            }
        }
        return fieldType;
    }

}
