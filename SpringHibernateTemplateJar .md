# exec-jar-maven
Automatically exported from code.google.com/p/exec-jar-maven

# SpringHibernateTemplateJar
##step by step
Berikut adalah langkah-langkah yang bisa dilakukan jika ingin buat sendiri dari awal :
siapkan direktori untuk project yang akan anda buat
generate project by maven (command prompt)
   mvn archetype:generate -DgroupId=[packageName] -DartifactId=[projectName] -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
tambahkan project ke eclipse
Import > import > Existing Maven Projects
edit pom.xml, tambahkan kode berikut setelah/sebelum tags dependencies
    <build>
        <finalName>execSpringHibernateTemplateJar</finalName>
        <plugins>
                <plugin>
                        <groupId>org.apache.maven.plugins</groupId>
                        <artifactId>maven-jar-plugin</artifactId>
                        <configuration>
                                <archive>
                                        <manifest>
                                                <mainClass>com.std.pack.App</mainClass>
                                        </manifest>
                                </archive>
                        </configuration>
                </plugin>
                        <plugin>
                                <groupId>org.apache.maven.plugins</groupId>
                                <artifactId>maven-shade-plugin</artifactId>
                                <version>2.2</version>
                                <executions>
                                        <execution>
                                                <phase>package</phase>
                                                <goals>
                                                        <goal>shade</goal>
                                                </goals>
                                                <configuration>
                                                        <artifactSet>
                                                                <!-- Use this to in/exclude only specific dependencies -->
                                                                <excludes>
                                                                        <exclude>junit:junit</exclude>
                                                                </excludes>
                                                        </artifactSet>
                                                        <transformers>
                                                                <transformer                    implementation="org.apache.maven.plugins.shade.resource.ComponentsXmlResourceTransformer" />
                                                                <transformer implementation="org.apache.maven.plugins.shade.resource.AppendingTransformer">
                                                                        <resource>META-INF/spring.handlers</resource>
                                                                </transformer>
                                                                <transformer implementation="org.apache.maven.plugins.shade.resource.AppendingTransformer">
                                                                        <resource>META-INF/spring.schemas</resource>
                                                                </transformer>
                                                        </transformers>
                                                </configuration>
                                        </execution>
                                </executions>
                        </plugin>
                </plugins>
        </build>
##compile project (command prompt)
      mvn clean install
hasil compile-an ada di folder target
##run project (command prompt)
       java -jar [project.jar]
