# flutter-maven-plugin

![GitHub Workflow Status](https://img.shields.io/github/workflow/status/rivasdiaz/flutter-maven-plugin/Build?style=for-the-badge)
![GitHub](https://img.shields.io/github/license/rivasdiaz/flutter-maven-plugin?style=for-the-badge)
![GitHub tag (latest SemVer)](https://img.shields.io/github/v/tag/rivasdiaz/flutter-maven-plugin?sort=semver&style=for-the-badge)

A plugin to build Flutter projects with [Maven](https://maven.apache.org).

## Usage

The plugin assumes the project follows the structure required by the Flutter SDK.

### Step 1: Declare flutter packaging

To make sure the project is configured as a Flutter package, make sure you define your packaging
to match the plugin.

```xml
<project>
  <modelVersion>4.0.0</modelVersion>

  <groupId>YOUR PROJECT GROUP ID</groupId>
  <artifactId>YOUR PROJECT ARTIFACT ID</artifactId>
  <packaging>flutter</packaging>

  <!-- additional project configuration -->
</project>
```

### Step 2: Include Jitpack.io repository

Currently, this plugin is only available via jitpack.io.

Add a reference to Jitpack in your project `pom.xml`

```xml
<project>
  <!-- additional project configuration -->

  <pluginRepositories>
    <pluginRepository>
      <id>jitpack</id>
      <name>JitPack</name>
      <url>https://jitpack.io</url>
      <releases>
        <enabled>true</enabled>
      </releases>
      <snapshots>
        <enabled>true</enabled>
      </snapshots>
    </pluginRepository>
  </pluginRepositories>

  <!-- additional project configuration -->
</project>
```

### Step 3: Define your build directory

To make sure Maven understands your Flutter project, define `${basedir}/build` as your project's
build directory.

```xml
<project>
  <!-- additional project configuration -->

  <build>
    <directory>${project.basedir}/build</directory>

    <!-- additional build configuration -->
  </build>

  <!-- additional project configuration -->
</project>
```

### Step 4: Declare the flutter plugin

Declare the flutter plugin. Make sure to include `<extensions>true</extensions>` to allow the plugin
to configure tasks on the lifecycle.

```xml
  <build>
    <!-- additional build configuration -->

    <plugins>
      <plugin>
        <groupId>com.github.rivasdiaz</groupId>
        <artifactId>flutter-maven-plugin</artifactId>
        <extensions>true</extensions>
        <!-- flutter plugin configuration -->
      </plugin>
      
      <!-- other plugins -->
    </plugins>
  
    <!-- additional build configuration -->
  </build>
```

## Lifecycle

* `clean` &#8594; `flutter-maven-plugin:clean`
* `initialize` &#8594; `flutter-maven-plugin:dependencies`
* `compile` &#8594; `flutter-maven-plugin:build`
* `test` &#8594; `flutter-maven-plugin:test`

## Configuration options

| Property | Default value | Description |
|----------|---------------|-------------|
| `flutterHomeDirectory` | `${flutter.home}` | **_Optional_**. Define where is flutter installed. If not present, tries to run it from the path. |
