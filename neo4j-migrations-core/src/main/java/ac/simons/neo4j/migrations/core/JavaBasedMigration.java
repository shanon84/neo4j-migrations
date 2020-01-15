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
package ac.simons.neo4j.migrations.core;

import java.util.Optional;

/**
 * Interface to be implemented for Java based migrations.
 *
 * @author Michael J. Simons
 */
public interface JavaBasedMigration extends Migration {

	@Override
	default MigrationVersion getVersion() {
		return MigrationVersion.of(getClass());
	}

	@Override
	default String getDescription() {
		return Optional.ofNullable(getVersion().getDescription()).orElseGet(() -> getClass().getSimpleName());
	}

	@Override
	default MigrationType getType() {
		return MigrationType.JAVA;
	}

	@Override
	default String getSource() {
		return getClass().getName();
	}
}