package build.buildfarm.common.config.yml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

public final class BuildfarmConfigs {
  private static BuildfarmConfigs buildfarmConfigs;

  private static String digestFunction = "SHA256";

  private static long defaultActionTimeout = 600;

  private static long maximumActionTimeout = 3600;
  private static Server server = new Server();

  private static Backplane backplane = new Backplane();

  private static Worker worker = new Worker();

  private static Memory memory = new Memory();

  private BuildfarmConfigs() {}

  public static BuildfarmConfigs getInstance() {
    if (buildfarmConfigs == null) {
      buildfarmConfigs = new BuildfarmConfigs();
    }
    return buildfarmConfigs;
  }

  public void loadConfigs(String configLocation) throws IOException {
    try (InputStream inputStream = new FileInputStream(new File(configLocation))) {
      Yaml yaml = new Yaml(new Constructor(buildfarmConfigs.getClass()));
      buildfarmConfigs = yaml.load(inputStream);
    }
  }

  public void loadConfigs(Path configLocation) throws IOException {
    try (InputStream inputStream = Files.newInputStream(configLocation)) {
      Yaml yaml = new Yaml(new Constructor(buildfarmConfigs.getClass()));
      buildfarmConfigs = yaml.load(inputStream);
    }
  }

  public BuildfarmConfigs getBuildfarmConfigs() {
    return buildfarmConfigs;
  }

  public void setBuildfarmConfigs(BuildfarmConfigs buildfarmConfigs) {
    this.buildfarmConfigs = buildfarmConfigs;
  }

  public String getDigestFunction() {
    return digestFunction;
  }

  public void setDigestFunction(String digestFunction) {
    this.digestFunction = digestFunction;
  }

  public long getDefaultActionTimeout() {
    return defaultActionTimeout;
  }

  public void setDefaultActionTimeout(long defaultActionTimeout) {
    BuildfarmConfigs.defaultActionTimeout = defaultActionTimeout;
  }

  public long getMaximumActionTimeout() {
    return maximumActionTimeout;
  }

  public void setMaximumActionTimeout(long maximumActionTimeout) {
    BuildfarmConfigs.maximumActionTimeout = maximumActionTimeout;
  }

  public Server getServer() {
    return server;
  }

  public void setServer(Server server) {
    this.server = server;
  }

  public Backplane getBackplane() {
    return backplane;
  }

  public void setBackplane(Backplane backplane) {
    this.backplane = backplane;
  }

  public Worker getWorker() {
    return worker;
  }

  public void setWorker(Worker worker) {
    this.worker = worker;
  }

  public Memory getMemory() {
    return memory;
  }

  public void setMemory(Memory memory) {
    BuildfarmConfigs.memory = memory;
  }

  @Override
  public String toString() {
    return "BuildfarmConfigs{"
        + "digestFunction='"
        + digestFunction
        + '\''
        + ", defaultActionTimeout="
        + defaultActionTimeout
        + ", maximumActionTimeout="
        + maximumActionTimeout
        + ", server="
        + server
        + ", worker="
        + worker
        + ", backplane="
        + backplane
        + ", memory="
        + memory
        + '}';
  }
}
