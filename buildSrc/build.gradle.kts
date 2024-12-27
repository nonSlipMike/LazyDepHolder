import java.net.URI

plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    maven { url = URI.create("https://jitpack.io") } // JitPack
}
