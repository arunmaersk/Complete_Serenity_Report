This project have serenity report implemented at begining with
	Spring framework
	Cucbumber
	Serenity Report
	Serenity Rest Assured for Request only
	Rest Assured for Response

******************************************************************************

Serenity Report Requirement : 

import net.serenitybdd.cucumber.CucumberWithSerenity;
{@RunWith(CucumberWithSerenity.class)}
import com.maersk.ohm.sit.springapplication.CucumberSpringApplication;
{@SpringBootTest(classes = CucumberSpringApplication.class)}

<dependency>
	<groupId>net.serenity-bdd.maven.plugins</groupId>
	<artifactId>serenity-maven-plugin</artifactId>
	<version>3.0.5</version>
</dependency>
<dependency>
	<groupId>net.serenity-bdd</groupId>
	<artifactId>serenity-core</artifactId>
	<version>3.0.5</version>
	<scope>compile</scope>
</dependency>
<dependency>
	<groupId>net.serenity-bdd</groupId>
	<artifactId>serenity-junit</artifactId>
	<version>3.0.5</version>
</dependency>
<dependency>
	<groupId>net.serenity-bdd</groupId>
	<artifactId>serenity-cucumber</artifactId>
	<version>3.0.5</version>
</dependency>
<plugin>
	<groupId>net.serenity-bdd.maven.plugins</groupId>
	<artifactId>serenity-maven-plugin</artifactId>
	<version>3.0.5</version>
	<configuration></configuration>
	<executions>
		<execution>
			<id>serenity-reports</id>
			<phase>post-integration-test</phase>
			<goals>
				<goal>aggregate</goal>
			</goals>
		</execution>
	</executions>
</plugin>