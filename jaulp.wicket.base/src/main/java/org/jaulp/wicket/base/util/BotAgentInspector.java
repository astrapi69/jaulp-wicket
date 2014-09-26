package org.jaulp.wicket.base.util;
import java.util.HashSet;
import java.util.Set;

/**
 * The Class BotAgentInspector has on method that checks for agent strings.
 */
public class BotAgentInspector {

	/** The Constant BOT_AGENTS. */
	private static final Set<String> BOT_AGENTS = new HashSet<String>() {
		private static final long serialVersionUID = 1L;
		{
			add("googlebot");
			add("msnbot");
			add("slurp");
			add("jeeves");
			add("appie");
			add("architext");
			add("ferret");
			add("harvest");
			add("htdig");
			add("linkwalker");
			add("lycos_");
			add("moget");
			add("muscatferret");
			add("myweb");
			add("nomad");
			add("scooter");
			add("yahoo!\\sslurp\\schina");
			add("antibot");
			add("bruinbot");
			add("digout4u");
			add("echo!");
			add("ia_archiver");
			add("jennybot");
			add("mercator");
			add("netcraft");
			add("petersnews");
			add("unlost_web_crawler");
			add("voila");
			add("webbase");
			add("webcollage");
			add("cfetch");
			add("zyborg");
			add("wisenutbot");
			add("robot");
			add("spider");
		}
	};

	/** The Constant NO_BOT_AGENTS. */
	private static final Set<String> NO_BOT_AGENTS = new HashSet<String>() {
		private static final long serialVersionUID = 1L;
		{
			add("firefox");
			add("msie");
			add("opera");
			add("netscape");
			add("safari");
			add("chrome");
		}
	};

	/**
	 * Checks if the given String object is agent over the String array
	 * BOT_AGENTS.
	 * 
	 * @param agent
	 *            String to check.
	 * @return true, if is agent
	 */
	public static boolean isAgent(final String agent) {
		if (agent != null) {
			final String lowerAgent = agent.toLowerCase();
			for (String noBot : NO_BOT_AGENTS) {
				if (lowerAgent.indexOf(noBot) > -1) {
					return false;
				}
			}
			for (final String bot : BOT_AGENTS) {
				if (lowerAgent.indexOf(bot) > -1) {
					return true;
				}
			}
		}
		return false;
	}
}