package Beans;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
public class CovidBot {
	
	static HashMap<String, Topic> topics = new HashMap<String, Topic>();
	static List<String> allKeywords = new ArrayList<String>();
	static List<Topic> coveredTopics = new ArrayList<Topic>();

	//Some placeholder code to make this bot run in the command line
	public static void runInConsole() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Welcome to the chatbot! You can query me for information about COVID-19 in Hawaii,"
				+ "or type \"help\" for help with my interface.");
		while(true) {
			String input;
			try {
				input = reader.readLine();
			} catch (IOException e) {
				input = "invalid input";
				e.printStackTrace();
			}
			System.out.println(Chatbot (input));
			
		}
	}
	
	
	//The main chatbot response generator. This function can be adapted into the backend of a simple bot UI, but here it just
	//runs in command line. Input is user entry, returns bot response.
	public static String Chatbot (String input) {
		String output = "";
		String rawInput = input.replaceAll("[^\\w]", "").toLowerCase();
		List<Topic> potentialTopics = new ArrayList<Topic>();
		//Check for "keywords" in user's entry
		for (String keyword : allKeywords) {
			if (rawInput.contains(keyword) || rawInput.contains(keyword + "s") ) {
				Topic thisTopic = topics.get(keyword);
				if (thisTopic != null && !potentialTopics.contains(thisTopic)) {
					potentialTopics.add(thisTopic);
				}
			}
		}
		// Attempt to remove any topics that were explained in previous messages to the same user.
		List<Topic> vettedTopics = new ArrayList<Topic>();
		for (Topic potentialTopic : potentialTopics) {
			if(!coveredTopics.contains(potentialTopic)) {
				vettedTopics.add(potentialTopic);
			}
		}
		// If no topics remain after vetting, use the original topic list. 
		if (vettedTopics.size() > 0) {
			potentialTopics = vettedTopics;
		}
		//remember now which topics have already been discussed in this chat. Prefer not to show them when ambiguity arises.
		for (Topic potentialTopic : potentialTopics) {
			if(!coveredTopics.contains(potentialTopic)) {
				coveredTopics.add(potentialTopic);
			}
		}
		//If no topics have been found, check if the user is simply asking for help. Otherwise, dump information.
		if (potentialTopics.size() == 0) {
			if (rawInput.contains("help") && rawInput.length() < 10) {
				output += "help text dump";
			} else if (rawInput.contains("topics") && rawInput.length() < 10) {
				output += "topics text dump";
			}else {
				output += "I'm sorry. I don't have anything like that in my database. "
						+ "You can try asking me something else, or type \"help\" for help.";
			}
		} else {
			output += "It looks like you're searching for information regarding ";
			for (int i = 0; i < potentialTopics.size(); i++) {
				output += potentialTopics.get(i).topic;
				if (i == potentialTopics.size() - 2) {
					output += ", and ";
				} 
				else if (i == potentialTopics.size() - 1) {
					output += ".\n\n";
				}
				else {
					output += ", ";
				}
			}
			for (int i = 0; i < potentialTopics.size(); i++) {
				Topic thisTopic = potentialTopics.get(i);
				output += thisTopic.response + "\n\n";
				if (thisTopic.otherResource.length() > 1) {
					output += "More information about " + thisTopic.topic + " can be found at " + thisTopic.otherResource + ".\n\n";
				}
			}
			
			output += "I hope this helped! You can query me again, or type \"help\" for help with my interface.";
		}
		

		return output;
	}
	//Function for adding a topic to the "database".
	public static void addTopic(String topic, String response, String otherResource, String[] synonyms, String[] subtopics) {
		Topic newTopic = new Topic();
		newTopic.topic = topic;
		newTopic.response = response;
		newTopic.subtopics = subtopics;
		newTopic.synonyms = synonyms;
		newTopic.otherResource = otherResource;
		for (String synonym : synonyms) {
			topics.put(synonym, newTopic);
			allKeywords.add(synonym);
		}
		topics.put(topic, newTopic);
		allKeywords.add(topic);
		topics.put(topic.replaceAll("[^\\w]", "").toLowerCase(), newTopic);
		allKeywords.add(topic.replaceAll("[^\\w]", "").toLowerCase());
		/**
		 * If an added subtopic does not have its own entry, make a stub entry.
		 */
		for (String subtopic : subtopics) {
			if(topics.get(subtopic) == null) {
				addTopic(subtopic, "I'm sorry! I know I said I know about this, but I actually don't know anything about " 
			+ subtopic + ". This may be a bug!");
			}
		}
		
	}
	//overflows for quick topic entry
	public static void addTopic(String topic, String response) {
		addTopic(topic, response, "", new String[0], new String[0]);
	}
	public static void addTopic(String topic, String response, String otherResource) {
		addTopic(topic, response, otherResource, new String[0], new String[0]);
	}

}
//"Topic" structure, each added topic should fill out one of these.
class Topic {
	public String topic = "";
	public String response = "";
	public String[] subtopics = new String[0];
	public String[] synonyms = new String[0];
	public String otherResource = "";
}
