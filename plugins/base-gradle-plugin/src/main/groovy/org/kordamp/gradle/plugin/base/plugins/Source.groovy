/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2018 Andres Almiray.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kordamp.gradle.plugin.base.plugins

import groovy.transform.Canonical
import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode
import org.gradle.api.Project
import org.gradle.api.tasks.bundling.Jar

/**
 * @author Andres Almiray
 * @since 0.8.0
 */
@CompileStatic
@Canonical
@EqualsAndHashCode(excludes = ['project', 'projects', 'sourceTasks'])
class Source extends AbstractFeature {
    private final Set<Project> projects = new LinkedHashSet<>()
    private final Set<Jar> sourceTasks = new LinkedHashSet<>()

    Source(Project project) {
        super(project)
    }

    @Override
    String toString() {
        toMap().toString()
    }

    @Override
    @CompileDynamic
    Map<String, Map<String, Object>> toMap() {
        ['source': [
            enabled: enabled
        ]]
    }

    static void merge(Source o1, Source o2) {
        AbstractFeature.merge(o1, o2)
        o1.projects().addAll(o2.projects())
        o1.sourceTasks().addAll(o2.sourceTasks())
    }

    Set<Project> projects() {
        projects
    }

    Set<Jar> sourceTasks() {
        sourceTasks
    }
}
