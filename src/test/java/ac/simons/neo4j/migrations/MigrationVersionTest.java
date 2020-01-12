/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ac.simons.neo4j.migrations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Michael J. Simons
 */
public class MigrationVersionTest {

	@Test
	void shouldHandleCorrectClassNames() {

		MigrationVersion migrationVersion;
		migrationVersion = MigrationVersion.parse("V1__a");
		Assertions.assertEquals("1", migrationVersion.getValue());
		Assertions.assertEquals("a", migrationVersion.getDescription());

		migrationVersion = MigrationVersion.parse("V021__HalloWelt");
		Assertions.assertEquals("021", migrationVersion.getValue());
		Assertions.assertEquals("HalloWelt", migrationVersion.getDescription());
	}

	@Test
	void shouldIgnoreCypherExtension() {

		MigrationVersion migrationVersion;
		migrationVersion = MigrationVersion.parse("V1__a.cypher");
		Assertions.assertEquals("1", migrationVersion.getValue());
		Assertions.assertEquals("a", migrationVersion.getDescription());

		migrationVersion = MigrationVersion.parse("V021__HalloWelt.cypher");
		Assertions.assertEquals("021", migrationVersion.getValue());
		Assertions.assertEquals("HalloWelt", migrationVersion.getDescription());

		migrationVersion = MigrationVersion.parse("V4711__MirFallenKeineNamenEin.cypher");
		Assertions.assertEquals("4711", migrationVersion.getValue());
		Assertions.assertEquals("MirFallenKeineNamenEin", migrationVersion.getDescription());

		migrationVersion = MigrationVersion.parse("V4711__Ein Dateiname.cypher");
		Assertions.assertEquals("4711", migrationVersion.getValue());
		Assertions.assertEquals("Ein Dateiname", migrationVersion.getDescription());
	}

	@Test
	void shouldStripUnderscores() {

		MigrationVersion migrationVersion;
		migrationVersion = MigrationVersion.parse("V1__a_b");
		Assertions.assertEquals("a b", migrationVersion.getDescription());
	}

	@Test
	void shouldFailOnIncorrectClassNames() {

		Assertions.assertThrows(MigrationsException.class, () -> MigrationVersion.parse("HalloWelt"));
		Assertions.assertThrows(MigrationsException.class, () -> MigrationVersion.parse("V1"));
		Assertions.assertThrows(MigrationsException.class, () -> MigrationVersion.parse("V1__"));
		Assertions.assertThrows(MigrationsException.class, () -> MigrationVersion.parse("V__HalloWelt"));
		Assertions.assertThrows(MigrationsException.class, () -> MigrationVersion.parse("V1_HalloWelt"));
	}
}