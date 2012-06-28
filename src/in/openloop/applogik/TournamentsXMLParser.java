package in.openloop.applogik;

import in.openloop.db.model.Question;
import in.openloop.db.model.Subject;
import in.openloop.db.model.Tournament;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.util.Log;

public class TournamentsXMLParser {

	
	public static List<Tournament> parseTournaments(InputStream inputStream){

		List<Tournament> tournaments = new ArrayList<Tournament>();

		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		try{
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document document = docBuilder.parse(inputStream);
		
		document.getDocumentElement().normalize();
		NodeList noList = document.getElementsByTagName("tournament");
		
		for(int s=0; s < noList.getLength(); s++){
			Node tournament = noList.item(s);
			Tournament tournamentObj = new Tournament();
			tournaments.add(tournamentObj);
			
			if(tournament.getNodeType() == Node.ELEMENT_NODE){
				Element tournamentElement = (Element)tournament;
				//tournamentElement.getAttribute("author");
				
				tournamentObj.setName(tournamentElement.getAttribute("name"));
				
				Subject subject = new Subject();
				tournamentObj.setSubject(subject);
				subject.setSubjectName(tournamentElement.getAttribute("subject"));
				
				Log.w("Dharma","Executed till here");
				NodeList questions = tournamentElement.getElementsByTagName("question");
				for(int i=0; i < questions.getLength(); i++){
					Node question = questions.item(i);
				    if(question.getNodeType() == Node.ELEMENT_NODE){
				    	Element questionElement = (Element)question;
				    	tournamentObj.addQuestion( parseQuestion(questionElement));	
				    }
				}
				
			}
		}
		
		}catch(IOException io){
			Log.w("Dharma", io.getLocalizedMessage());
		}catch(SAXException sax){
			Log.w("Dharma", sax.getLocalizedMessage());
		}catch(ParserConfigurationException pce){
			Log.w("Dharma", pce.getLocalizedMessage());
		}
		return tournaments;
	}
	
	private static Question parseQuestion(Element question){
		
		Question questionObj = new Question();

		questionObj.setQuestionText(question.getElementsByTagName("text").item(0).getChildNodes().item(0).getNodeValue());
		Element answerChoices = (Element)question.getElementsByTagName("answerchoices").item(0);
		parseChoices(answerChoices, questionObj);
		String answerChoice = question.getElementsByTagName("answer").item(0).getChildNodes().item(0).getNodeValue();
		if(answerChoice != null){
			questionObj.setAnswerCode(Integer.valueOf(answerChoice)-1);
		}
		return questionObj;
	}
	
	private static void parseChoices(Element answerChoices, Question questionObj){
		
		String []choices = new String[4];
		questionObj.setChoices(choices);
		choices[0] = answerChoices.getElementsByTagName("a").item(0).getChildNodes().item(0).getNodeValue();
		choices[1] = answerChoices.getElementsByTagName("b").item(0).getChildNodes().item(0).getNodeValue();
		choices[2] = answerChoices.getElementsByTagName("c").item(0).getChildNodes().item(0).getNodeValue();
		choices[3] = answerChoices.getElementsByTagName("d").item(0).getChildNodes().item(0).getNodeValue();
		
	}
 }
