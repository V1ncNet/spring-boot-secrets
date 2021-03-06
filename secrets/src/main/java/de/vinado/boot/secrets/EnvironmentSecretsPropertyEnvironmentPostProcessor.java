package de.vinado.boot.secrets;

import org.springframework.boot.logging.DeferredLogFactory;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.function.Supplier;

/**
 * An environment post-processor that resolves every environment variable with a <em>_FILE</em> suffix.
 *
 * @author Vincent Nadoll
 */
public final class EnvironmentSecretsPropertyEnvironmentPostProcessor extends SecretsEnvironmentPostProcessor {

    public static final String ENV_VAR_SUFFIX = "_FILE";
    public static final int ORDER = EnvironmentConfigDataSecretsEnvironmentPostProcessor.ORDER + 1;

    public EnvironmentSecretsPropertyEnvironmentPostProcessor(DeferredLogFactory logFactory) {
        super(logFactory);
    }

    @Override
    protected PropertyIndexSupplier getPropertyIndexSupplier(ConfigurableEnvironment environment) {
        return new EnvironmentPropertyIndexSupplier(Supplier::get, environment, ENV_VAR_SUFFIX);
    }

    @Override
    public int getOrder() {
        return ORDER;
    }
}
