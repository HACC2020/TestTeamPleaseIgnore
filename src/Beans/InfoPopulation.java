package Beans;

public class InfoPopulation {

	//Topics added here will be reflected in the run program. This section can be adapted into a UI-interface if necessary.
	public static void main(String[] args) {
		//Added topics take the following parameters:
		//String name
		//String response: the recited response for when this topic comes up
		//String URL: a URL with more information about the topic
		//String[] synonyms: (not case or punctuation sensitive) other words that trigger acknowledgement of this topic
		//String[] subtopics: tangentially related topics to be recommended if the user is looking for more information
		
		CovidBot.addTopic(
				"COVID-19", 
				"COVID-19, also known as the \"coronavirus\", is the disease we are all dealing with right now. It carries "
				+ "a number of potentially fatal symptoms, and we as a community are doing our best to keep it under control. "
				+ "If you or a loved one may have contracted or otherwise come in contact with the coronavirus, seeking medical attention is advised.",
				"https://hawaiicovid19.com/",
				new String[] {"coronavirus", "covid", "virus"},
				new String[] {"COVID-19 symptoms", "COVID-19 prevention", "COVID-19 emergencies"}
				);
		CovidBot.addTopic(
				"face masks", 
				"Experts recommend covering your nose and mouth when outside the home. Doing so helps protect those around you,"
				+ "as covering your nose and mouth helps prevent the release of virus into the air. When we all wear our masks,"
				+ "we help keep each other safe. Examples of effective masks include",
				"https://hawaiicovid19.com/mask-guidance/",
				new String[] {"mask", "covering"},
				new String[] {"mask exemptions"}
				);
		CovidBot.addTopic(
				"COVID-19 symptoms", 
				"The symptoms of COVID-19 include coughing, fever, and shortness of breath. If you or a loved one have developed"
				+ "one or more of these symptoms after being out in public or exposed to COVID-19, seek medical attention.",
				"https://hawaiicovid19.com/mask-guidance/",
				new String[] {"symptom", "coughing", "cough", "fever"},
				new String[] {"COVID-19 emergencies"}
				);
		CovidBot.addTopic(
				"COVID-19 prevention", 
				"There are a number of steps that must be taken to prevent the spread of COVID-19. Remaining in an isolated"
				+ "social circle, such as with close family only, is recommended, as well as keeping 6 feet away from others"
				+ "and wearing a mask while out in public.",
				"https://hawaiicovid19.com/testing-isolation-quarantine/",
				new String[] {"prevention", "prevent", "safety", "safe"},
				new String[] {"face masks", "social distancing"}
				);
		CovidBot.addTopic(
				"Social Distancing", 
				"This is a stub topic! I do not have information on Social Distancing",
				"http://stubURL",
				new String[] {"six feet", "6 feet", "social", "distancing"},
				new String[] {}
				);
		CovidBot.addTopic(
				"Mask Exemptions", 
				"This is a stub topic! I do not have information on Mask Exemptions",
				"http://stubURL",
				new String[] {"exemption"},
				new String[] {}
				);
		CovidBot.addTopic(
				"COVID-19 emergencies", 
				"This is a stub topic! I do not have information on emergencies; call 911.",
				"http://stubURL",
				new String[] {"emergency", "emergencies"},
				new String[] {}
				);
		
		
		
		CovidBot.runInConsole();

	}

}
