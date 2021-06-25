package com.github.rivasdiaz;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Parameter;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.codehaus.plexus.util.cli.Commandline;

public abstract class AbstractFlutterMojo extends AbstractMojo {

  @Parameter(defaultValue = "${basedir}", required = true, readonly = true)
  File projectDirectory;

  @Parameter(defaultValue = "${flutter.home}")
  File flutterHomeDirectory;

  protected void flutter(String cmdline) throws MojoExecutionException {
    final var log = getLog();

    log.info(String.format("invoking: flutter %s", cmdline));

    final var cmd = new Commandline(String.format("%s %s", flutterCmd(), cmdline));
    cmd.setWorkingDirectory(projectDirectory);
    try {
      final var exitCode = CommandLineUtils.executeCommandLine(cmd, log::info, log::warn);
      if (exitCode != 0) {
        throw new MojoExecutionException("command returned exit code "+exitCode);
      }
    } catch (CommandLineException e) {
      throw new MojoExecutionException("failed to execute flutter", e);
    }
  }

  private String flutterCmd() throws MojoExecutionException {
    // attempt to resolve flutter from the specified installation folder
    if (flutterHomeDirectory != null) {
      final var flutterHomeDir = flutterHomeDirectory.toPath();
      if (!Files.isDirectory(flutterHomeDir))
        throw new MojoExecutionException("flutterHomeDirectory must point to flutter sdk");
      final var flutterBinDir = flutterHomeDir.resolve("bin");
      if (!Files.isDirectory(flutterBinDir))
        throw new MojoExecutionException("flutterHomeDirectory must point to flutter sdk");
      final var flutterCmd = flutterBinDir.resolve("flutter");
      if (!Files.isExecutable(flutterCmd))
        throw new MojoExecutionException("flutterHomeDirectory must point to flutter sdk");
      return flutterCmd.toString();
    }
    // attempt to get flutter from the path
    return "flutter";
  }

  protected Path projectDirectory() {
    return projectDirectory.toPath();
  }

  protected boolean hasWebTarget() {
    return Files.isDirectory(projectDirectory().resolve("web"));
  }

  protected boolean hasAndroidTarget() {
    return Files.isDirectory(projectDirectory().resolve("android"));
  }

  protected boolean hasIOSTarget() {
    return Files.isDirectory(projectDirectory().resolve("ios"));
  }

  protected boolean hasMacTarget() {
    return Files.isDirectory(projectDirectory().resolve("macos"));
  }

  protected boolean hasWindowsTarget() {
    return Files.isDirectory(projectDirectory().resolve("windows"));
  }

  protected boolean hasLinuxTarget() {
    return Files.isDirectory(projectDirectory().resolve("linux"));
  }
}
