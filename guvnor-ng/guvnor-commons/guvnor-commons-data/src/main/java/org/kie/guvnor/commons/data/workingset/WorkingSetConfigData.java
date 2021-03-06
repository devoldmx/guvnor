/*
 * Copyright 2012 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kie.guvnor.commons.data.workingset;

import org.jboss.errai.common.client.api.annotations.Portable;
import org.kie.guvnor.commons.data.factconstraints.ConstraintConfiguration;
import org.kie.guvnor.commons.data.factconstraints.customform.CustomFormConfiguration;

import java.util.List;

@Portable
public class WorkingSetConfigData {

    private String                        name;
    private String                        description;
    private List<ConstraintConfiguration> constraints;
    private List<CustomFormConfiguration> customForms;
    private String[]                      validFacts;
    private WorkingSetConfigData[]        workingSets;

    public WorkingSetConfigData() {

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<ConstraintConfiguration> getConstraints() {
        return constraints;
    }

    public List<CustomFormConfiguration> getCustomForms() {
        return customForms;
    }

    public String[] getValidFacts() {
        return validFacts;
    }

    public WorkingSetConfigData[] getWorkingSets() {
        return workingSets;
    }
}
