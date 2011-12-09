/*
 * Copyright 2011 the original author or authors.
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

package org.gradle.integtests.tooling.next

import org.gradle.integtests.tooling.fixture.MinTargetGradleVersion
import org.gradle.integtests.tooling.fixture.MinToolingApiVersion
import org.gradle.integtests.tooling.fixture.ToolingApiSpecification
import org.gradle.tooling.ProjectConnection
import org.gradle.tooling.model.Project
import spock.lang.Timeout

@MinToolingApiVersion(currentOnly = true)
@MinTargetGradleVersion(currentOnly = true)
class ConsumingStandardInputIntegrationTest extends ToolingApiSpecification {

    def setup() {
        toolingApi.isEmbedded = false
    }

    @Timeout(10)
    def "enables consuming input"() {
        given:
        dist.file('build.gradle')  << """
description = System.in.text
"""
        when:
        Project model = (Project) withConnection { ProjectConnection connection ->
            def model = connection.model(Project.class)
            model.standardInput = new ByteArrayInputStream("Cool project".bytes)
            model.get()
        }

        then:
        model.description == 'Cool project'
    }
}