# flutter-maven-plugin

![GitHub Workflow Status](https://img.shields.io/github/workflow/status/rivasdiaz/flutter-maven-plugin/Build?style=for-the-badge)
![GitHub](https://img.shields.io/github/license/rivasdiaz/flutter-maven-plugin?style=for-the-badge)
![GitHub tag (latest SemVer)](https://img.shields.io/github/v/tag/rivasdiaz/flutter-maven-plugin?sort=semver&style=for-the-badge)

A plugin to build [Flutter](https://flutter.dev) projects with [Maven](https://maven.apache.org).

## Basic usage

The plugin assumes the project follows the structure required by the Flutter SDK.

### Step 1: Declare flutter packaging

To make sure the project is configured as a Flutter package, make sure you define your packaging
to match the plugin.

```xml
<project>
  <modelVersion>4.0.0</modelVersion>

  <groupId>YOUR PROJECT GROUP ID</groupId>
  <artifactId>YOUR PROJECT ARTIFACT ID</artifactId>
  <version>0.0.0-SNAPSHOT</version>

  <packaging>flutter</packaging> :one:

  <!-- ... -->
</project>
```

### Step 2: Include Jitpack.io repository

Currently, this plugin is only available via jitpack.io.

Add a reference to Jitpack in your project `pom.xml`

```xml
<project>
  <!-- ... -->

  <pluginRepositories>
    <pluginRepository>
      <id>jitpack</id>
      <name>JitPack</name>
      <url>https://jitpack.io</url> :two:
      <releases>
        <enabled>true</enabled>
      </releases>
      <snapshots>
        <enabled>true</enabled>
      </snapshots>
    </pluginRepository>
  </pluginRepositories>

  <!-- ... -->
</project>
```

### Step 3: Define your build directory

To make sure Maven understands your Flutter project, define `${basedir}/build` as your project's
build directory.

```xml
<project>
  <!-- ... -->

  <build>
    <directory>${project.basedir}/build</directory> :three:

    <!-- ... -->
  </build>

  <!-- ... -->
</project>
```

### Step 4: Declare the flutter plugin

Declare the flutter plugin. Make sure to include `<extensions>true</extensions>` to allow the plugin
to configure tasks on the lifecycle.

```xml
<project>
  <!-- ... -->

  <build>
    <!-- ... -->

    <plugins>
      <plugin>
        <groupId>com.github.rivasdiaz</groupId>
        <artifactId>flutter-maven-plugin</artifactId> :four:
        <extensions>true</extensions>
        <!-- flutter plugin configuration -->
      </plugin>
      
      <!-- ... -->
    </plugins>
  
    <!-- ... -->
  </build>

  <!-- ... -->
</project>
```

### Using the plugin

#### Flutter project skeleton creation

If you haven't created your Flutter project yet, you can create a basic project by running:

```shell
mvn flutter:create
```

This will create the project using the configuration defined on your POM. Make sure to include
the description tag as that's used to generate the `pubspec.yaml`.

Add the parameter `-Dflutter.target.platforms=<flutter platform list>` to generate scaffolding
for only a subset of the supported platforms, for example:

```shell
mvn flutter:create -Dflutter.target.platforms=web
```

#### Building

To build your project, use the command:

```shell
mvn package
```

All supported platforms will be built. Use `-Dflutter.target.<platform>.skip=true` to optionally 
skip building some platforms.

#### Running

To run your project, use the command:

```shell
mvn flutter:run -Dflutter.target.device=<device>
```

This command is not part of any maven lifecycle, and requires dependencies to be resolved. If you
have never build the project before, you can use `flutter:dependencies` target.

For example, to run the web version of the app, use:

```shell
mvn flutter:dependencies flutter:run -Dflutter.target.device=chrome
```

You can check the list of available devices, by running:

```shell
mvn flutter:devices
```

## Lifecycle

* `clean` :arrow_right: `flutter-maven-plugin:clean`
* `initialize` :arrow_right: `flutter-maven-plugin:dependencies`
* `compile` :arrow_right: `flutter-maven-plugin:build`
* `test` :arrow_right: `flutter-maven-plugin:test`

## Configuration options

| Property | Default value | Description |
|----------|---------------|-------------|
| `flutterHomeDirectory` | `${flutter.home}` | **_Optional_**. Define where is flutter installed. If not present, tries to run it from the path. |
