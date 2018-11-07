import org.gradle.api.Plugin
import org.gradle.api.Project

public class MirrorPlugin implements Plugin<Project> {

    void apply(Project project) {
        System.out.println("========================");
        System.out.println("Hello MirrorPlugin!");
        System.out.println("========================");
    }
}

