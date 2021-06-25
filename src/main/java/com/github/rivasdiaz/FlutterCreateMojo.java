package com.github.rivasdiaz;

import java.util.List;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(
    name = "create",
    requiresProject = false
)
public class FlutterCreateMojo extends AbstractFlutterMojo {

  @Parameter(defaultValue = "${project.artifactId}", required = true, readonly = true)
  String projectName;

  @Parameter(defaultValue = "${project.groupId}", required = true, readonly = true)
  String projectOrganization;

  @Parameter(defaultValue = "${project.description}", required = true, readonly = true)
  String projectDescription;

  @Parameter(defaultValue = "${flutter.target.platforms}")
  List<String> targetPlatforms;

  @Override
  public void execute() throws MojoExecutionException, MojoFailureException {
    final var cmd = new StringBuilder();
    cmd.append("create");
    cmd.append(" --no-pub --no-overwrite");
    if (projectName != null) {
      cmd.append(" --project-name ").append(projectName);
    }
    if (projectOrganization != null) {
      cmd.append(" --org ").append(projectOrganization);
    }
    if (projectDescription != null) {
      cmd.append(" --description '").append(projectDescription).append("'");
    }
    if (targetPlatforms != null && !targetPlatforms.isEmpty()) {
      cmd.append(" --platforms ").append(String.join(",", targetPlatforms));
    }
    cmd.append(" '").append(projectDirectory()).append("'");
    flutter(cmd.toString());
  }
}
