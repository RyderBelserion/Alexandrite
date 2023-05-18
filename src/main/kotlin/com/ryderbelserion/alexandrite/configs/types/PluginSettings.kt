package com.ryderbelserion.alexandrite.configs.types

import ch.jalu.configme.Comment
import ch.jalu.configme.SettingsHolder
import ch.jalu.configme.configurationdata.CommentsConfiguration
import ch.jalu.configme.properties.PropertyInitializer

/**
 * @author RyderBelserion
 *
 * Description: The plugin-settings.yml options.
 */
class PluginSettings : SettingsHolder {

    override fun registerComments(conf: CommentsConfiguration) {
        val header = arrayOf(
            "Github: https://github.com/Crazy-Crew",
            "",
            "Issues/Features: https://github.com/Crazy-Crew/CrazyCrates/issues"
        )

        conf.setComment("settings", *header)
    }

    companion object {

        @Comment("Whether you want to have verbose logging enabled or not.")
        val VERBOSE_LOGGING = PropertyInitializer.newProperty("settings.verbose-logging", true)

    }
}