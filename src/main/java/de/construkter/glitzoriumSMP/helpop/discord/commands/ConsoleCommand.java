package de.construkter.glitzoriumSMP.helpop.discord.commands;

import de.construkter.glitzoriumSMP.helpop.HelpOPSender;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;

public class ConsoleCommand extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("console")) {
            Member member = event.getMember();

            if (member == null) {
                event.reply("Unknown error occurred.").setEphemeral(true).queue();
                return;
            }

            if (!member.hasPermission(Permission.ADMINISTRATOR)) {
                event.reply("You don't have permission to use this command.").setEphemeral(true).queue();
                return;
            }

            var command = event.getOption("command");

            if (command == null) {
                event.reply("Please provide a command to execute.").setEphemeral(true).queue();
                return;
            }

            HelpOPSender sender = new HelpOPSender("HelpOP-Discord-Console-Command");
            Bukkit.dispatchCommand(sender, command.getAsString());

            String output = sender.getOutput().isEmpty() ? "No output." : sender.getOutput();
            output = output.replaceAll("§.", "");

            EmbedBuilder embed = new EmbedBuilder()
                    .setTitle("Console Command Executed")
                    .addField("Command", command.getAsString(), false)
                    .addField("Output", "```\n" + output + "\n```", false)
                    .setColor(0x00FF00);

            event.replyEmbeds(embed.build()).queue();
        }
    }
}
