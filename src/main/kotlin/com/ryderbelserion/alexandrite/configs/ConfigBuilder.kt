package com.ryderbelserion.alexandrite.configs

import ch.jalu.configme.configurationdata.ConfigurationDataBuilder
import com.ryderbelserion.alexandrite.configs.types.PluginSettings

object ConfigBuilder {

    /**
     * Builds the core configuration data.
     *
     * @return configuration data object containing the main crate configurations.
     */
    fun buildConfigurationData(): ch.jalu.configme.configurationdata.ConfigurationData {
        return ConfigurationDataBuilder.createConfiguration(
            PluginSettings::class.java
        )
    }
}