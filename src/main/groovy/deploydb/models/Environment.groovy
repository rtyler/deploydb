package deploydb.models

import com.google.common.collect.Lists
import com.fasterxml.jackson.annotation.JsonProperty
import deploydb.models.Webhook.Webhook

import javax.validation.constraints.Size
import org.hibernate.validator.constraints.NotEmpty

/**
 * Representation class for the concept of a Environment
 */
class Environment {

    /**
     * Environment identifier
     *
     * Retrieved from the configuration base filename. Used as a key in
     * registry
     */
    @JsonProperty
    String ident

    /**
     * Environment description
     */
    @NotEmpty
    @Size(max=8192)
    @JsonProperty
    String description

    /**
     * Webhook
     */
    @JsonProperty
    Webhook webhooks

    /**
     * Empty constructor used by Jackson for object deserialization
     */
    Environment() { }

    /**
     *  Constructor to be used by DeployDB internally. It accepts all
     *  of the parameters
     */
    Environment(String ident,
                String description,
                Webhook webhooks) {
        this.ident = ident
        this.description = description
        this.webhooks = webhooks
    }

    @Override
    boolean equals(Object o) {

        /* First object identity */
        if (this.is(o)) {
            return true
        }

        if (!(o instanceof Environment)) {
            return false
        }

        final Environment that = (Environment)o

        return Objects.equals(this.ident, that.ident) &&
                Objects.equals(this.description, that.description) &&
                Objects.equals(this.webhooks, that.webhooks)
    }

    @Override
    String toString() {
        String output
        output += "ident = ${ident}, description: ${description}, webhooks: ${webhooks}"
        return output
    }
}
