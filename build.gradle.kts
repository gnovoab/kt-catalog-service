import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

//PLugins
plugins {
	id("org.springframework.boot") version "2.5.3"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.5.21"
	kotlin("plugin.spring") version "1.5.21"
	jacoco
}


//Setup
group = "com.gnovoab.example"
java.sourceCompatibility = JavaVersion.VERSION_1_8

//Jar Settings
tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
	archiveFileName.set("catalog-service.jar")
}

tasks.getByName<Jar>("jar") {
	enabled = false
}

//Code coverage
tasks.jacocoTestReport {
	reports {
		xml.required.set(false)
		csv.required.set(false)
		html.required.set(true)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {

	//Actuator
	implementation("org.springframework.boot:spring-boot-starter-actuator")

	//Web
	implementation("org.springframework.boot:spring-boot-starter-web")

	//JPA
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")

	//Kotlin
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	//Metadata
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

	//Test
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.mockito.kotlin:mockito-kotlin:3.2.0")

	// swagger for API visualization
	implementation("org.springdoc:springdoc-openapi-ui:1.5.10")
	implementation("org.springdoc:springdoc-openapi-kotlin:1.5.10")

	//DB
	runtimeOnly("com.h2database:h2")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
	finalizedBy(tasks.jacocoTestReport)
}
