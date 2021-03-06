/*
 * Copyright 2010 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kie.guvnor.datamodel.backend.server;

import org.junit.Test;
import org.kie.guvnor.datamodel.backend.server.builder.packages.PackageDataModelOracleBuilder;
import org.kie.guvnor.datamodel.backend.server.builder.projects.ProjectDefinitionBuilder;
import org.kie.guvnor.datamodel.model.FieldAccessorsAndMutators;
import org.kie.guvnor.datamodel.oracle.DataModelOracle;
import org.kie.guvnor.datamodel.oracle.ProjectDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import static org.junit.Assert.*;

public class DataModelMethodsTest {

    @Test
    public void testMethodsOnJavaClass_TreeMap() throws Exception {
        final ProjectDefinition pd = ProjectDefinitionBuilder.newProjectDefinitionBuilder()
                .addClass( TreeMap.class )
                .build();

        final DataModelOracle dmo = PackageDataModelOracleBuilder.newDataModelBuilder( "java.util" ).setProjectDefinition( pd ).build();

        final String[] getters = dmo.getFieldCompletions( FieldAccessorsAndMutators.ACCESSOR,
                                                          TreeMap.class.getSimpleName() );
        assertEquals( 17,
                      getters.length );
        assertEquals( "this",
                      getters[ 0 ] );
        assertEquals( "clone",
                      getters[ 1 ] );
        assertEquals( "comparator",
                      getters[ 2 ] );
        assertEquals( "descendingKeySet",
                      getters[ 3 ] );
        assertEquals( "descendingMap",
                      getters[ 4 ] );
        assertEquals( "empty",
                      getters[ 5 ] );
        assertEquals( "entrySet",
                      getters[ 6 ] );
        assertEquals( "firstEntry",
                      getters[ 7 ] );
        assertEquals( "firstKey",
                      getters[ 8 ] );
        assertEquals( "keySet",
                      getters[ 9 ] );
        assertEquals( "lastEntry",
                      getters[ 10 ] );
        assertEquals( "lastKey",
                      getters[ 11 ] );
        assertEquals( "navigableKeySet",
                      getters[ 12 ] );
        assertEquals( "pollFirstEntry",
                      getters[ 13 ] );
        assertEquals( "pollLastEntry",
                      getters[ 14 ] );
        assertEquals( "size",
                      getters[ 15 ] );
        assertEquals( "values",
                      getters[ 16 ] );

        final String[] setters = dmo.getFieldCompletions( FieldAccessorsAndMutators.MUTATOR,
                                                          TreeMap.class.getSimpleName() );
        assertEquals( 0,
                      setters.length );
    }

    @Test
    public void testMethodsOnJavaClass_ArrayList() throws Exception {
        final ProjectDefinition pd = ProjectDefinitionBuilder.newProjectDefinitionBuilder()
                .addClass( ArrayList.class )
                .build();

        final DataModelOracle dmo = PackageDataModelOracleBuilder.newDataModelBuilder( "java.util" ).setProjectDefinition( pd ).build();

        final List<String> methodNames = dmo.getMethodNames( ArrayList.class.getSimpleName() );

        assertNotNull( methodNames );
        assertFalse( methodNames.isEmpty() );

        for ( final String methodName : methodNames ) {
            assertFalse( "Method " + methodName + " is not allowed.",
                         allowedMethod( methodName ) );
        }

    }

    private boolean allowedMethod( final String methodName ) {
        return ( "hashCode".equals( methodName )
                || "equals".equals( methodName )
                || "listIterator".equals( methodName )
                || "lastIndexOf".equals( methodName )
                || "indexOf".equals( methodName )
                || "subList".equals( methodName )
                || "get".equals( methodName )
                || "isEmpty".equals( methodName )
                || "containsKey".equals( methodName )
                || "values".equals( methodName )
                || "entrySet".equals( methodName )
                || "containsValue".equals( methodName )
                || "keySet".equals( methodName )
                || "size".equals( methodName )
                || "toArray".equals( methodName )
                || "iterator".equals( methodName )
                || "contains".equals( methodName )
                || "isEmpty".equals( methodName )
                || "containsAll".equals( methodName )
                || "size".equals( methodName ) );
    }

}
