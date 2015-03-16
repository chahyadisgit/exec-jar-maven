# exec-jar-maven
Automatically exported from code.google.com/p/exec-jar-maven

## StdTemplateJar 
Coba iseng-iseng buat standar template project java (.jar) dengan menggunakan maven.

Mudah-mudahan bisa bermanfaat bagi yang membutuhkan.

#step by step
Berikut adalah langkah-langkah yang bisa dilakukan jika ingin buat sendiri dari awal :
1. siapkan direktori untuk project yang akan anda buat
2. generate project by maven (command prompt)
      mvn archetype:generate -DgroupId=[packageName] -DartifactId=[projectName] -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
3. tambahkan project ke eclipse
      Import > import > Existing Maven Projects
4. edit pom.xml, tambahkan kode berikut setelah/sebelum tags dependencies
    <build>
                <finalName>execTemplateJar</finalName>
                <plugins>
                        <plugin>
                            <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-assembly-plugin</artifactId>
                <version>2.2.1</version>
                <executions>
                    <execution>
                        <id>package-jar-with-dependencies</id>
                        <phase>package</phase>
                        <goals>
                            <goal>single</goal>
                        </goals>
                        <configuration>
                            <appendAssemblyId>false</appendAssemblyId>
                            <descriptorRefs>
                                <descriptorRef>jar-with-dependencies</descriptorRef>
                            </descriptorRefs>
                            <archive>
                                <manifest>
                                    <mainClass>com.std.pack.App</mainClass>
                                </manifest>
                            </archive>
                        </configuration>
                    </execution>
                </executions>
                        </plugin>
                </plugins>
        </build>
        
#compile project (command prompt)
      mvn clean install

#hasil compile-an ada di folder target
run project (command prompt)
       java -jar [project.jar]
