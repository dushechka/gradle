/*
 * Copyright 2018 the original author or authors.
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
    implementation(project(":distribution-core:base-services-groovy"))
    implementation(project(":distribution-core:core-api"))
    implementation(project(":distribution-core:files"))
    implementation(project(":distribution-core:model-core"))
    implementation(project(":distribution-core:logging"))
    implementation(project(":distribution-core:native"))

    implementation(libs.slf4jApi)
    implementation(libs.groovy)
    implementation(libs.guava)
    implementation(libs.commonsIo)
    implementation(libs.commonsLang)
    implementation(libs.inject)

    testImplementation(project(":distribution-core:process-services"))
    testImplementation(project(":distribution-core:resources"))
    testImplementation(project(":distribution-core:snapshots"))
    testImplementation(testFixtures(project(":distribution-core:core")))
    testImplementation(testFixtures(project(":distribution-core:core-api")))
    testImplementation(testFixtures(project(":distribution-core:model-core")))

    testFixturesImplementation(project(":distribution-core:base-services"))
    testFixturesImplementation(project(":distribution-core:core-api"))
    testFixturesImplementation(project(":distribution-core:native"))

    testFixturesImplementation(libs.guava)

    testRuntimeOnly(project(":distribution-setup:distributions-core")) {
        because("Tests instantiate DefaultClassLoaderRegistry which requires a 'gradle-plugins.properties' through DefaultPluginModuleRegistry")
    }
    integTestDistributionRuntimeOnly(project(":distribution-setup:distributions-core"))
}

strictCompile {
    ignoreRawTypes() // raw types used in public API
}

classycle {
    // Some cycles have been inherited from the time these classes were in :distribution-core:core
    excludePatterns.set(listOf("org/gradle/api/internal/file/collections/"))
}
