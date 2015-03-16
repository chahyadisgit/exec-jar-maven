Coba iseng-iseng buat standar template project java (.jar) dengan menggunakan maven.

Mudah-mudahan bisa bermanfaat bagi yang membutuhkan.<br />


# step by step #

Berikut adalah langkah-langkah yang bisa dilakukan jika ingin buat sendiri dari awal :
<ol>
<li>siapkan direktori untuk project yang akan anda buat</li>
<li>generate project by maven (command prompt)</li>
<pre><code>   mvn archetype:generate -DgroupId=[packageName] -DartifactId=[projectName] -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false<br>
</code></pre>
<li>tambahkan project ke eclipse</li>
<blockquote><table><thead><th> <i><b>Import > import > Existing Maven Projects</b></i> </th></thead><tbody>
<li>edit <b>pom.xml</b>, tambahkan kode berikut setelah/sebelum tags dependencies</li>
<pre><code>    &lt;build&gt;<br>
	&lt;finalName&gt;execSpringHibernateTemplateJar&lt;/finalName&gt;<br>
	&lt;plugins&gt;<br>
		&lt;plugin&gt;<br>
	                &lt;groupId&gt;org.apache.maven.plugins&lt;/groupId&gt;<br>
			&lt;artifactId&gt;maven-jar-plugin&lt;/artifactId&gt;<br>
			&lt;configuration&gt;<br>
				&lt;archive&gt;<br>
					&lt;manifest&gt;<br>
						&lt;mainClass&gt;com.std.pack.App&lt;/mainClass&gt;<br>
					&lt;/manifest&gt;<br>
				&lt;/archive&gt;<br>
			&lt;/configuration&gt;<br>
		&lt;/plugin&gt;<br>
			&lt;plugin&gt;<br>
				&lt;groupId&gt;org.apache.maven.plugins&lt;/groupId&gt;<br>
				&lt;artifactId&gt;maven-shade-plugin&lt;/artifactId&gt;<br>
				&lt;version&gt;2.2&lt;/version&gt;<br>
				&lt;executions&gt;<br>
					&lt;execution&gt;<br>
						&lt;phase&gt;package&lt;/phase&gt;<br>
						&lt;goals&gt;<br>
							&lt;goal&gt;shade&lt;/goal&gt;<br>
						&lt;/goals&gt;<br>
						&lt;configuration&gt;<br>
							&lt;artifactSet&gt;<br>
								&lt;!-- Use this to in/exclude only specific dependencies --&gt;<br>
								&lt;excludes&gt;<br>
									&lt;exclude&gt;junit:junit&lt;/exclude&gt;<br>
								&lt;/excludes&gt;<br>
							&lt;/artifactSet&gt;<br>
							&lt;transformers&gt;<br>
								&lt;transformer			implementation="org.apache.maven.plugins.shade.resource.ComponentsXmlResourceTransformer" /&gt;<br>
								&lt;transformer implementation="org.apache.maven.plugins.shade.resource.AppendingTransformer"&gt;<br>
									&lt;resource&gt;META-INF/spring.handlers&lt;/resource&gt;<br>
								&lt;/transformer&gt;<br>
								&lt;transformer implementation="org.apache.maven.plugins.shade.resource.AppendingTransformer"&gt;<br>
									&lt;resource&gt;META-INF/spring.schemas&lt;/resource&gt;<br>
								&lt;/transformer&gt;<br>
							&lt;/transformers&gt;<br>
						&lt;/configuration&gt;<br>
					&lt;/execution&gt;<br>
				&lt;/executions&gt;<br>
			&lt;/plugin&gt;<br>
		&lt;/plugins&gt;<br>
	&lt;/build&gt;<br>
</code></pre>
<li>compile project (command prompt)</li>
<pre><code>      mvn clean install<br>
</code></pre>
<i>hasil compile-an ada di folder target<br></i><li>run project (command prompt)</li>
<pre><code>      java -jar [project.jar]<br>
</code></pre>
</ol>