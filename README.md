# Weblogic Deploy App

Install 
```shell
.\mvnw clean install -DskipTests
```

Run in local
```shell
.\mvnw spring-boot:run
```

########################
Install Jatmi Jar Local Project
########################

```shell
- .\mvnw install:install-file -Dfile=C:\bcap\com.bea.core.jatmi.jar -DgroupId=com.oracle.weblogic -DartifactId=wls-tuxedo -Dversion=14.1.2 -Dpackaging=jar
- .\mvnw install:install-file -Dfile="C:\bcap\com.bea.core.jatmi.jar" -DgroupId=com.oracle.weblogic -DartifactId=wls-tuxedo -Dversion=14.1.2 -Dpackaging=jar
- .\mvnw install:install-file "-Dfile=C:\bcap\com.bea.core.jatmi.jar" "-DgroupId=com.oracle.weblogic" "-DartifactId=wls-tuxedo" "-Dversion=14.1.2" "-Dpackaging=jar"
```