plugins {
    id("java")
    id("war") // 웹 애플리케이션을 위해 WAR 플러그인을 적용
}



group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    // 기존의 테스트 종속성

    // 서블릿 API 종속성 추가
    implementation("jakarta.servlet:jakarta.servlet-api:5.0.0")
    // 다른 필요한 종속성들도 여기에 추가합니다.
    implementation ("javax.servlet:javax.servlet-api:4.0.1")
    // MySQL JDBC Driver
    runtimeOnly ("mysql:mysql-connector-java:8.0.28")

}

tasks.test {
    useJUnitPlatform()
}