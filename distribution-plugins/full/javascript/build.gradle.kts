/*
 * Copyright 2012 the original author or authors.
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
plugins {
    id("gradlebuild.distribution.api-java")
}

dependencies {
    implementation(project(":distribution-core:base-services"))
    implementation(project(":distribution-core:logging"))
    implementation(project(":distribution-core:process-services"))
    implementation(project(":distribution-core:core-api"))
    implementation(project(":distribution-core:model-core"))
    implementation(project(":distribution-core:core"))
    implementation(project(":distribution-plugins:core:reporting"))
    implementation(project(":distribution-plugins:core:plugins"))
    implementation(project(":distribution-plugins:core:workers"))
    implementation(project(":distribution-plugins:core:dependency-management")) // Required by JavaScriptExtension#getGoogleApisRepository()
    implementation(project(":distribution-plugins:core:language-java")) // Required by RhinoShellExec

    implementation(libs.groovy)
    implementation(libs.slf4jApi)
    implementation(libs.commonsIo)
    implementation(libs.inject)
    implementation(libs.rhino)
    implementation(libs.gson) // used by JsHint.coordinates
    implementation(libs.simple) // used by http package in envjs.coordinates

    testImplementation(testFixtures(project(":distribution-core:core")))

    testRuntimeOnly(project(":distribution-setup:distributions-core")) {
        because("ProjectBuilder tests load services from a Gradle distribution.")
    }
    integTestDistributionRuntimeOnly(project(":distribution-setup:distributions-full"))
}

classycle {
    excludePatterns.set(listOf("org/gradle/plugins/javascript/coffeescript/**"))
}
