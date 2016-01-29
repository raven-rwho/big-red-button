package de.kraussdev.redbutton.dagger;

import javax.inject.Singleton;

import dagger.Component;
import de.kraussdev.redbutton.RedButton;
import de.kraussdev.redbutton.rest.JenkinsClient;

@Singleton
@Component(
        modules = {
                AppModule.class,
                JenkinsModule.class
        }
)
public interface RedButtonComponent {
    JenkinsClient jenkinsClient();
}