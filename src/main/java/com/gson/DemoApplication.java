package com.gson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

public class DemoApplication {

	public static void main(String[] args) {

		String jsonString = "{ \"Fields\": [ { \"Name\": \"end-date\", \"values\": [ { \"value\": \"2024-12-31\" } ] }, { \"Name\": \"last-modified\", \"values\": [ { \"value\": \"2024-12-23 19:36:05\" } ] }, { \"Name\": \"req-count\", \"values\": [ { \"value\": \"0\" } ] }, { \"Name\": \"ver-stamp\", \"values\": [ { \"value\": \"1\" } ] }, { \"Name\": \"name\", \"values\": [ { \"value\": \"Release1\" } ] }, { \"Name\": \"description\", \"values\": [ { \"value\": \"<html><body> \\n<ul style=\\\"margin-top:0mm;margin-bottom:0mm;margin-left:0mm;padding-left:0pt;list-style-type:disc\\\"> \\n<li style=\\\"margin-left:20pt;margin-right:0pt;padding-left:8pt;text-indent:0pt;font-size:10pt;color:#010101\\\"><font face=\\\"Arial\\\"><span dir=\\\"ltr\\\" style=\\\"font-size:8pt;font-family:'arial';color:#010101;font-weight:normal;font-style:normal\\\">Testing Release 1</span></font></li> \\n</ul> \\n</body></html>\" } ] }, { \"Name\": \"scope-items-count\", \"values\": [ { \"value\": \"0\" } ] }, { \"Name\": \"start-date\", \"values\": [ { \"value\": \"2024-12-23\" } ] }, { \"Name\": \"milestones-count\", \"values\": [ { \"value\": \"0\" } ] }, { \"Name\": \"id\", \"values\": [ { \"value\": \"1003\" } ] }, { \"Name\": \"parent-id\", \"values\": [ { \"value\": \"1\" } ] }, { \"Name\": \"has-attachments\", \"values\": [ {} ] } ], \"Type\": \"release\", \"children-count\": 0 }";

		ObjectMapper mapper = new ObjectMapper();

		try {
			JsonNode rootNode = mapper.readTree(jsonString);
			ArrayNode fields = (ArrayNode) rootNode.get("Fields");
			ObjectNode output = mapper.createObjectNode();

			for (JsonNode field : fields) {
				String name = field.get("Name").asText();
				JsonNode values = field.get("values");
				if (values.size() > 0 && values.get(0).has("value")) {
					String value = values.get(0).get("value").asText();
					if (name.equals("name") || name.equals("start-date") || name.equals("end-date") || name.equals("description")) {
						output.put(name, value);
					}
				}
			}

			System.out.println(output.toPrettyString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
