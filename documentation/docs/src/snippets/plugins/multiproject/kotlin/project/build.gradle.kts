// tag::distribution-plugins:core:plugins-on-subprojects[]
plugins {
    id("com.example.hello") version "1.0.0" apply false
    id("com.example.goodbye") version "1.0.0" apply false
}
// end::distribution-plugins:core:plugins-on-subprojects[]
