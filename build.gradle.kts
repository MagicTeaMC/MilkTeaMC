plugins {
    `java-library`

    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("io.papermc.paperweight.patcher") version "1.3.3"
}

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/") {
        content { onlyForConfigurations("paperclip") }
    }
}

dependencies {
    decompiler("net.minecraftforge:forgeflower:1.5.498.22")
    remapper("net.fabricmc:tiny-remapper:0.7.0:fat")
    paperclip("io.papermc:paperclip:3.0.2")
}

subprojects {
    apply(plugin = "java-library")
    apply(plugin = "maven-publish")

    java {
        withSourcesJar()
        withJavadocJar()

        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17

        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
        }
    }

    tasks.withType<JavaCompile> {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(17)
    }

    repositories {
        mavenCentral()

        maven("https://oss.sonatype.org/content/groups/public/")
        maven("https://papermc.io/repo/repository/maven-public/")
        maven("https://ci.emc.gs/nexus/content/groups/aikar/")
        maven("https://repo.aikar.co/content/groups/aikar")
        maven("https://repo.md-5.net/content/repositories/releases/")
        maven("https://hub.spigotmc.org/nexus/content/groups/public/")
        maven("https://jitpack.io")
    }
}
